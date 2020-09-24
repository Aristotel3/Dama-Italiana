package dominio;
import java.util.Random;

import java.util.ArrayList;
import java.util.Collections;




public class Partita implements Observer{
	private static final int MAX_RANGE = 10;
	private static final int MIN_RANGE = 1;
	private static final int ARRAYLIST_SIZE = 2;
	private static final char BIANCO = 'b';
	private static final char NERO = 'n';
	private static Partita istance = null;
	private Damiera d;
	private Giocatore[] giocatori; 
	private int count;
	private Giocatore gwin;
	private Giocatore glose;
	private Random random;
	private ArrayList<Integer> numRandom = new ArrayList<Integer>();
	
	private boolean start = true;
	
	

	
	public static Partita getInstance(String usernameg1,String usernameg2,Boolean test) {
		if (istance==null)
			istance=new Partita(usernameg1,usernameg2,test);
		return istance;
	}
	
	public static void deleteInstance(){
	     istance=null;
	  }
  
	
	
	public Partita(String username1, String username2, boolean test) {
		
		this.d= Damiera.getInstance();
		this.giocatori=new Giocatore[2];
		
		Giocatore g1=new Giocatore(username1,getDamiera());
		Giocatore g2=new Giocatore(username2,getDamiera());
		if(test == true) {
			try {
				this.giocatori[0]=g1;
				this.giocatori[1]=g2;
				this.giocatori[0].register(this);
				this.giocatori[1].register(this);
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
		
		
		getDamiera().printCaselle();
		this.giocatori[0].register(this);
		this.giocatori[1].register(this);
		
		gestione_turno();
		Damiera.deleteInstance();
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
	
	public Giocatore getGiocatore(int index) {
		return this.giocatori[index];
 }
	
	public Damiera getDamiera() {
		return this.d;
	}
	
	
	
	public Boolean insertGiocatore(Giocatore gio1, Giocatore gio2) {
		Boolean add=true;
		for(int countx=0; countx<2; countx++) {
		 if(countx == 0)try{
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
	
	
	
	public void gestione_turno() {
		 while(this.start == true) {
			if(this.giocatori[0].getTurno() == true) {
				this.giocatori[0].turno(this.giocatori[1]);
				checker(this.giocatori[0], this.giocatori[1]);
				switchGiocatore(this.giocatori[1], this.giocatori[0]);
			}
			else {
				this.giocatori[1].turno(this.giocatori[0]);
				checker(this.giocatori[1], this.giocatori[0]);
				switchGiocatore(this.giocatori[0], this.giocatori[1]);
			}
		  }

		}
	
	
	private void checker(Giocatore gmove, Giocatore gstop){
		 
		
		if(gmove.getStatus() == "null") {
		this.d.setlockMS(false);
		System.out.println("ultima mossa fatta da " + gmove.getUsername());
		this.d.printCaselle();
		}
		return;
	}
	
	
	
	public void switchGiocatore(Giocatore gswitchON, Giocatore gswitchOFF) {
		lockGiocatore(gswitchOFF);
		//System.out.println("Bloccato " + gswitchOFF.getUsername()+ " " + gswitchOFF.getTurno());
		unlockGiocatore(gswitchON);
		//System.out.println("Sbloccato " + gswitchON.getUsername()+ " " + gswitchON.getTurno());	
	}
	
	
	
	 
  	
	
		
		
	 
	
	
 public boolean proposePareggio(Giocatore gmove, Giocatore gstop) {
		System.out.println("Pareggio proposto da " + gmove.getUsername() + "\n" + gstop.getUsername() + " digita 1 per accettare, 2 per rifiutare");
		String response = gstop.inputCasella();
		if(response.equals("1")) {
			System.out.println("Partita terminata in pareggio");
			gstop.setStatus("draw");
			this.start = false;
			return true;
	    }
		else {
			if(response.equals("2")) {
				System.out.println("Pareggio rifiutato");
				gmove.setStatus("null");
				this.start = true;
				return false;
			}
			else {
				System.out.println("Scelta non valida, riprova!");
				return proposePareggio(gmove, gstop);
			}
		}
	
 }
 
 

 
	
 
	
 public void lockGiocatore(Giocatore glock) {
			glock.setTurno(false);
 }
 
 public void unlockGiocatore(Giocatore gunlock) {
		gunlock.setTurno(true);
 }

	
	public Giocatore getWinner() {
		if(this.gwin != null)
			return this.gwin;
		return null;
	}
	
	public Giocatore getLoser() {
		if(this.glose != null)
			return this.glose;
		return null;
	}
	
	public void win(Giocatore gwinner, Giocatore gloser) {
		this.start = false;
		this.gwin = gwinner;
		this.glose = gloser;
		System.out.println("Giocatore vincente " + gwinner.getUsername() + "Giocatore perdente " + gloser.getUsername() + "\n");
	}

	@Override
	public void update() {
		if(giocatori[0].getTurno() == true) {
			switch(giocatori[0].getStatus()) {
			case "win":
				giocatori[1].setStatus("defeat");
				win(giocatori[0], giocatori[1]);
				break;
			case "draw":
				if(proposePareggio(giocatori[0], giocatori[1]));
				else
					gestione_turno();
				break;
			case "retreat":
				giocatori[1].setStatus("win");
				win(giocatori[1], giocatori[0]);
				break;
			default:
				break;
			}
			return;
		}
		else {
			switch(giocatori[1].getStatus()) {
			case "win":
				giocatori[0].setStatus("defeat");
				win(giocatori[1], giocatori[0]);
				break;
			case "draw":
				if(proposePareggio(giocatori[1], giocatori[0]));
				else
					gestione_turno();
				break;
			case "retreat":
				giocatori[0].setStatus("win");
				win(giocatori[0], giocatori[1]);
				break;
			default:
				break;
			}
			return;
		}
		// TODO Auto-generated method stub
		
	}
		
	
	
	 
	
	
	
}
