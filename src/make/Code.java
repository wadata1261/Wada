package make;

import Flow.MinCostFlow;
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
        Code c1=new Code("C:\\Users\\wadat\\IdeaProjects\\File\\anserfile\\kadai12_2\\Tuika7\\student35\\Train02.java");
        Code c2=new Code("C:\\Users\\wadat\\IdeaProjects\\File\\anserfile\\kadai12_2\\Tuika7\\student34\\Train02.java");
        c1.Code();
        c2.Code();
        //System.out.println(c.getSource());
        //System.out.println(c.checkName());
        System.out.println(c1.CodeName());//name
        for(ValueLog vl1:c1.getvll().getValueLogList()){
            System.out.println(vl1.getName()+":"+vl1.getValueLog());
        }
        System.out.println(c2.CodeName());//path
        for(ValueLog vl2:c2.getvll().getValueLogList()){
            System.out.println(vl2.getName()+":"+vl2.getValueLog());
        }
        MinCostFlow mcf=new MinCostFlow();
        mcf.MinCostFlow(c1.getvll(),c2.getvll());
        System.out.println("dis="+mcf.getCostdis());
        //System.out.println(c.getParent());
        //System.out.println(c.getAnser());
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
       System.out.println(path);
        this.ell=ell;
        System.out.println("こいつはnullになる：");
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

    public String CodeName(){
        File file=new File(this.path);
        String str=file.getParent();
        String s=file.getName();
        //System.out.println("s"+s);
        //System.out.println("str:"+str);
        String str2=str.substring(str.lastIndexOf('\\')+1);
        //System.out.println("str2:"+str2);
        String result=str2.substring(str2.lastIndexOf('\\')+1);
        //System.out.println("result:"+result);
        return result;
    }

    public String FileName(){
        File file=new File(this.path);
        return file.getName();
    }

    public String checkName(){
        File file=new File(this.path);
        String s=file.getName();
        String ss=CodeName()+"\\"+s;
        return ss;
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
