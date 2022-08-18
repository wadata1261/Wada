package sourcehandling;

import com.sun.jdi.LocalVariable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExecutedLine {
    private int lineNumber;
    private String methodName;
    private Map<LocalVariable, Object> variables;

    public ExecutedLine(int lineNumber, String methodName, Map<LocalVariable, Object> variables){
        this.lineNumber = lineNumber;
        this.methodName = methodName;
        this.variables = variables;
    }

    public void addVariable(LocalVariable key, Object value){
        this.variables.put(key, value);
    }

    public Object getValue(String name, String type){ //変数
        for(Map.Entry<LocalVariable, Object> e : variables.entrySet()){
            if(Objects.equals(name, e.getKey().name()) && Objects.equals(type, e.getKey().typeName())){
                return e.getValue();
            }
        }
        return null;
    }

    public Object getValue(String name, String type, int idx){ //1次元配列
        Object obj = getValue(name, type);
        if(obj != null && obj instanceof List){
            //System.out.println("if(obj != null && obj instanceof List)");
            return ((List) obj).get(idx);
        }

        return null;
    }

    public Object getValue(String name, String type, int idx1, int idx2){ //2次元配列
        Object obj = getValue(name, type);
        if(obj != null && obj instanceof List){
            Object l = ((List) obj).get(idx1);
            if(l != null && l instanceof List) {
                return ((List) l).get(idx2);
            }
        }

        return null;
    }

    public void show(){
        for(Map.Entry<LocalVariable, Object> e : variables.entrySet()){
            System.out.print("line:"+this.lineNumber+" method:"+ this.methodName+" type:"+e.getKey().typeName()+" name:"+e.getKey().name()+" value:");
            if(e.getValue() instanceof String){
                System.out.println( e.getValue() );
            }
            if( e.getValue() instanceof List){
                System.out.print("[");
                for(Object o : (List)e.getValue() ){
                    if(o instanceof List){
                        System.out.print("[");
                        for(Object o2 : (List)o ){
                            if(o2 instanceof String){
                                System.out.print(o2);
                                System.out.print(",");
                            }
                        }
                        System.out.print("]");
                    }

                    if( o instanceof String){
                        System.out.print(o);
                    }
                    System.out.print(",");
                }
                System.out.println("]");
            }
        }
    }
}
