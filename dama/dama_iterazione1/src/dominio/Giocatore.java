package dominio;

import interfaccia.text.Parser;

public class Giocatore {
private String username;
private int countPedine;
private int numRand;
private Boolean turno;
private char color;
private int numGiocatore=0;


public Giocatore(String name) {
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

public int doMove(Damiera dam) {
	dam.setTrovata(false);
	//int postorig = selectOrigine(dam);
	//System.out.println(postorig);
	switch(selectOrigine(dam)) {
	case 2:
		return 0;
	case 3:
		return 2;
	default:
		break;
	}
//	if(postorig == 2)
//		return 0;
//	if(postorig == 3)
//		return 2;
	selectDestinazione(dam);
	return 1;
}


public String inputCasella() {
	return Parser.getInstance().read();
}


public int selectOrigine(Damiera dam) {
	System.out.println("\n"+getUsername() + " con Pedine: ["+getColor()+"]\n"+"Numero pedine " + getCountPedine()+"\nseleziona la casella di origine: NUMERO_RIGA NUMERO_COLONNA");
	String oread = inputCasella();
	if((!oread.equals("ritiro")) && (!oread.equals("pareggio"))) {
		String [] inputo = oread.split(" ");
		if (oread.length() <= 3) {	
			try {
			if(Integer.parseInt(inputo[0]) <= 7 && Integer.parseInt(inputo[0]) >= 0 && Integer.parseInt(inputo[1]) >= 0  && Integer.parseInt(inputo[1]) <= 7) {
				dam.findCasella(Integer.parseInt(inputo[0]), Integer.parseInt(inputo[1]), getColor());
				if(dam.getTrovata() == true) { 
					dam.co = dam.getCasella(Integer.parseInt(inputo[0]), Integer.parseInt(inputo[1]));
					if(dam.evaluateMossa()==0) {
					 System.out.println("Non hai mosse disponibili, riprova!");
					 return selectOrigine(dam);
				    }
					else return 1;
				}
				else {
					System.out.println("casella non valida, riprova!");
					return selectOrigine(dam);
				}
			}
			else {
				dam.setTrovata(false);
				System.out.println("Input non valido, il primo carattere è un numero(0-7), il secondo è uno spazio, il terzo è un numero(0-7)");
				return selectOrigine(dam);
			}		}
			catch(Exception e) {
				dam.setTrovata(false);
				return selectOrigine(dam);
			}
		}else {
			dam.setTrovata(false);
			System.out.println("Inserire solo 3 caratteri, 1: numero(0-7), 2: spazio, 3: numero(0-7), riprova!");
			return selectOrigine(dam);
		}	
	}
	else {
	//dam.setTrovata(true);
	if(oread.equals("pareggio")) return 3;
	
	return 2;
	}
	//return null;
	
		
}



public int selectDestinazione(Damiera dam) {
	System.out.println(getUsername() + " seleziona la casella di destinazione: NUMERO_RIGA NUMERO_COLONNA");
	String dread = inputCasella();
	String [] inputd = dread.split(" ");
	if (dread.length() <= 3) {
		try {
		if(Integer.parseInt(inputd[0]) <= 7 && Integer.parseInt(inputd[0]) >= 0 && Integer.parseInt(inputd[1]) >= 0  && Integer.parseInt(inputd[1]) <= 7) {
			dam.findCasella(Integer.parseInt(inputd[0]), Integer.parseInt(inputd[1]), '.');
			if(dam.getTrovata() == true) { 
				dam.cd = dam.getCasella(Integer.parseInt(inputd[0]), Integer.parseInt(inputd[1]));
			//	validateMossa();
				return 1;
			}
			else {
				System.out.println("casella non valida, riprova!");
				return selectDestinazione(dam);
				
		}
			}
		else { 
			dam.setTrovata(false);
			System.out.println("Input non valido, il primo carattere è un numero(0-7), il secondo è uno spazio, il terzo è un numero(0-7)");
			return selectDestinazione(dam);
				
		}	}
		catch(Exception e) {
			dam.setTrovata(false);
			return selectDestinazione(dam);
				
		}
}else {
	dam.setTrovata(false);
	System.out.println("Inserire solo 3 caratteri, 1: numero(0-7), 2: spazio, 3: numero(0-7), riprova!");
	return selectDestinazione(dam);
}

	
		
}




}
