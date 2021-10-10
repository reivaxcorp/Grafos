package testMain;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Test;
import grafos.Grafo;
import grafos.UtilGrafos;
import main.KruskalBFS;

public class KruskalBFSTest {

	/**
	 * Kruskal
	 * @param g
	 * @param verticePartida
	 * @return
	 */
	
	@Test
	public void generarArbolMinimo() {
		
        KruskalBFS kbfs = new KruskalBFS();
        
        Grafo g = new Grafo(5);
        
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(1, 3);
		g.agregarArista(1, 4);
		g.agregarArista(3, 4);
		g.agregarArista(4, 2);
		
		g.agregarPesoArista(0, 1, 0.11f);
		g.agregarPesoArista(0, 2, 0.23f);
		g.agregarPesoArista(1, 3, 0.45f);
		g.agregarPesoArista(1, 4, 0.45f);
		g.agregarPesoArista(3, 4, 0.34f);
		g.agregarPesoArista(4, 2, 0.43f);

	
		 
 		Grafo arbol = kbfs.arbolGeneradorMinimo(g);
 		
 		// donde el primero es el vertice y el set son sus vecinos
		HashMap<Integer, Set<Integer>> arbolGeneradorVecinos = UtilGrafos.dameVecinosVertice(arbol);
		for(Entry<Integer, Set<Integer>> vertice : arbolGeneradorVecinos.entrySet()) {
			for(int vecinos: vertice.getValue()) {
				System.out.println("vertice " + vertice.getKey() + " vecinos " + vecinos );
			}
		}
 		
 		assertTrue(arbol.existeArista(0, 1));
 		assertTrue(arbol.existeArista(0, 2));
 		assertTrue(arbol.existeArista(2, 4));
 		assertTrue(arbol.existeArista(3, 4));
 		
 		assertFalse(arbol.existeArista(1, 3)); // forman ciclos
 		assertFalse(arbol.existeArista(1, 4)); // forman ciclos
	}
}
