package dominio;
import java.util.Collection;

import dominio.Casella;

public class Damiera {
	private static final int DIM_RIGA = 8;
	private static final int DIM_COLONNA = 8;
	private static final char BIANCO = 'b';
	private static final char NERO = 'n';
	private static final char CASELLA_VUOTA = '.';
	private static Damiera singleton;
	private Casella [][] caselle;
	private Casella cn;
	private Casella cb;
	private Casella cv1;
	private Casella cv2;
	private boolean trovata = false;
	private boolean lockMS = false;
	public Casella co;
	public Casella cd;
	private boolean update = false;
	
	
	public static Damiera getInstance() {
		if (singleton==null)
			singleton=new Damiera();
		return singleton;
	}
		
	public void setCaselle() {	
	 this.caselle = new Casella[DIM_RIGA][DIM_COLONNA];
		for(int i=0; i<DIM_RIGA; i++) {	
			for(int j=0; j<DIM_COLONNA; j++) {
				if((j+i)%2==0) {
					if(i > 4) { //5
						this.cn= new Casella(i,j,NERO);
						this.caselle[i][j]=this.cn;
					
					}
					if(i < 3) { //4
						this.cb= new Casella(i,j,BIANCO);
						this.caselle[i][j]=this.cb;
					}
					if (i==3 || i==4) {
						this.cv1= new Casella(i,j,CASELLA_VUOTA);
						caselle[i][j]=this.cv1;
					}
				}
				else {
				
					this.cv2 = new Casella(i,j,CASELLA_VUOTA);
					this.caselle[i][j]=this.cv2;
				}
			
			
			}

		}
	}
	
	public Casella[] getRigaCaselle(int riga) {	//Per il test
			return caselle[riga];
		
	}
	
	public void printCaselle() {
		for(int i=DIM_RIGA-1; i>=0; i--) {//caselle.lenght
			for(int j=0; j<caselle[i].length; j++) {
				System.out.print(this.caselle[i][j].toString());
   				
   			 if(j==DIM_COLONNA -1) 
             	System.out.println(" "+i+"\n");
   			 
			}
		}
		System.out.print("0 1 2 3 4 5 6 7\n");
	}
	
	public void setLockMS(boolean status) {
		this.lockMS = status;
	}
	
	public boolean findCasella(int riga, int colonna, char colore) {
				if((riga>=0 && riga <=7 && colonna >=0 && colonna<=7)&&(this.caselle[riga][colonna].getSimbolo() == colore || this.caselle[riga][colonna].getSimbolo() == Character.toUpperCase(colore)))   { 
					setTrovata(true);
					return true;		
				}		
			
				else {
					setTrovata(false);
					return false;
				}
	}
	
	public Casella getCasella(int riga, int colonna) {
			if(riga>=0 && riga<=7 && colonna>=0 && colonna<=7)
				return this.caselle[riga][colonna];
			else
				return null;
		
	}
	
	
	public boolean getTrovata() {
		return this.trovata;
	}
	
	public void setCasella(int riga, int colonna, char simbolo) {
		this.caselle[riga][colonna].setSimbolo(simbolo);
	}
	
	public void setTrovata(boolean status) {
		this.trovata = status;
	}
	
	public int evaluateMossa() {
		System.out.println("evaluate mossa " + this.co.getRiga() + this.co.getColonna() + this.co.getSimbolo());
		int a = slideupDx();
		int b = slideupSx();
		int c = slidedownDx();
		int d = slidedownSx();
		if(a == 0 && b == 0 && c == 0 && d == 0)
			return 0;
		else {
		if(a == 2 || b == 2 || c == 2 || d == 2)
			return 2;
		if(a == 1 || b == 1 || c == 1 || d == 1)
			return 1;
		
		}
		System.out.println("Error 45, sarebbe impossibile arrivi fino a qui la funzione?!");
		return 45;
	}
	
	
	
	
	private int slideupDx() {
		if(this.co.getSimbolo() == 'b' || this.co.getSimbolo() == 'B' || this.co.getSimbolo() == 'N') {
			if(this.co.getRiga()+1<=7 && this.co.getColonna()+1<=7) {
				Casella cud = getCasella(this.co.getRiga()+1, this.co.getColonna()+1);
				if(cud.getSimbolo() == '.' && this.lockMS == false) {
					this.co.setTipoMossa("mossa sempliceUDX", cud);
					return 1;
				}
				if(((this.co.getSimbolo() == 'B'|| this.co.getSimbolo() == 'b') && (cud.getSimbolo() == 'n' || cud.getSimbolo() == 'N') && cud.getRiga()+1 <= 7 && cud.getColonna()+1<=7) || (this.co.getSimbolo() == 'N' && (cud.getSimbolo() == 'b' || cud.getSimbolo() == 'B') && cud.getRiga()+1 <= 7 && cud.getColonna()+1 <=7)){
					Casella cud1 = getCasella(cud.getRiga()+1,cud.getColonna()+1);
					if(cud1.getSimbolo() == '.') {
						this.co.setTipoMossa("mossa con presaUDX", cud1);
						return 2;
					}
				}
			}
		}
		return 0;
		
	}
	
