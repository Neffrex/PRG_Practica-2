package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.jcp.xml.dsig.internal.SignerOutputStream;

import dades.*;

public class UsaLlistaEstacionsVE {
	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Indica el número de línies a llegir del fitxer (màxim 418)");
		int numLinies = Integer.parseInt(teclat.nextLine()), numEstacionsPropers=0, altitud=0, longitud=0;
		String[] dataset = llegirLiniesFitxer(numLinies);
		String buffer;
		EstacioRecarregaVE estacionsProperes[] = null, estacioAmbMesPlaces = null, llistaPropers[] = null, bufferEstacio;
		byte opcio=0;
		boolean end=false;
		
		for (int i = 0; i < dataset.length; i++) {
			System.out.println("Linia " + (i + 1) + " conté " + dataset[i]);
		}

		// Completar el codi a partir d'aquí
		LlistaEstacionsRecarrega llista = new LlistaEstacionsRecarrega(dataset.length);
		System.out.println("Carregant dades...");
		for(int i=0; i<dataset.length; i++) {
			String s = dataset[i];
			s = s.replace(',', '.');
			String[] elem = s.split(";");
			String[] velocitats = elem[1].split(" i ");
			
			llista.afegirEstacio(new EstacioRecarregaVE(
					elem[0],
					velocitats[0],
					Float.parseFloat(elem[2]),
					Float.parseFloat(elem[3]),
					elem[4],
					elem[5],
					Integer.parseInt(elem[6])));
			
			for (int j=1; j<velocitats.length; j++)
				llista.getLlista()[i].afegirTipusVelocitat(velocitats[j]);
		}
		
		System.out.println("Dades carregades i llestes per usar-se");
		
		Scanner teclat = new Scanner(System.in);
		
