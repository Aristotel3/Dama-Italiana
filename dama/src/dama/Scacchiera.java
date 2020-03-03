package dama;

import java.awt.*;  
import javax.swing.*;  
  
public class Scacchiera{  
JPanel f;  

  Scacchiera(){  
    f=new JPanel();  
    
    /*  
    JButton b1=new JButton("1");  
    JButton b2=new JButton("2");  
    JButton b3=new JButton("3");  
    JButton b4=new JButton("4");  
    JButton b5=new JButton("5");  
    JButton b6=new JButton("6");  
    JButton b7=new JButton("7");  
    JButton b8=new JButton("8");  
    JButton b9=new JButton("9");  
    
    f.add(b1);f.add(b2);f.add(b3);f.add(b4);f.add(b5);  
    f.add(b6);f.add(b7);f.add(b8);f.add(b9); */
    
    
  //setting grid layout of 3 rows and 3 columns
    f.setLayout(new GridLayout(8,8));  
    for(int i=1;i <= 8; i++) {
        for(int j=1;j <= 8; j++) {
          JButton button=new JButton();
          if  ( ( i + j ) % 2 == 0 )  {
                      button.setBackground ( Color.white ) ;
                  }  else  { 
                      button.setBackground ( Color.BLACK ) ;
                  } 
                  f.add ( button ) ;
        }
        
      }
      
  
    //f.setSize(300,300);  
    f.setVisible(true);  
  }  
}  