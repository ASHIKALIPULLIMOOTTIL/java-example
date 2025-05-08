import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

 class Action extends JFrame implements ActionListener{
 JButton jb;
 JLabel jl;
 
 Action(){
  jb=new JButton("click");
  jb.setBounds(200,100,100,50);
  jb.addActionListener(this);
  
  jl=new JLabel("cr7");
  jl.setBounds(150,100,100,50);
  jl.setVisible(false);
  
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLayout(null);
  this.setSize(500,500);
  this.setVisible(true);
  this.add(jb); 
  this.add(jl);
 }
 
 public void actionPerformed(ActionEvent e){
  if(e.getSource()==jb){
   jl.setVisible(true);
   jb.setEnabled(false);
  }
 }
}

public class action{
 public static void main(String args[]){
  new Action();
 }
}
