package make;

public class CodeDisMin {
    public Code[] c;
    String pathname;
    public CodeDisMin(Code[] c,String pathname){
        this.c=c;
        this.pathname=pathname;
    }

    public Code codedismin(){
        Code code=new Code(this.pathname);
        CodeDis cd=new CodeDis();
        double min=Double.MAX_VALUE;
        int a=0;
        for (int i=0;i<c.length;i++){
            cd.vllCodeDis(code.getvll(),c[i].getvll());
            if(min>cd.getCodeDis()) {
                min=cd.getCodeDis();
                a=i;
            }
        }
        return c[a];
    }
}
