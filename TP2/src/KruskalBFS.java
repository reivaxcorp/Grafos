import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import grafos.Grafo;

public class KruskalBFS {

	
	
	public static void main(String[] args) {
	
	}
	
	
	
	
	public Grafo arbolGeneradorMinimo(Grafo g) {
		
		if(g == null) 
			throw new IllegalArgumentException("el grafo es null");
		
		if(UtilGrafos.esConexo(g) == false)
			throw new IllegalArgumentException("el arbol no es conexo");
		
		int aristaActual = 0;
		Set<Integer> conjuntoAristas = new HashSet<>();
		
		Grafo arbolGenerador = new Grafo(g.tamano());
		
		// la catidad de aristas esa menos uno con la cantidad de vertices.
		// el index 0 de nuestro grafo es el primer vertice
		while(aristaActual < g.tamano()) {
			
			
			aristaActual ++;
		}
		
		return null; 
	}
	
	
	
}
