package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.DecimalFormatSymbols;


public class UtilGrafos {

	private static Random rd = new Random();
/**
 * 	
 * @param g grafo para verificar si es conexo.
 * @param verticePartida un vertice de partida, usualmente cero.
 * @return devuelve true si es conexo.
 */
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
		
		  // el vertice y su conjunto de vecinos
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
	
    public static HashMap<Integer, Integer> dameAristaDeMenorPesoBFS(Grafo g) {
	
      /**
       * Donde el primer valor del Hashmap Integer, representa nuestro vertice del grafo, 
       * y el set seran los vecinos	
       */
      HashMap<Integer, Set<Integer>> vecinosList = dameVecinosVertice(g);
      
      HashMap<Integer, Integer> minimoActual = new HashMap<Integer, Integer>();
      int verticeMinimo = 0; 
      int verticeVecinoMinimo = 0;
      float  pesoMinimo = 2.0f; // no exite arista de peso superior a 1. 2.0f es un buen numero para comparar 
      
      
      for (int vertice = 0; vertice < vecinosList.size(); vertice++) {
    		  for(int vecino : vecinosList.get(vertice)) {
  
    			  if(g.existeArista(vertice, vecino)) {
//    	  			  System.out.println(""+vertice+ " " +vecino);

    				  float pesoArista = g.obtenerPesoArista(vertice, vecino);
    				  if(pesoArista == 2.0f)
    					  throw new IllegalArgumentException("La arista no tiene peso "); 
//    				  System.out.println(pesoArista);
    				  if(pesoArista < pesoMinimo) {
    					 pesoMinimo  = pesoArista;
    					 verticeMinimo = vertice;
    					 verticeVecinoMinimo = vecino;
    				  }
    			  }
    	  }
      }
      minimoActual.put(verticeMinimo, verticeVecinoMinimo);
      return minimoActual;
    	
	}
	
    /**
	 *  
	 * @param g es el arbol que estamos generando
	 * @param verticePartida es el vertice donde comenzamos a inicializar el recorrido de vecinos
	 * @param verticeFinal si no forma circuito entonces, lo podemos agregar. 
	 * @return retorna verdadero si hay circuito de lo contrario, retorna falso.
	 */
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
		// si es True, estan en la misma componente conexa, de lo contrario no. 
    	return marcados.contains(verticeFinal); 
    }
    
	public static float obtenerPesoAleatorio() {

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat truncar = new DecimalFormat("##.#", simbolos);
		int min = 0;
		int max = 1;
		float numeroAleatorio = min + rd.nextFloat() * (max - min);
		float num = Float.parseFloat(truncar.format(numeroAleatorio));
		return  num;
	}
	
	/**
	 * 
	 * @param cantidadVertices el numero de vertice minimo
	 * @param cantidadAritas el numero de aristas maximo
	 * @return
	 */
	public static Grafo obtenerGrafoAleatorio(int cantidadVertices, int cantidadAristas) {
		
		if(!(cantidadAristas >= cantidadVertices -1))
			throw new IllegalArgumentException("cantidad de aristas debe ser mayor o igual a cantidad vertices menos uno");

		Grafo g = new Grafo(cantidadVertices);
//		System.out.println("tamanio grafo "+g.tamano());

    	conexar(g); // lo hacemos conexo
		
		int aristaIndex = 0;
		int verticeIndex = 0;
		
		// descontamos las aristas de el metodo conexo (cantidadVertices -1)
		while(aristaIndex < cantidadAristas - (cantidadVertices -1)) {
			
			if(verticeIndex < cantidadVertices) {

				int verticeAleatorio = obtenerAristaAleatoriaValida(verticeIndex, cantidadVertices);
				g.agregarArista(verticeIndex, verticeAleatorio);
				g.agregarPesoArista(verticeIndex, verticeAleatorio, UtilGrafos.obtenerPesoAleatorio());
//			System.out.println("vertice seleccionado aleatorio para: " +verticeIndex+ " es: " +verticeAleatorio);
				aristaIndex ++;
				verticeIndex ++;
 
			} else {
				verticeIndex = 0;
			}
			
		}		
		return g;
	}

	private static int obtenerAristaAleatoriaValida(int verticeActual, int cantidadVertices) {
		
		ArrayList<Integer> verticesValidos = new ArrayList<Integer>();
		
		for(int vertice = verticeActual + 1; vertice < cantidadVertices; vertice ++ ) {
			verticesValidos.add(vertice);
		} 
		for(int vertice = verticeActual - 1; vertice >= 0; vertice -- ) {
			verticesValidos.add(vertice);
		}
		
//		for(int vertices: verticesValidos)
//			System.out.println(vertices);
		int numero = rd.nextInt(verticesValidos.size());
		
		return verticesValidos.get(numero);
	}
	
	// conexamos aleatoreamente a todas las aristas, luego agregamos aleatoriamente mas. 
	// la cantidad de aristas es V -1
		private static Grafo conexar(Grafo g) {
			Grafo grafoConexo = g;
			int padre = rd.nextInt(g.tamano());
			for(int vertice = 0; vertice < grafoConexo.tamano(); vertice ++) {
				
				if(vertice != padre) {
					grafoConexo.agregarArista(padre, vertice);
					grafoConexo.agregarPesoArista(padre, vertice, UtilGrafos.obtenerPesoAleatorio());
				}
			}
			return grafoConexo;
		}

}
