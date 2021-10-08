import grafos.Grafo;

public class KruskalBFS {

	
	
	public static void main(String[] args) {
		Grafo g = new Grafo(5);
		g.agregarArista(1, 0);
		g.agregarArista(0, 4);
		g.agregarArista(2, 3);
		
		Grafo g2 = new Grafo(5);
		g2.agregarArista(0, 1);
		g2.agregarArista(0, 2);
		g2.agregarArista(1, 3);
		g2.agregarArista(2, 4);
		g2.agregarArista(1, 4);
		
		Util.esConexa(g2);
	}
	
	
	
}
