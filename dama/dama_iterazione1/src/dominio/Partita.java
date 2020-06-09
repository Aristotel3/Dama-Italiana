package dominio;
import java.util.Random;

import interfaccia.text.ElencoComandi;
import interfaccia.text.Parser;

import java.util.Collections;
import java.util.ArrayList;

public class Partita {
	private static final int MAX_RANGE = 10;
	private static final int MIN_RANGE = 1;
	private static final int ARRAYLIST_SIZE = 2;
	private static final char BIANCO = 'b';
	private static final char NERO = 'n';
	private Giocatore[] giocatori; 
	private int count;
	private Random random;
	private ArrayList numRandom = new ArrayList();
	private Damiera d;
	private boolean start = true;
	
	public Partita() {
		
	}
    
	public Partita(String username1, String username2) {
		
		d= new Damiera();
		
		giocatori=new Giocatore[2];
		
		Giocatore g1=new Giocatore(username1);
		Giocatore g2=new Giocatore(username2);
		
		if(insertGiocatore(g1, g2)) {
			System.out.println("Giocatori Inseriti Con Successo:");
		}	
		else System.out.println("Giocatori Non Inseriti :(");
		
		sorteggio(MIN_RANGE,MAX_RANGE);
		
		printGiocatori();
		
		d.setCaselle();
		d.printCaselle();
		avvio_partita();
					
		
	}
	
	private Boolean insertGiocatore(Giocatore gio1, Giocatore gio2) {
		Boolean add=true;
		for(this.count=0; this.count<2; this.count++) {
		 if(count == 0)	try{
			             this.giocatori[count] = gio1;
			            }catch(Exception e){
			            	add = false;
			            }
		 else try{
			   this.giocatori[count] = gio2;
		 	  }catch(Exception e) {
		 		  add = false;
		 	  }
		}
		
		return add;
	}
	
	private void avvio_partita() {
		String ultima_mossa;
		if (this.giocatori[0].getnumGiocatore() == 1) {
			doMove(this.giocatori[0]);
			lockGiocatore(this.giocatori[0]);
			System.out.println("Bloccato " + giocatori[0].getUsername()+ " " + giocatori[0].getTurno());
			ultima_mossa = this.giocatori[0].getUsername();
			System.out.println(ultima_mossa + " ultimamossa");
			}
		else {
			doMove(this.giocatori[1]);
			lockGiocatore(this.giocatori[1]);
			System.out.println("Bloccato " + giocatori[1].getUsername()+ " " + giocatori[1].getTurno());
			ultima_mossa = this.giocatori[1].getUsername();
			System.out.println(ultima_mossa + " ultimamossa");
			}	
		
		
		while(start) {
			if (ultima_mossa == this.giocatori[0].getUsername()){
				unlockGiocatore(this.giocatori[1]);
				System.out.println("Sbloccato " + giocatori[1].getUsername()+ " " + giocatori[1].getTurno());
				doMove(this.giocatori[1]);
				lockGiocatore(this.giocatori[1]);
				System.out.println("Bloccato " + giocatori[1].getUsername()+ " " + giocatori[1].getTurno());
				ultima_mossa = this.giocatori[1].getUsername();
				System.out.println(ultima_mossa + " ultimamossa");
				
			}
			else {
				unlockGiocatore(this.giocatori[0]);
				System.out.println("Sbloccato " + giocatori[0].getUsername()+ " " + giocatori[0].getTurno());
				doMove(this.giocatori[0]);
				lockGiocatore(this.giocatori[0]);
				System.out.println("Bloccato " + giocatori[0].getUsername()+ " " + giocatori[0].getTurno());
				ultima_mossa = this.giocatori[0].getUsername();
				System.out.println(ultima_mossa + " ultimamossa");
			}
				}
		System.out.println("Fine");

		
	}
	
	public Giocatore getGiocatore(int index) {
		return this.giocatori[index];
	}

	private void sorteggio(int minRANGE,int maxRANGE) {
		/*this.random = new Random();
		this.numRand = random.nextInt(RANGE)+1;
		return numRand;*/
		this.random = new Random();
		this.count=0;
		while (this.count < ARRAYLIST_SIZE ) {
		    int random_number = random.nextInt(maxRANGE - minRANGE) + minRANGE;
		    this.numRandom.add(random_number);
		    count ++;
		}
	    System.out.println(numRandom.get(0));
	    System.out.println(numRandom.get(1));
	    
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
			System.out.println(giocatori[count].toString());
		}
	}
	
	private void lockGiocatore(Giocatore glock) {
			glock.setTurno(false);
	}
	private void unlockGiocatore(Giocatore gunlock) {
		gunlock.setTurno(true);
		
}
	
	private void doMove(Giocatore gmove) {
		String dread = "";
		String oread = "";
		Casella co;
		Casella cd;
		d.setTrovata(false);
		while(d.getTrovata() == false) {
			System.out.println(gmove.getUsername() + " seleziona la casella di origine: NUMERO_RIGA NUMERO_COLONNA");
			oread = gmove.inputCasella();
			if(!oread.equals("ritiro")) {
				String [] inputo = oread.split(" ");
				if (oread.length() <= 3) {
					try {
					if(Integer.parseInt(inputo[0]) <= 7 && Integer.parseInt(inputo[0]) >= 0 && Integer.parseInt(inputo[1]) >= 0  && Integer.parseInt(inputo[1]) <= 7) {
						d.findCasella(Integer.parseInt(inputo[0]), Integer.parseInt(inputo[1]), gmove.getColor());
						if(d.getTrovata() == true) { 
							co = d.getCasella(Integer.parseInt(inputo[0]), Integer.parseInt(inputo[1]));
							d.evaluateMossa(co);
							System.out.println("Orgine settata coordinate: " + oread + "\nInserisci destinazione: ");
							dread = gmove.inputCasella();
							if(dread.length() <= 3) {
								String[] inputd = dread.split(" ");
								if(Integer.parseInt(inputd[0]) <= 7 && Integer.parseInt(inputd[0]) >= 0 && Integer.parseInt(inputd[1]) >= 0 && Integer.parseInt(inputd[1]) <= 7) {
									d.findCasella(Integer.parseInt(inputd[0]), Integer.parseInt(inputd[1]), ".".charAt(0));
									if(d.getTrovata() == true) {
										cd = d.getCasella(Integer.parseInt(inputd[0]), Integer.parseInt(inputd[1]));
										d.validateMossa(cd);
										System.out.println("Destinazione settata coordinate: " + dread + "\nInserisci destinazione: ");
									}
									else System.out.println("casella non valida, riprova!");
								}
								else  
									System.out.println("Input non valido, il primo carattere è un numero(0-7), il secondo è uno spazio, il terzo è un numer(0-7), riprova: ");
							}
							else {
								System.out.println("Inserire solo 3 caratteri, 1: numero(0-7), 2: spazio, 3: numero(0-7), riprova!"); 
								d.setTrovata(false);
							}
			
						}
						else System.out.println("casella non valida, riprova!");
		} 
		
		
		else System.out.println("Input non valido, il primo carattere è un numero(0-7), il secondo è uno spazio, il terzo è un numero(0-7)");
					}catch(Exception e) {
						d.setTrovata(false);
					}}
				
				else System.out.println("Inserire solo 3 caratteri, 1: numero(0-7), 2: spazio, 3: numero(0-7), riprova!");
			}else {
					d.setTrovata(true);
					start=false;
				}
		
		}
		
	}
	
	

}