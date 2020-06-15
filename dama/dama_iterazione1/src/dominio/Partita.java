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
		
		this.d= new Damiera();
		
		this.giocatori=new Giocatore[2];
		
		Giocatore g1=new Giocatore(username1);
		Giocatore g2=new Giocatore(username2);
		
		if(insertGiocatore(g1, g2)) {
			System.out.println("Giocatori Inseriti Con Successo:");
		}	
		else System.out.println("Giocatori Non Inseriti :(");
		
		sorteggio(MIN_RANGE,MAX_RANGE);
		
		printGiocatori();
		
		this.d.setCaselle();
		
		this.d.printCaselle();
		
		avvio_partita();
					
		
	}
	
	private Boolean insertGiocatore(Giocatore gio1, Giocatore gio2) {
		Boolean add=true;
		for(this.count=0; this.count<2; this.count++) {
		 if(this.count == 0)try{
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
			System.out.println("\nBloccato " + this.giocatori[0].getUsername()+ " " + this.giocatori[0].getTurno());
			ultima_mossa = this.giocatori[0].getUsername();
			System.out.println(ultima_mossa + " ultimamossa");
			}
		else {
			doMove(this.giocatori[1]);
			lockGiocatore(this.giocatori[1]);
			System.out.println("\nBloccato " + this.giocatori[1].getUsername()+ " " + this.giocatori[1].getTurno());
			ultima_mossa = this.giocatori[1].getUsername();
			System.out.println(ultima_mossa + " ultimamossa\n");
			}	
		
		
		while(start) {
			if (ultima_mossa == this.giocatori[0].getUsername()){
				this.d.printCaselle();
				//System.out.print("\nGiocatore " + this.giocatori[1].getUsername() + " Pedine " + this.giocatori[1].getColor() + " numero pedine " + this.giocatori[1].getCountPedine());
				unlockGiocatore(this.giocatori[1]);
				System.out.print("\nSbloccato " + this.giocatori[1].getUsername()+ " " + this.giocatori[1].getTurno());
				doMove(this.giocatori[1]);
				this.d.setlockMS(false);
				lockGiocatore(this.giocatori[1]);
				System.out.print("\nBloccato " + this.giocatori[1].getUsername()+ " " + this.giocatori[1].getTurno());
				ultima_mossa = this.giocatori[1].getUsername();
				System.out.println(ultima_mossa + " ultimamossa");
				
			}
			else {
				this.d.printCaselle();
				//System.out.print("\nGiocatore " + this.giocatori[0].getUsername() + " Pedine " + this.giocatori[0].getColor() + " numero pedine " + this.giocatori[0].getCountPedine());
				unlockGiocatore(this.giocatori[0]);
				System.out.print("\nSbloccato " + this.giocatori[0].getUsername()+ " " + this.giocatori[0].getTurno());
				doMove(this.giocatori[0]);
				this.d.setlockMS(false);
				lockGiocatore(this.giocatori[0]);
				System.out.print("\nBloccato " + this.giocatori[0].getUsername()+ " " + this.giocatori[0].getTurno());
				ultima_mossa = this.giocatori[0].getUsername();
				System.out.println(ultima_mossa + " ultimamossa");
			}
		}
		System.out.println("\nFine");

		
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
	
	private void doMove(Giocatore gmove) {
		this.d.setTrovata(false);
		while(this.d.getTrovata() == false) {
			selectorigine(gmove);
			if(this.start == false) return;
			selectdestinazione(gmove);	
		}
		
	}
	
	private void selectorigine(Giocatore gmove) {
		System.out.println("\n"+gmove.getUsername() + " con Pedine: ["+gmove.getColor()+"]\n"+"Numero pedine " + gmove.getCountPedine()+"\nseleziona la casella di origine: NUMERO_RIGA NUMERO_COLONNA");
		String oread = gmove.inputCasella();
		if(!oread.equals("ritiro")) {
			String [] inputo = oread.split(" ");
			if (oread.length() <= 3) {
				try {
				if(Integer.parseInt(inputo[0]) <= 7 && Integer.parseInt(inputo[0]) >= 0 && Integer.parseInt(inputo[1]) >= 0  && Integer.parseInt(inputo[1]) <= 7) {
					this.d.findCasella(Integer.parseInt(inputo[0]), Integer.parseInt(inputo[1]), gmove.getColor());
					if(this.d.getTrovata() == true) { 
						this.d.co = this.d.getCasella(Integer.parseInt(inputo[0]), Integer.parseInt(inputo[1]));
						 if(this.d.evaluateMossa()==0) {
						  System.out.println("Non hai mosse disponibili, riprova!");
						  selectorigine(gmove);
						  return;
					    }
					}
					else {
						System.out.println("casella non valida, riprova!");
						selectorigine(gmove);
						return;
					}
				}
				else {
					this.d.setTrovata(false);
					System.out.println("Input non valido, il primo carattere è un numero(0-7), il secondo è uno spazio, il terzo è un numero(0-7)");
					selectorigine(gmove);
					return;
				}		}
				catch(Exception e) {
					this.d.setTrovata(false);
					selectorigine(gmove);
					return;
				}
		}
		  else {
		   this.d.setTrovata(false);
		   System.out.println("Inserire solo 3 caratteri, 1: numero(0-7), 2: spazio, 3: numero(0-7), riprova!");
		   selectorigine(gmove);
		   return;
		  }	
		}
		else {
		this.d.setTrovata(true);
		this.start=false;
		if(gmove.getUsername().equals(this.giocatori[0].getUsername()))
		      win(gmove, this.giocatori[1]);
		    else
		      win(this.giocatori[0],gmove);
		}
		
			
	}
	
	
	private void selectdestinazione(Giocatore gmove) {
		System.out.println("\n"+gmove.getUsername() + " seleziona la casella di destinazione: NUMERO_RIGA NUMERO_COLONNA");
		String dread = gmove.inputCasella();
		if(!dread.equals("ritiro")) {
			String [] inputd = dread.split(" ");
			if (dread.length() <= 3){
				try {
				if(Integer.parseInt(inputd[0]) <= 7 && Integer.parseInt(inputd[0]) >= 0 && Integer.parseInt(inputd[1]) >= 0  && Integer.parseInt(inputd[1]) <= 7) {
					this.d.findCasella(Integer.parseInt(inputd[0]), Integer.parseInt(inputd[1]), '.');
					 if(d.getTrovata() == true) { 
						this.d.cd = this.d.getCasella(Integer.parseInt(inputd[0]), Integer.parseInt(inputd[1]));
						validateMossa();
						return;
					 }
					 else {
						System.out.println("casella non valida, riprova!");
						selectdestinazione(gmove);
						return;								
					 }
					}
				 else{ 
					this.d.setTrovata(false);
					System.out.println("Input non valido, il primo carattere è un numero(0-7), il secondo è uno spazio, il terzo è un numero(0-7)");
					selectdestinazione(gmove);
					return;
				 }	
				}
				catch(Exception e) {
					this.d.setTrovata(false);
					selectdestinazione(gmove);
					return;				
				}
			}
		    else {
		     this.d.setTrovata(false);
		     System.out.println("Inserire solo 3 caratteri, 1: numero(0-7), 2: spazio, 3: numero(0-7), riprova!");
		     selectdestinazione(gmove);
		     return;
		   }
	   }
		else {
		 this.d.setTrovata(true);
		 this.start=false;
		 if(gmove.getUsername().equals(this.giocatori[0].getUsername()))
		      win(gmove, this.giocatori[1]);
		    else
		      win(this.giocatori[0],gmove);
		}
		
			
	}
	
		
	
	private void validateMossa() { 
		int mosse = this.d.scrollMosse();
		if(mosse == 2) {
			if(this.d.getUpdate()==false) {
				if(this.giocatori[0].getTurno() == true)
					selectdestinazione(this.giocatori[0]);
				else
					selectdestinazione(this.giocatori[1]);
				return;
				}
			else {
				this.d.printCaselle();
				if(this.giocatori[0].getTurno() == true) {
					this.giocatori[1].setCountPedine(this.giocatori[1].getCountPedine()-1);
					if(this.giocatori[1].getCountPedine()==0) {
						win(this.giocatori[0],this.giocatori[1]);
						return;
					}
				}
				else {
					this.giocatori[0].setCountPedine(this.giocatori[0].getCountPedine()-1);
					if(this.giocatori[0].getCountPedine()==0) {
						win(this.giocatori[1],this.giocatori[0]);
						return;
					}
				}
				if(this.d.evaluateMossa() == 2)
					validateMossa();
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
					if(this.giocatori[0].getTurno() == true)
						selectdestinazione(this.giocatori[0]);
					else
						selectdestinazione(this.giocatori[1]);
					return;
				}
			}
			if(mosse == 0) {
			if(this.d.getUpdate() == true) {
				this.d.setUpdate(false);
				return;
			}
			else {
				if(this.giocatori[0].getTurno() == true)
					doMove(this.giocatori[0]);
				else
					doMove(this.giocatori[1]);
				return;
			}
			}
			
		}
					
			
	}
	
	private void win(Giocatore gwin, Giocatore glose) {
	    this.start = false;
	    System.out.println("\nVince giocatore " + gwin.getUsername());
	  }
			
			
}
		
