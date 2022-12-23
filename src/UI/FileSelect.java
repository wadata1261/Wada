package UI;

import Mysql.Mysql;
import main.FileCreate;
import main.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class FileSelect extends JFrame{
    List jLabelList=new List();
    JPanel panel=new JPanel();
    JLabel[] label;
    JButton[] buttons1;
    JButton[] buttons2;
    int size;
    String filename;
    public static void main(String args[]){
        run();
    }
    private static void run(){
        FileSelect frame = new FileSelect();
        frame.setVisible(true);
    }
    public String getFilename(){return filename;}
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        for(int i=0;i<size;i++){
            if(obj == buttons1[i]){ //追加
                AnserCheck.first(label[i].getText());
                //AnserCheck ac=new AnserCheck();
                //ac.first(label[i].getText());

                System.out.println(label[i].getText());
                filename=label[i].getText();
            }
            if(obj == buttons2[i]){ //クラスタ
                System.out.println(label[i].getText());
                filename=label[i].getText();

                Mysql ms=new Mysql();
                System.out.println(label[i].getText());
                ArrayList<String> msl=ms.getNamelist(label[i].getText());
                for(int j=0;j<msl.size();j++){
                    System.out.println(msl.get(j));
                }
                Teacher t=new Teacher();
                ArrayList<Double> td=t.tofs(label[i].getText());
                for(int j=0;j<td.size();j++){
                    System.out.println(td.get(j));
                }
                FileCreate fc=new FileCreate();
                fc.FileCreate(label[i].getText());



            }
        }
    }
    public FileSelect(){
        setTitle("課題選択");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NewFile nw=new NewFile();
        size=nw.getPathname().size();
        label=new JLabel[size];
        buttons1=new JButton[size];
        buttons2=new JButton[size];
        JPanel[] panels=new JPanel[size];
        for(int i=0;i<size;i++){
            label[i]=new JLabel(nw.getPathname().get(i));
            buttons1[i]=new JButton("追加");
            buttons1[i].addActionListener(this::actionPerformed);
            buttons2[i]=new JButton("クラスタ");
            buttons2[i].addActionListener(this::actionPerformed);
            panels[i]=new JPanel();
            panels[i].setPreferredSize(new Dimension(300,200));
            panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.X_AXIS));
            panels[i].add(label[i]);
            panels[i].add(buttons1[i]);
            panels[i].add(buttons2[i]);
        }

        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(300,200));
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        for(int i=0;i<size;i++){
            p2.add(panels[i]);
        }

        Container contentPane = getContentPane();
        getContentPane().add(p2, BorderLayout.CENTER);
        setPanel(p2);
    }
    private void setPanel(JPanel panel){
        this.panel=panel;
    }
    public JPanel getPanel(){
        run();
        return this.panel;
    }
}
