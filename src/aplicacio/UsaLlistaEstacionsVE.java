package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dades.*;

public class UsaLlistaEstacionsVE {
	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		/* Declaració de les dades */
		String buffer;
		LlistaEstacionsRecarrega llistaEstacionsProperes = null;
		EstacioRecarregaVE estacionsProperes[] = null;
		byte opcio=0;
		boolean end=false;

		System.out.println("Indica el número de línies a llegir del fitxer (màxim 418)");
		int numLinies = Integer.parseInt(teclat.nextLine());
		Float latitud, longitud;
		String[] dataset = llegirLiniesFitxer(numLinies);

		for (int i = 0; i < dataset.length; i++) {
			System.out.println("Linia " + (i + 1) + " conté " + dataset[i]);
		}

		LlistaEstacionsRecarrega llista = new LlistaEstacionsRecarrega(dataset.length);
		System.out.println("Carregant dades...");
		carregar_dades(llista, dataset);
		System.out.println("Dades carregades i llestes per usar-se");
		
		Scanner teclat = new Scanner(System.in);
		
		while (!end){
			
			System.out.println("**********************************************************************");
			System.out.println("Menu de l'aplicació EstacióRecàrregaVE");
			System.out.println("Escull l'opció que vols usar, per seleccionar-ne un introdueix el número corresponent i pressiona Enter.");
			System.out.println(" 1- Eliminar conjunt d'estacions en una població.");
			System.out.println(" 2- Mostrar primera estació ubicada a la província de Lleida i Barcelona.");
			System.out.println(" 3- Mostrar número d'estacions que disposen de punts de recàrrega d'un tipus de velocitat.");
			System.out.println(" 4- Mostrar l’estació que té més places de capacitat.");
			System.out.println(" 5- Mostrar l’estació més propera a la nostra posició.");
			System.out.println(" 6- Mostrar les dades de les estacions que es troben properes a la nostra posició");
			System.out.println(" 7- Amplia el valor de distància propera a 50 km i recalcula les estacions properes");
			System.out.println(" 8- Del resultat de les estacions properes mostrar les dades de l’estació amb més places de capacitat.");
			System.out.println(" 9- Mostrar el conjunt d’estacions de la llista.");
			System.out.println("10- Sortir del programa.");
			System.out.println("**********************************************************************");
			
			try {
				
				System.out.println("Selecciona una opció: ");
				opcio = Byte.parseByte(teclat.nextLine());
				
				System.out.println("Executant opció " + opcio);
				
				switch(opcio){
					case 1:
						System.out.println("Poblacio del conjunt a eliminar: ");
						buffer = teclat.nextLine();
						System.out.println("La llista a eliminar és:"+(llista.EstacionsDePoblacioX(buffer).toString()));
						
						System.out.println("Vols eliminar aquestes estacions de la llista? Introdueix 1 Si vols eliminar-los, 0 en cas contrari.");
						if (teclat.nextInt()==1){
							System.out.println((llista.eliminarEstacionsDePoblacioX(buffer)?"Estacions eliminades amb exit":"No s'ha fet cap eliminació"));
						}
						else System.out.println("No s'ha eliminat cap estació.");
						break;
						
					case 2:
						LlistaEstacionsRecarrega llistaLleidaBarcelona= new LlistaEstacionsRecarrega(2);
						llistaLleidaBarcelona.afegirEstacio(llista.primeraEstacioEnProvinciaX("Lleida"));
						llistaLleidaBarcelona.afegirEstacio(llista.primeraEstacioEnProvinciaX("Barcelona"));
						System.out.println(llistaLleidaBarcelona.toString());
						
						System.out.println("Introdueix la altitud:");
						latitud=Float.parseFloat(teclat.nextLine());
						System.out.println("Intrudueix la longitud:");
						longitud=Float.parseFloat(teclat.nextLine());
						System.out.println("Distancia a Lleida: "+llistaLleidaBarcelona.get(0).distanciaA(latitud, longitud));
						System.out.println("Distancia a Barcelona: "+llistaLleidaBarcelona.get(1).distanciaA(latitud, longitud));
						System.out.println("Estacio més propera: "+llistaLleidaBarcelona.estacioMesProperaAPosicioX(latitud, longitud).toString());
						break;
						
					case 3:
						System.out.println("Tipus de velocitat: ");
						buffer = teclat.nextLine();
						System.out.println("Hi han " + llista.numEstacionsAmbVelocitatX(buffer) + " estacions amb velocitat " + buffer + ".\n");
						break;
						
					case 4:
						if (llista==null){
							System.out.println("La llista està buida.");
						} else {
							System.out.println(llista.majorNumPlaces().toString());
						}
						break;
						
					case 5:
						System.out.println("Introdueix la latitud:");
						latitud=Float.parseFloat(teclat.nextLine());
						System.out.println("Intrudueix la longitud:");
						longitud=Float.parseFloat(teclat.nextLine());
						System.out.println(llista.estacioMesProperaAPosicioX(latitud, longitud).toString());
						break;
						
					case 6:
						System.out.println("Introdueix la latitud:");
						latitud=Float.parseFloat(teclat.nextLine());
						System.out.println("Intrudueix la longitud:");
						longitud=Float.parseFloat(teclat.nextLine());
						System.out.println("El rang de proximitat actual es: " + LlistaEstacionsRecarrega.getDistanciaMaxEstacioPropera());
						
						if (estacionsProperes==null) {
							System.out.println("No hi ha cap estació propera.");
							break;
						}
						System.out.println(llista.estacionsProperesAPosicioX(latitud, longitud).toString());
						break;
						
					case 7:
						if(LlistaEstacionsRecarrega.getDistanciaMaxEstacioPropera() != 50) {
							LlistaEstacionsRecarrega.setDistanciaMaxEstacioPropera(50);
							System.out.println("Rang augmentat a 50km");
						}
						
						System.out.println("Introdueix la latitud:");
						latitud=Float.parseFloat(teclat.nextLine());
						System.out.println("Intrudueix la longitud:");
						longitud=Float.parseFloat(teclat.nextLine());
						System.out.println("El rang de proximitat actual es: " + LlistaEstacionsRecarrega.getDistanciaMaxEstacioPropera());
						
						llistaEstacionsProperes = llista.estacionsProperesAPosicioX(latitud, longitud);
						if (llistaEstacionsProperes==null) {
							System.out.println("No hi ha cap estació propera.");
							break;
						}
						System.out.println(llistaEstacionsProperes.toString());
						break;
						
					case 8:
						if (llistaEstacionsProperes.esBuida()){
							System.out.println("No hi ha cap estació propera.");
						} else {
							System.out.println(llistaEstacionsProperes.majorNumPlaces().toString()); 
						}
						break;
						
					case 9:
						System.out.println(llista.toString());
						break;
						
					case 10:
						System.out.println("Sortint del programa...");
						end = true;
						break;
						
					default:
						System.out.println("Introdueix un valor vàlid.");
						break;
				}

				waitForResponse("Prem ENTER per continuar... ");
				
			} catch(NumberFormatException e) {
				// Formato incorrecto al introducir un dato
				System.err.println("ERROR: El valor introduit no es un valor vàlid");
				waitForResponse("Prem ENTER per continuar... ");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}

		}
		
