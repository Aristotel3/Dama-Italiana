package interfaccia.text;
import dominio.Dama;

public class ComandoNonValido implements Comando {
	
	public static final String codiceComando="-1";
	public static final String descrizioneComando="comando non valido";
  @Override
   	public String getCodiceComando() {
		return codiceComando;
	}
	
   	public String getDescrizioneComando() {
		return descrizioneComando;
	}

    public void esegui(Dama dama) {
		System.out.println("   COMANDO INESISTENTE");
	}

}
