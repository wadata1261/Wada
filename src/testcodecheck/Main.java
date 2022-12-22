package testcodecheck;

public class Main {
    public static void main(String[] args) {
        int a=2,b=1;
        int c=a*b;
        int[] d=new int[3];
        int[][] e=new int[3][3];
        String hello="hello";
        String value="value";
        for(int i=0;i<e.length;i++){
            for(int j=0;j<e[i].length;j++){
                e[i][j]=i+j;
            }
        }
        for (int k=0;k<10;k++){
            c+=k;
        }
        d[0]+=a;
        d[1]+=b;
        d[2]+=c;
        a+=c;
        e[0][0]+=a;
        e[0][1]+=b;
        e[0][2]+=c;
        b=a*c;
        hello+=value;
        for(int l=0;l<d.length;l++){
            System.out.println("d"+d[l]);
        }
        System.out.println(hello);
        System.out.println(c);
    }
}
