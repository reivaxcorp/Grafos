package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import grafos.UtilGrafos;

import grafos.Grafo;

public class KruskalBFS {


	
	public Grafo arbolGeneradorMinimo(Grafo g) {
		
		if(g == null) 
			throw new IllegalArgumentException("el grafo es null");
		
		if(UtilGrafos.esConexo(g, 0) == false)
			throw new IllegalArgumentException("el arbol no es conexo");
		
		
		Grafo  arbolGeneradorMinimo = new Grafo(g.tamano());
		Grafo copiaGrafo = null;
		int verticeActual = 0;

		try {
			// iremos quitando las aristas minimas sucesivamente. 
			copiaGrafo = (Grafo) g.clone();  
			// la catidad de aristas esa menos uno con la cantidad de vertices.
			// el index 0 de nuestro grafo es el primer vertice
			while(verticeActual < g.tamano()) {
				
				// retorna un par de vertices, el vertice y el vecino de menor peso
				HashMap<Integer, Integer> aristaMenor = UtilGrafos.dameAristaDeMenorPesoBFS(copiaGrafo); 
	             
	            for(Entry<Integer, Integer> vertice: aristaMenor.entrySet()) {
	            	int pVertice = vertice.getKey();
	            	int pVecino = vertice.getValue();
//	            	System.out.println(pVertice +" "+ pVecino);
//          	    System.out.println(UtilGrafos.formaCircuito(arbolGeneradorMinimo, pVertice, pVecino));
	            	if(UtilGrafos.formaCircuito(arbolGeneradorMinimo, pVertice, pVecino) == false) {
	            		
	            		arbolGeneradorMinimo.agregarArista(pVertice, pVecino);
	            		arbolGeneradorMinimo.agregarPesoArista(pVertice, pVecino, copiaGrafo.obtenerPesoArista(pVertice, pVecino));
	            		
	            		copiaGrafo.eliminarArista(pVertice, pVecino); // ya no necesitamos esta arista en la siguiente iteracion
	            	} else {
	            		copiaGrafo.eliminarArista(pVertice, pVecino); // encontro una arista que forma circuito, la omitimos en la siguiente iteracion
	            	}
	            	
	            }
				
			verticeActual ++;
			}
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return arbolGeneradorMinimo; 
	}
	
	
	
}
