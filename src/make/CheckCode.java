package make;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class CheckCode {
    String trueanser;
    String stuanser;
    public CheckCode(String path1,String path2){
        this.trueanser =path1;
        this.stuanser =path2;
    }

    public boolean doCheck(){
        String txtfile=getText(this.trueanser);
        boolean test=checker(txtfile,stuanser);
        return test;
    }

    public static void main(String[] args){
        System.out.println(getAnser("\\Users\\wadat\\IdeaProjects\\File\\Wada\\src\\testcode\\Main.java"));
        //batfile();
        //scannerAnser("C:\\Users\\wadat\\IdeaProjects\\Wada\\src\\testcode\\Scanner1.java");
        String txtfile=getText("C:\\Users\\wadat\\IdeaProjects\\File\\Wada\\src\\main\\anser.txt");
        //boolean test=checker(txtfile,"\\Users\\wadat\\IdeaProjects\\File\\Wada\\src\\testcode\\Main.java");
        //boolean test=checker("d2 d1 d47 hellovalue 47","\\Users\\wadat\\IdeaProjects\\Wada\\src\\testcode\\Main.java");
        //System.out.println(test);
    }




    public static void batfile(){
        String result=new String();
        String retu=new String();
        try {
            Runtime rt = Runtime.getRuntime();
            String[] a={"2"};
            Process p =rt.exec("\\Users\\wadat\\IdeaProjects\\Wada\\src\\BatFile\\StringConverter.bat"+"\\Users\\wadat\\IdeaProjects\\Wada\\src\\testcode\\Scanner1.java "+ "C:\\Users\\wadat\\IdeaProjects\\Wada\\src\\testcode\\Scanner1.java "+ "new Scanner(System.in) "+ "new Scanner(2)");
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            //retu = br.readLine();
            while ((result = br.readLine()) != null) {
                //retu+=result.replaceAll("　| ","");
                System.out.println(result);
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    public static boolean checker(String trueanser,String path){
        if(getTrueAnser(trueanser).equals(getAnser(path))){
            return true;
        }
        return false;
    }

    public static String getTrueAnser(String a){
        //String a= "d2 d1 d47 hellovalue 47";
        return a;//.replaceAll("　| ","");
    }

    public static String getText(String filepath){ //txtfile
        String result = new String();
        File file = new File(filepath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = br.readLine()) != null) {
                result+=str+"\n";
                //System.out.println(str);
            }
            //System.out.println(result.replace("\n", ""));
            //System.out.println("getText f");
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
        System.out.println("gettext");
        System.out.println(result);
        return result;
    }




    public static String getAnser(String path){
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
                retu+=result;//.replaceAll("　| ","");
                //System.out.println(result);

            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        System.out.println(retu);
        return retu;
    }

    public static String scannerAnser(String path){
        String result=new String();
        String retu =new String();
        try {
            Runtime rt = Runtime.getRuntime();
            String[] a={"2"};
            Process p =rt.exec("java "+path);
            InputStream is = p.getInputStream();
            OutputStream os = p.getOutputStream();



            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            //retu = br.readLine();
            while ((result = br.readLine()) != null) {
                //System.out.println(result);


            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        //System.out.println("getAnser="+retu);
        return result;
    }
    }


