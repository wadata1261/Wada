package main;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileGet {
    public static String fileGet() {
        String src = "";

        JFileChooser chooser = new JFileChooser();
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
