package testcode;

import java.io.IOException;

public class test {
    public static void main(String[] args){
        Runtime r=Runtime.getRuntime();
        try{
            r.exec("calc");
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
