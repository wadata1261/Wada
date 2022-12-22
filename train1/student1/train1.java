package train1.student1;

public class train1 {
public static void main(String[] args) {
int[][] matrix={{11,63,77,94,45},{61,35,23,25,88},{93,66,31,10,70}};
int total=0;
int[] row=new int[matrix.length];
int[] col=new int[matrix[0].length];
int max=matrix[0][0];
int min=matrix[0][0];
for(int i=0;i<matrix.length;i++){
for(int j=0;j<matrix[0].length;j++){
total+=matrix[i][j];
row[i]+=matrix[i][j];
if(max<matrix[i][ j])max=matrix[i][j];
if(min>matrix[i][j])min=matrix[i][j];
col[j]+=matrix[i][j];
}
}
System.out.print("row=[");
for(int aa:row)
System.out.print(aa+" ");
System.out.println("]");
System.out.print("col=[");
for(int bb:col)
System.out.print(bb+" ");
System.out.println("]");
System.out.println("total="+total);
System.out.println("max:"+max);
System.out.println("min:"+min);
}
}