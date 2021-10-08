import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import grafos.Grafo;

public class Util {

	
	public static boolean esConexa(Grafo g) {
	
		if(g == null) 
			throw new IllegalArgumentException("es null");
	   
		if(g.tamano() == 0)
			throw new IllegalArgumentException("El grafo no tiene vertices");
		
		
		Set<Integer> pendientes = new HashSet<>();
		
		ArrayList<Integer> marcados = new ArrayList<Integer>();
		
		for(int vertice = 0; vertice <  g.tamano(); vertice ++) {   
					
					Set<Integer> listaVecinos = g.vecinos(vertice);
		
						marcados.add(vertice);
						
						for(int vecino : listaVecinos) {
							
							if(!marcados.contains(vecino))
								pendientes.add(vecino);
							
						}
				
		}               
		          System.out.println(pendientes.size());                                 
		return false;
		
	}
	
}
