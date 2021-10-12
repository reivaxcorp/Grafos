package testMain;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Test;

import main.Grafo;
import main.KruskalUnionFind;
import main.UtilGrafos;

public class KruskalUnionFindTest {

	/**
	 * Kruskal
	 * @param g
	 * @param verticePartida
	 * @return
	 */
	
	@Test
	public void generarArbolMinimoUnionFind() {
		
		    KruskalUnionFind kuf = new KruskalUnionFind();
		
	     	Grafo g = new Grafo(7);
	        
			g.agregarArista(2, 1);
			g.agregarArista(1, 3);
			g.agregarArista(5, 3);
			g.agregarArista(0, 4);
			g.agregarArista(6, 4);
			g.agregarArista(5, 0);
			
			g.agregarPesoArista(2, 1, 0.5f);
			g.agregarPesoArista(1, 3, 0.2f);
			g.agregarPesoArista(5, 3, 0.6f);
			g.agregarPesoArista(0, 4, 0.97f);
			g.agregarPesoArista(6, 4, 0.96f);
			g.agregarPesoArista(5, 0, 0.99f);
			
			Grafo arbol = kuf.arbolGeneradorMinimoUnionFind(g);
		

	 		// donde el primero es el vertice y el set son sus vecinos
			HashMap<Integer, Set<Integer>> arbolGeneradorVecinos = UtilGrafos.dameVecinosVertice(arbol);
			for(Entry<Integer, Set<Integer>> vertice : arbolGeneradorVecinos.entrySet()) {
				for(int vecinos: vertice.getValue()) {
					System.out.println("vertice " + vertice.getKey() + " vecinos " + vecinos );
				}
			}
			
		
	 		assertTrue(arbol.existeArista(5, 6)); // union
	 		
	 		assertFalse(arbol.existeArista(2, 5)); // forman ciclos
	 		assertFalse(arbol.existeArista(5, 0)); // forman ciclos
	}

}