		teclat.close();
		
	}
	
	private static void waitForResponse(String msg) {
		System.out.println(msg); teclat.nextLine();
	}
	
	private static String[] llegirLiniesFitxer(int nLinies) throws FileNotFoundException {
		String[] result;
		if (nLinies < 0)
			nLinies = 0;
		if (nLinies > 418)
			nLinies = 418;
		result = new String[nLinies];
		Scanner f = new Scanner(new File("EstacionsRecarregaReduit.csv"));

		String capcalera = f.nextLine();
		System.out.println("El format de les dades en cada línia és el següent\n" + capcalera);
		for (int i = 0; i < nLinies; i++) {
			result[i] = f.nextLine();
		}
		f.close();
		return result;
	}
	
	private static void carregar_dades(LlistaEstacionsRecarrega llistaEstacions, String[] dataset) {
		
		for(int i=0; i<dataset.length; i++) {
			//Separació de les dades
			String s = dataset[i];
			s = s.replace(',', '.');
			String[] dadesEstacio = s.split(";");
			String[] velocitats = dadesEstacio[1].split(" i ");
			
			//Omplir la llista amb les estacions amb les dades corresponents a dataset
			llistaEstacions.afegirEstacio(new EstacioRecarregaVE(
					dadesEstacio[0],
					velocitats[0],
					Float.parseFloat(dadesEstacio[2]),
					Float.parseFloat(dadesEstacio[3]),
					dadesEstacio[4],
					dadesEstacio[5],
					Integer.parseInt(dadesEstacio[6])));
			
			for (int j=1; j<velocitats.length; j++)
				llistaEstacions.getLlista()[i].afegirTipusVelocitat(velocitats[j]);
		}
		
	}
	
}