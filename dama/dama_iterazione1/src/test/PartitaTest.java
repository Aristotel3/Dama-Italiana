package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dominio.Giocatore;
import dominio.Partita;

class PartitaTest {
	
	static Partita p = new Partita("nico", "giov", true);
	
//	@BeforeAll
//	void Partita() {
		
//	}
	

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
	void testProposePareggio() {
		p.setStart(true);
		System.out.println("Test Pareggio, premi 1 per accettare");
		assertTrue(p.proposePareggio(p.getGiocatore(0), p.getGiocatore(0)));
		assertFalse(p.getStart());
		System.out.println("Test Pareggio, premi 2 per rifiutare");
		assertFalse(p.proposePareggio(p.getGiocatore(0), p.getGiocatore(0)));
		assertTrue(p.getStart());
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

	@Test
	void testValidateMossa() {
		int [] in = {2, 2, 2, 0, 2, 6};
		int [] ou = {4, 4, 4, 2, 3, 7};
		p.sorteggio(1, 10);
		Giocatore gmove;
		if(p.getGiocatore(0).getTurno() == true)
			gmove = p.getGiocatore(0);
		else
			gmove = p.getGiocatore(1);
		p.getDamiera().setCaselle();
		p.getDamiera().setCasella(3, 1, 'n');
		p.getDamiera().setCasella(3, 3, 'n');
		p.getDamiera().setCasella(6, 6, '.');
		p.getDamiera().printCaselle();
		for (int i=0; i<in.length; i+=2) {
			p.getDamiera().setLockMS(false);
			p.getDamiera().co = p.getDamiera().getCasella(in[i], in[i+1]);
			p.getDamiera().cd = p.getDamiera().getCasella(ou[i], ou[i+1]);
			if(i==0)
				assertEquals(2, p.getDamiera().evaluateMossa());
			if(i==2)
				assertEquals(2, p.getDamiera().evaluateMossa());
			if(i==4)
				assertEquals(1, p.getDamiera().evaluateMossa());
			p.validateMossa();
			assertFalse(p.getDamiera().getUpdate());
	}
	}
}
