package sourcehandling;

import com.sun.jdi.*;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.VMStartException;
import com.sun.jdi.event.*;
import com.sun.jdi.request.BreakpointRequest;
import com.sun.jdi.request.ClassPrepareRequest;
import com.sun.jdi.request.StepRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Debugger {
    private Class debugClass;
    private int[] breakPointLines;

    private Map<String, Object> variables = new HashMap<String, Object>();
    ExecutedLineList executedLineList = new ExecutedLineList();

    public ExecutedLineList debug(Class clazz) throws Exception {

        this.setDebugClass(clazz);
        List<String> methodNames = new ArrayList<>();
        for(java.lang.reflect.Method reflectMethod : clazz.getMethods()){
            methodNames.add( reflectMethod.getName() );
        }
        VirtualMachine vm = null;

        try {
            vm = this.connectAndLaunchVM();
            this.enableClassPrepareRequest(vm);


            EventSet eventSet = null;

            while ((eventSet = vm.eventQueue().remove()) != null) {
                flushConsole(vm);
                //System.out.println(vm.process().getOutputStream());
                for (Event event : eventSet) {

                    if(event instanceof MethodEntryEvent){
                        System.out.println("MethodEntryEvent");
                        System.out.println(((MethodEntryEvent) event).location().lineNumber());
                    }
                    if (event instanceof VMStartEvent) {
                        System.out.println("XD");
                    }
                    if (event instanceof ClassPrepareEvent) {
                        List<Method> methods = ((ClassPrepareEvent)event).referenceType().allMethods();
                        List<Location> locations = new ArrayList<>();
                        for(Method m: methods){
                            if(methodNames.contains(m.name()) ) {
                                for (Location l : m.allLineLocations()) {
                                    locations.add(l);
                                }
                            }
                        }
                        this.setBreakPoints(vm, locations);
                    }
                    if (event instanceof BreakpointEvent) {
                        this.displayVariables((BreakpointEvent) event);
                        //this.enableStepRequest(vm, (BreakpointEvent) event);
                    }
                    if (event instanceof StepEvent) {
                        //this.displayVariables((StepEvent) event);
                    }
                    if (event instanceof VMDeathEvent || event instanceof VMDisconnectEvent) {
                        break;
                    } else if (event instanceof ExceptionEvent) {
                        break;
                    }
                    vm.resume();
                }

            }
        } catch (VMDisconnectedException e) {
            System.out.println("Virtual Machine is disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            InputStreamReader reader = new InputStreamReader(vm.process().getInputStream());
            OutputStreamWriter writer = new OutputStreamWriter(System.out);
            char[] buf = new char[vm.process().getInputStream().available()];
            reader.read(buf);
            writer.write(buf);
            writer.flush();
        }
        //this.executedLineList.show();
        return this.executedLineList;
    }

    public void flushConsole(VirtualMachine vm) throws IOException {
        InputStreamReader reader = new InputStreamReader(vm.process().getInputStream());
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        char[] buf = new char[vm.process().getInputStream().available()];
        reader.read(buf);
        writer.write(buf);
        writer.flush();
    }


    public void enableStepRequest(VirtualMachine vm, BreakpointEvent event) {
        // enable step request for last break point
        if (event.location().toString().contains(debugClass.getName() + ":" + breakPointLines[breakPointLines.length - 1])) {
            StepRequest stepRequest = vm.eventRequestManager().createStepRequest(event.thread(), StepRequest.STEP_LINE, StepRequest.STEP_OVER);
            stepRequest.enable();
        }
    }

    public void displayVariables(LocatableEvent event) throws IncompatibleThreadStateException, ClassNotLoadedException {
        StackFrame stackFrame = event.thread().frame(0);
        if (stackFrame.location().toString().contains(debugClass.getName())) {
            Map<LocalVariable, Value> visibleVariables = null;
            try {
                visibleVariables = stackFrame.getValues(stackFrame.visibleVariables());
            } catch (AbsentInformationException e) {
                e.printStackTrace();
            }
            System.out.println();
            System.out.println("Variables at " + stackFrame.location().toString() + " > ");
            this.executedLineList = addLine(event.location().lineNumber(), event.location().method().name(),visibleVariables, this.executedLineList);
        }

    }

    public ExecutedLineList addLine(int lineNumber, String methodName, Map<LocalVariable, Value> visibleVariables, ExecutedLineList ell){
        Map<LocalVariable,Object> m = new HashMap<>();

        for (Map.Entry<LocalVariable, Value> entry : visibleVariables.entrySet()) {
            if(entry.getValue() instanceof ArrayReference){
                ArrayReference ar = (ArrayReference) entry.getValue();
                List<Value> vl = ar.getValues();
                List<Object> ol = new ArrayList<>();
                if(vl == null){
                    ol.add(vl);
                }else{
                    for(Value v : vl){
                        Object toAddToOl = null;
                        if(v == null){
                            toAddToOl = null;
                        }
                        else if(v instanceof ArrayReference){
                            ArrayReference ar2 = (ArrayReference) v;
                            List<Value> vl2 = ar2.getValues();
                            List<Object> ol2 = new ArrayList<>();
                            if(vl2 == null){
                                ol2.add(vl2);
                            }else{
                                for(Value valueOfVl2 : vl2){
                                    if(valueOfVl2 == null){
                                        ol2.add(null);
                                    }else{
                                        ol2.add(valueOfVl2.toString());
//                                        System.out.println(valueOfVl2.toString());
                                    }
                                }
                            }
                            toAddToOl = ol2;
                        }
                        else{
                            toAddToOl = v.toString();
                        }
                        ol.add(toAddToOl);
                    }
                }
                m.put(entry.getKey(), ol);
            }
            else{
                m.put(entry.getKey(), entry.getValue().toString());
            }

//            System.out.println(entry.getKey().name() + " = " + entry.getValue());
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
        }
        ell.addLine(lineNumber, methodName, m);
        return ell;
    }

    public void setBreakPoints(VirtualMachine vm, ClassPrepareEvent event) throws AbsentInformationException {
        ClassType classType = (ClassType) event.referenceType();
        for (int lineNumber : breakPointLines) {
            Location location = classType.locationsOfLine(lineNumber).get(0);
            BreakpointRequest bpReq = vm.eventRequestManager().createBreakpointRequest(location);
            bpReq.enable();
        }
    }

    public void setBreakPoints(VirtualMachine vm, List<Location> locations){
        for(Location l : locations) {
            BreakpointRequest bpReq = vm.eventRequestManager().createBreakpointRequest(l);
            bpReq.enable();
        }
    }

    public void enableClassPrepareRequest(VirtualMachine vm) {
        ClassPrepareRequest classPrepareRequest = vm.eventRequestManager().createClassPrepareRequest();
        //classPrepareRequest.addClassFilter(debugClass.getName());
        classPrepareRequest.addClassFilter("work.*");
        classPrepareRequest.enable();
    }

    public VirtualMachine connectAndLaunchVM() {
        VirtualMachine r = null;
        LaunchingConnector launchingConnector = Bootstrap.virtualMachineManager()
                .defaultConnector();
        Map<String, Connector.Argument> arguments = launchingConnector.defaultArguments();
        System.out.println(arguments);
        arguments.get("main").setValue(debugClass.getName());

        //クラスパスの設定
        String classpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("classpath = " + classpath);
        arguments.get("options").setValue(" -classpath "+classpath);
        //arguments.get("options").setValue("-classpath out\\production\\wada");
        try {
            r = launchingConnector.launch(arguments);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalConnectorArgumentsException e) {
            e.printStackTrace();
        } catch (VMStartException e) {
            e.printStackTrace();
        }
        return r;
    }

    public Class getDebugClass() {
        return debugClass;
    }

    public void setDebugClass(Class debugClass) {
        this.debugClass = debugClass;
    }

    public int[] getBreakPointLines() {
        return breakPointLines;
    }

    public void setBreakPointLines(int[] breakPointLines) {
        this.breakPointLines = breakPointLines;
    }
}