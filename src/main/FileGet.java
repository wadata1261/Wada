package main;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileGet {
    public static String fileGet() {
        String src = "";

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //ダイアログの表示
        chooser.showOpenDialog(null);
        //ダイアログの選択結果を取得
        File file = chooser.getSelectedFile();
        FileReader fileReader = null;
        try {
            //ファイルの探索

            //File mainClassFile = file;
            //visualizationFileName = file.toString();

            //fileReader = new FileReader(mainClassFile);

            //src = visualizationFileName;
            src = file.toString();

        } finally {
            try {
                if (fileReader != null) {
                    //ファイルを閉じる
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return src;
    }
    public static String fileGets() {
        File file0=new File("");
        String name= file0.getAbsolutePath();
        String paths=name.substring(0,name.length()-5);
        File dir=new File(paths+"\\createfile");
        System.out.println(dir.getAbsolutePath());
        String src = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //ダイアログの表示
        chooser.showOpenDialog(null);
        //ダイアログの選択結果を取得
        File file = chooser.getSelectedFile();
        FileReader fileReader = null;
        try {
            //ファイルの探索

            //File mainClassFile = file;
            //visualizationFileName = file.toString();

            //fileReader = new FileReader(mainClassFile);

            //src = visualizationFileName;
            src = file.toString();

        } finally {
            try {
                if (fileReader != null) {
                    //ファイルを閉じる
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return src;
    }
}
