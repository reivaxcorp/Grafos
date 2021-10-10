package grafos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.DecimalFormatSymbols;

import grafos.Grafo;

public class UtilGrafos {

	
	public static boolean esConexo(Grafo g, int verticePartida) {
	
    	if(g == null) 
			throw new IllegalArgumentException("el grafo es null");
	   
		Set<Integer> pendientes = new HashSet<>(); // lista de pendientes
		pendientes.add(verticePartida);// marcamos primero
		
		Iterator<Integer> vertices = pendientes.iterator();
		ArrayList<Integer> marcados = new ArrayList<Integer>();

		while(vertices.hasNext()) {
			
			int vertice = vertices.next();
			
			marcados.add(vertice);
			vertices.remove(); // lo sacamos de la lista de pendientes
			
			Set<Integer> listaVecinos = g.vecinos(vertice);
			for(int vecino : listaVecinos) {
			 if(!marcados.contains(vecino)) // no agregamos uno ya marcado
				pendientes.add(vecino); 
			}
			vertices = pendientes.iterator(); // actualizar lista
		}        
		
		return g.tamano() == marcados.size();
		
	}

	public static HashMap<Integer, Set<Integer>> dameVecinosVertice(Grafo g) {
		
		  HashMap<Integer, Set<Integer>> vecinosList = new HashMap<>();
		  Set<Integer> vecinosYaAniadidos = new HashSet<Integer>();
		  
	      for (int vertice = 0; vertice < g.tamano(); vertice++) {
	    	
	    	  Set<Integer> listaVecinos = g.vecinos(vertice);
	    	  Set<Integer> grupoVecinos = new HashSet<Integer>();
	    	  
				for(int vecino : listaVecinos) {
					if(!vecinosYaAniadidos.contains(vecino)) // no agrega arista rendundante
					    grupoVecinos.add(vecino); 
				}
			
			  vecinosYaAniadidos.add(vertice);	
	    	  vecinosList.put(vertice, grupoVecinos); // guardamos los vecinos del vertice
		  
	        }
	      return vecinosList;
	}
	
    public static Set<Integer> dameAristaDeMenorPesoBFS(Grafo g, Grafo arbol) {
	
      /**
       * Donde el primer valor del Hashmap Integer, representa nuestro vertice del grafo, 
       * y el set seran los vecinos	
       */
      HashMap<Integer, Set<Integer>> vecinosList = dameVecinosVertice(g);
      HashMap<Integer, Set<Integer>> vecinosListArbol = dameVecinosVertice(arbol);
      
      
//      Set<Integer> minimoActual = new HashSet<Integer>();
      int verticeMinimo = 0; 
      int verticeVecinoMinimo = 0;
      float  pesoMinimo = 2.0f; // no exite arista de peso superior a 1. 2.0f es un buen numero para comparar 
  
      
      for (int vertice = 0; vertice < vecinosList.size(); vertice++) {
    	   
    	  
    		  for(int vecino : vecinosList.get(vertice)) {
    			  
    			  	
    			  if(g.existeArista(vertice, vecino)) {
//    	  			  System.out.println(""+vertice+ " " +vecino);

    				  float pesoArista = g.obtenerPesoArista(vertice, vecino);
//    				  System.out.println(pesoArista);
    				  if(pesoArista < pesoMinimo) {
    					 pesoMinimo  = pesoArista;
    					 verticeMinimo = vertice;
    					 verticeVecinoMinimo = vecino;
    				  }
    			  }
    		  	
    	  }

      }
      
      System.out.println(pesoMinimo);
      return null;
    	
	}
	
    // el vertice final es el que sera la arista selecionada que no forma circuito
    public static boolean formaCircuito(Grafo g, int verticePartida, int verticeFinal) {
    	
    	if(g == null) 
			throw new IllegalArgumentException("el grafo es null");
	   
		Set<Integer> pendientes = new HashSet<>(); // lista de pendientes
		pendientes.add(verticePartida);// marcamos primero
		
		Iterator<Integer> vertices = pendientes.iterator();
		ArrayList<Integer> marcados = new ArrayList<Integer>();

		while(vertices.hasNext()) {
			
			int vertice = vertices.next();
			
			marcados.add(vertice);
			vertices.remove(); // lo sacamos de la lista de pendientes
			
			Set<Integer> listaVecinos = g.vecinos(vertice);
			for(int vecino : listaVecinos) {
			 if(!marcados.contains(vecino)) // no agregamos uno ya marcado
				pendientes.add(vecino); 
			}
			vertices = pendientes.iterator(); // actualizar lista
		}        
	
    	return marcados.contains(verticeFinal);
    }
    
    
	public static float obtenerPesoAleatorio() {
		Random rd = new Random();
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat truncar = new DecimalFormat("##.#", simbolos);
		int min = 0;
		int max = 1;
		float numeroAleatorio = min + rd.nextFloat() * (max - min);
		float num = Float.parseFloat(truncar.format(numeroAleatorio));
		return  num;
	}
	
	
}
