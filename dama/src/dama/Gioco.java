package dama;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dama.Menu;
import java.awt.TextArea;
import java.awt.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;

public class Gioco extends JFrame implements Runnable{

	/**
	 * 
	 */
	
	private JPanel contentPane; //panel esteso per tutta la finestra

	/**
	 
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gioco frame = new Gioco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public Gioco(String user1,String user2) {
		
		
		//GESTIONE CHIUSURA FINESTRA
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                Menu m= new Menu();
               m.frame.setVisible(true);
            }
        });
		 
		setBounds(200, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		//layout->Group
		JTextArea textAreaGiocatori = new JTextArea();
		textAreaGiocatori.setText("Giocatore1: "+user1+"\n"+"Giocatore2: "+user2);
		textAreaGiocatori.setEditable(false);
		
		JPanel panel = new JPanel();
	    panel.add(new Scacchiera().f);
	    panel.setVisible(true);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addComponent(textAreaGiocatori, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(495, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(337, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 553, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(textAreaGiocatori, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void run() {
		try {
			
			 new Gioco(null,null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
