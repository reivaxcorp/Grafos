package grafos;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Grafo implements Cloneable
{
	// Representamos el grafo por su matriz de adyacencia
	private boolean[][] A;
	// Peso de las Aristas
	private HashMap<String, Float> pesoAristas;
	
	
	// La cantidad de vertices esta predeterminada desde el constructor
	public Grafo(int vertices)
	{
		A = new boolean[vertices][vertices];
	    pesoAristas = new HashMap<>();
	}

	// Agregado de aristas
	public void agregarArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		
		A[i][j] = true;
		A[j][i] = true;
		
	}
	
	public void agregarPesoArista(int i, int j, float peso) {
		
		if(peso >= 0 && peso <= 1) { 
			pesoAristas.put(""+i+j, peso); // entre 0 y 1
			pesoAristas.put(""+j+i, peso); // agregamos en los dos sentidos el peso
		} else {
			throw new IllegalArgumentException("El peso de la arista debe estar entre 0 y 1");
		}
		
	}
	
	public float obtenerPesoArista(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		
		if(pesoAristas.get(""+i+j) != null) {
			return pesoAristas.get(""+i+j);
		}else {
			return 2.0f; // no existen arista validas de peso superior a 2.0f
		}
	}
	
	// Eliminacion de aristas
	public void eliminarArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		A[i][j] = false;
		A[j][i] = false;
	}

	// Informa si existe la arista especificada
	public boolean existeArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return A[i][j];
	}

	// Cantidad de vertices
	public int tamano()
	{
		return A.length;
	}
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i)
	{
		verificarVertice(i);
		
		Set<Integer> ret = new HashSet<Integer>();
		for(int j = 0; j < this.tamano(); ++j) 
		
		if( i != j ){
			if( this.existeArista(i,j) )
				ret.add(j);
		}
		
		return ret;		
	}
	
	// Verifica que sea un vertice valido
	private void verificarVertice(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= A.length )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j)
	{
		if( i == j )
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
	
	
	public boolean[][] getGrafo() {
		boolean[][] grafo = A.clone();
		return grafo;
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
