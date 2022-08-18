package sourcehandling;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
public class PackageChangeVisitor extends VoidVisitorAdapter<String>{
    @Override
    public void visit(PackageDeclaration n, String arg) {
        n.setName("work");
        super.visit(n, arg);
    }
}
