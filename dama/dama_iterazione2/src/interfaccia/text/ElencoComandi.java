package interfaccia.text;

public class ElencoComandi {
    
	public static final int DAMA = 0;
	public static final int NUOVA_PARTITA = 1;
	public static final int VISUALIZZA_STORICO = 2;
	
	
	/* MENU' PRINCIPALE */
    private static final String comandiValidiDamaConsole[][] = {
		
    		{ComandoNuovaPartita.codiceComando,ComandoNuovaPartita.descrizioneComando},
    		{ComandoVisualizzaStorico.codiceComando,ComandoVisualizzaStorico.descrizioneComando},
    		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
    };
	
	/* USE CASE 1 : INSERIMENTO NUOVA PARTITA */
	private static final String comandiValidiNuovaPartitaConsole[][] = { 
	
		{ComandoEsci.codiceComando, ComandoEsci.descrizioneComando}
	};
	
	
	
   public static String elencoTuttiComandi(int console){
    	int i=0;
    	StringBuffer elenco = new StringBuffer();
		String comandi[][]=getComandi(console);
		
		
    	for (i=0; i<comandi.length-1; i++) {
			elenco.append(comando(i,console));
			elenco.append("\n");
		}
		elenco.append(comando(i,console));
		return elenco.toString();
    }
	
	private static String comando(int i, int console) {
		String comandi[][]= getComandi(console);
		return " " + comandi[i][0] + ")" + comandi[i][1];
	}

	public static String[][] getComandi(int console){
		
		String comandi[][]=null;
		
		switch (console){
			case DAMA: comandi = comandiValidiDamaConsole; break;
			case NUOVA_PARTITA: comandi = comandiValidiNuovaPartitaConsole; break;
		
			
		};
		return comandi;
	}
	
    public boolean comandoValido(String stringa, int console) {
		String comandi[][]= getComandi(console);
		for(int i = 0; i < comandi.length; i++) {
            if(comandi[i][0].equals(stringa))
                return true;
        }
        return false;
    }
	
}