package testMain;
import static org.junit.Assert.*;

import org.junit.Test;

import grafos.Grafo;
import grafos.UtilGrafos;

public class UtilGrafosTest {

	@Test 
	public void esConexoTest() {
		
		Grafo g = new Grafo(5);
		g.agregarArista(1, 0);
		g.agregarArista(0, 4);
		g.agregarArista(2, 3);
		Grafo g2 = new Grafo(7);
		g2.agregarArista(0, 1);
		g2.agregarArista(0, 2);
		g2.agregarArista(1, 3);
		g2.agregarArista(2, 4);
		g2.agregarArista(1, 4);
		g2.agregarArista(1, 5);
		g2.agregarArista(1, 6);
		Grafo g3= new Grafo(7);
		
		assertFalse(UtilGrafos.esConexo(g, 0));
    	assertTrue(UtilGrafos.esConexo(g2, 0));
    	assertFalse(UtilGrafos.esConexo(g3, 0));

	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void obtenerPesoArista() {
		
		Grafo g = new Grafo(5);
		g.agregarArista(1, 0);
		g.agregarArista(0, 4);
		g.agregarArista(2, 3);
		
		
		assertTrue(g.obtenerPesoArista(0, 0) < 0); // no existe la arista -1
		assertTrue(g.obtenerPesoArista(1, 0) >= 0 || g.obtenerPesoArista(1, 0) <= 1);
		assertTrue(g.obtenerPesoArista(0, 4) >= 0 || g.obtenerPesoArista(0, 4) <= 1);
		assertFalse(g.obtenerPesoArista(4, 3) >= 0 || g.obtenerPesoArista(4, 3) <= 1);

	}
	
	@Test
	public void arbolGeneradorMinimo() {
		
	}
	
	
//	@Test
//	public void distanciaDFSTest() {
//		Grafo g = new Grafo(5);
//		g.agregarArista(1, 0);
//		g.agregarArista(0, 4);
//		g.agregarArista(2, 3);
//		Grafo g2 = new Grafo(6);
//		g2.agregarArista(0, 1);
//		g2.agregarArista(0, 2);
//		g2.agregarArista(1, 3);
//		g2.agregarArista(2, 4);
////		g2.agregarArista(1, 4);
//		g2.agregarArista(1, 5);
////		g2.agregarArista(1, 6);
//		Grafo g3 = new Grafo(1);
//		
////		assertEquals(2, UtilGrafos.distanciaDFS(g, 1, 4));
//		assertEquals(2, UtilGrafos.distanciaDFS(g2, 0, 4));
////		assertEquals(0, UtilGrafos.distanciaDFS(g3, 0, 0));
//	}


}
