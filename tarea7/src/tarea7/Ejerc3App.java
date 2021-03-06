/**
 * 
 */
package tarea7;

import java.util.Hashtable;
import javax.swing.JOptionPane;


/**
 * @author viach
 * Base de datos con interfaz visual para controlar el stock de articulos de una tienda.
 */
public class Ejerc3App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//Variables
		Hashtable<String,String> baseDatosArticulos = new Hashtable<>();	//Articulo/precio
		boolean salir = false;
		
		//Valores de la hashtable
		baseDatosArticulos.put("Praga Caput Regni","49,95");
		baseDatosArticulos.put("Terraforming Mars","44,95");
		baseDatosArticulos.put("Cthulhu: Death May Die","99,95");
		baseDatosArticulos.put("Decrypto","19,95");
		baseDatosArticulos.put("Cartografos","14,95");
		baseDatosArticulos.put("Underwater Cities","59,95");
		baseDatosArticulos.put("Root","44,95");
		baseDatosArticulos.put("Yokohama","61,45");
		baseDatosArticulos.put("Argent: The Consortium","45,95");
		baseDatosArticulos.put("Risk: Europe","44,65");

		//Salida por pantalla, captura de datos y llamada a metodos
		do {
			String input = JOptionPane.showInputDialog(null, "Que desea hacer?\n"
					+ "1. Consultar un articulo\n"
					+ "2. Aņadir o borrar articulo\n"
					+ "3. Listar todos los articulos\n"
					+ "4. Salir",
					"Base de datos de articulos", JOptionPane.PLAIN_MESSAGE);
			switch (input){
				case "1":
					consultarArticulo(baseDatosArticulos);
					break;
				case "2":
					aņadirBorrarArticulo(baseDatosArticulos);
					break;
				case "3":
					mostrarArticulos(baseDatosArticulos);
					break;
				case "4":
					salir = true;
			}
		} while (!salir);
			
	}
	
	
	//Metodo para consultar un articulo de la hashtable
	public static void consultarArticulo (Hashtable<String, String> baseDatosArticulos) {
		
		String eleccionArticulo;
		
		eleccionArticulo = JOptionPane.showInputDialog(null, "Introduzca el nombre del "
				+ "articulo:", "Vista de articulo", JOptionPane.PLAIN_MESSAGE);
		
		JOptionPane.showMessageDialog(null, eleccionArticulo + ": "
				+ baseDatosArticulos.get(eleccionArticulo));
		
	}
	
	
	//Metodo para aņadir/borrar un articulo de la hashtable
	public static void aņadirBorrarArticulo (Hashtable<String, String> baseDatosArticulos) {

		//Variables
		String eleccion, aņadirArticuloNombre, aņadirArticuloPrecio, borrarArticulo;
		
		//El usuario escoge si desea aņadir articulos o borrarlos
		eleccion = JOptionPane.showInputDialog(null, "Desea aņadir (1) o borrar algun "
				+ "articulo (2)?", "Aņadido y borrado de articulos", JOptionPane.PLAIN_MESSAGE);
		//Aņadir
		if (eleccion.equals("1")) {
			aņadirArticuloNombre = JOptionPane.showInputDialog(null, "Introduca el nombre del "
					+ "articulo:", "Aņadido de articulo", JOptionPane.PLAIN_MESSAGE);
			aņadirArticuloPrecio = JOptionPane.showInputDialog(null, "Introduzca el precio del "
					+ "articulo:", "Aņadido de articulo",  JOptionPane.PLAIN_MESSAGE);
			
			baseDatosArticulos.put(aņadirArticuloNombre, aņadirArticuloPrecio);
		}
		//Borrar
		else if (eleccion.equals("2")) {
			borrarArticulo = JOptionPane.showInputDialog(null, "Introduca el nombre del "
					+ "articulo:", "Borrado de articulo", JOptionPane.PLAIN_MESSAGE);
			baseDatosArticulos.remove(borrarArticulo);
		}
		
	}
	
	
	
	//Metodo para listar la hashtable
	public static void mostrarArticulos (Hashtable<String, String> baseDatosProductos) {
		
		JOptionPane.showMessageDialog(null, baseDatosProductos.toString());
			
	}
	
}
