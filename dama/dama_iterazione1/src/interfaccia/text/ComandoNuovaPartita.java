package interfaccia.text;


import dominio.Dama;
import interfaccia.text.Comando;

public class ComandoNuovaPartita implements Comando {
	
	public static final String codiceComando="1";
	public static final String descrizioneComando="Nuova Partita";
	
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui(Dama dama) {
    	
		dama.startPartita();
		
	}
    
}
