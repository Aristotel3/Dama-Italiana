package interfaccia.text;

import dominio.Dama;

public class ComandoVisualizzaStorico implements Comando{
	public static final String codiceComando="2";
	public static final String descrizioneComando="Visualizza storico";
	
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui(Dama dama) {
		dama.viewStorico();
		
	}

}