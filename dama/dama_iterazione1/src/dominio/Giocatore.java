package dominio;

import interfaccia.text.Parser;

public class Giocatore {
private String username;
private int countPedine;
private int numRand;
private Boolean turno;
private char color;
private int numGiocatore=0;
private Damiera dam;

public Giocatore(String name,Damiera d, char color) {
	this.dam=d;
	this.setUsername(name);
	this.setCountPedine(12);
	this.color = color;
}

public Giocatore(String name,Damiera d) {
	this.dam=d;
	this.setUsername(name);
	this.setCountPedine(12);
}

public String getUsername() {
	return this.username;
}

public void setUsername(String username) {
	this.username = username;
}

public int getCountPedine() {
	return this.countPedine;
}

public void setCountPedine(int countPedine) {
	this.countPedine = countPedine;
}

public int getNumRand() {
	return this.numRand;
}

public void setNumRand(int num) {
	this.numRand = num;
}

public Damiera getDamiera() {
	return this.dam;
}

public Boolean getTurno() {
	return this.turno;
}

public void setTurno(Boolean turno) {
	this.turno = turno;
}

public char getColor() {
	return this.color;
}

public void setColor(char color) {
	this.color = color;
}

public int getnumGiocatore() {
	return this.numGiocatore;
}

public void setnumGiocatore(int numero) {
	this.numGiocatore=numero;
}

public String toString(){
   return("\n"+ "Giocatore "+ this.numGiocatore +": "+ this.username +"\n"+ "N° sorteggio:"+numRand +"\nPedine assegnate: "+ color +"\nN° pedine: "+countPedine+"\n");
        
}

public int turno(Giocatore gstop) {
	getDamiera().setTrovata(false);
	switch(selectOrigine()) {
	case 2: //ritiro
		return 4;
	default:
		break;
	}
	selectDestinazione();
	return validateMossa(gstop);
}


public String inputCasella() {
	return Parser.getInstance().read();
}


public int selectOrigine() {
	System.out.println("\n"+getUsername() + " con Pedine: ["+getColor()+"]\n"+"Numero pedine " + getCountPedine()+"\nseleziona la casella di origine: NUMERO_RIGA NUMERO_COLONNA");
	String oread = inputCasella();
	if((!oread.equals("ritiro"))) {
		String [] inputo = oread.split(" ");
		if (oread.length() <= 3) {	
			try {
			if(Integer.parseInt(inputo[0]) <= 7 && Integer.parseInt(inputo[0]) >= 0 && Integer.parseInt(inputo[1]) >= 0  && Integer.parseInt(inputo[1]) <= 7) {
				getDamiera().findCasella(Integer.parseInt(inputo[0]), Integer.parseInt(inputo[1]), getColor());
				if(getDamiera().getTrovata() == true) { 
					getDamiera().co = getDamiera().getCasella(Integer.parseInt(inputo[0]), Integer.parseInt(inputo[1]));
					if(evaluateMossa()==0) {
					 System.out.println("Non hai mosse disponibili, riprova!");
					 return selectOrigine();
				    }
					else return 1;
				}
				else {
					System.out.println("casella non valida, riprova!");
					return selectOrigine();
				}
			}
			else {
				getDamiera().setTrovata(false);
				System.out.println("Input non valido, il primo carattere è un numero(0-7), il secondo è uno spazio, il terzo è un numero(0-7)");
				return selectOrigine();
			}		}
			catch(Exception e) {
				getDamiera().setTrovata(false);
				return selectOrigine();
			}
		}else {
			getDamiera().setTrovata(false);
			System.out.println("Inserire solo 3 caratteri, 1: numero(0-7), 2: spazio, 3: numero(0-7), riprova!");
			return selectOrigine();
		}	
	}
	else {
	
	return 2;
	}
	
	
		
}



public int selectDestinazione() {
	System.out.println(getUsername() + " seleziona la casella di destinazione: NUMERO_RIGA NUMERO_COLONNA");
	String dread = inputCasella();
	String [] inputd = dread.split(" ");
	if (dread.length() <= 3) {
		try {
		if(Integer.parseInt(inputd[0]) <= 7 && Integer.parseInt(inputd[0]) >= 0 && Integer.parseInt(inputd[1]) >= 0  && Integer.parseInt(inputd[1]) <= 7) {
			getDamiera().findCasella(Integer.parseInt(inputd[0]), Integer.parseInt(inputd[1]), '.');
			if(getDamiera().getTrovata() == true) { 
				getDamiera().cd = getDamiera().getCasella(Integer.parseInt(inputd[0]), Integer.parseInt(inputd[1]));
			//	validateMossa();
				return 1;
			}
			else {
				System.out.println("casella non valida, riprova!");
				return selectDestinazione();
				
		}
			}
		else { 
			getDamiera().setTrovata(false);
			System.out.println("Input non valido, il primo carattere è un numero(0-7), il secondo è uno spazio, il terzo è un numero(0-7)");
			return selectDestinazione();
				
		}	}
		catch(Exception e) {
			getDamiera().setTrovata(false);
			return selectDestinazione();
				
		}
}else {
	getDamiera().setTrovata(false);
	System.out.println("Inserire solo 3 caratteri, 1: numero(0-7), 2: spazio, 3: numero(0-7), riprova!");
	return selectDestinazione();
}

	
		
}

public int evaluateMossa() {
	System.out.println("evaluate mossa " + getDamiera().co.getRiga() + getDamiera().co.getColonna() + getDamiera().co.getSimbolo());
	int a = getDamiera().slideupDx();
	int b = getDamiera().slideupSx();
	int c = getDamiera().slidedownDx();
	int d = getDamiera().slidedownSx();
	if(a == 0 && b == 0 && c == 0 && d == 0)
		return 0;
	else {
	if(a == 2 || b == 2 || c == 2 || d == 2)
		return 2;
	if(a == 1 || b == 1 || c == 1 || d == 1)
		return 1;
	
	}
	System.out.println("Error 45, sarebbe impossibile arrivi fino a qui la funzione?!");
	return 45;
}

public int validateMossa(Giocatore gstop) { 
	int mosse = getDamiera().scrollMosse();
	if(mosse == 2) {
		if(getDamiera().getUpdate()==false) {
				selectDestinazione();
				return validateMossa(gstop);
			}
		
		else {
			getDamiera().printCaselle();
			gstop.setCountPedine(gstop.getCountPedine()-1);
			if(gstop.getCountPedine() == 0)
				return 3;
			if(evaluateMossa() == 2) 
				return validateMossa(gstop);
			getDamiera().setUpdate(false);  ///aggiunta ora
			return 2; // se ha mangiato e non ha piu mosse
		}
	}
	else {
		if(mosse == 1) {
			if(getDamiera().getUpdate() == true) {
				getDamiera().setUpdate(false);
				return 1; //fatta mossa semplice e ha finito
			}
			else {
					selectDestinazione();
					return validateMossa(gstop);
			}
		}
		if(mosse == 0) {
			getDamiera().setUpdate(false);
			return 0;
		}
		
	}
				
		return -1;
}




}
