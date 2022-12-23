package make;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import sourcehandling.*;
import sourcehandling.Compiler;

import java.io.*;
import java.nio.file.Path;
import java.util.Date;

public class Code {
    public ExecutedLineList ell;
    public ValueLogList valueLogList;
    public String path;
    public Date date;
    public String source;
    public boolean check;

    public static void main(String[] args){
        Code c=new Code("\\Users\\wadat\\IdeaProjects\\File\\Wada\\src\\testcode\\Main.java");
        c.Code();
        System.out.println(c.getSource());
        System.out.println();
        System.out.println(c.getAnser());
    }

    public Code(String path){
        this.path=path;
        this.source=null;
        this.check=false;
        this.ell=new ExecutedLineList();
        this.valueLogList=new ValueLogList();
        this.date=new Date();
    }
    public void setSource(String source){this.source=source;}
    public String getSource(){return this.source;}

    public void Code(){
        File file = new File(this.path);
        //fileからPathオブジェクトを取得
        Path sourcePath = file.toPath();
        //ファイル名からクラス名を取得
        String className = setFileName(this.path);

        this.date=new Date(file.lastModified());

        //JavaParserのインスタンスを生成
        JavaParser jp = new JavaParser();
        //Pathオブジェクトを引数とし，ソースコードをパースしてASTを作成
        ParseResult<CompilationUnit> units = null;
        try {
            units = jp.parse(sourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //パッケージ名が適当だと危ないので，workに変更
        units.getResult().get().accept(new PackageChangeVisitor(), "");

        //各行の命令の種類を記録するVisitorをインスタンス化．名前は適当なので適宜修正
        MyVisitor mv = new MyVisitor();
        //mvをアクセプトし，ASTを走査
        units.getResult().get().accept(mv, "");

        //パッケージ名変更後のソースコードを文字列化
        String sourceText = units.getResult().get().toString(); //正解例
        setSource(sourceText);

        //ソースをコンパイルしてクラスオブジェクトを取得
        System.out.println(className+":nini;"+sourceText);
        Class<?> clazz = Compiler.compile(className, sourceText);

        //Debuggerクラス（自作）のインスタンスを作成
        Debugger debugger = new Debugger();
        //クラスオブジェクトを引数としてdebugを実行し，実行行と各行における変数のリストを得る
        //ExecuteLineListクラスやExecutedLineクラスは自作
        ExecutedLineList ell = null;
        try {
            ell = debugger.debug(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.ell=ell;
        System.out.println(ell);
    }

    public ExecutedLineList getEll(){return this.ell;}

    public ValueLogList getvll(){
        ValueLogList vll = new ValueLogList();
        ValueLogGet vlg = new ValueLogGet();
        vll = vlg.MakeList(this.ell);
        this.valueLogList=vll;
        return this.valueLogList;
    }

    //Node


    public static String setFileName(final String fullPathString) {
        File file = new File(fullPathString);
        String basename = file.getName();
        String woext = basename.substring(0, basename.lastIndexOf('.'));
        return woext;
    }

    public String getFileName(){
        File file=new File(this.path);
        String basename = file.getName();
        String woext = basename.substring(0, basename.lastIndexOf('.'));
        return woext;
    }

    public boolean isCheck() {
        return check;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
    public String getPath(){return this.path;}
    public String getParent(){
        File file=new File(this.path);
        String str=file.getParent();
        System.out.println(str);
        String result=str.substring(str.lastIndexOf('\\')+1);
        return result;
    }


    public Date getDate() {
        return date;
    }

    public String getAnser(){
        String result;
        String retu =new String();
        try {
            Runtime rt = Runtime.getRuntime();
            String[] a={"2"};
            Process p =rt.exec("java "+path);
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            //retu = br.readLine();
            while ((result = br.readLine()) != null) {
                retu+=result+"\n";//.replaceAll("　| ","");
                //System.out.println(result);

            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        //System.out.println(retu);
        return retu;
    }
}
