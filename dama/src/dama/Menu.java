package dama;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dama.Gioco;

public class Menu{

	public JFrame frame; //meglio farla private
	private JTextField txtDamaItaliana;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu menu = new Menu(); //detro initialize
					menu.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusableWindowState(false);
		frame.getContentPane().setLayout(null);
		
		txtDamaItaliana = new JTextField();
		txtDamaItaliana.setText("Dama Italiana");
		txtDamaItaliana.setBounds(161, 0, 114, 19);
		frame.getContentPane().add(txtDamaItaliana);
		txtDamaItaliana.setColumns(10);
		
		JButton btnNuovaPartita = new JButton("Nuova partita");
		btnNuovaPartita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user1=JOptionPane.showInputDialog(null, "Inserire username giocatore 1");
				String user2=JOptionPane.showInputDialog(null, "Inserire username giocatore 2");
				JFrame window=new Gioco(user1, user2);
				window.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNuovaPartita.setBounds(148, 44, 148, 35);
		frame.getContentPane().add(btnNuovaPartita);
		
		JButton btnStorico = new JButton("Storico");
		btnStorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnStorico.setBounds(148, 98, 148, 35);
		frame.getContentPane().add(btnStorico);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEsci.setBounds(148, 152, 148, 35);
		frame.getContentPane().add(btnEsci);
	}
}
