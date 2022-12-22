package sourcehandling;

import make.JavaSource;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Compiler {
    public static Class<?> compile(String className, String sourceText ){
        JavaFileObject jfp = new JavaSourceFromString(className, sourceText);
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        String classpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        //String[] compileOptions = new String[]{"-g","-classpath",classpath};
        String[] compileOptions = new String[]{"-g","-d", "out\\production\\Wada", "-classpath",classpath};
        Iterable<String> compilationOptionss = Arrays.asList(compileOptions);
        DiagnosticCollector<JavaFileObject> diagnostics =
                new DiagnosticCollector<>();
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(jfp);
        JavaCompiler.CompilationTask task = javac.getTask(
                null,
                null,
                diagnostics,
                compilationOptionss,
                null,
                compilationUnits
        );
        boolean success = task.call();

        Class<?> clazz = null;
        System.out.println(success);
        if (success) {
            try {
                clazz = Class.forName("work." + className);//TODO:Name of a directory to place the compiled classfile , "work", should be changed.
                return clazz;

            } catch (ClassNotFoundException e) {
                System.err.println("Class not found: " + e);
                return null;
            }
        }
        return null;
    }
    public static Class<?> compile(String workDir, String mainClassName, List<JavaSource> javaSourceList){
        List<JavaFileObject> jfpList = new ArrayList<>();
        for(JavaSource javaSource : javaSourceList){
            jfpList.add(new JavaSourceFromString(javaSource.getName(),javaSource.getSource()));
        }
        Iterable<? extends JavaFileObject> compilationUnits = jfpList;
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        String classpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("classpath = " + classpath);
        String[] compileOptions = new String[]{"-g","-d", "out\\production\\JDISample", "-classpath",classpath};
        Iterable<String> compilationOptionss = Arrays.asList(compileOptions);
        DiagnosticCollector<JavaFileObject> diagnostics =
                new DiagnosticCollector<>();

        JavaCompiler.CompilationTask task = javac.getTask(
                null,
                null,
                diagnostics,
                compilationOptionss,
                null,
                compilationUnits
        );
        boolean success = false;
        try {
            success = task.call();
        }catch(Exception e){
            e.printStackTrace();
        }

        Class<?> clazz = null;
        System.out.println(success);
        if (success) {
            try {
                clazz = Class.forName(workDir+"."+mainClassName);
                return clazz;

            } catch (ClassNotFoundException e) {
                System.err.println("Class not found: " + e);
                return null;
            }
        }
        return null;
    }
}
