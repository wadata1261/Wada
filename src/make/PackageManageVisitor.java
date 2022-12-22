package make;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class PackageManageVisitor extends VoidVisitorAdapter<String>{

    private String topPackageName;
    private String workDir;

    public PackageManageVisitor(String topPackageName, String workDir){

        this.topPackageName = topPackageName;
        this.workDir = workDir;
    }

    @Override
    public void visit(PackageDeclaration n, String arg) {
        System.out.println("n.getNameAsString():"+n.getNameAsString());
        this.topPackageName = n.getNameAsString();
        if(this.topPackageName.contains(".")){
            this.topPackageName = n.getNameAsString().split(".")[0];
        }
        n.setName(workDir+"."+n.getNameAsString());
        super.visit(n, arg);
    }

}
