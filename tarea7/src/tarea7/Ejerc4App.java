/**
 * 
 */
package tarea7;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author viach
 * Aplicacion para gestionar ventas y control de stock.
 */
public class Ejerc4App {

	//Constantes
	private static final double IVA_21 = 0.21;
	private static final double IVA_04 = 0.04;
	
	//Main
	public static void main(String[] args) {
		
		//Variables
		double precioTotalBruto = 0, precioTotalConIVA = 0, devolucionCambio = 0;
		String cantidadPagadaCadena;
		ArrayList<Double> listaCompra = new ArrayList<>();
		Hashtable<String,String> baseDatosArticulos = new Hashtable<>();	//Articulo/precio
		boolean salir = false;
		
		//Valores de la hashtable baseDatosArticulos
		baseDatosArticulos.put("Praga Caput Regni","49.95");	//Muy importante poner el precio
																//con punto, con coma parse falla
		baseDatosArticulos.put("Terraforming Mars","44.95");
		baseDatosArticulos.put("Cthulhu: Death May Die","99.95");
		baseDatosArticulos.put("Decrypto","19.95");
		baseDatosArticulos.put("Cartografos","14.95");
		baseDatosArticulos.put("Underwater Cities","59.95");
		baseDatosArticulos.put("Root","44.95");
		baseDatosArticulos.put("Yokohama","61.45");
		baseDatosArticulos.put("Argent: The Consortium","45.95");
		baseDatosArticulos.put("Risk: Europe","44.65");

		//Salida por pantalla, captura de datos y llamada a metodos
		do {
			String input = JOptionPane.showInputDialog(null, "Que desea hacer?\n"
					+ "1. Consultar un articulo\n"
					+ "2. Añadir o borrar articulo\n"
					+ "3. Listar todos los articulos de la base de datos\n"
					+ "4. Crear nueva lista de la compra\n"
					+ "5. Numero de articulos en lista de la compra\n"
					+ "6. Precio (bruto) de la lista de la compra\n"
					+ "7. Precio final (con IVA) de la lista de la compra\n"
					+ "8. Cambio para el cliente\n"
					+ "Cualquier otra tecla para SALIR",
					"Base de datos de articulos", JOptionPane.PLAIN_MESSAGE);
			switch (input){
				case "1":
					consultarArticulo(baseDatosArticulos);
					break;
				case "2":
					añadirBorrarArticulo(baseDatosArticulos);
					break;
				case "3":
					mostrarArticulos(baseDatosArticulos);
					break;
				case "4":
					listaCompra = crearListaCompra(baseDatosArticulos);
					break;
				case "5":
					numeroArticulos(listaCompra);
					break;
				case "6":
					precioTotalBruto = precioTotal(listaCompra);	//Necesitamos guardarlo
																	//para sacar el precio con IVA
					break;
				case "7":
					precioTotalConIVA = aplicarIVA(precioTotalBruto);
					JOptionPane.showMessageDialog(null, String.format("El precio total con IVA "
							+ "de la lista de la compra es de %.2f", precioTotalConIVA));
					break;
				case "8":
					cantidadPagadaCadena = JOptionPane.showInputDialog(null, "Cuanto ha pagado "
							+ "el cliente?", "Cobro y cambio", JOptionPane.PLAIN_MESSAGE);
					devolucionCambio = cambioParaCliente(Double.parseDouble(cantidadPagadaCadena),
							precioTotalConIVA);
					JOptionPane.showMessageDialog(null, String.format("El cambio del cliente "
							+ "es de %.2f", devolucionCambio));
					break;
				default:
					salir = true;
			}
		} while (!salir);

	}

	
	//Metodo para crear una lista de la compra
	public static ArrayList<Double> crearListaCompra(Hashtable<String,String> baseDatosArticulos) {
		
		//Variables
		String numeroArticulosEnLista, añadirArticuloNombre;
		ArrayList<Double> listaCompra = new ArrayList<>();
		
		//Solicitamos cantidad de articulos en lista de la compra
		numeroArticulosEnLista = JOptionPane.showInputDialog(null, "Cuantos articulos "
				+ "querras añadir?", "Lista de la compra", JOptionPane.PLAIN_MESSAGE);
		
		//Iteramos llenando la lista creada
		for (int i = 0; i < Integer.parseInt(numeroArticulosEnLista); i++) {
			añadirArticuloNombre = JOptionPane.showInputDialog(null, "Introduzca el nombre del "
					+ "articulo:", "Añadido de articulo", JOptionPane.PLAIN_MESSAGE);
			//Comprobacion sobre la base de datos para ver si el articulo existe
			if (baseDatosArticulos.keySet().contains(añadirArticuloNombre)) {
				//Añadimos a listaCompra el valor (precio) asociado a la llave (nombre)
				//de la base de datos hashtable
				listaCompra.add(Double.parseDouble(baseDatosArticulos.get(añadirArticuloNombre)));
			}
			else {
				//Mostramos mensaje de error si el articulo no está en la base de datos
				JPanel mensajeError = new JPanel();
				JOptionPane.showMessageDialog(mensajeError, añadirArticuloNombre + " no esta en "
						+ "la base de datos!", "Error", JOptionPane.ERROR_MESSAGE);
				i--;
			}
		}
		
		return listaCompra;
		
	}
	
