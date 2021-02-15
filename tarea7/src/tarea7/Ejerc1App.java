/**
 * 
 */
package tarea7;

//Imports
import java.util.Hashtable;
import java.util.Enumeration;

/**
 * @author viach
 * App que calcula la nota media de los estudiantes de un curso de programación.
 */
public class Ejerc1App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Variables, vectores y diccionario
		String[] estudiantes = {"Jorge", "Maria", "Ana", "Edgar", "Lucia"};
		int[][] notasEstudiantes = {{7, 4, 5, 6, 7},
				 					{8, 9, 7, 8, 8},
				 					{5, 4, 5, 6, 6},
				 					{7, 8, 9, 8, 9},
				 					{5, 5, 6, 6, 5}};
		int sumatorioNotas = 0, numeroNotas = 0;
		double notaMedia;
		Hashtable<String,String> notasMediasEstudiantes = new Hashtable<String,String>();
		
		//Llamada al metodo notaMedia
		//Recorremos el vector estudiantes, por cada estudiante recorremos su parte correspondiente
		//de la matriz notas, las sumamos y le mandamos el sumatorioNotas y el numeroNotas
		//al metodo notaMedia
		for (int i = 0; i < estudiantes.length; i++) {
			for (int j = 0; j < notasEstudiantes[i].length; j++) {
				sumatorioNotas += notasEstudiantes[i][j];
				numeroNotas++;
			}
			notaMedia = notaMedia(sumatorioNotas, numeroNotas);
			//Almacenamos la nota media junto al nombre del estudiante en el hashtable notaMediaEstudiantes
			notasMediasEstudiantes.put(Double.toString(notaMedia), estudiantes[i]);
			//Asignamos sumatorioNotas y numeroNotas a cero para el próximo estudiante
			sumatorioNotas = 0;
			numeroNotas = 0;
		}
		
		//Salida por pantalla
		//Usamos Enumeration, es importante declararlo despues de asignar el hashtable
		Enumeration<String> estudianteHashtable = notasMediasEstudiantes.elements();
		Enumeration<String> notaMediaHashtable = notasMediasEstudiantes.keys();
		//Mientras haya estudiantes en el hashtable los imprimimos con sus notas
		System.out.printf("%10s %20s\n", "ESTUDIANTE", "NOTA");
		while (estudianteHashtable.hasMoreElements()) {
			System.out.printf("%10s %20s\n", estudianteHashtable.nextElement(), notaMediaHashtable.nextElement());
		}
		
	}	
	
	
	//Metodo notaMedia
	public static double notaMedia (int sumatorioNotas, int numeroNotas) {
		
		//Variables
		double nMedia;
		
		//Calculamos nota media (suma de notas entre su numero)
		//Importante castear el primer valor de la operacion a double
		nMedia = (double)sumatorioNotas / numeroNotas;
		
		//Return
		return nMedia;
		
	}

}
