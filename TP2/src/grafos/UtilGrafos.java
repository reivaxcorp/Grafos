package grafos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.DecimalFormatSymbols;

import grafos.Grafo;

public class UtilGrafos {

	
	public static boolean esConexo(Grafo g, int verticePartida) {
	
    	if(g == null) 
			throw new IllegalArgumentException("es null");
	   
		Set<Integer> pendientes = new HashSet<>(); // lista de pendientes
		pendientes.add(verticePartida);// marcamos primero
		
		Iterator<Integer> vertices = pendientes.iterator();
		ArrayList<Integer> marcados = new ArrayList<Integer>();

		while(vertices.hasNext()) {
			
			int vertice = vertices.next();
			
			marcados.add(vertice);
			vertices.remove(); // lo sacamos de la lista de pendientes
			
			Set<Integer> listaVecinos = g.vecinos(vertice);
			for(int vecino : listaVecinos) {
			 if(!marcados.contains(vecino)) // no agregamos uno ya marcado
				pendientes.add(vecino); 
			}
			vertices = pendientes.iterator(); // actualizar lista
		}        
		
		return g.tamano() == marcados.size();
		
	}
	
	/**
	 * Kruskal
	 * @param g
	 * @param verticePartida
	 * @return
	 */
	public Grafo generarArbolMinimo(Grafo g, int verticePartida) {
		
		return null;
	}
	
//	public static int distanciaDFS(Grafo g, int verticeInicial, int verticeFinal ) {
//
//		if(g == null) 
//			throw new IllegalArgumentException("es null");
//
//		if(verticeInicial < 0 || verticeFinal < 0)
//			throw new IllegalArgumentException("vertice negativo");
//
//		Set<Integer> pendientes = new HashSet<>(); // lista de pendientes
//		pendientes.add(verticeInicial);// marcamos primero
//		
//		Iterator<Integer> vertices = pendientes.iterator();
//		ArrayList<Integer> marcados = new ArrayList<Integer>();
//        int distancia = 0;
//     
//		while(vertices.hasNext()) {
//			
//			int vertice = vertices.next();
//			
//			marcados.add(vertice);
//			vertices.remove(); // lo sacamos de la lista de pendientes
//			distancia ++;
//			
//			Set<Integer> listaVecinos = g.vecinos(vertice);
//			for(int vecino : listaVecinos) {
//
//				if(!marcados.contains(vecino)) { // no agregamos uno ya marcado
//					pendientes.add(vecino); 
//				}
//
//				if(vecino == verticeFinal) {
//					return distancia;
//				}  
//				
//			}
//			
//			vertices = pendientes.iterator(); // actualizar lista
//		}        
//		
////		for(int vert: marcados) {
////			System.out.println(vertices);
////		}
//		return distancia;
//	}
	
	public static float obtenerPesoAleatorio() {
		Random rd = new Random();
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat truncar = new DecimalFormat("##.#", simbolos);
		int min = 0;
		int max = 1;
		float numeroAleatorio = min + rd.nextFloat() * (max - min);
		float num = Float.parseFloat(truncar.format(numeroAleatorio));
		return  num;
	}
	
	public void dameAristaMasBarata(Grafo g) {
		
	}
}
