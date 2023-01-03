package sourcehandling;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyVisitor extends VoidVisitorAdapter<String> {
    protected List<LineNode> lineNodeList = new ArrayList<>();
    private Map<String, String> variable = new HashMap<>();
    private Map<String, Integer> NameElementCount = new HashMap<>();
    private Map<String, String> NameSpecifiedElement = new HashMap<>();

    @Override
    public void visit(VariableDeclarationExpr n, String arg) {
        int line = n.getBegin().get().line;
        LineNode ln = new LineNode(line, n.getClass().getTypeName(), n);
        lineNodeList.add(ln);
        super.visit(n, arg);
    }

    @Override
    public void visit(AssignExpr n, String arg) {
        int line = n.getBegin().get().line;
        LineNode ln = new LineNode(line, n.getClass().getTypeName(), n);
        lineNodeList.add(ln);
        super.visit(n, arg);
    }

    //ここから下は田﨑佐々木が作ったメソッドをくっつけたもの．行数の取得と，LineNodeをlineNodeListに入れる処理が書けていないので，上のメソッドを参考に追加する必要がある．

    public void visit(ArrayCreationExpr n, String arg) {
        System.out.println(n + " 　　" + n.getRange().get().end.line + "行目"); //newからあと
        super.visit(n, arg);
    }

    public void visit(ArrayInitializerExpr n, String arg) {
        System.out.println(n + " 　　" + n.getRange().get().end.line + "行目"); //{1,2,3,4,6,7}　他の書き方のときは表示されない
        System.out.println(n.getValues().size()); //いっきに代入した時の配列要素数
        System.out.println(n.getValues().getParentNodeForChildren().getChildNodes() + " 　　" + n.getRange().get().end.line + "行目"); //1,2,3,4,6,7
        System.out.println(n.getValues().get(0/*3*/) + " 　　" + n.getRange().get().end.line + "行目"); //引数に配列の要素数を指定して変数表示
        System.out.println();
        super.visit(n, arg);
    }

    public void visit(ArrayAccessExpr n, String arg) {
        System.out.println(n + " 　　" + n.getRange().get().end.line + "行目 ここ"); //array1[0]
        System.out.println(n.getIndex() + " 　　" + n.getRange().get().end.line + "行目 こっこ"); //配列[]のなかの数字
//                    System.out.println(n.getParentNode().get()+" ここっここ"); //分けて宣言した時のこんな感じarray1[0] = 3
        System.out.println(n.getParentNode().get().getChildNodes().get(0) + " 　　" + n.getRange().get().end.line + "行目 こ"); //代入した値
        System.out.println();
        super.visit(n, arg);
    }

    public void visit(ArrayCreationLevel n, String arg) {
        System.out.println(n + " 　　" + n.getRange().get().end.line + "行目"); //配列の[]取得
        super.visit(n, arg);
    }


    public List<LineNode> getLineNodeList() {
        return lineNodeList;
    }
}
