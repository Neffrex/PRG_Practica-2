package aplicacio;


import dades.*;
/**
 * 
 * @author jialiang Chen
 * @author Jose Luis Pueyo Viltres
 *
 */
public class LlistaEstacionsRecarrega {
	
	
	private EstacioRecarregaVE[] llista;
	private int nElem;
	/**
	 * 8 - registre per saber la distància màxima per poder considerar-se proper
	 * @param distanciaMaxEstacioPropera - distància màxima que pot estar l'estació com per definir-la com propera
	 */
	private static int distanciaMaxEstacioPropera = 30;
	
	public LlistaEstacionsRecarrega (int mida) {
		llista = new EstacioRecarregaVE[mida];
		nElem = 0;
	}
	
	/**
	 * 1 - Funció per afegir una estació al final de la llista
	 * @param estacio - la estació a afegir
	 * @return boolean - true: s'han afegit l'estació; false: no s'ha pogut afegir l'estació
	 */
	
	public boolean afegirEstacio (EstacioRecarregaVE estacio) {
		if (nElem >= llista.length) return false;
		
		llista[nElem++] = estacio.copia();
		return true;
	}
	
	/**
	 * 2 - Funció per eliminar totes les estacions d'una població
	 * @param poblacio - la població de les estacións que es volen eliminar
	 * @return novaLlista - Llista d'estacions que es troben en aquell municipi
	 */
	
	public LlistaEstacionsRecarrega EstacionsDePoblacioX (String poblacio) {
		int numPoblacio=0;
		for (int i=0; i<nElem; i++){
			if (llista[i].esTrobaEnAquestMunicipi(poblacio)){
				numPoblacio++;
			}
		}
		LlistaEstacionsRecarrega novaLlista= new LlistaEstacionsRecarrega(numPoblacio);
		for (int i=0; i<numPoblacio; i++) {
			if (llista[i].esTrobaEnAquestMunicipi(poblacio)){
				novaLlista.afegirEstacio(llista[i]);
			}
		}
		return novaLlista;
	}
	/**
	 * 
	 * @param poblacio - la població de les estacións que es volen eliminar
	 * @return boolean - true: s'han eliminat les estacions; false: no s'ha pogut fer l'eliminació
	 */
	public boolean eliminarEstacionsDePoblacioX (String poblacio) {
        int numInicial = nElem;
        for (int i=0; i<nElem; i++){
            if (llista[i].esTrobaEnAquestMunicipi(poblacio)){
                nElem--;
                for (int j=i; j<nElem; j++){
                    llista[j]=llista[j+1];
                }
            i--;
            }
        }
        return (numInicial>nElem);
    }
	
	/**
	 * 3 - Funció per obtenir les dades de les estacions d'una població
	 * @param poblacio - la població de les estacións de les que vols obtenir les dades
	 * @return String[] - un vector amb les dades de les estacions, cada element del vector correspon a una estació
	 */
	
	public String[] dadesEstacionsDePoblacioX (String poblacio) {
		
		String[] dades;
		int numDades=0;
		for (int i=0; i<nElem; i++){
			if (llista[i].esTrobaEnAquestMunicipi(poblacio)){
				numDades++;
			}
		}
		dades= new String[numDades];

		for (int i=0, j=0; i<nElem; i++){
			if (llista[i].esTrobaEnAquestMunicipi(poblacio)){
				dades[j++] = llista[i].toString();
			}
		}
		return dades;
	}
	
	/**
	 * 4 - Funció per obtenir la primera estació de la llista que es trobi en la provincia indicada
	 * @param provincia - la provincia de la estacio que es cerca
	 * @return EstacioRecarregaVE - la instància de la primera estació amb la provincia indicada
	 */
	
	public EstacioRecarregaVE primeraEstacioEnProvinciaX (String provincia) {
		for (int i=0; i<nElem; i++){
			if (llista[i].esTrobaEnAquestaProvincia(provincia)){
				return llista[i].copia();
			}
		}
		return null;
	}
	
