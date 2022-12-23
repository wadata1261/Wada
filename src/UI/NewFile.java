package UI;

import Mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class NewFile extends JFrame implements ActionListener {
    public ArrayList<String> pathname;
    JLabel label;
    JTextField text1;
    public int pathlength=0;
    Button button,button1;
    JPanel p2;
    public static void main(String[] args){
        NewFile t=new NewFile();
        t.run();

    }
    public void run(){
        NewFile frame = new NewFile();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 800, 1000);
        frame.setTitle("新規課題作成");
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj == button){
            label.setText(text1.getText());
             Mysql ms=new Mysql();
             ms.maketable(label.getText());
            fileCreate(String.valueOf(label.getText()));
            }
        if(obj == button1){
            System.out.println("できた");
            FileSelect fs=new FileSelect();
            fs.getPanel();

        }
        //System.exit(0);

    }

    NewFile(){
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel();
        p.setLayout(null);
        JLabel name=new JLabel("課題名");
        label=new JLabel();
        button = new Button("課題作成");
        button.setBounds(450, 10, 80, 30);
        button.addActionListener(this);
        text1 = new JTextField();
        text1.setBounds(300,10,150,30);
        p.add(name);
        p.add(button);
        p.add(text1);

        JPanel panel1=new JPanel();
        button1=new Button("ファイル選択");
        button1.addActionListener(this);
        panel1.setPreferredSize(new Dimension(300,200));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(button1);
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(300,200));
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.add(p);
        p2.add(panel1);
        Container contentPane = getContentPane();
        getContentPane().add(p2, BorderLayout.CENTER);

    }
    /*

     */
    public void fileCreate(String path){
        File file=new File("");
        String name= file.getAbsolutePath();
        String paths=name.substring(0,name.length()-5);
        File dir=new File(paths+"\\createfile");
        File newfile = new File(paths+"\\createfile\\"+path);


        if (newfile.mkdir()) {
            System.out.println("ディレクトリの作成に成功しました");
        } else {
            System.out.println("ディレクトリの作成に失敗しました");
        }
    }
    public ArrayList<String> getPathname(){
        File file=new File("");
        pathname=new ArrayList<>();
        String name= file.getAbsolutePath();
        String paths=name.substring(0,name.length()-5);
        File dir=new File(paths+"\\createfile");
        File[] list=dir.listFiles();
        for(int i=0;i<list.length;i++) {//System.out.println(list[i]);
        String a=list[i].getAbsolutePath();
        System.out.println(a);
        }
        ArrayList<File> lists=removelist(list);
        for(int i=0;i<lists.size();i++){
            //pathname.add(list[i].getName());
            System.out.println(lists.get(i));
            pathname.add(lists.get(i).getName());
            //System.out.println(pathname.get(i));
        }
        return pathname;
    }

    public static ArrayList<File> removelist(File[] files){
        ArrayList<File> f=new ArrayList<>(Arrays.asList(files));
        for(int i=0;i<files.length;i++) {
            String str = files[i].getName();
            String result = str.substring(str.lastIndexOf('\\') + 1);
            if(result.equals("createfile.iml")){
                f.remove(files[i]);
            }
            if(result.equals(".idea")){
                f.remove(files[i]);
            }
        }
        return f;
    }

    public void getPathlength(String path){
        File file=new File("");
        pathname=new ArrayList<>();
        String name= file.getAbsolutePath();
        String paths=name.substring(0,name.length()-5);
        File dir=new File(paths+"\\createfile\\"+path);
        File[] list=dir.listFiles();
        for(int i=0;i<list.length;i++){
            pathlength++;
        }
    }


}