	//Metodo numeroArticulos
	public static void numeroArticulos (ArrayList<Double> listaArticulos) {
		
		int numArticulos = 0;
		
		for (Object articulo : listaArticulos)
			numArticulos++;
		
		JOptionPane.showMessageDialog(null, "La lista de la compra tiene " + numArticulos
				+ " articulos");
		
	}
	
	
	//Metodo aplicarIVA
	public static double aplicarIVA (double precio) {
		
		double precioConIVA = 0;
		String opcion;
		
		//Consultamos por pantalla que IVA se va a aplicar
		opcion = JOptionPane.showInputDialog(null, "Introduzca el IVA que se le aplica al articulo:\n"
				+ "1. IVA 21%\n"
				+ "2. IVA 4%", "IVA", JOptionPane.PLAIN_MESSAGE);
		switch (opcion) {
			case "1":
				precioConIVA = precio + (precio * IVA_21);
				break;
			case "2":
				precioConIVA = precio + (precio * IVA_04);
				break;
			default:
				//Mostramos mensaje de error si la opcion escogida no está contemplada
				JPanel mensajeError = new JPanel();
				JOptionPane.showMessageDialog(mensajeError, "El IVA escogido no es correcto!",
						"Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return precioConIVA;
		
	}
	
	
	//Metodo precioTotal
	public static double precioTotal (ArrayList<Double> listaPrecios) {
		
		double varPrecioTotal = 0;
		
		//Usamos un iterador para recorrer la lista y sumar los precios en varPrecioTotal
		Iterator<Double> it = listaPrecios.iterator();
		while (it.hasNext()) {
			varPrecioTotal += it.next();
		}
		
		JOptionPane.showMessageDialog(null, String.format("La lista de la compra tiene un precio "
				+ "bruto total de %.2f", varPrecioTotal));
		
		return varPrecioTotal;
		
	}
	
	
	//Metodo cambioParaCliente
	public static double cambioParaCliente (double dineroCobrado, double precio) {
		
		double dineroADevolver;
		
		dineroADevolver = dineroCobrado - precio;
		
		return dineroADevolver;
		
	}
		
		
	//Metodo para consultar un articulo de la hashtable
	public static void consultarArticulo (Hashtable<String, String> baseDatosArticulos) {
		
		String eleccionArticulo;
		
		eleccionArticulo = JOptionPane.showInputDialog(null, "Introduca el nombre del "
				+ "articulo:", "Vista de articulo", JOptionPane.PLAIN_MESSAGE);
		
		JOptionPane.showMessageDialog(null, eleccionArticulo + ": "
				+ baseDatosArticulos.get(eleccionArticulo));
		
	}
	
	
	//Metodo para añadir/borrar un articulo de la hashtable
	public static void añadirBorrarArticulo (Hashtable<String, String> baseDatosArticulos) {

		//Variables
		String eleccion, añadirArticuloNombre, añadirArticuloPrecio, borrarArticulo;
		
		//El usuario escoge si desea añadir articulos o borrarlos
		eleccion = JOptionPane.showInputDialog(null, "Desea añadir (1) o borrar algun "
				+ "articulo (2)?", "Añadido y borrado de articulos", JOptionPane.PLAIN_MESSAGE);
		//Añadir
		if (eleccion.equals("1")) {
			añadirArticuloNombre = JOptionPane.showInputDialog(null, "Introduca el nombre del "
					+ "articulo:", "Añadido de articulo", JOptionPane.PLAIN_MESSAGE);
			añadirArticuloPrecio = JOptionPane.showInputDialog(null, "Introduzca el precio del "
					+ "articulo:", "Añadido de articulo",  JOptionPane.PLAIN_MESSAGE);
			
			baseDatosArticulos.put(añadirArticuloNombre, añadirArticuloPrecio);
		}
		//Borrar
		else if (eleccion.equals("2")) {
			borrarArticulo = JOptionPane.showInputDialog(null, "Introduzca el nombre del "
					+ "articulo:", "Borrado de articulo", JOptionPane.PLAIN_MESSAGE);
			baseDatosArticulos.remove(borrarArticulo);
		}
		
	}
	
	
	//Metodo para listar la hashtable
	public static void mostrarArticulos (Hashtable<String, String> baseDatosProductos) {
		
		JOptionPane.showMessageDialog(null, baseDatosProductos.toString());
			
	}
		
}
