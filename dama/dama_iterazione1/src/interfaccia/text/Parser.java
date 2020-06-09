package interfaccia.text;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Parser {

    private ElencoComandi comandi;
	private static Parser singleton;
	
    public Parser() {
        comandi = new ElencoComandi();
    }

	public static Parser getInstance() {
		if (singleton==null)
			singleton=new Parser();
		return singleton;
	}

    public String read() {
        String inputLine = "";

        System.out.print(" > ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        }
        catch(java.io.IOException exc) {
            System.out.println ("Errore durante la lettura: " + exc.getMessage());
        }
		return inputLine;
    }
		
    public Comando getComando(int console) { //console rappresenta lo scenario di console in base l'OP
        String parola = read();
		Comando comando = null;
		
		if(comandi.comandoValido(parola,console)) {
			/* CONSOLE PRINCIPALE */
			if (console == ElencoComandi.DAMA){
				
				if (parola.equals("1"))
					comando = new ComandoNuovaPartita();
				 
				if (parola.equals("2"))
					comando = new ComandoVisualizzaStorico();
				/*
				if (parola.equals("3"))
					comando = new ComandoNuovoOrdine();
					*/
			}
			/* CONSOLE NUOVO TIPO COLAZIONE */
			/*ritorno da NuovoTipoColazioneConsole {{che è chiamato da ComandoNuovoTipoColazione}}(xkè ha stampaBenvenuto(){ElencoComandi.elencoTuttiComandi(ElencoComandi.NUOVO_TIPO_COLAZIONE)} */
			/* if (console == ElencoComandi.NUOVA_PARTITA){ 
				System.out.println("    ciao");
				
				if (parola.equals("1"))
					comando = new ComandoAggiungiComponenteColazione();
				if (parola.equals("2"))
					comando = new ComandoDefinisciPrezzo();
				if (parola.equals("3"))
					comando = new ComandoConfermaTipoColazione();
				
			}*/
			/* CONSOLE NUOVO ORDINE 
			if (console == ElencoComandi.VISUALIZZA_STORICO){ //NUOVO ORDINE
				
				if (parola.equals("1"))
					comando = new ComandoNuovaColazione();
				if (parola.equals("2"))
					comando = new ComandoDefinisciModoServizio();
				if (parola.equals("3"))
					comando = new ComandoAssociaOrdineCliente();
				if (parola.equals("4"))
					comando = new ComandoConfermaOrdine();
				
			}*/
			
			/* 
			if (console == ElencoComandi.NUOVA_COLAZIONE){

				if (parola.equals("1"))
					comando = new ComandoModificaCompColazioneOrdinata();
				if (parola.equals("2"))
					comando = new ComandoAggiungiCompColazioneOrdinata();
				if (parola.equals("3"))
					comando = new ComandoConfermaNuovaColazione();

			}*/
			/* TORNA AL MENU' PRECEDENTE O ESCI */
			if (parola.equals("0"))
				comando = new ComandoEsci();
	   } else comando = new ComandoNonValido();
		
       return comando; //ritorniamo in DamaConsole
    }
}