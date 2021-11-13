package aplicacio;

import dades.*;

public class LlistaEstacionsRecarrega {
	
	
	private EstacioRecarregaVE[] llista;
	private int nElem;
	/* 8 */
	private static int distanciaMaxEstacioPropera = 30;
	
	public LlistaEstacionsRecarrega (int mida) {
		llista = new EstacioRecarregaVE[mida];
		nElem = 0;
	}
	
	
	/*	1
	 * 	Funció per afegir una estació al final de la llista
	 * 	Param:
	 * 		elem - la estació a afegir
	 * 	Retorna:
	 * 		boolean - true: s'han afegit l'estació; false: no s'ha pogut afegir l'estació
	 */
	public boolean afegirEstacio (EstacioRecarregaVE elem) {
		if (nElem >= llista.length) return false;
		
		llista[nElem++] = elem.copia();
		return true;
	}
	
	
	/*	2
	 * 	Funció per eliminar totes les estacions d'una població
	 * 	Param:
	 * 		poblacio - la població de les estacións que es volen eliminar
	 * 	Retorna:
	 * 		boolean - true: s'han eliminat estacions; false: no s'ha eliminat cap estació
	 */
	public boolean eliminarEstacionsDePoblacioX (String poblacio) {
		//TODO eliminarEstacionsDePoblacioX()
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	
	/*	3
	 * 	Funció per obtenir les dades de les estacions d'una població
	 * 	Param:
	 * 		poblacio - la població de les estacións de les que vols obtenir les dades
	 * 	Retorna:
	 * 		String[] - un vector amb les dades de les estacions, cada element del vector correspon a una estació
	 */
	public String[] dadesEstacionsDePoblacioX (String poblacio) {
		//TODO dadesEstacionsDePoblacioX()
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	
	/*	4
	 * 	Funció per obtenir la primera estació de la llista que es trobi en la provincia indicada
	 * 	Param:
	 * 		provincia - la provincia de la estacio que es cerca
	 * 	Retorna:
	 * 		EstacioRecarregaVE - la instància de la primera estació amb la provincia indicada
	 */
	public EstacioRecarregaVE primeraEstacioEnProvinciaX (String provincia) {
		//TODO primeraEstacioEnProvinciaX()
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	
	/*	5
	 * 	Funció per obtenir el numero de estacions que disposen de punts de recárrega d'un tipus de velocitat
	 * 	Param:
	 * 		velocitat - el tipus de velocitat de les estacions a tenir en compte
	 * 	Retorna:
	 * 		int - numero de estacions amb el tipus de velocitat indicada
	 */
	public int numEstacionsAmbVelocitatX (int velocitat) {
		//TODO numEstacionsAmbVelocitatX()
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	
	/*	6
	 * 	Funció per obtenir la estació amb més places de capacitat
	 * 	Retorna:
	 * 		EstacioRecarregaVE - la instància de la estació amb mes places de capacitat, en cas d'empat es retorna la primera intancia de les empatades
	 */
	public EstacioRecarregaVE majorNumPlaces () {
		//TODO majorNumPlaces()
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	
	/*	7
	 * 	Funció per obtenir un duplicat de la estació més propera a una posició
	 *	Param:
	 *		posicio - posició de la qual es determina si la estació es propera o no 
	 * 	Retorna:
	 * 		EstacioRecarregaVE - la instància de la estació amb mes places de capacitat, en cas d'empat es retorna la primera intancia de les empatades
	 */
	public EstacioRecarregaVE estacioMesProperaAPosicioX (float posicio) {
		//TODO estacioMesPropera()
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	
	/*	9
	 * 	Funció per obtenir un vector amb les estacions properes a una posició
	 *	Param:
	 *		posicio - posició de la qual es determina si la estació es propera o no 
	 * 	Retorna:
	 * 		EstacioRecarregaVE[] - vector amb totes les estacions properes a la posició indicada
	 */
	public EstacioRecarregaVE[] estacionsProperesAPosicioX (float posicio) {
		//TODO estacionsProperesAPosicioX()
		throw new UnsupportedOperationException("Not Implemented Yet");
	}
	
	
}
