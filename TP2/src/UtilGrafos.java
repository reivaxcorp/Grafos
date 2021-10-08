import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import grafos.Grafo;

public class UtilGrafos {

	
	public static boolean esConexo(Grafo g) {
	
    	if(g == null) 
			throw new IllegalArgumentException("es null");
	   
		if(g.tamano() == 0)
			throw new IllegalArgumentException("El grafo no tiene vertices");
		
		
		Set<Integer> pendientes = new HashSet<>(); // lista de pendientes
		pendientes.add(0);// marcamos uno arbritario
		
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
	
}
