package non;

import main.Teacher;
import make.Code;
import make.ValueLog;

import java.util.ArrayList;

public class TeacherTest {
    public static void main(String[] args){
        Teacher t=new Teacher();
        ArrayList<Double> td=t.tofs("kadai12_2");
        Code[] c=t.getC();
        for(int i=0;i< c.length;i++){
            System.out.println(c[i].getPath());
            System.out.println(c[i].getSource());
            System.out.println(c[i].getAnser());
            for(ValueLog vl:c[i].getvll().getValueLogList()){
                System.out.println(vl.getName()+":"+vl.getValueLog());
            }
        }

        for(Double d:td){
            System.out.println(d);
        }
    }
}
