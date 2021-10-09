package grafos;

public class Arista {


	private int aristaInicio;
	private int aristaFinal;
	private float peso;
	
	public Arista(int aristaInicio, int aristaFinal, float peso) {
		this.aristaInicio = aristaInicio;
		this.aristaFinal = aristaFinal;
		this.peso = peso;
	}
	
	public int getAristaInicio() {
		return aristaInicio;
	}

	public void setAristaInicio(int aristaInicio) {
		this.aristaInicio = aristaInicio;
	}

	public int getAristaFinal() {
		return aristaFinal;
	}

	public void setAristaFinal(int aristaFinal) {
		this.aristaFinal = aristaFinal;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}


}
