package dominio;

import interfaccia.text.Parser;

public class Dama {



	public Dama(){
		
		
	}
	
	public void startPartita() {
		System.out.println("   Inserisci nome Giocatore ");	
		String username1 = Parser.getInstance().read();
		System.out.println("   Inserisci nome Giocatore : ");
		String username2 = Parser.getInstance().read();
		System.out.println();
		Partita.getInstance(username1,username2, false); //terzo parametro riconosce modalità test, se settato true il sw entra in modalità test
		Partita.deleteInstance();
	}
	
	
	
}
