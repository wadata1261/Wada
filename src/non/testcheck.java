package non;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testcheck {
    public static void main(String[] args){
        int[][] a;
        a= new int[4][];
        a[0][1]=1;
        a[1][2]=2;

        String[] ac=new String[]{"a","b"};

        for(int i=0;i<a.length;i++){

        }
        List<String> ad=Arrays.asList(ac);

        ArrayList<ArrayList<String>> arrays = new ArrayList<ArrayList<String>>();
        if(arrays.isEmpty()) System.out.println("empty");
        for(int i = 0; i < 3; i++) {
            ArrayList<String> array = new ArrayList<>();
            for(int j = 0; j < 4; j++) {
                array.add("j");
            }
            arrays.add(array);
           // System.out.println(arrays.get(i+1).add("wow"));
        }
        System.out.println(arrays.get(0).size());
        for (int i=1;i<3;i++) arrays.get(i).add("wow");
        //System.out.println(arrays.get(0).get(4));
        System.out.println(arrays);
    }
}
