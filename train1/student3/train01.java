package train1.student3;

public class train01 {
 public static void main(String[] args){
 int[][] matrix = {
 {11,63,77,94,45}, {61,35,23,25,88}, {93,66,31,10,70} };
 int[] row = new int[matrix.length]; //行
 int[] col = new int[matrix[0].length]; //列 
int total = 0;
 int max = 0;
 int min = 100;
 for(int i = 0;i<matrix.length;i++) {
 for (int j = 0; j < matrix[i].length; j++) {
 row[i] += matrix[i][j];
 col[j] += matrix[i][j];
 if(max<matrix[i][j])max = matrix[i][j];
 if(min>matrix[i][j])min = matrix[i][j];
 }
 }
 for (int[] data2 : matrix) {
 for (int data : data2) total += data;
 }
 for(int row0 :row) {
 System.out.print("row = [" + row0 + "]");
 }
 System.out.println();
 for(int col0 : col){
 System.out.print("col = ["+col0+"]");
 }
 System.out.println();
 System.out.println("total:"+total);
 System.out.println("max:"+max);
 System.out.println("min:"+min);
} }