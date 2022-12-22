package train1.student4;

public class train01 {
 public static void main(String[] args) {
 int[][] matrix = {{11, 63, 77, 94, 45}, {61, 35, 23, 25, 88}, {93, 66, 31, 10, 70}};
 int[]row=new int[matrix.length];
 int[]col=new int[matrix[0].length];
 int total=0;
 int value1=matrix[0][0];
 int value=matrix[0][0];
 for(int i =0; i< matrix.length; i++){
 for(int j=0; j< matrix[i].length;j++){
 row[i]+=matrix[i][j];
 col[j]+=matrix[i][j];
 total+=matrix[i][j];
 if(value<matrix[i][j]){
 value=matrix[i][j];
 }
 if(value1>matrix[i][j]){
 value1=matrix[i][j];
 }
 }
 }
 System.out.print("row = [");
 for (int s:row){
 System.out.print(" "+s);
 }
 System.out.println(" ]");
 System.out.print("col = [");
 for(int c:col){
 System.out.print(" "+c);
 }
 System.out.println(" ]");
 System.out.println("total: "+total);
 System.out.println("max: "+value);
System.out.println("min: "+value1);
 }
 }