package main;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import sourcehandling.*;
import sourcehandling.Compiler;

import java.io.File;
import java.nio.file.Path;

public class Main {


    public static void main(String[] args) throws Exception {

        //javaファイルを取得
        String[] pathname = new String[10];
        File[] file =new File[9];
        for(int i=0;i<100;i++) {
            pathname[i] = FileGet.fileGet();
            //いちいち取得するのが面倒な場合は，開発中だけ直指定すると楽になる
            //String pathname = "C:\\Users\\wadat\\IdeaProjects\\Sam.java";
            //ファイルパスからファイルオブジェクトを取得
            file[i] = new File(pathname[i]);
            //File file = new File(pathname);
            //fileからPathオブジェクトを取得
            Path sourcePath = file[i].toPath();
            //ファイル名からクラス名を取得
            String className = getFileName(pathname[i]);

            //JavaParserのインスタンスを生成
            JavaParser jp = new JavaParser();
            //Pathオブジェクトを引数とし，ソースコードをパースしてASTを作成
            ParseResult<CompilationUnit> units = jp.parse(sourcePath);
            System.out.println("***********************************************");
            //パッケージ名が適当だと危ないので，workに変更
            units.getResult().get().accept(new PackageChangeVisitor(), "");

            //各行の命令の種類を記録するVisitorをインスタンス化．名前は適当なので適宜修正
            MyVisitor mv = new MyVisitor();
            //mvをアクセプトし，ASTを走査
            units.getResult().get().accept(mv, "");

            //mvに記録された各行のノードを表示(デバッグ用)
            for (LineNode ln : mv.getLineNodeList()) {
                System.out.println(ln);
            }

            System.out.println(units.getResult().get());
            System.out.println("***********************************************");

            //パッケージ名変更後のソースコードを文字列化
            String sourceText = units.getResult().get().toString();



            //ソースをコンパイルしてクラスオブジェクトを取得
            Class<?> clazz = Compiler.compile(className, sourceText);

            //Debuggerクラス（自作）のインスタンスを作成
            Debugger debugger = new Debugger();
            //クラスオブジェクトを引数としてdebugを実行し，実行行と各行における変数のリストを得る
            //ExecuteLineListクラスやExecutedLineクラスは自作
            ExecutedLineList ell = debugger.debug(clazz);

            //記録された実行行と変数リストを表示
            ell.show();
        }
    }

    //ファイルのフルパスからクラス名を取り出す
    public static String getFileName(final String fullPathString) {
        File file = new File(fullPathString);
        String basename = file.getName();
        String woext = basename.substring(0, basename.lastIndexOf('.'));
        return woext;
    }
}