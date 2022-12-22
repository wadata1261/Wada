package make;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImportManageVisitor extends VoidVisitorAdapter<String>{
    private List<String> topLevelPackageNameList;
    private String workDir;

    public ImportManageVisitor(List<String> fqcnList, String workDir){
        super();
        this.workDir = workDir;
        this.topLevelPackageNameList = new ArrayList<>();
        for(String fqcn : fqcnList){
            String pkgName = fqcn;
            if(pkgName.contains(".")){
                System.out.println("pkgName = " + pkgName);
                pkgName = pkgName.split("\\.")[0];
            }
            this.topLevelPackageNameList.add(pkgName);
        }
    }

    @Override
    public void visit(ImportDeclaration n, String args) {
        System.out.println("n.getNameAsString():"+n.getNameAsString());
        String pkgName = n.getNameAsString();
        if(pkgName.contains(".")){
            pkgName = pkgName.split("\\.")[0];
        }

        //srcの下のパッケージ名から始まる場合はworkDirを頭に付ける
        if(this.topLevelPackageNameList.contains(pkgName)){
            System.out.println(workDir);
            n.setName(workDir+"."+n.getNameAsString());
        }
        super.visit(n, args);
    }
}
