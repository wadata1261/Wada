package UI;
import Mysql.*;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class SampleApp extends JFrame{
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JScrollPane pane;

    public SampleApp(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Mysql ms=new Mysql();
        ArrayList<Mysqllist> msl=ms.getMysqllist("kadai12_1");

        String[][] test=new String[5][3];
        for(int i=0;i<msl.size();i++){
            test[i]= new String[]{String.valueOf(i+1), msl.get(i).getName(), String.valueOf(msl.get(i).isBoo())};
        }

        String[][] data = {
                {"A","B","C","d"},
                {"123","456","789",""},
                {"あいう","かきく","さしす",""}
        };
        String[] header = {"number","name","boolean"};
        table = new JTable(test,header);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        pane = new JScrollPane(table);
        this.add(pane,BorderLayout.CENTER);
        this.setSize(new Dimension(500,400));
    }

    public static void main(String[] args) {
        new SampleApp().setVisible(true);
    }
}
