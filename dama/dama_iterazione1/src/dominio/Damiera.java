package dominio;
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
	private Casella cv;
	private boolean trovata = false;
	private boolean lockMS = false;
	
	public static Damiera getInstance() {
		if (singleton==null)
			singleton=new Damiera();
		return singleton;
	}
	
	public void setCaselle() {
		caselle = new Casella[DIM_RIGA][DIM_COLONNA];
		for(int i=0; i<DIM_RIGA; i++) {
			for(int j=0; j<DIM_COLONNA; j++) {
				if((j+i)%2==0) {
					if(i > 4) { //5
						this.cn= new Casella(i,j,NERO);
						caselle[i][j]=cn;
					
					}
					if(i < 3) { //4
						this.cb= new Casella(i,j,BIANCO);
						caselle[i][j]=cb;
					}
					
				}
				else {
				
					this.cv= new Casella(i,j,CASELLA_VUOTA);
					caselle[i][j]=cv;
				}
			}
			
		}
		for(int i=3; i<5; i++) {
			for(int j=0; j<DIM_COLONNA; j++) {
					caselle[i][j]=cv;			
			}
		}
		
	}
	
	public void printCaselle() {
		for(int i=DIM_RIGA-1; i>=0; i--) {//caselle.lenght
			for(int j=0; j<caselle[i].length; j++) {
   				System.out.print(caselle[i][j].toString());
   				
   			 if(j==DIM_COLONNA -1) 
             	System.out.println(" "+i+"\n");
   			 
			}
		}
		System.out.print("0 1 2 3 4 5 6 7\n");
	}
	
	public boolean findCasella(int riga, int colonna, char colore) {
		if(caselle[riga][colonna]!= null && caselle[riga][colonna].getSimbolo() == colore) { 
			trovata = true;
			return true;
		
	}
	else {
		trovata = false;
		return false;
	}
	}
	
	public Casella getCasella(int riga, int colonna) {
		return caselle[riga][colonna];
	}
	
	public boolean getTrovata() {
		return trovata;
	}
	
	public void setTrovata(boolean status) {
			trovata = status;
	}
	
	protected void evaluateMossa(Casella co) {
		System.out.println("evaluate mossa " + co.getRiga() + co.getColonna() + co.getSimbolo());
		slideupDx(co.getRiga(), co.getColonna(), co.getSimbolo());
		slideupSx(co.getRiga(), co.getColonna(), co.getSimbolo());
		slidedownDx(co.getRiga(), co.getColonna(), co.getSimbolo());
		slidedownSx(co.getRiga(), co.getColonna(), co.getSimbolo());
	}
	
	protected void validateMossa(Casella cd) {
		System.out.println("validate mossa" + cd.getRiga() + cd.getColonna() + cd.getSimbolo());
	}
	
	
	private void slideupDx(int riga, int colonna, char simbolo) {
		if(simbolo == 'b' || simbolo == 'B' || simbolo == 'N') {
			if(riga+1<=7 && colonna+1<=7) {
				Casella cud = getCasella(riga+1, colonna+1);
				if(cud.getSimbolo() == '.' && lockMS == false) {
					settaTipoMossa(cud, "mossa sempliceDSX");
				}
				if(((simbolo == 'B'|| simbolo == 'b') && (cud.getSimbolo() == 'n' || cud.getSimbolo() == 'N') && cud.getRiga()+1 <= 7 && cud.getColonna()+1<=7) || (simbolo == 'N' && (cud.getSimbolo() == 'b' || cud.getSimbolo() == 'B') && cud.getRiga()+1 <= 7 && cud.getColonna()+1 <=7)){
					Casella cud1 = getCasella(cud.getRiga()+1,cud.getColonna()+1);
					if(cud1.getSimbolo() == '.') {
						settaTipoMossa(cud1, "mossa con presaDDX");
					}
				}
			}
		}
		
	}
	
	private void slideupSx(int riga, int colonna, char simbolo) {
		if(simbolo == 'b' || simbolo == 'B' || simbolo == 'N') {
			if(riga+1<=7 && colonna-1>=0) {
				Casella cus = getCasella(riga+1, colonna-1);
				if(cus.getSimbolo() == '.' && lockMS == false) {
					settaTipoMossa(cus, "mossa sempliceUSX");
				}
				if(((simbolo == 'B'|| simbolo == 'b') && (cus.getSimbolo() == 'n' || cus.getSimbolo() == 'N') && cus.getRiga()+1 <= 7 && cus.getColonna()-1>=0) || (simbolo == 'N' && (cus.getSimbolo() == 'b' || cus.getSimbolo() == 'B') && cus.getRiga()+1 <= 7 && cus.getColonna()-1 >=0)){
					Casella cus1 = getCasella(cus.getRiga()+1,cus.getColonna()-1);
					if(cus1.getSimbolo() == '.') {
						settaTipoMossa(cus1, "mossa con presaUSX");
					}
				}
			}
		}
	}

	private void slidedownDx(int riga, int colonna, char simbolo) {
		if(simbolo == 'n' || simbolo == 'B' || simbolo == 'N') {
			if(riga-1>=0 && colonna+1<=7) {
				Casella cdd = getCasella(riga-1, colonna+1);
				if(cdd.getSimbolo() == '.' && lockMS == false) {
					settaTipoMossa(cdd, "mossa sempliceDDX");
				}
				if(((simbolo == 'N'|| simbolo == 'n') && (cdd.getSimbolo() == 'b' || cdd.getSimbolo() == 'B') && cdd.getRiga()-1 >= 0 && cdd.getColonna()+1<=7) || (simbolo == 'B' && (cdd.getSimbolo() == 'n' || cdd.getSimbolo() == 'N') && cdd.getRiga()-1 >= 0 && cdd.getColonna()+1 <=7)){
					Casella cdd1 = getCasella(cdd.getRiga()-1,cdd.getColonna()+1);
					if(cdd1.getSimbolo() == '.') {
						settaTipoMossa(cdd1, "mossa con presaDDX");
					}
				}
			}
		}
	}

	private void slidedownSx(int riga, int colonna, char simbolo) {
		if(simbolo == 'n' || simbolo == 'B' || simbolo == 'N') {
			if(riga-1>=0 && colonna-1>=0) {
				Casella cds = getCasella(riga-1, colonna-1);
				if(cds.getSimbolo() == '.' && lockMS == false) {
					settaTipoMossa(cds, "mossa sempliceDSX");
				}
				if(((simbolo == 'N'|| simbolo == 'n') && (cds.getSimbolo() == 'b' || cds.getSimbolo() == 'B') && cds.getRiga()-1 >= 0 && cds.getColonna()-1>=0) || (simbolo == 'B' && (cds.getSimbolo() == 'n' || cds.getSimbolo() == 'N') && cds.getRiga()-1 >= 0 && cds.getColonna()-1 >=0)){
					Casella cds1 = getCasella(cds.getRiga()-1,cds.getColonna()-1);
					if(cds1.getSimbolo() == '.') {
						settaTipoMossa(cds1, "mossa con presaDSX");
					}
				}
			}
		}
	}
	
	private void settaTipoMossa(Casella c, String chiave) {
		
	}
	
	private void setlockMS(boolean statlockMS) {
		lockMS = statlockMS;
	}
	
	public boolean getlockMS() {
		return lockMS;
	}
}