	/**
	 * 5 - Funció per obtenir el numero de estacions que disposen de punts de recárrega d'un tipus de velocitat
	 * @param velocitat - el tipus de velocitat de les estacions a tenir en compte
	 * @return int - numero de estacions amb el tipus de velocitat indicada
	 */
	
	public int numEstacionsAmbVelocitatX (String velocitat) {
		int num=0;
		for (int i=0; i<nElem;i++){
			if (llista[i].teAquestTipusRecarrega(velocitat)) num++;
			// num += llista[i].teAquestTipusRecarrega(velocitat);
		}
		return num;
	}
	
	/**
	 * 6 - Funció per obtenir la estació amb més places de capacitat de la llista
	 * @return EstacioRecarregaVE - la instància de la estació amb mes places de capacitat, en cas d'empat es retorna la primera intancia de les empatades
	 */
	
	public EstacioRecarregaVE majorNumPlaces () {
		EstacioRecarregaVE estacioMesPlaces = llista[0];
		for (int i=1; i<nElem; i++)
			if (llista[i].getNumPlaces() > estacioMesPlaces.getNumPlaces())
				estacioMesPlaces = llista[i];
		return estacioMesPlaces;
	}

	/**
	 * 7 - Funció per obtenir un duplicat de la estació més propera a una posició
	 * @param latitud - latitud de la posició
	 * @param longitud - longitud de la posició
	 * @return EstacioRecarregaVE - la instància de la estació amb mes places de capacitat, en cas d'empat es retorna la primera intancia de les empatades
	 */
	
	public EstacioRecarregaVE estacioMesProperaAPosicioX (float latitud, float longitud) {
		
		EstacioRecarregaVE estacioMesPropera = llista[0];
		double distanciaMenor = llista[0].distanciaA(latitud, longitud);
		
		for (int i=1; i<nElem; i++) {
			double distancia = llista[i].distanciaA(latitud, longitud);
			if (distancia < distanciaMenor) {
				estacioMesPropera = llista[i];
				distanciaMenor = distancia;
			}
		}
		return estacioMesPropera;
	}
	
	/**
	 * 9 - Funció per obtenir un vector amb les estacions properes a una posició
	 * @param latitud - latitud de la posició
	 * @param longitud - longitud de la posició
	 * @return LlistaEstacionsRecarrega - llista amb totes les estacions properes a la posició indicada
	 */
	
	public LlistaEstacionsRecarrega estacionsProperesAPosicioX (float latitud, float longitud) {
		LlistaEstacionsRecarrega llistaPropers = new LlistaEstacionsRecarrega(nElem);
		for (int i=0; i<nElem; i++)
			if (llista[i].distanciaA(latitud, longitud) < distanciaMaxEstacioPropera)
				llistaPropers.afegirEstacio(llista[i]);
		return llistaPropers;
	}
	
	public EstacioRecarregaVE get(int i) {
		return (i<=nElem && llista[i]!=null)?llista[i]:null;
	}
	
	public boolean esBuida() {
		return (nElem == 0);
	}
	
	public LlistaEstacionsRecarrega duplicat() {
		LlistaEstacionsRecarrega duplicat = new LlistaEstacionsRecarrega(llista.length);
		for (int i = 0; i<this.nElem; i++) 
			duplicat.afegirEstacio(llista[i]);
		return duplicat;
	}
	
	@Override
	public String toString() {
		String buffer = "LlistaEstacionsRecarrega =>";
		for(int i=0; i<nElem; i++)
			buffer += "\n\t"+llista[i].toString();
		return buffer;
	}
	

	public EstacioRecarregaVE[] getLlista() {return llista;}
	public int getnElem() {return nElem;}
	public static int getDistanciaMaxEstacioPropera() {return distanciaMaxEstacioPropera;}

	public static void setDistanciaMaxEstacioPropera(int distanciaMaxEstacioPropera) {
		LlistaEstacionsRecarrega.distanciaMaxEstacioPropera = distanciaMaxEstacioPropera;
	}
	
}