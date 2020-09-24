package dominio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import interfaccia.text.Parser;

public class Dama {
private Partita p;

	public Dama(){
		
		
	}
	
	public void startPartita() {
		System.out.println("   Inserisci nome Giocatore 1: ");	
		String username1 = Parser.getInstance().read();
		while(username1.isEmpty()) {
			System.out.println("   Hai inserito un campo vuoto, riprova! Inserisci nome Giocatore 1: ");
			username1 = Parser.getInstance().read();
		}
		System.out.println("   Inserisci nome Giocatore 2: ");
		String username2 = Parser.getInstance().read();
		while(username2.isEmpty()) {
			System.out.println("   Hai inserito un campo vuoto, riprova! Inserisci nome Giocatore 2: ");
			username2 = Parser.getInstance().read();
		}
		System.out.println();
		this.p=Partita.getInstance(username1,username2, false); //terzo parametro riconosce modalità test, se settato true il software entra in modalità test
	//	this.p = new Partita(username1, username2, false);
		if (this.p.getWinner()!= null)
			updateStorico(this.p.getWinner(), this.p.getLoser(), false);
		else
			updateStorico(this.p.getGiocatore(0), this.p.getGiocatore(1), true);
		Partita.deleteInstance();
	}
	
	private File openFile() {
		try {
			File file = new File("./storico.txt");
			if (!file.exists())
				file.createNewFile();
			return file;
			} catch (IOException e) {
			e.printStackTrace();
			}
		return null;
		
	}

	private StringBuffer readFile() {
		openFile();
		BufferedReader br = null;
        FileReader fr = null;
        StringBuffer righe = new StringBuffer("");
        String line = "";
		
		try {

            fr = new FileReader("./storico.txt");
            br = new BufferedReader(fr);
            
            // read line by line
            while ((line = br.readLine()) != null){
            	righe.append(line + "\n");
            }


        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        } finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }
        }
		return righe;
		

		  }
	
	public void viewStorico() {
		System.out.println(readFile());
	}
		
	
	
	
	
	
	
	private void updateStorico(Giocatore player1, Giocatore player2, boolean draw) {
		File file = openFile();
		StringBuffer righe = readFile();
		int num_righe = righe.toString().split("\n").length;
		
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			if(!draw) {
			int matchWinner = 0;
			int winWinner = 0;
			int drawWinner  =0;
			int defeatWinner = 0;
			
			int matchLoser = 0;
			int winLoser = 0;
			int drawLoser  =0;
			int defeatLoser = 0;
			int i_win =-1;
			int i_los = -1;
			
			String winner = player1.getUsername();
			String loser = player2.getUsername();
			String contentWinner = "";
			String contentLoser = "";
			if (righe.toString().isEmpty()) {
				String content = winner + " 1 1 0 0\n" + loser+ " 1 0 0 1\n";
				bw.write("Giocatore Partite Vittorie Pareggi Sconfitte\n");
				bw.append(content);
			}
			else {
				for (int i = 1; i<num_righe; i++) {
					if(winner.equals(righe.toString().split("\n")[i].split(" ")[0])) {
						matchWinner=Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[1]))+1;
						winWinner=Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[2]))+1;
						drawWinner= Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[3]));
						defeatWinner= Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[4]));
						i_win = i;
						contentWinner = winner + " " + Integer.toString(matchWinner) + " " + Integer.toString(winWinner) + " " + Integer.toString(drawWinner) + " " + Integer.toString(defeatWinner) + "\n";
					}
					if(loser.equals(righe.toString().split("\n")[i].split(" ")[0])) {
						matchLoser=Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[1]))+1;
						winLoser = Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[2]));
						drawLoser= Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[3]));
						defeatLoser= Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[4]))+1;
						i_los = i;
						contentLoser = loser + " " + Integer.toString(matchLoser) + " " + Integer.toString(winLoser) + " " + Integer.toString(drawLoser) + " " + Integer.toString(defeatLoser) + "\n";
					}
				}
				for (int x = 0; x<num_righe; x++) {
					if(x == 0) 
						bw.write("Giocatore Partite Vittorie Pareggi Sconfitte\n");
					else {
						if(i_win != x && i_los != x) {
							bw.append(righe.toString().split("\n")[x] + "\n");
							if (i_win == -1) {
								bw.append(winner + " 1 1 0 0\n");
								i_win = 0;
							}
							if (i_los == -1) {
								bw.append(loser + " 1 0 0 1\n");
								i_los = 0;
							}
						}
						else {
							if(i_win == x)
								bw.append(contentWinner);
							if(i_los == x)
								bw.append(contentLoser);
							}
						
					}
					}
			
			}
			}
			else {
				int matchG1 = 0;
				int winG1 = 0;
				int drawG1  =0;
				int defeatG1 = 0;
				
				int matchG2 = 0;
				int winG2 = 0;
				int drawG2  =0;
				int defeatG2 = 0;
				int i_g1 =-1;
				int i_g2 = -1;
				String g1 = player1.getUsername();
				String g2 = player2.getUsername();
				String contentG1 = "";
				String contentG2 = "";
				if (righe.toString().isEmpty()) {
					String content = g1 + " 1 0 1 0\n" + g2+ " 1 0 1 0\n";
					bw.write("Giocatore Partite Vittorie Pareggi Sconfitte\n");
					bw.append(content);
				}
				else {
					for (int i = 1; i<num_righe; i++) {
						if(g1.equals(righe.toString().split("\n")[i].split(" ")[0])) {
							matchG1=Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[1]))+1;
							winG1=Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[2]));
							drawG1= Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[3]))+1;
							defeatG1= Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[4]));
							i_g1 = i;
							contentG1 = g1 + " " + Integer.toString(matchG1) + " " + Integer.toString(winG1) + " " + Integer.toString(drawG1) + " " + Integer.toString(defeatG1) + "\n";
						}
						if(g2.equals(righe.toString().split("\n")[i].split(" ")[0])) {
							matchG2=Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[1]))+1;
							winG2 = Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[2]));
							drawG2= Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[3]))+1;
							defeatG2= Integer.parseInt(String.valueOf(righe.toString().split("\n")[i].split(" ")[4]));
							i_g2 = i;
							contentG2 = g2 + " " + Integer.toString(matchG2) + " " + Integer.toString(winG2) + " " + Integer.toString(drawG2) + " " + Integer.toString(defeatG2) + "\n";
						}
					}
					for (int x = 0; x<num_righe; x++) {
						if(x == 0) 
							bw.write("Giocatore Partite Vittorie Pareggi Sconfitte\n");
						else {
							if(i_g1 != x && i_g2 != x) {
								bw.append(righe.toString().split("\n")[x] + "\n");
								if (i_g1 == -1) {
									bw.append(g1 + " 1 0 1 0\n");
									i_g1 = 0;
								}
								if (i_g2 == -1) {
									bw.append(g2 + " 1 0 1 0\n");
									i_g2 = 0;
								}
							}
							else {
								if(i_g1 == x)
									bw.append(contentG1);
								if(i_g2 == x)
									bw.append(contentG2);
								}
							
						}
						}
				
				}	
			}
			bw.flush();
			bw.close();
			}
			catch(IOException e) {
			e.printStackTrace();
			}
		
		
		
	}
	
	
	
	
}