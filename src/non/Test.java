package non;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class Test implements ActionListener{
 JFrame f;
 JDialog dialog;
 JButton button, ok, cancel;

 Test(){
 // メイン画面を作成
 f = new JFrame("メイン画面");
 Container pane = f.getContentPane();
 // メイン画面のボタンを作成
button = new JButton("サブ画面を作成");
button.addActionListener(this);

// ボタンを貼り付けるパネルを作成
JPanel panel = new JPanel();

// ボタンを貼り付ける
panel.add(button);
pane.add(panel);

// 表示
f.setBounds(0, 0, 200, 80);
f.setVisible(true);
}

public static void main(String[] args){
new Test();
}

public void actionPerformed(ActionEvent e){
// メイン画面のボタンが押されたら、
// サブ画面(ダイアログ)を開く
if(e.getSource() == button){
dialog = new JDialog(f, "サブ画面");
Container pane = dialog.getContentPane();

// OKボタンとCANCELボタンを作成
ok = new JButton("OK");
cancel = new JButton("CANCEL");

ok.addActionListener(this);
cancel.addActionListener(this);

// ボタンを貼り付けるパネルを作成
JPanel panel = new JPanel();

// ボタンを貼り付ける
panel.add(ok);
panel.add(cancel);
pane.add(panel);

// 表示
dialog.setBounds(30, 30, 200, 80);
dialog.setVisible(true);
}

// サブ画面(ダイアログ)のCANCELボタンが押されたら
// サブ画面(ダイアログ)を消す
if(e.getSource() == cancel){
dialog.hide();
}
}
}