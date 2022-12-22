package make;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import sourcehandling.*;
import sourcehandling.Compiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FileDo {
    public double codedis;
    public FileDo(){this.codedis=getCodedis();}
    public void fileDo(String path,String path2){
        //javaファイルを取得
        //String pathname = FileGet.fileGet();
        //いちいち取得するのが面倒な場合は，開発中だけ直指定すると楽になる
        //File file3 = new File(String.valueOf(Paths.get("").toAbsolutePath())+"\\src\\testcode");
        String pathname = path;
        String pathname2 = path2;
        //ファイルパスからファイルオブジェクトを取得





        File file = new File(pathname);
        File file2 = new File(pathname2);
        //fileからPathオブジェクトを取得
        Path sourcePath = file.toPath();
        Path sourcePath2 = file2.toPath();
        //ファイル名からクラス名を取得
        String className = getFileName(pathname);
        String className2 = getFileName(pathname2);

        //JavaParserのインスタンスを生成
        JavaParser jp = new JavaParser();
        //Pathオブジェクトを引数とし，ソースコードをパースしてASTを作成
        ParseResult<CompilationUnit> units = null;
        try {
            units = jp.parse(sourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ParseResult<CompilationUnit> units2 = null;
        try {
            units2 = jp.parse(sourcePath2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("***********************************************");
        //パッケージ名が適当だと危ないので，workに変更
        units.getResult().get().accept(new PackageChangeVisitor(), "");
        units2.getResult().get().accept(new PackageChangeVisitor(), "");

        //各行の命令の種類を記録するVisitorをインスタンス化．名前は適当なので適宜修正
        MyVisitor mv = new MyVisitor();
        MyVisitor mv2 = new MyVisitor();
        //mvをアクセプトし，ASTを走査
        units.getResult().get().accept(mv, "");
        units2.getResult().get().accept(mv2, "");
        //mvに記録された各行のノードを表示(デバッグ用)
        /*
        for (LineNode ln : mv.getLineNodeList()) {
            System.out.println("file1");
            System.out.println(ln);
        }
        for (LineNode ln : mv2.getLineNodeList()) {
            System.out.println("file2");
            System.out.println(ln);
        }
        System.out.println("na");

         */
        //ソースコード表示
        System.out.println(units.getResult());
        System.out.println(units2.getResult().get());
        System.out.println("***********************************************");

        //パッケージ名変更後のソースコードを文字列化
        String sourceText = units.getResult().get().toString(); //正解例はこれを使う
        if(sourceText.contains("main")) System.out.println("scanner");
        String sourceText2 = units2.getResult().get().toString();


        //ソースをコンパイルしてクラスオブジェクトを取得
        Class<?> clazz = Compiler.compile(className, sourceText); //違い
        Class<?> clazz2 = Compiler.compile(className2, sourceText2);

        //Debuggerクラス（自作）のインスタンスを作成
        Debugger debugger = new Debugger();
        Debugger debugger2 = new Debugger();
        //クラスオブジェクトを引数としてdebugを実行し，実行行と各行における変数のリストを得る
        //ExecuteLineListクラスやExecutedLineクラスは自作
        ExecutedLineList ell = null;
        try {
            ell = debugger.debug(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExecutedLineList ell2 = null;
        try {
            ell2 = debugger2.debug(clazz2);
        } catch (Exception e) {
            e.printStackTrace();
        }


        twoCheck tw=new twoCheck();
        tw.twoCheck(ell,ell2);
        //setCodedis(tw.getCodedis());

        //記録された実行行と変数リストを表示
        //ell.show();

    }
    public static List<String> getFQCNList(List<Path> javaPathList, String startDir, String userSourceDir){
        List<String> fqcnList = new ArrayList<>();
        for (Path sourcePath : javaPathList) {
            Path directory = Paths.get(startDir + File.separator + userSourceDir);
            System.out.println("directory = " + directory);
            Path file = Paths.get(sourcePath.toString());
            System.out.println("file = " + file);
            Path relativePath = directory.relativize(file);
            String fqcn = relativePath.toString().replace(File.separator, ".");
            fqcn = getFileName(fqcn);
            fqcnList.add(fqcn);
        }
        return fqcnList;
    }

    //ファイルのフルパスからクラス名を取り出す
    public static String getFileName(final String fullPathString) {
        File file = new File(fullPathString);
        String basename = file.getName();
        String woext = basename.substring(0, basename.lastIndexOf('.'));
        return woext;
    }

    public double getCodedis() {
        return codedis;
    }

    public void setCodedis(double codedis) {
        this.codedis = codedis;
    }
}
