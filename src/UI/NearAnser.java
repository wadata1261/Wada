package UI;

import Mysql.Mysql;
import Mysql.Mysqllist;
import main.*;
import make.Code;

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
    String fullpath;

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
        JLabel labelT=new JLabel("クラスタ->作成したjavaファイルを選択");
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
        p2.add(labelT);
        for(int i=0;i<size;i++){
            p2.add(panels[i]);
        }

        Container contentPane = getContentPane();
        getContentPane().add(p2, BorderLayout.CENTER);
    }

    public String getPath(){return this.fullpath;}

    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();

        for(int i=0;i<size;i++){
            if(obj == buttons2[i]){ //クラスタ
                System.out.println(label[i].getText());
                filename=label[i].getText();

                Mysql ms=new Mysql();
                System.out.println("text"+label[i].getText());
                ArrayList<Mysqllist> msl=ms.getMysqllist(label[i].getText());
                for(int j=0;j<msl.size();j++){
                    System.out.println("75:"+msl.get(j));
                }
                File file=new File("");
                String name= file.getAbsolutePath();
                String paths=name.substring(0,name.length()-5);
                File dir=new File(paths+"\\anserfile\\"+label[i].getText());
                this.fullpath=paths+"\\anserfile\\"+label[i].getText();
                Student s=new Student();
                Path p= Paths.get(FileGet.fileGets()); //1こ
                String path=String.valueOf(p.toAbsolutePath());
                System.out.println("84:"+paths+"\\anserfile\\"+label[i].getText());
                //s.run(path,paths);
                s.runs(paths+"\\anserfile\\"+label[i].getText(),path,label[i].getText());
                ArrayList<Double> list=s.getList();
                FileCreateS fcs=new FileCreateS();
                fcs.setAns(s.getAnser().getFileName(),s.getAnser().CodeName());
                fcs.setTable(list);
                fcs.setlist(msl,list);
                fcs.FileCreate(label[i].getText());
                CodeView.setC(s.getAnser(),fcs.getFullpath());
                //cv.setC(s.getAnser());
                //cv.run();
                /*
                fc.setSorce(source);
                ArrayList<Double> td=new ArrayList<>();
                Teacher t=new Teacher();
                td=t.tofs(label[i].getText());
                fc.setTable(list);
                fc.setlist(msl,list);
                fc.FileCreate(label[i].getText());
                 */
            }
        }
    }
}

