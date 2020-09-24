package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import dominio.Casella;
import dominio.Damiera;
import dominio.Giocatore;
//import dominio.Partita;
import dominio.Partita;


public class GiocatoreTest {
	
	
	public static Damiera d = Damiera.getInstance();
	public Giocatore g = new Giocatore("nico",d, 'b');
	
	
	
	
	@Test
	public void testcostruttoreGiocatore() throws Exception{
		String expect_user = "nico";
		int expect_ped = 12;
		assertNotNull(g);
		assertEquals(expect_user, g.getUsername());
		assertEquals(expect_ped, g.getCountPedine());
		
	}
	
	
	@Test
	public void testselectOrigine() throws Exception{
		d.setCaselle();
		d.printCaselle();
		d.co = null;
		System.out.println("Input da testare CASELLA ORIGINE: ");	
		String input = g.inputCasella(); //input da testare deve essere uguale a quello successivo
		switch(input) {
		case "pareggio":
			
			assertEquals(3, g.selectOrigine());
			assertNull((Object) d.co);
			break;
		case "ritiro":
			
			assertEquals(2, g.selectOrigine());
			assertNull((Object) d.co);
			break;
		default:
			if (input.length() == 3) {
				String [] inputo = input.split(" ");
				if(Integer.parseInt(inputo[0]) <= 7 && Integer.parseInt(inputo[0]) >= 0 && Integer.parseInt(inputo[1]) >= 0  && Integer.parseInt(inputo[1]) <= 7) {
					Casella cso = d.getCasella(Integer.parseInt(inputo[0]), Integer.parseInt(inputo[1]));
					if(cso.getSimbolo() != 'b') {
						System.out.println("Errore input, la casella deve avere simbolo 'b'.. riavvio testSelectOrigine... ");
						testselectOrigine();
						return;
					}
					assertEquals(1, g.selectOrigine());
					assertNotNull((Object) d.co);
					assertEquals(Integer.parseInt(inputo[0]), d.co.getRiga());
					assertEquals(Integer.parseInt(inputo[1]), d.co.getColonna());
					break;
				}
				else {
					System.out.println("Errore input, riavvio testSelectOrigine... ");
					testselectOrigine();
					return;
				}
			}
			else {
				System.out.println("Errore input, riavvio testSelectOrigine... ");
				testselectOrigine();
				return;
			}
		}
		
	}
	
	@Test
	public void ztestselectDestinazione() throws Exception{
		d.setCaselle();
		d.printCaselle();
		d.cd = null;
		System.out.println("Input da testare CASELLA DESTINAZIONE: ");
		String input = g.inputCasella(); //input da testare deve essere uguale a quello successivo
		if (input.length() == 3) {
			String [] inputo = input.split(" ");
			if(Integer.parseInt(inputo[0]) <= 7 && Integer.parseInt(inputo[0]) >= 0 && Integer.parseInt(inputo[1]) >= 0  && Integer.parseInt(inputo[1]) <= 7) {
				Casella csd = d.getCasella(Integer.parseInt(inputo[0]), Integer.parseInt(inputo[1]));
				if(csd.getSimbolo() != '.') {
					System.out.println("Errore input, la casella deve avere simbolo '.'.. riavvio testSelectOrigine... ");
					ztestselectDestinazione();
					return;
				}
				assertEquals(1, g.selectDestinazione());
				assertNotNull((Object) d.cd);
				assertEquals(Integer.parseInt(inputo[0]), d.cd.getRiga());
				assertEquals(Integer.parseInt(inputo[1]), d.cd.getColonna());
				return;
			}
			else {
				System.out.println("Errore input, riavvio testSelectDestinazione... ");
				ztestselectDestinazione();
				return;
			}
		}
		else {
			System.out.println("Errore input, riavvio testSelectDestinazione... ");
			ztestselectDestinazione();
			return;
		}
		
	}
	
	@Test
	public void ztestEvaluateMossa() {
		d.setCaselle();
		int [] in = {2, 0, 0, 0, 2, 6};
		d.setCasella(3, 1, 'n');
		d.setCasella(5, 3, '.');
		d.setCasella(3, 3, 'n');
		d.setCasella(5, 1, '.');
		d.printCaselle();
		for (int i=0; i<in.length; i+=2) {
		d.co = d.getCasella(in[i], in[i+1]);
		if(i==0)
			assertEquals(2, g.evaluateMossa()); //mossa con presa rilevata
		if(i==2)
			assertEquals(0, g.evaluateMossa()); //mossa non disponibile
		if(i==4)
			assertEquals(1, g.evaluateMossa()); //mossa semplice rilevata
	}
	}
	
	@Test
	public void testValidateMossa() {
		int [] in = {2, 2, 0, 0, 2, 6};
		int [] ou = {4, 4, 1, 1, 3, 7};
		Giocatore gstop = new Giocatore ("giov", d, 'n');
		d.setCaselle();
		d.setCasella(3, 1, 'n');
		d.setCasella(5, 3, '.');
		d.setCasella(3, 3, 'n');
		d.setCasella(5, 1, '.');
		d.setCasella(6, 6, '.');
		d.setCasella(4, 0, '.');
		d.printCaselle();
		for (int i=0; i<in.length; i+=2) {
			d.setlockMS(false);
			d.co = d.getCasella(in[i], in[i+1]);
			d.cd = d.getCasella(ou[i], ou[i+1]);
			if(i==0) {
				assertEquals(2, g.evaluateMossa()); //mossa con presa rilevata
				assertEquals(2, g.validateMossa(gstop)); //mossa con presa effettuata e non piu mosse disponibili
			}
			if(i==2) {
				assertEquals(0, g.evaluateMossa()); //mossa non disponibile
				assertEquals(0, g.validateMossa(gstop)); //mossa non disponibile o fine partita dopo aver effettuato mossa con presa
			}
			if(i==4) {
				assertEquals(1, g.evaluateMossa()); //mossa semplice rilevata
				assertEquals(1, g.validateMossa(gstop)); //mossa semplice effettuata
			}
			
			assertFalse(d.getUpdate());
	    }
	}
	


	

	

}


