package non;

import java.io.File;
import java.util.Scanner;

public class Scanner1 {
    public static void main(String[] args){
        File file = new File("C:\\Users\\wadat\\IdeaProjects\\Wada\\src\\testcode\\sample.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("年齢を入力してください");
        String old = scanner.next();

        System.out.println("年齢は" + old + "です");
    }
}
