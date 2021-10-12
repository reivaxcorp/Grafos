package grafos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.DecimalFormatSymbols;


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
	
	public static Grafo obtenerGrafoAleatorio() {
		int cantidadVertices = obtenerNumeroAleatorioEntero(4, 20); // vertices entre 4 y 20
		Grafo g = new Grafo(cantidadVertices);
		
		Set<Integer> aristas = new HashSet<Integer>();
		
		for(int vertice = 0; vertice < cantidadVertices; vertice ++) {
			int aristaAleatoria = obtenerNumeroAleatorioEntero(vertice, cantidadVertices);
			
		}
		
		return null;
	}
	private static int obtenerAristaAleatoriaValida(int verticeActual, int totalVertices) {
		
		ArrayList<Integer> verticesValidos = new ArrayList<Integer>();
		
		for(int vertice = verticeActual + 1; vertice < totalVertices; vertice ++ ) {
			verticesValidos.add(vertice);
		} 
		for(int vertice = verticeActual - 1; vertice > 0; vertice -- ) {
			verticesValidos.add(vertice);
		}
		
		Random numeroAleatorio = new Random();
		int numero= 0 + numeroAleatorio.nextInt() * (verticesValidos.size() - 0);
		
		return verticesValidos.get(numero);
	}
	private static int obtenerNumeroAleatorioEntero(int min, int max) {
		Random numeroAleatorio = new Random();
		int numero= min + numeroAleatorio.nextInt() * (max - min);
		return numero;
	}
}
