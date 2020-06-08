package dominio;

public class Giocatore {
private String username;
private int countPedine;
private int numRand;
private Boolean turno;
private char color;
private int numGiocatore;

public Giocatore(String name) {
	this.setUsername(name);
	this.setCountPedine(12);
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public int getCountPedine() {
	return countPedine;
}

public void setCountPedine(int countPedine) {
	this.countPedine = countPedine;
}

public int getNumRand() {
	return numRand;
}

public void setNumRand(int num) {
	this.numRand = num;
}

public Boolean getTurno() {
	return turno;
}

public void setTurno(Boolean turno) {
	this.turno = turno;
}

public char getColor() {
	return color;
}

public void setColor(char color) {
	this.color = color;
}

public int getnumGiocatore(int numero) {
	return numGiocatore;
}

public void setnumGiocatore(int numero) {
	this.numGiocatore=numero;
}

public String toString(){
   return("\n"+ "Giocatore "+ this.numGiocatore +": "+ this.username +"\n"+ "N° sorteggio:"+numRand +"\nPedine assegnate: "+ color +"\nN° pedine: "+countPedine+"\n");
        
}


}