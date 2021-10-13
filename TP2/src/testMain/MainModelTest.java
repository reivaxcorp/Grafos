package testMain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.Grafo;
import main.KruskalBFS;
import main.KruskalUnionFind;
import main.MainModel;
public class MainModelTest {

	@Test
	public void MainModelTest() {
		MainModel mainModel = new MainModel();
		KruskalBFS kruskalBFS = new KruskalBFS();
		KruskalUnionFind kruskalUnionFind = new KruskalUnionFind();
		
		ArrayList<ArrayList<Grafo>> grafos = mainModel.crearGrafosCantSeleccionada(4, 3, 10);
		long tiempoNanoSegundosKruskalBFS = mainModel.calcularKruskalBFS(grafos.get(0), kruskalBFS);
		long tiempoNanoSegundosKruskalUnionFind = mainModel.calcularKruskalUnionFind(grafos.get(1), kruskalUnionFind);
		
		assertTrue(tiempoNanoSegundosKruskalBFS > 0);
		assertTrue(tiempoNanoSegundosKruskalUnionFind > 0);
	}

}
