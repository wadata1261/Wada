package make;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MainClassVisitor extends VoidVisitorAdapter<String>{
    private boolean hasMainMethod=false;

    @Override
    public void visit(MethodDeclaration n, String arg) {
        if(n.getNameAsString().equals("main")){
            hasMainMethod=true;
        }
        super.visit(n, arg);
    }

    public boolean hasMainMethod() {
        return hasMainMethod;
    }
}
