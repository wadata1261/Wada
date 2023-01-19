package UI;

import Mysql.Mysql;
import Mysql.Mysqllist;
import main.FileCreate;
import main.Teacher;
import make.Code;
import make.ValueLog;

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
    JLabel lab;
    JButton newb;
    int size;
    String filename;
    public static void main(String args[]){
        run();
    }
    public static void run(){
        FileSelect frame = new FileSelect();
        frame.setVisible(true);
}
    public String getFilename(){return filename;}
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        ArrayList<Double> td=new ArrayList<>();
        if(obj == newb){
            NewFile nf=new NewFile();
            nf.run();
        }
        for(int i=0;i<size;i++){
            if(obj == buttons1[i]){ //追加
                AnserCheck.First(label[i].getText());
                //AnserCheck ac=new AnserCheck();
                //ac.first(label[i].getText());

                System.out.println(label[i].getText());
                filename=label[i].getText();
            }
            if(obj == buttons2[i]){ //クラスタ
                System.out.println(label[i].getText());
                filename=label[i].getText();

                Mysql ms=new Mysql();
                ArrayList<Mysqllist> msl=ms.getMysqllist(label[i].getText());
                Teacher t=new Teacher();
                td=t.tofs(label[i].getText());
                System.out.println("ここまできた");
                FileCreate fc=new FileCreate();
                //fc.setSorce(source);
                fc.setlist(msl);
                fc.setTable(td);
                fc.FileCreate(label[i].getText());
                File file=new File("");
                String name= file.getAbsolutePath();
                String paths=name.substring(0,name.length()-5);
                lab.setText("ファイル作成場所："+paths+"\\htmlfiles\\"+label[i].getText());



            }
        }
        System.out.println("tdf");
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
        lab=new JLabel();
        newb=new JButton("新規課題作成");
        newb.addActionListener(this::actionPerformed);
        JLabel l=new JLabel("追加：追加する解答のみのフォルダを選択");
        JLabel l2=new JLabel("クラスタ：追加した解答のクラスタリング");
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(300,200));
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.add(l);
        p2.add(l2);
        for(int i=0;i<size;i++){
            p2.add(panels[i]);
        }
        p2.add(lab);
        p2.add(newb);
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