	private int slideupSx() {
		if(this.co.getSimbolo() == 'b' || this.co.getSimbolo() == 'B' || this.co.getSimbolo() == 'N') {
			if(this.co.getRiga()+1<=7 && this.co.getColonna()-1>=0) {
				Casella cus = getCasella(this.co.getRiga()+1, this.co.getColonna()-1);
				if(cus.getSimbolo() == '.' && this.lockMS == false) {
					this.co.setTipoMossa("mossa sempliceUSX", cus);
					return 1;
				}
				if(((this.co.getSimbolo() == 'B'|| this.co.getSimbolo() == 'b') && (cus.getSimbolo() == 'n' || cus.getSimbolo() == 'N') && cus.getRiga()+1 <= 7 && cus.getColonna()-1>=0) || (this.co.getSimbolo() == 'N' && (cus.getSimbolo() == 'b' || cus.getSimbolo() == 'B') && cus.getRiga()+1 <= 7 && cus.getColonna()-1 >=0)){
					Casella cus1 = getCasella(cus.getRiga()+1,cus.getColonna()-1);
					if(cus1.getSimbolo() == '.') {
						this.co.setTipoMossa("mossa con presaUSX", cus1);
						return 2;
					}
				}
			}
		}
		return 0;
	}

	private int slidedownDx() {
		if(this.co.getSimbolo() == 'n' || this.co.getSimbolo() == 'B' || this.co.getSimbolo() == 'N') {
			if(this.co.getRiga()-1>=0 && this.co.getColonna()+1<=7) {
				Casella cdd = getCasella(this.co.getRiga()-1, this.co.getColonna()+1);
				if(cdd.getSimbolo() == '.' && this.lockMS == false) {
					this.co.setTipoMossa("mossa sempliceDDX", cdd);
					return 1;
				}
				if(((this.co.getSimbolo() == 'N'|| this.co.getSimbolo() == 'n') && (cdd.getSimbolo() == 'b' || cdd.getSimbolo() == 'B') && cdd.getRiga()-1 >= 0 && cdd.getColonna()+1<=7) || (this.co.getSimbolo() == 'B' && (cdd.getSimbolo() == 'n' || cdd.getSimbolo() == 'N') && cdd.getRiga()-1 >= 0 && cdd.getColonna()+1 <=7)){
					Casella cdd1 = getCasella(cdd.getRiga()-1,cdd.getColonna()+1);
					if(cdd1.getSimbolo() == '.') {
						this.co.setTipoMossa("mossa con presaDDX", cdd1);
						return 2;
					}
				}
			}
		}
		return 0;
	}

	private int slidedownSx() {
		if(this.co.getSimbolo() == 'n' || this.co.getSimbolo() == 'B' || this.co.getSimbolo() == 'N') {
			if(this.co.getRiga()-1>=0 && this.co.getColonna()-1>=0) {
				Casella cds = getCasella(this.co.getRiga()-1, this.co.getColonna()-1);
				if(cds.getSimbolo() == '.' && this.lockMS == false) {
					this.co.setTipoMossa("mossa sempliceDSX", cds);
					return 1;
				}
				if(((this.co.getSimbolo() == 'N'|| this.co.getSimbolo() == 'n') && (cds.getSimbolo() == 'b' || cds.getSimbolo() == 'B') && cds.getRiga()-1 >= 0 && cds.getColonna()-1>=0) || (this.co.getSimbolo() == 'B' && (cds.getSimbolo() == 'n' || cds.getSimbolo() == 'N') && cds.getRiga()-1 >= 0 && cds.getColonna()-1 >=0)){
					Casella cds1 = getCasella(cds.getRiga()-1,cds.getColonna()-1);
					if(cds1.getSimbolo() == '.') {
						this.co.setTipoMossa("mossa con presaDSX", cds1);
						return 2;
					}
				}
			}
		}
		return 0;
	}
	
	
	