		while (!end){
			
			System.out.println("**********************************************************************");
			System.out.println("Menu de l'aplicació EstacióRecàrregaVE");
			System.out.println("Escull l'opció que vols usar, per seleccionar-ne un introdueix el número corresponent i pressiona Enter.");
			System.out.println("01- Eliminar conjunt d'estacions en una població.");
			System.out.println("02- Mostrar primera estació ubicada a la província de Lleida i Barcelona.");
			System.out.println("03- Mostrar número d'estacions que disposen de punts de recàrrega d'un tipus de velocitat.");
			System.out.println("04- Mostrar l’estació que té més places de capacitat.");
			System.out.println("05- Mostrar l’estació més propera a la nostra posició.");
			System.out.println("06- Mostrar les dades de les estacions que es troben properes a la nostra posició");
			System.out.println("07- Amplia el valor de distància propera a 50 km");
			System.out.println("08- Del  resultat  de  les  estacions  properes  mostra  les  dades  de  l’estació  que  té  més  places  de capacitat.");
			System.out.println("09- Mostrar el conjunt d’estacions de la llista.");
			System.out.println("10- Sortir del programa.");
			
			opcio = teclat.nextByte();
			
			System.out.println("**********************************************************************\n");
			System.out.println("Executant opció " + opcio);
			
			switch(opcio){

			case 1:
				System.out.println("Poblacio del conjunt a eliminar: ");
				teclat.nextLine();
				buffer = teclat.nextLine();
				System.out.println((llista.eliminarEstacionsDePoblacioX(buffer)?"Estacions eliminades amb exit":"No s'ha fet cap eliminació"));
				break;
			case 2:
				bufferEstacio = llista.primeraEstacioEnProvinciaX("Lleida");
				System.out.println((bufferEstacio!=null)?bufferEstacio.toString():"No hi ha cap estació en Lleida");
				bufferEstacio = llista.primeraEstacioEnProvinciaX("Barcelona");
				System.out.println((bufferEstacio!=null)?bufferEstacio.toString():"No hi ha cap estació en Lleida");
				break;
			case 3:
				System.out.println("Tipus de velocitat: ");
				teclat.nextLine();
				buffer = teclat.nextLine();
				System.out.println("Hi han " + llista.numEstacionsAmbVelocitatX(buffer) + " estacions amb velocitat " + buffer + ".\n");
				break;
			case 4:
				if (llista==null){
					System.out.println("La llista està buida.");
				}
				else{
					if (estacionsProperes==null){
						System.out.println("No hi ha cap estació propera.");
						break;
					}
					estacioAmbMesPlaces=llista.getLlista()[0];
					for (int i=1; i<estacionsProperes.length; i++){
						if (llista.getLlista()[i].getNumPlaces()>estacioAmbMesPlaces.getNumPlaces()){
							estacioAmbMesPlaces = llista.getLlista()[i];
						}
					}
					System.out.println(estacioAmbMesPlaces.toString());
				}
				break;
			case 5:
				System.out.println("Introdueix la altitud:");
				altitud=teclat.nextInt();
				System.out.println("Intrudueix la longitud:");
				longitud=teclat.nextInt();
				System.out.println(llista.estacioMesProperaAPosicioX(altitud, longitud).toString());
				break;
			case 6:
				System.out.println("Introdueix la altitud:");
				altitud=teclat.nextInt();
				System.out.println("Intrudueix la longitud:");
				longitud=teclat.nextInt();
				llistaPropers = llista.estacionsProperesAPosicioX(altitud, longitud);
				System.out.println(Arrays.toString(llistaPropers));
				for (EstacioRecarregaVE s: llistaPropers){
					if (s == null) {
						System.out.println("No hi han més estacions properes");
						break;
					}
				}
				break;
			case 7:
				LlistaEstacionsRecarrega.setDistanciaMaxEstacioPropera(50);
				System.out.println("Afegir 50km fet.");
				llistaPropers = llista.estacionsProperesAPosicioX(altitud, longitud);
				break;
			case 8:
				if (estacionsProperes==null){
					System.out.println("No hi ha cap estació propera.");
					break;
				}
				estacioAmbMesPlaces = estacionsProperes[0];
				for (int i=1; i<estacionsProperes.length; i++){
					if (llista.getLlista()[i].getNumPlaces()>estacioAmbMesPlaces.getNumPlaces()){
						estacioAmbMesPlaces = llista.getLlista()[i];
					}
				}
				System.out.println(estacioAmbMesPlaces.toString()); 
				
				break;
			case 9:
				System.out.println("Mostrant el conjunt d'estacions:");
				for (int i=0; i<llista.getnElem(); i++){
					System.out.println(llista.getLlista()[i].toString());
				}
				break;
			case 10:
				System.out.println("Sortint del programa...");
				end = true;
				//return;
				break;
			default:
				System.out.println("Introdueix un valor vàlid. :(");	
				break;
				
			}
			
			// 4 segundos de tiempo para ver la respuesta de la opcion
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		teclat.close();
		new Menu(llista);
		
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
	
	private static class Menu extends javax.swing.JFrame {
		
		private int amplada = 900, alçada = 600;
		private LlistaEstacionsRecarrega llista;
		
		private javax.swing.JPanel JPanelMain;
		private javax.swing.JTextField JTextFieldEliminaEstacionsDePoblacio;
		private javax.swing.JLabel JLabelEliminaEstacionsDePoblacio;
		private javax.swing.JButton JButtonEliminaEstacionsDePoblacio;
		
		public Menu (LlistaEstacionsRecarrega llista) {
			this.llista = llista;
			
			JPanelMain = new javax.swing.JPanel();
			
			this.setTitle("Menú");
			
			this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setMinimumSize(new java.awt.Dimension(amplada, alçada));
			this.setBackground(java.awt.Color.DARK_GRAY);
			
			JLabelEliminaEstacionsDePoblacio = new javax.swing.JLabel("Eliminar totes les estacions de la poblacio: ");
			JPanelMain.add(JLabelEliminaEstacionsDePoblacio);
			
			JTextFieldEliminaEstacionsDePoblacio = new javax.swing.JTextField();
			JTextFieldEliminaEstacionsDePoblacio.setMinimumSize(new java.awt.Dimension(100,40));
			JPanelMain.add(JTextFieldEliminaEstacionsDePoblacio);
			
			JButtonEliminaEstacionsDePoblacio = new javax.swing.JButton("Elimina");
			JButtonEliminaEstacionsDePoblacio.addActionListener( e -> {
				llista.eliminarEstacionsDePoblacioX(JTextFieldEliminaEstacionsDePoblacio.getText());
				} );
			JPanelMain.add(JButtonEliminaEstacionsDePoblacio);
			
			this.add(JPanelMain);
			this.pack();
			this.setVisible(true);
		}
		
		
		
	}
	
}