package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;

import dominio.Giocatore;
import dominio.Damiera;


class GiocatoreTest {
	static Giocatore g = new Giocatore("nico");
	static Damiera d = new Damiera();
	
	@BeforeAll
	static void setup() throws Exception{
		
		d.setCaselle();
		g.setColor('b');
	}
	

	@Test
	void testcostruttoreGiocatore() throws Exception{
		String expect_user = "nico";
		int expect_ped = 12;
		assertNotNull(g);
		assertEquals(expect_user, g.getUsername());
		assertEquals(expect_ped, g.getCountPedine());
		
	}
	/*
	@Test
	void testDoMove() {
		g.doMove(d);
		int expected_riga_origine = 2;
		int expected_riga_destinazione = 3;
		int expected_colonna_origine = 2;
		int expected_colonna_destinazione = 3;
		assertEquals(expected_riga_origine, d.co.getRiga());
		assertEquals(expected_colonna_origine, d.co.getColonna());
		assertEquals(expected_riga_destinazione, d.cd.getRiga());
		assertEquals(expected_colonna_destinazione, d.cd.getColonna());
	}
	*/
	
	@Test
	void testselectOrigine() throws Exception{
		System.out.println("Input da testare: ");	
		String input = g.inputCasella(); //input da testare (non si può sbagliare)
		switch(input) {
		case "pareggio":
			assertEquals(3, g.selectOrigine(d));
			assertNull((Object) d.co);
			break;
		case "ritiro":
			assertEquals(2, g.selectOrigine(d));
			assertNull((Object) d.co);
			break;
		default:
			assertEquals(1, g.selectOrigine(d));
			assertNotNull((Object) d.co);
			String [] inputO = input.split(" ");
			assertEquals(Integer.parseInt(inputO[0]), d.co.getRiga());
			assertEquals(Integer.parseInt(inputO[1]), d.co.getColonna());		
			break;
		}
		
	}
	
	@Test
	void ztestselectDestinazione() throws Exception{
		System.out.println("Input da testare: ");
		String input = g.inputCasella(); //input da testare
		assertEquals(1, g.selectDestinazione(d));
		assertNotNull((Object) d.cd);
		String [] inputO = input.split(" ");
		assertEquals(Integer.parseInt(inputO[0]), d.cd.getRiga());
		assertEquals(Integer.parseInt(inputO[1]), d.cd.getColonna());
	}

	

	

}
