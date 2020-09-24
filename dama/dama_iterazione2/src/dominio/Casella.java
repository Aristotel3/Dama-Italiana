package dominio;
import java.util.*; 

public class Casella implements Subject{
	private int riga;
	private int colonna;
	private char simbolo;
	private List<Observer> observers = new ArrayList <>();
	private HashMap<String, Casella> mosse = new HashMap<String, Casella>(); 
	
	public Casella(int riga, int colonna, char simbolo){
	this.riga=riga;
	this.colonna=colonna;
	this.simbolo=simbolo;
	
	}



	public int getRiga() {
		return this.riga;
		
	}

	public void setRiga(int riga) {
		this.riga = riga;
		
	}

	public int getColonna() {
		return this.colonna;
	}

	public void setColonna(int colonna) {
		this.colonna = colonna;
	}

	public char getSimbolo() {
		return this.simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
		if(simbolo == 'B' || simbolo == 'N')
			return;
		switch(this.riga) {
		case 7 :
			if(this.simbolo == 'b')
				notifyObservers();
			break;
		case 0 :
			if(this.simbolo == 'n')
				notifyObservers();
			break;
		default :
			break;
		}
	}
	
	public String toString(){
		  return(""+simbolo+" ");
		        
	}
	
	public void setTipoMossa (String chiave, Casella cd) {
		this.mosse.put(chiave, cd);
	}

	public HashMap<String, Casella> printmap() {
		 return this.mosse;
	}
	
	public Casella mosseget(String chiave) {
		return this.mosse.get(chiave);
	}
	
	protected void clearmosse() {
		this.mosse.clear();
		
	}



	@Override
	public void register(Observer oC) {
		observers.add(oC);
		
	}



	@Override
	public void unregister(Observer oC) {
		observers.remove(oC);
		
	}



	@Override
	public void notifyObservers() {
		for ( Observer observer : observers) {
			observer.update();
		}

		
	}
}