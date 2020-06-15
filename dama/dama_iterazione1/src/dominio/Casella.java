package dominio;
import java.util.*; 

public class Casella {
	private int riga;
	private int colonna;
	private char simbolo;
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

	public void setColonna(char colonna) {
		this.colonna = colonna;
	}

	public char getSimbolo() {
		return this.simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	
	public String toString(){
		  return(""+simbolo+" ");
		        
	}
	
	protected void setTipoMossa (String chiave, Casella cd) {
		this.mosse.put(chiave, cd);
	}

	protected HashMap<String, Casella> printmap() {
		 return this.mosse;
	}
	
	protected Casella mosseget(String chiave) {
		return this.mosse.get(chiave);
	}
	
	protected void clearmosse() {
		this.mosse.clear();
		
	}
}
