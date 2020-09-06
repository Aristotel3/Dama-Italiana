package dominio;

import interfaccia.text.Parser;

public class Dama {
Partita p;

	public Dama(){
		
		
	}
	
	public void startPartita() {
		System.out.println("   Inserisci nome Giocatore ");	
		String username1 = Parser.getInstance().read();
		System.out.println("   Inserisci nome Giocatore : ");
		String username2 = Parser.getInstance().read();
		System.out.println();
		p=new Partita(username1,username2, false); //terzo parametro riconosce modalità test, se settato true il software entra in modalità test
		updateStorico();
	}
	
	private void updateStorico() {
		if (p.getWinner() != null && p.getLoser()!= null)
			System.out.println("vittoria_aggiornaStorico");
		else
			System.out.println("pareggio_aggiornastorico");
			
	}
	
	
}
