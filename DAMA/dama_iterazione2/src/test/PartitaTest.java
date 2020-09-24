package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dominio.Partita;


public class PartitaTest {
	
	private static Partita p = Partita.getInstance("nico","giov", true);
	

	

	@Test
	public void testInsertGiocatore(){
		assertNotNull(p.getGiocatore(0));
		assertNotNull(p.getGiocatore(1));
		assertTrue(p.insertGiocatore(p.getGiocatore(0), p.getGiocatore(1)));
		
	}

	@Test
	public void test7SwitchGiocatore() {
		assertNotNull(p.getGiocatore(0));
		assertNotNull(p.getGiocatore(1));
		p.switchGiocatore(p.getGiocatore(0), p.getGiocatore(1));
		assertTrue(p.getGiocatore(0).getTurno());
		assertFalse(p.getGiocatore(1).getTurno());
		p.switchGiocatore(p.getGiocatore(1), p.getGiocatore(0));
		assertTrue(p.getGiocatore(1).getTurno());
		assertFalse(p.getGiocatore(0).getTurno());
	}

	
	@Test
	public void testProposePareggio() {
		p.setStart(true);
		System.out.println("Test Pareggio, premi 1 per accettare");
		assertTrue(p.proposePareggio(p.getGiocatore(0), p.getGiocatore(1)));
		assertFalse(p.getStart());
		System.out.println("Test Pareggio, premi 2 per rifiutare");
		assertFalse(p.proposePareggio(p.getGiocatore(0), p.getGiocatore(1)));
		assertTrue(p.getStart());
	}


	

	@Test
	public void testSorteggio() {
		int [] in = {5, 6, 1, 10, 5, 6, 5, 6, 5, 6, 5, 6, 4, 5, 4, 5, 1, 2, 3, 4, 3, 4, 3, 4};
		for(int i=0; i<in.length; i+=2) {
		p.getGiocatore(0).setnumGiocatore(0);
		p.getGiocatore(1).setnumGiocatore(0);
		p.sorteggio(in[i], in[i+1]);
		assertNotEquals(p.getGiocatore(0).getnumGiocatore(), p.getGiocatore(1).getnumGiocatore());
	}
	}
    
	@Test
	public void testSconfitta_Observer() {
		p.getGiocatore(0).setTurno(true);
		p.getGiocatore(1).setTurno(false);
		p.getGiocatore(0).setStatus("win"); //vince giocatore 0
		p.getGiocatore(1).setCountPedine(0); //giocatore 1 è sconfitto
		assertEquals(p.getGiocatore(0), p.getWinner()); //confronto vincitore con il giocatore 0
		assertEquals(p.getGiocatore(1), p.getLoser()); //confronto perdente con il giocatore 1
		assertEquals(false, p.getStart());
	}
	
	@Test
	public void testWin() {
		p.setStart(true);
		p.win(p.getGiocatore(0), p.getGiocatore(1));
		assertFalse(p.getStart());
	}


}
