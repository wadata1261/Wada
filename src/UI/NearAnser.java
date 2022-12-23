package UI;

import Mysql.Mysql;
import main.FileCreate;
import main.FileGet;
import main.Student;
import main.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class NearAnser extends JFrame{
    JButton[] buttons2;
    JLabel[] label;
    int size;
    String filename;

    public static void main(String[] args){
        run();
    }

    public static void run(){
        NearAnser frame=new NearAnser();
        frame.setVisible(true);
    }
    NearAnser(){
        setTitle("課題選択");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NewFile nw=new NewFile();
        size=nw.getPathname().size();
        label=new JLabel[size];
        buttons2=new JButton[size];
        JPanel[] panels=new JPanel[size];
        for(int i=0;i<size;i++){
            label[i]=new JLabel(nw.getPathname().get(i));
            buttons2[i]=new JButton("クラスタ");
            buttons2[i].addActionListener(this::actionPerformed);
            panels[i]=new JPanel();
            panels[i].setPreferredSize(new Dimension(300,200));
            panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.X_AXIS));
            panels[i].add(label[i]);
            panels[i].add(buttons2[i]);
        }

        JLabel l=new JLabel();
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(300,200));
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        for(int i=0;i<size;i++){
            p2.add(panels[i]);
        }

        Container contentPane = getContentPane();
        getContentPane().add(p2, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();

        for(int i=0;i<size;i++){
            if(obj == buttons2[i]){ //クラスタ
                System.out.println(label[i].getText());
                filename=label[i].getText();

                Mysql ms=new Mysql();
                System.out.println("text"+label[i].getText());
                ArrayList<String> msl=ms.getNamelist(label[i].getText());
                for(int j=0;j<msl.size();j++){
                    System.out.println("75:"+msl.get(j));
                }
                File file=new File("");
                String name= file.getAbsolutePath();
                String paths=name.substring(0,name.length()-5);
                File dir=new File(paths+"\\createfile\\"+label[i].getText());
                Student s=new Student();
                Path p= Paths.get(FileGet.fileGets()); //1こ
                String path=String.valueOf(p.toAbsolutePath());
                System.out.println("84:"+paths+"\\createfile\\"+label[i].getText());
                s.runs(paths+"\\createfile\\"+label[i].getText(),path);
                ArrayList<Double> list=s.getList();
                int num=s.getCount();
                String source=s.getSource();
                FileCreate fc=new FileCreate();
                fc.setSorce(source);
                fc.setlist(num,list);
                fc.FileCreate(label[i].getText());

            }
        }
    }
}