	protected void setlockMS(boolean lockMS) {
		this.lockMS = lockMS;
	}
	
	public boolean getlockMS() {
		return lockMS;
	}
	
	public int scrollMosse() {
		if(this.co.printmap().containsKey("mossa con presaUDX") || this.co.printmap().containsKey("mossa con presaUSX") || this.co.printmap().containsKey("mossa con presaDDX") || this.co.printmap().containsKey("mossa con presaDSX")) {
			setlockMS(true);
			setUpdate(false);
			if(this.cd == this.co.mosseget("mossa con presaUDX")) {
				Casella cud = getCasella(this.co.getRiga()+1, this.co.getColonna()+1);
				this.co.clearmosse();
				updateDamiera(cud);
			//	evaluateMossa();
			}
			if(this.cd == this.co.mosseget("mossa con presaUSX")) {
				Casella cus = getCasella(this.co.getRiga()+1, this.co.getColonna()-1);
				this.co.clearmosse();
				updateDamiera(cus);
			//	setlockMS(true);
			//	evaluateMossa();
			}
			if(this.cd == this.co.mosseget("mossa con presaDDX")) {
				Casella cdd = getCasella(this.co.getRiga()-1, this.co.getColonna()+1);
				this.co.clearmosse();
				updateDamiera(cdd);
			//	setlockMS(true);
			//	evaluateMossa();
			}
			if(this.cd == this.co.mosseget("mossa con presaDSX")) {
				Casella cds = getCasella(co.getRiga()-1, this.co.getColonna()-1);
				this.co.clearmosse();
				updateDamiera(cds);
			//	setlockMS(true);
			//	evaluateMossa();
			}
			return 2;
	
		}
		else {
			if(this.co.printmap().containsKey("mossa sempliceUDX") || this.co.printmap().containsKey("mossa sempliceUSX") || this.co.printmap().containsKey("mossa sempliceDDX") || this.co.printmap().containsKey("mossa sempliceDSX")) {
				setUpdate(false);
				if(this.cd == this.co.mosseget("mossa sempliceUDX") || this.cd == this.co.mosseget("mossa sempliceUSX") || this.cd == this.co.mosseget("mossa sempliceDDX") || this.cd == this.co.mosseget("mossa sempliceDSX")){
					this.co.clearmosse();
					updateDamiera(null);
			}
				return 1;
		}
		}
		return 0;
	
	}
	
	public boolean getUpdate() {
		return this.update;
	}
	
	protected void setUpdate(boolean update) {
		this.update = update;
	}
	
	
	private void become_dama(char simbolo) {
		setCasella(this.cd.getRiga(), this.cd.getColonna(), simbolo);
	}
	
	public void updateDamiera(Casella cm) {
		if(this.cd.getRiga() == 7 || this.cd.getRiga() == 0) {
			if(this.cd.getRiga() == 7 && this.co.getSimbolo() == 'b')
				become_dama('B');
			if(this.cd.getRiga() == 0 && this.co.getSimbolo() == 'n')
				become_dama('N');
			if(this.co.getSimbolo() == 'B' || this.co.getSimbolo() == 'N')
				setCasella(this.cd.getRiga(), this.cd.getColonna(), this.co.getSimbolo());
		}
		else
			setCasella(this.cd.getRiga(), this.cd.getColonna(), this.co.getSimbolo());
		setCasella(this.co.getRiga(), this.co.getColonna(), '.');
		if(cm!=null) {
			setCasella(cm.getRiga(), cm.getColonna(), '.');
			this.co = getCasella(this.cd.getRiga(), this.cd.getColonna());
			//this.cd = null;
		}
		
	//	printCaselle();
		setUpdate(true);
	}
}
