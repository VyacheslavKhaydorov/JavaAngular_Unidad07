/**
 * 
 */
package tarea7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author viach
 * App que gestiona el flujo de ventas en una caja de supermercado.
 */
public class Ejerc2App {

	//Constantes
	private static final double IVA_21 = 0.21;
	private static final double IVA_04 = 0.04;

	
	public static void main(String a[]){
		
		//Variables
		double precioTotalBruto, precioTotalConIVA, cantidadPagada, devolucionCambio;
		int numArticulosComprados;
		ArrayList<Double> listaPrecios = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		
		//Añadimos precios a la listaPrecios
		listaPrecios.add(28.30);
		listaPrecios.add(14.95);
		listaPrecios.add(10.00);
		listaPrecios.add(9.95);
		listaPrecios.add(32.45);
		
		//Llamamos a los metodos y damos el output por pantalla
		//Tambien consultamos la cantida de dinero que ha pagado el cliente para darle
		//el cambio (metodo devolucionCambio)
		numArticulosComprados = numeroArticulos(listaPrecios);
		precioTotalBruto = precioTotal(listaPrecios);
		precioTotalConIVA = aplicarIVA(precioTotalBruto);
		System.out.println("Articulos comprados: " + numArticulosComprados);
		System.out.println("Precio total bruto: " + precioTotalBruto);
		System.out.printf("Precio total con IVA: %.2f\n", precioTotalConIVA);
		System.out.println("Cuanto paga el cliente?");
		cantidadPagada = input.nextDouble();
		devolucionCambio = cambioParaCliente(cantidadPagada, precioTotalConIVA);
		System.out.printf("Cambio a devolver: %.2f\n", devolucionCambio);
        
    }
	
	
	//Metodo numeroArticulos
	public static int numeroArticulos (ArrayList<Double> listaArticulos) {
		
		int numArticulos = 0;
		
		for (Object articulo : listaArticulos)
			numArticulos++;
		
		return numArticulos;
		
	}
	
	
	//Metodo aplicarIVA
	public static double aplicarIVA (double precio) {
		
		double precioConIVA = 0;
		int opcion;
		Scanner input = new Scanner(System.in);
		
		//Consultamos por pantalla que IVA se va a aplicar
		System.out.println("Qué IVA aplicamos?\n"
				+ "1. IVA 21%\n"
				+ "2. IVA 4%");
		opcion = input.nextInt();	//Es importante usar una variable para el input,
									//si no no llegaria hasta el segundo if
		if (opcion == 1)
			precioConIVA = precio + (precio * IVA_21);
		else if (opcion == 2)
			precioConIVA = precio + (precio * IVA_04);
		
		input.nextLine();
		
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
		
		return varPrecioTotal;
		
	}
	
	
	//Metodo cambioParaCliente
	public static double cambioParaCliente (double dineroCobrado, double precio) {
		
		double dineroADevolver;
		
		dineroADevolver = dineroCobrado - precio;
		
		return dineroADevolver;
		
	}

}
