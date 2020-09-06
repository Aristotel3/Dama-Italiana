package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dominio.Casella;
//import dominio.Giocatore;
import dominio.Damiera;

//import interfaccia.text.Parser;

class DamieraTest {
    static Damiera d = new Damiera();

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
		int [] in= {2,0, 12, 0};
		
	//	char cn='n';
		//int [] in= {5,0};
		for(int i=0; i<in.length; i+=2) {
			if(i == 0) {
				assertTrue(d.findCasella(in[i],in[i+1],cb));
				assertTrue(d.getTrovata());
				}
			if(i==2) {
				assertFalse(d.findCasella(in[i], in[i+1],cb)); //ERRORE
				assertFalse(d.getTrovata());
			}
		}
		
	}

	@Test
	void testGetCasella() {
		int [] in = {2, 0, 18, 9};
		for(int i=0; i<in.length; i+=2) {
			if(i==0)
				assertNotNull(d.getCasella(in[i], in[i+1]));
			if(i==2)
				assertNull(d.getCasella(in[i], in[i+1]));
	}
	}

	@Test
	void ztestEvaluateMossa() {
		int [] in = {2, 0, 0, 0, 2, 6};
		d.setCasella(3, 1, 'n');
		d.setCasella(3, 3, 'n');
		d.printCaselle();
		for (int i=0; i<in.length; i+=2) {
		d.co = d.getCasella(in[i], in[i+1]);
		if(i==0)
			assertEquals(2, d.evaluateMossa());
		if(i==2)
			assertEquals(0, d.evaluateMossa());
		if(i==4)
			assertEquals(1, d.evaluateMossa());
	}
	}

	@Test
	void testScrollMosse() {
		int [] in = {2, 0, 2, 6, 0, 0};
		d.setCasella(3, 1, 'n');
		d.setCasella(3, 3, 'n');
		d.printCaselle();
		for(int i=0; i<in.length; i+=2) {
		d.co = d.getCasella(in[i], in[i+1]);
		d.cd = d.getCasella(1, 1);
		d.evaluateMossa();
		if(i==0)
			assertEquals(2, d.scrollMosse());
		if(i==2)
			assertEquals(1, d.scrollMosse());
		if(i==4)
			assertEquals(0, d.scrollMosse());
		}
	}

	@Test
	void testUpdateDamiera() {
		int [] in = {2, 4, 2, 2, 2, 6, 0, 0};
		int [] on = {3, 5, 4, 0, 3, 7, 1, 1};
		d.setCasella(3, 1, 'n');
		d.setCasella(3, 3, 'n');
		d.printCaselle();
		for(int i=0; i<in.length; i+=2) {
			d.co = d.getCasella(in[i], in[i+1]);
			d.cd = d.getCasella(on[i], on[i+1]);
			d.evaluateMossa();
			if(d.co.printmap().containsKey("mossa con presaUDX") || d.co.printmap().containsKey("mossa con presaUSX") || d.co.printmap().containsKey("mossa con presaDDX") || d.co.printmap().containsKey("mossa con presaDSX")) {
				if(d.cd == d.co.mosseget("mossa con presaUDX") || d.cd == d.co.mosseget("mossa con presaUSX") || d.cd == d.co.mosseget("mossa con presaDDX") || d.cd == d.co.mosseget("mossa con presaDSX")) {
					d.scrollMosse();
					assertTrue(d.getUpdate());
				}
				else {
					d.scrollMosse();
					assertFalse(d.getUpdate());
				}
				
	}
			else {
			if(d.co.printmap().containsKey("mossa sempliceUDX") || d.co.printmap().containsKey("mossa sempliceUSX") || d.co.printmap().containsKey("mossa sempliceDDX") || d.co.printmap().containsKey("mossa sempliceDSX")) {
				if(d.cd == d.co.mosseget("mossa sempliceUDX") || d.cd == d.co.mosseget("mossa sempliceUSX") || d.cd == d.co.mosseget("mossa sempliceDDX") || d.cd == d.co.mosseget("mossa sempliceDSX")) {
					d.scrollMosse();
					assertTrue(d.getUpdate());
				}	
				else {
					d.scrollMosse();
					assertFalse(d.getUpdate());
			}
			}
		
			}
		}
}

}
