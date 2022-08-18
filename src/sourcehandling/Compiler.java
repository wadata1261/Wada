package sourcehandling;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.util.Arrays;

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
}
