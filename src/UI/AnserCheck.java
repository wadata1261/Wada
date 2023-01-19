package UI;

import Mysql.Mysql;
import main.Teacher;
import make.Code;
import make.ValueLog;
import make.ValueLogList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnserCheck extends JFrame implements ActionListener {
    JLabel labelfin;
    JLabel labelpath;
    JButton button1;
    JButton to,from;
    JTextField text1;
    JPanel p2,p=new JPanel();
    JButton b1,b2,fin;
    static Code[] c;
    static int i;
    ButtonGroup bgroup;
    static String patha;
    JRadioButton radio1;
    JRadioButton radio2;
    public static void main(String[] args){
        First("kadai12_1");
    }



    public static void First(String file){
        Teacher t=new Teacher();
        patha=file;
        t.doto();
        c=t.getC();
        i=0;
        doto();
    }

    public static void doto(){
        run(c[i]);
    }
    public static void run(Code c){
            Code code =c;
            code.Code();
            AnserCheck frame = new AnserCheck();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(10, 10, 800, 500);
            frame.setTitle("タイトル");
            frame.setVisible(true);
    }
    public AnserCheck(){
        setTitle("Title");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1=new JPanel();
        JTextField text1 = new JTextField();
        JLabel sorce =new JLabel("プログラム");
        JLabel ans = new JLabel("出力結果");
        JLabel check = new JLabel("正誤判定");
        JTextArea label=new JTextArea(10,10);
        JTextArea label1=new JTextArea(10,10);
        radio1 = new JRadioButton("True");
        radio1.addActionListener(this);
        radio1.setActionCommand("true");
        radio2 = new JRadioButton("False");
        radio2.addActionListener(this);
        radio2.setActionCommand("false");
        bgroup = new ButtonGroup();
        bgroup.add(radio1);
        bgroup.add(radio2);
        button1 =new JButton("確定");
        button1.addActionListener(this);
        JPanel bu=new JPanel();
        bu.setLayout(new BoxLayout(bu,BoxLayout.X_AXIS));
        b1=new JButton("前");
        b1.addActionListener(this);
        b2=new JButton("次");
        b2.addActionListener(this);
        bu.add(b1);
        bu.add(button1);
        bu.add(b2);
        JPanel button=new JPanel();
        button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
        button.add(radio1);
        button.add(radio2);

        JPanel buttons=new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
        buttons.add(button);



        label.setText(c[i].getSource());
        label1.setText(c[i].getAnser());
        JScrollPane scrollpane1 = new JScrollPane(label);
        JScrollPane scrollPane2 = new JScrollPane(label1);
        System.out.println(c[i].getPath());
        System.out.println(c[i].getSource());
        System.out.println(c[i].getAnser());
        for(ValueLog vl:c[i].getvll().getValueLogList()){
            System.out.println(vl);
        }
        panel1.setPreferredSize(new Dimension(200,50));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
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
        p2.add(check);
        p2.add(button);
        p2.add(bu);
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

        if(i==c.length-1){
            labelfin.setText("全解答終了");
        }

        String name=null;
        if(radio1.isSelected()){
            System.out.println(true);
            System.out.println("koko"+bgroup.getSelection().getActionCommand());
        }
        if(radio2.isSelected()){
            System.out.println(false);
        }
        if(obj == button1){
            if((radio1.isSelected() || radio2.isSelected()) && i<c.length) {
                //labelpath.setText(text1.getText());
                name = c[i].getParent();
                System.out.println("name is "+name/*.substring(0,name.length()-name.lastIndexOf("\\"))*/);
                Mysql mysql = new Mysql();
                mysql.run(this.patha, name, c[i].getFileName(), bgroup.getSelection().getActionCommand());
                labelpath.setText("確定："+bgroup.getSelection().getActionCommand());
            }
        }
        if(obj == b1 && i>0){
                i--;
                doto();
        }
        if(obj == b2 && i<c.length){
                i++;
                doto();
        }

        if(obj==fin){
            FileSelect fs=new FileSelect();
            fs.run();
        }
    }
}
