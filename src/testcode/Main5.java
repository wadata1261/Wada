package testcode;

public class Main5 {
    public static void main(String[] args) {
        int a=0,b=1,c=2;
        for(int i=0;i<10;i++){
            b*=i;
            c+=a/b;
            a*=c;
            a=b+c*i;
            System.out.println(a);
        }
    }
}
