package dominio;

public class Casella {
	private int riga;
	private int colonna;
	private char simbolo;
	
	public Casella(int riga, int colonna, char simbolo){
	this.riga=riga;
	this.colonna=colonna;
	this.simbolo=simbolo;
	}



	public int getRiga() {
		return riga;
	}

	public void setRiga(int riga) {
		this.riga = riga;
	}

	public int getColonna() {
		return colonna;
	}

	public void setColonna(char colonna) {
		this.colonna = colonna;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	
	public String toString(){
		  return(""+simbolo+" ");
		        
	}


}
