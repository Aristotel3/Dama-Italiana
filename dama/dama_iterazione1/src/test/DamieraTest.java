package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dominio.Casella;
//import dominio.Giocatore;
import dominio.Damiera;
import dominio.Giocatore;

//import interfaccia.text.Parser;

class DamieraTest {
    static Damiera d = Damiera.getInstance();
    public Giocatore g = new Giocatore("nico",d, 'b');

	@BeforeAll
	static void setup() throws Exception{
		d.setCaselle();
	}
	
	@Test
	void testSetCaselle() {
		for(int i=0;i<8;i++) {
			assertEquals(8,d.getRigaCaselle(i).length);
			for(int j=0;j<8;j++) {
				assertNotNull(d.getCasella(i, j));
			}
			
		}
	}

	@Test
	void testFindCasella() {
		char cb='b';
		char cn= 'n';
		char cp='.';
		int [] in= {0, 0, 7, 0, 7, 7, 0, 7, 42, 1, 3, 3};
	
		for(int i=0; i<in.length; i+=2) {
			switch(i) {
			case 0:
				assertTrue(d.findCasella(in[i],in[i+1],cb));
				assertTrue(d.getTrovata());
				break;
			case 2:
				assertTrue(d.findCasella(in[i],in[i+1],cp));
				assertTrue(d.getTrovata());
				break;
			case 4:
				assertTrue(d.findCasella(in[i],in[i+1],cn));
				assertTrue(d.getTrovata());
				break;
			case 6:
				assertTrue(d.findCasella(in[i], in[i+1],cp)); 
				assertTrue(d.getTrovata());
				break;
			case 8:
				assertFalse(d.findCasella(in[i],in[i+1],cb)); //ERRORE
				assertFalse(d.getTrovata());
				break;
			case 10:
				assertTrue(d.findCasella(in[i], in[i+1],cp)); 
				assertTrue(d.getTrovata());
				break;
			}
			
		}
		
	}

	@Test
	void testGetCasella() {
		int [] in= {0, 0, 7, 0, 7, 7, 0, 7, 42, 1, 3, 3};
		
		for(int i=0; i<in.length; i+=2) {
			switch(i) {
			case 0:
				assertNotNull(d.getCasella(in[i],in[i+1]));
				break;
			case 2:
				assertNotNull(d.getCasella(in[i],in[i+1]));
				break;
			case 4:
				assertNotNull(d.getCasella(in[i],in[i+1]));
				break;
			case 6:
				assertNotNull(d.getCasella(in[i], in[i+1])); 
				break;
			case 8:
				assertNull(d.getCasella(in[i],in[i+1])); //ERRORE
				break;
			case 10:
				assertNotNull(d.getCasella(in[i], in[i+1])); 
				break;
			}
			
		}
	}



	@Test
	void testScrollMosse() {
		int [] in = {2, 4, 2, 0, 2, 6, 0, 0}; //caselle origine
		int [] out = {3, 5, 4, 2, 3, 7, 0, 0}; //caselle destinazione
		d.setCasella(3, 1, 'n');
		d.setCasella(5, 3, '.');
		d.setCasella(3, 3, 'n');
		d.setCasella(5, 1, '.');
		d.printCaselle();
		for(int i=0; i<in.length; i+=2) {
		d.setlockMS(false);
		d.setUpdate(false);
		d.co = d.getCasella(in[i], in[i+1]);
		d.cd = d.getCasella(out[i], out[i+1]);
		g.evaluateMossa(); //inserisco per ogni casella d'origine le possibili destinazioni che vengono salvate nelle mappe
		if(i==0) {
			assertEquals(2, d.scrollMosse()); //caso mossa con presa ma viene selezionata una mossa semplice
			assertEquals(false, d.getUpdate()); //non viene aggiornata la damiera perchè DEVE essere effettuata una mossa con presa
		}
		if(i==2) {
			assertEquals(2, d.scrollMosse()); //caso mossa con presa
			assertEquals(true, d.getUpdate()); //corretto aggiornamento di damiera
		}
		if(i==4) {
			assertEquals(1, d.scrollMosse()); //caso mossa semplice
			assertEquals(true, d.getUpdate());//corretto aggiornamento di damiera
		}
		if(i == 6) {
			assertEquals(0, d.scrollMosse()); //caso mosse non disponibile
			assertEquals(false, d.getUpdate()); //nessun aggiornamento di damiera
		}
	
		}
	}

	@Test
	void testUpdateDamiera() {
		int [] in = {2, 4, 2, 2, 2, 6, 0, 0}; //caselle origine
		int [] on = {3, 5, 4, 0, 3, 7, 1, 1}; //caselle destinazione
		d.setCasella(3, 1, 'n');
		d.setCasella(5, 3, '.');
		d.setCasella(3, 3, 'n');
		d.setCasella(5, 1, '.');
		d.printCaselle();
		Casella cm = null;
		for(int i=0; i<in.length; i+=2) {
			d.setUpdate(false);
			d.co = d.getCasella(in[i], in[i+1]);
			d.cd = d.getCasella(on[i], on[i+1]);
			if (i==2)
				cm = d.getCasella(3, 1);
			assertEquals(1,d.updateDamiera(cm));
			assertEquals(true, d.getUpdate());
			cm = null;
		}
}

}
