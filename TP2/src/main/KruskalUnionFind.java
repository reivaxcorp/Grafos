package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class KruskalUnionFind {

	
	public Grafo arbolGeneradorMinimoUnionFind(Grafo g) {
		
		if(g == null) 
			throw new IllegalArgumentException("el grafo es null");

		if(UtilGrafos.esConexo(g, 0) == false)
			throw new IllegalArgumentException("el grafo no es conexo");
		
		
		Grafo  arbolGeneradorMinimo = new Grafo(g.tamano());
		Grafo copiaGrafo = null;
		int verticeActual = 0;

		try {
			// iremos quitando las aristas minimas sucesivamente. 
			copiaGrafo = (Grafo) g.clone();  
			// la catidad de aristas esa menos uno con la cantidad de vertices.
			// el index 0 de nuestro grafo es el primer vertice
			while(verticeActual < g.tamano() -1) {
				
				// retorna un par de vertices, el vertice y el vecino de menor peso
				HashMap<Integer, Integer> aristaMenor = UtilGrafos.dameAristaDeMenorPesoBFS(copiaGrafo); 
	             
	            for(Entry<Integer, Integer> vertice: aristaMenor.entrySet()) {
	            	int pVertice = vertice.getKey();
	            	int pVecino = vertice.getValue();
//                	System.out.println(pVertice +" "+ pVecino + " " + verticeActual);
//          	    System.out.println(UtilGrafos.formaCircuito(arbolGeneradorMinimo, pVertice, pVecino));
	            	if(UtilGrafos.formaCircuito(arbolGeneradorMinimo, pVertice, pVecino) == false) {

	            		arbolGeneradorMinimo.agregarArista(pVertice, pVecino);
	            		arbolGeneradorMinimo.agregarPesoArista(pVertice, pVecino, copiaGrafo.obtenerPesoArista(pVertice, pVecino));
	            	
	            		copiaGrafo.eliminarArista(pVertice, pVecino); // ya no necesitamos esta arista en la siguiente iteracion
		            
	            		unionFind(arbolGeneradorMinimo);

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
	
	/**
	 * 
	 * @param arbol que vamos contruyendo
	 * @param grafo el grafo en que debemos ver si tenemos alguna arista que conectar
	 * @return modificamos el arbol y el grafo por referencia
	 */
    private static void unionFind(Grafo arbol) {
    	
       if(UtilGrafos.esConexo(arbol, 0) == false) {

//    	System.out.println("ITERACION");
//      	System.out.println("raiz "+  raiz(g, 0));
    	Set<Integer> raices = new HashSet<Integer>();
      	for(int vertice = 0; vertice < arbol.tamano(); vertice ++) {
      		int raiz = raiz(arbol, vertice);
      		// que no sea un vertice sin vecinos
      		if(raiz != vertice && !raices.contains(raiz)) {
//      	    	System.out.println("ITERACION");
//          	    System.out.println("raiz "+  raiz);
      			raices.add(raiz);
      		}
      	}
//      	System.out.println("tamaño union " + raices.size());
      	
      	// encontro dos raices que se podrian unir
      	if(raices.size() == 2) {
      		union(arbol, raices);
      	}
       }
    	
    }
    /**
     * 
     * @param arbol el arbol de kruskal que estamos generando
     * @param raices las raices de nuestro componentes conexas
     * @return modificamos el arbol y el grafo por referencia
     */
    public static void union(Grafo arbol, Set<Integer> raices) {
    	   
    	   int [] raizUnion = new int[2];
    	   Iterator<Integer> it = raices.iterator();
    	   int index = 0;
    	   while(it.hasNext()) {
    		   raizUnion[index] = it.next();
    		   index ++;
    	   }
    	 arbol.agregarArista(raizUnion[0], raizUnion[1]);	
    	 arbol.agregarPesoArista(raizUnion[0], raizUnion[1], UtilGrafos.obtenerPesoAleatorio());
    }
    
	public static int raiz(Grafo g, int vertice) {
		
		   Set<Integer> padre = new HashSet<Integer>();
		   padre.add(vertice);	

		   Set<Integer> listaVecinos = new HashSet<Integer>();
		   listaVecinos.addAll(g.vecinos(vertice));
		   
	       int cantVecinos = 0;
		   while(cantVecinos < listaVecinos.size()) {
			
			   for(int vecino: listaVecinos) {
				   if(!padre.contains(vecino)) {
					   padre.add(vecino);	
//					   System.out.println(vecino);
				   }
				   cantVecinos ++;
			   }
			   listaVecinos.clear();
			   cantVecinos = 0;
			   
			   for(int padreSiguiente: padre) {
				   
				   Set<Integer> padreSiguientes = new HashSet<Integer>();
				   padreSiguientes = g.vecinos(padreSiguiente);
				   for(int vecino: padreSiguientes) {
					   if(!padre.contains(vecino)) {
						   listaVecinos.add(vecino);
					   }
				   }
				   
			   }
		   }           
		     
		    int raiz = 0;
		    Iterator<Integer> it = padre.iterator();
		    while(it.hasNext()) {
		    	raiz = it.next();
		    }
		   
	       return raiz;
	}
    
    
}
