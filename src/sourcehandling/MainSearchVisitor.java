package sourcehandling;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MainSearchVisitor extends VoidVisitorAdapter<String>{
    int lineNumberOfMain = -1;
    @Override
    public void visit(MethodDeclaration n, String arg) {
        //mainメソッドの行数を保存
        lineNumberOfMain = n.getRange().get().begin.line;
        System.out.println("main: "+(lineNumberOfMain+1));
        super.visit(n, arg);
    }

    public int getLineNumberOfMain() {
        return lineNumberOfMain;
    }
}
