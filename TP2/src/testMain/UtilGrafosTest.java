package testMain;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import main.Grafo;
import main.KruskalBFS;
import main.UtilGrafos;

public class UtilGrafosTest {

	@Test 
	public void esConexoTest() {
		
		Grafo g = new Grafo(5);
		g.agregarArista(1, 0);
		g.agregarArista(0, 4);
		g.agregarArista(2, 3);
		Grafo g2 = new Grafo(5);
		g2.agregarArista(0, 1);
		g2.agregarArista(0, 2);
		g2.agregarArista(1, 3);
		g2.agregarArista(1, 0);
		g2.agregarArista(1, 4);
		g2.agregarArista(3, 1);
		g2.agregarArista(3, 4);
		g2.agregarArista(4, 3);
		g2.agregarArista(4, 1);
		g2.agregarArista(4, 2);
		
		Grafo g3= new Grafo(7);
		
		assertFalse(UtilGrafos.esConexo(g, 0));
    	assertTrue(UtilGrafos.esConexo(g2, 0));
    	assertFalse(UtilGrafos.esConexo(g3, 0));

	}
	
	@Test
	public void obtenerPesoArista() {
		
		Grafo g = new Grafo(5);
		g.agregarArista(1, 0);
		g.agregarArista(0, 4);
		g.agregarArista(2, 3);
		
		
		assertTrue(g.obtenerPesoArista(1, 4) > 1); // no existe la arista validas mayores a 1
		assertTrue(g.obtenerPesoArista(1, 0) >= 0 || g.obtenerPesoArista(1, 0) <= 1);
		assertTrue(g.obtenerPesoArista(0, 4) >= 0 || g.obtenerPesoArista(0, 4) <= 1);
		assertTrue(g.obtenerPesoArista(4, 3) >= 0 || g.obtenerPesoArista(4, 3) <= 1);

	}
	
	
	@Test
	public void formaCircuitoTestBFS() {
		
		Grafo g = new Grafo(5);
		g.agregarArista(1, 0);
		g.agregarArista(0, 4);
		g.agregarArista(2, 3);
		
		assertFalse(UtilGrafos.formaCircuito(g, 4, 2));
		assertFalse(UtilGrafos.formaCircuito(g, 4, 3));
		
		assertTrue(UtilGrafos.formaCircuito(g, 4, 1));
		assertTrue(UtilGrafos.formaCircuito(g, 1, 4));
	}

	@Test
	public void dameAristaDeMenorPesoBFSTest() {
		
		Grafo g = new Grafo(5);
		g.agregarArista(1, 0);
		g.agregarArista(0, 4);
		g.agregarArista(2, 3);
		
		g.agregarPesoArista(1, 0, 0.2f);
		g.agregarPesoArista(0, 4, 0.5f);
		g.agregarPesoArista(2, 3, 0.4f);
		
		
		HashMap<Integer, Integer> minimoActual = new HashMap<Integer, Integer>();
		minimoActual.put(0, 1);
		
		assertEquals(minimoActual, UtilGrafos.dameAristaDeMenorPesoBFS(g));
		
	}
	
	@Test
	public void dameVecinosVerticeTest() {
		
		Grafo g = new Grafo(5);
		g.agregarArista(1, 0);
		g.agregarArista(0, 4);
		g.agregarArista(2, 3);
		HashMap<Integer, Set<Integer>> vecinosList = UtilGrafos.dameVecinosVertice(g);
	
		
		Set<Integer> primerVertice = new HashSet<Integer>();
		primerVertice.add(1);
		primerVertice.add(4);
	
		Set<Integer> segundoVertice = new HashSet<Integer>();
		segundoVertice.add(3);
		
		assertEquals(vecinosList.get(0), primerVertice); 
		assertEquals(vecinosList.get(2), segundoVertice); 
		
	}
	
	
	@Test
	public void obtenerGrafoAleatorioTestFallo() {
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void obtenerGrafoAleatorioTest() {
		
		for(int i = 0; i < 30; i++)
			assertTrue(UtilGrafos.esConexo(UtilGrafos.obtenerGrafoAleatorio(4, 2), 0));
	}
	
	
	
}
