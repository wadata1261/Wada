package UI;

import Mysql.Mysql;
import main.Teacher;
import make.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodeView extends JFrame implements ActionListener {
    JLabel labelfin;
    JLabel labelpath;
    JButton button1;
    JButton to,from;
    JTextField text1;
    JPanel p2,p=new JPanel();
    JButton b1,b2,fin;
    static Code code;
    static int i;
    ButtonGroup bgroup;
    static String patha;
    JRadioButton radio1;
    JRadioButton radio2;
    static String fullpath;
    public static void main(String[] args){

    }



    public static void setC(Code c,String path){
        code= c;
        fullpath=path;
        run();
    }



    public static void run(){
        CodeView frame = new CodeView();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 800, 500);
        frame.setTitle("タイトル");
        frame.setVisible(true);
    }
    public CodeView(){
        setTitle("Title");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1=new JPanel();
        text1 = new JTextField();
        text1.setText(fullpath);
        JLabel html=new JLabel("html");
        JLabel sorce =new JLabel("プログラム");
        JLabel ans = new JLabel("出力結果");
        JTextArea label=new JTextArea(10,10);
        JTextArea label1=new JTextArea(10,10);

        this.code.Code();
        label.setText(this.code.getSource());
        label1.setText(this.code.getAnser());
        JScrollPane scrollpane1 = new JScrollPane(label);
        JScrollPane scrollPane2 = new JScrollPane(label1);
        //System.out.println(c.getSource());
        //System.out.println(c.getAnser());
        panel1.setPreferredSize(new Dimension(200,50));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(html);
        panel1.add(text1);
        panel1.add(sorce);
        panel1.add(scrollpane1);
        panel1.add(ans);
        panel1.add(scrollPane2);
        //label1.setText(c[i].getAnser());

        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(300,200));
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        fin=new JButton("課題選択画面へ");
        fin.addActionListener(this);
        p.add(fin);
        p2.add(panel1);
        labelfin=new JLabel();
        labelpath=new JLabel();
        p2.add(labelpath);
        p2.add(p);
        p2.add(labelfin);


        Container contentPane = getContentPane();
        getContentPane().add(p2, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();

        String name=null;
        if(obj==fin){
            NearAnser na=new NearAnser();
            na.run();
            text1.setText(na.getPath());
        }
    }
}

