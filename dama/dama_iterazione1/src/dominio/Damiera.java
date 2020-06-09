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
		if(status == false)
			trovata = false;
		else
			trovata=true;
	}
	
	
}
