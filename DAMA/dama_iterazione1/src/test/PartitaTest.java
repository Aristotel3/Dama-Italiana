package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import dominio.Giocatore;
import dominio.Partita;

class PartitaTest {
	
	private Partita p = new Partita("nico", "giov", true);
	

	

	@Test
	void testInsertGiocatore(){
		assertNotNull(p.getGiocatore(0));
		assertNotNull(p.getGiocatore(1));
		assertTrue(p.insertGiocatore(p.getGiocatore(0), p.getGiocatore(1)));
		
	}

	@Test
	void test7SwitchGiocatore() {
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
	void testSorteggio() {
		int [] in = {5, 6, 1, 10, 5, 6, 5, 6, 5, 6, 5, 6, 4, 5, 4, 5, 1, 2, 3, 4, 3, 4, 3, 4};
		for(int i=0; i<in.length; i+=2) {
		p.getGiocatore(0).setnumGiocatore(0);
		p.getGiocatore(1).setnumGiocatore(0);
		p.sorteggio(in[i], in[i+1]);
		assertNotEquals(p.getGiocatore(0).getnumGiocatore(), p.getGiocatore(1).getnumGiocatore());
	}
	}

	@Test
	void testWin() {
		p.setStart(true);
		p.win(p.getGiocatore(0), p.getGiocatore(1));
		assertFalse(p.getStart());
	}


}
