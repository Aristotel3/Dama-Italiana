package dominio;
import java.util.Random;

import interfaccia.text.ElencoComandi;
import interfaccia.text.Parser;

import java.util.Collections;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class Partita {
	private static final int MAX_RANGE = 10;
	private static final int MIN_RANGE = 1;
	private static final int ARRAYLIST_SIZE = 2;
	private static final char BIANCO = 'b';
	private static final char NERO = 'n';
	private Giocatore[] giocatori; 
	private int count;
	private Giocatore gwin;
	private Giocatore glose;
	private Random random;
	private ArrayList<Integer> numRandom = new ArrayList<Integer>();
	private Damiera d;
	private boolean start = true;
	
//	public Partita() {
		
//	}
    
	public Partita(String username1, String username2, boolean test) {
		
		this.d= new Damiera();
		
		this.giocatori=new Giocatore[2];
		
		Giocatore g1=new Giocatore(username1);
		Giocatore g2=new Giocatore(username2);
		if(test == true) {
			try {
				this.giocatori[0]=g1;
				this.giocatori[1]=g2;
			}catch(Exception e) {
				System.err.println("Errore nel setting dei giocatori");
				return;
			}
		}
		else {
		if(insertGiocatore(g1, g2)) {
			System.out.println("Giocatori Inseriti Con Successo:");
		}	
		else System.out.println("Giocatori Non Inseriti :(");
		
		
		sorteggio(MIN_RANGE,MAX_RANGE);
		
		printGiocatori();
		
		this.d.setCaselle();
		this.d.printCaselle();
		
			gestione_turno();
			System.out.println("Fine");
			}
	//	avvio();
		
	}
	
	
	public boolean getStart() {
		return this.start;
	}
	
	public void setStart(boolean status) {
		this.start = status;
	}
	
	
	
	public Damiera getDamiera() {
		return this.d;
	}
	
	public Boolean insertGiocatore(Giocatore gio1, Giocatore gio2) {
		Boolean add=true;
		for(int countx=0; countx<2; countx++) {
		 if(countx == 0)	try{
			           		this.giocatori[countx] = gio1;
			            }catch(Exception e){
			            	add = false;
			            }
		 else try{
			 	this.giocatori[countx] = gio2;
		 	  }catch(Exception e) {
		 		  add = false;
		 	  }
		}
		
		return add;
	}
	
	public void switchGiocatore(Giocatore gswitchON, Giocatore gswitchOFF) {
		lockGiocatore(gswitchOFF);
		System.out.println("Bloccato " + gswitchOFF.getUsername()+ " " + gswitchOFF.getTurno());
		unlockGiocatore(gswitchON);
		System.out.println("Sbloccato " + gswitchON.getUsername()+ " " + gswitchON.getTurno());
		
	}
	/*
	private void openFile() {
		try {
			File file = new File("./storico.txt");
			if (!file.exists())
				file.createNewFile();
			} catch (IOException e) {
			e.printStackTrace();
			}
		
	}
	*/
	
	public void gestione_turno() {
		while(this.start == true) {
		if(this.giocatori[0].getTurno() == true) {
			turno(this.giocatori[0], this.giocatori[1]);
			switchGiocatore(this.giocatori[1], this.giocatori[0]);
		}
		else {
			turno(this.giocatori[1], this.giocatori[0]);
			switchGiocatore(this.giocatori[0], this.giocatori[1]);
		}
		}

	}
	
	public void turno(Giocatore gmove, Giocatore gstop) {
		int control = gmove.doMove(this.d);
		if(control != 0 && control !=2)
			validateMossa();
		else {
			if(control == 0) {
				win(gstop, gmove);
				return;
			}
			else {
				if(proposePareggio(gmove, gstop))
					return;
				else {
					turno(gmove, gstop);
					return;
		}
			}
		}
			
		this.d.setlockMS(false);
		System.out.println("ultima mossa fatta da " + gmove.getUsername());
		this.d.printCaselle();
		

		
	}
	
	public boolean proposePareggio(Giocatore gmove, Giocatore gstop) {
		System.out.println("Pareggio proposto da " + gmove.getUsername() + "\n" + gstop.getUsername() + " digita 1 per accettare, 2 per rifiutare");
		String response = gstop.inputCasella();
		if(response.equals("1")) {
			System.out.println("Partita terminata in pareggio");
			//updateStorico();
			this.start = false;
			return true;
	}
		else {
			if(response.equals("2")) {
				System.out.println("Pareggio rifiutato");
				this.start = true;
				return false;
			}
			else {
				System.out.println("Scelta non valida, riprova!");
				return proposePareggio(gmove, gstop);
			}
		}
	
	}
	public Giocatore getGiocatore(int index) {
		return this.giocatori[index];
	}

	public void sorteggio(int minRANGE,int maxRANGE) {
		this.random = new Random();
		this.count=0;
		while (this.count < ARRAYLIST_SIZE ) {
		    int random_number = this.random.nextInt(maxRANGE - minRANGE) + minRANGE;
		    this.numRandom.add(random_number);
		    this.count ++;
		}
	    System.out.println(this.numRandom.get(0));
	    System.out.println(this.numRandom.get(1));
	    
		if(this.numRandom.get(0)== this.numRandom.get(1)) {
	    int temp_num=(int) this.numRandom.get(0);
	    temp_num++;
		this.numRandom.set(0,temp_num);
		Collections.shuffle(this.numRandom);
		}

		
		this.giocatori[0].setNumRand((int)this.numRandom.get(0));
		this.giocatori[1].setNumRand((int)this.numRandom.get(1));
	    if(this.giocatori[0].getNumRand() > this.giocatori[1].getNumRand()) {
	    	this.giocatori[0].setColor(BIANCO);
	    	this.giocatori[0].setTurno(true);
	    	this.giocatori[0].setnumGiocatore(1);
	    	this.giocatori[1].setColor(NERO);
	    	this.giocatori[1].setTurno(false);
	    	this.giocatori[1].setnumGiocatore(2);
	    }
	    else {
	    	this.giocatori[1].setColor(BIANCO);
	    	this.giocatori[1].setTurno(true);
	    	this.giocatori[1].setnumGiocatore(1);
	        this.giocatori[0].setColor(NERO);
	        this.giocatori[0].setTurno(false);
	        this.giocatori[0].setnumGiocatore(2);
	    }
	  
	    
	    
	}
	
	private void printGiocatori() {	
		for(this.count=0; this.count<2; this.count++) {
			System.out.println(this.giocatori[count].toString());
		}
	}
	
	private void lockGiocatore(Giocatore glock) {
			glock.setTurno(false);
	}
	private void unlockGiocatore(Giocatore gunlock) {
		gunlock.setTurno(true);
		
}
	/*
	private String readFile(String usernameW, String usernameL) {
		char[] in = new char[50];
		try {
		File file = new File("./storico.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		br.read(in);
		String [] righe = in.toString().split("\n");
		int num_righe = righe.length;
		br.close();
		for(int i = 0; i<num_righe; i++) {
			if(usernameW.equals(righe[i].split(" ")[1])) {
				int vittorie = Integer.parseInt(righe[i].split(" ")[3]) + 1;
				int partite = Integer.parseInt(righe[i].split(" ")[2]) + 1;
				righe[i].split(" ")[2] = String.valueOf(partite);
				righe[i].split(" ")[3] = String.valueOf(vittorie);
			}if(usernameL.equals(righe[i].split(" ")[1])) { 
				int sconfitte = Integer.parseInt(righe[i].split(" ")[4]) + 1;
				int partite = Integer.parseInt(righe[i].split(" ")[2]) + 1;
				righe[i].split(" ")[2] = String.valueOf(partite);
				righe[i].split(" ")[4] = String.valueOf(sconfitte);
			}
		}
		return righe.toString();
		}
		 catch(IOException e) {
		e.printStackTrace();
		}
		return null;
		
		
	}
	
	
	
	
	
	private void updateStorico(Giocatore gwin, Giocatore glose) {
		String righe = readFile(gwin.getUsername(), glose.getUsername());
		try {
		File file = new File("./storico.txt");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		if (righe[0] == null)
			bw.append(righe);
		bw.write("Questo è il nostro primo file");
		bw.flush();
		bw.close();
		}
		catch(IOException e) {
		e.printStackTrace();
		}
	}
	
	*/
	
	public Giocatore getWinner() {
		if(this.gwin != null)
			return gwin;
		return null;
	}
	
	public Giocatore getLoser() {
		if(this.glose != null)
			return glose;
		return null;
	}
	
	public void win(Giocatore gwinner, Giocatore gloser) {
		this.start = false;
		gwin = gwinner;
		glose = gloser;
		System.out.println("Giocatore vincente " + gwinner.getUsername() + "Giocatore perdente " + gloser.getUsername());
	}
	
	public void validateMossa() { 
		int mosse = this.d.scrollMosse();
		if(mosse == 2) {
			if(this.d.getUpdate()==false) {
				if(this.giocatori[0].getTurno() == true) {
					this.giocatori[0].selectDestinazione(this.d);
					validateMossa();
				}
				else {
					this.giocatori[1].selectDestinazione(this.d);
					validateMossa();
				}
				return;
				}
			else {
				this.d.printCaselle();
				if(this.giocatori[0].getTurno() == true) {
					this.giocatori[1].setCountPedine(this.giocatori[1].getCountPedine()-1);
					if(this.giocatori[1].getCountPedine() == 0) {
						win(this.giocatori[0], this.giocatori[1]);
						return;
					}
				}
				else {
					this.giocatori[0].setCountPedine(this.giocatori[0].getCountPedine()-1);
					if(this.giocatori[0].getCountPedine() == 0) {
						win(this.giocatori[1], this.giocatori[0]);
						return;
					}
				}
				if(this.d.evaluateMossa() == 2) {
					validateMossa();
					return; //
				}
				this.d.setUpdate(false);  ///aggiunta ora
				return;
			}
		}
		else {
			if(mosse == 1) {
				if(this.d.getUpdate() == true) {
					this.d.setUpdate(false);
					return;
				}
				else {
					if(this.giocatori[0].getTurno() == true) {
						this.giocatori[0].selectDestinazione(this.d);
						validateMossa();
					}
					else {
						this.giocatori[1].selectDestinazione(this.d);
						validateMossa();
					}
					return;
				}
			}
			if(mosse == 0) {
				this.d.setUpdate(false);
				return;
			}
			
		}
					
			
	}
			
				
				
	
	

			
}

