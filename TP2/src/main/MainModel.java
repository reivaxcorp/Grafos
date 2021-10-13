package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainModel implements KeyListener{
	
	private static final int MIN_GRAFOS_ALEATORIOS_POR_DEFECTO = 10;
	private static final int MAX_GRAFOS_ALEATORIOS_POR_DEFECTO = 40;
	
	private static final int MIN_VERTICES_ALEATORIOS_POR_DEFECTO = 4;
	private static final int MAX_VERTICES_ALEATORIOS_POR_DEFECTO = 40;
	
	private static final int MIN_ARISTA_ALEATORIOS_POR_DEFECTO = 3;
	private static final int MAX_ARISTA_ALEATORIOS_POR_DEFECTO = 100;
	


	private int cantGrafos; 
	private int cantAristas; 
	private int cantVertices; 
	
	private JTextField textFieldCantVertices;
	private JTextField textFieldCantidadAristas;
	private JTextField textFieldCantidadGrafos;
	
	private JLabel resultadoKruskalBFS;
	private JLabel resultadoKruscalUnionFind;
	private JLabel lblLabelError;

	private JButton btnComparar;
	
	private KruskalBFS kruskalBFS;
	private KruskalUnionFind kruskalUnionFind;
	private  Pattern pattern;
	
	public MainModel(
			JTextField textFieldCantVertices,
			JTextField textFieldCantidadAristas, 
			JTextField textFieldCantidadGrafos,
			JButton btnRandom, 
			JButton btnComparar,
			JLabel resultadoKruskalBFS,
			JLabel resultadoKruscalUnionFind, JLabel lblLabelError
			) {
		
		this.cantVertices = MIN_VERTICES_ALEATORIOS_POR_DEFECTO;
		this.cantAristas = MIN_ARISTA_ALEATORIOS_POR_DEFECTO;
		this.cantGrafos = MIN_GRAFOS_ALEATORIOS_POR_DEFECTO;
		
		this.textFieldCantVertices = textFieldCantVertices;
		this.textFieldCantidadAristas = textFieldCantidadAristas;
		this.textFieldCantidadGrafos = textFieldCantidadGrafos;

		this.btnComparar = btnComparar;
		
		this.resultadoKruskalBFS = resultadoKruskalBFS;
		this.resultadoKruscalUnionFind = resultadoKruscalUnionFind;

		this.kruskalBFS = new KruskalBFS();
		this.kruskalUnionFind = new KruskalUnionFind();
		
		this.lblLabelError = lblLabelError;
		
        this.pattern = Pattern.compile("\\D|\\s");

		textFieldCantVertices.setText(String.valueOf(cantVertices));
		textFieldCantidadAristas.setText(String.valueOf(cantAristas));
		textFieldCantidadGrafos.setText(String.valueOf(cantGrafos));

		
		btnRandom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int verticesRandom = obtenerNumeroAleatorioEntero(
						 MIN_VERTICES_ALEATORIOS_POR_DEFECTO,
						 MAX_VERTICES_ALEATORIOS_POR_DEFECTO
						);
				int aristasRandom = obtenerNumeroAleatorioEntero(
						verticesRandom -1,
						MAX_ARISTA_ALEATORIOS_POR_DEFECTO
						);
				int grafosRandom = obtenerNumeroAleatorioEntero(
						MIN_GRAFOS_ALEATORIOS_POR_DEFECTO,
						MAX_GRAFOS_ALEATORIOS_POR_DEFECTO
						);
				textFieldCantVertices.setText(String.valueOf(verticesRandom));
				textFieldCantidadAristas.setText(String.valueOf(aristasRandom));
				textFieldCantidadGrafos.setText(String.valueOf(grafosRandom));
				
				cantVertices = verticesRandom;
				cantAristas = aristasRandom;
				cantGrafos = grafosRandom;
				
				btnComparar.setEnabled(true);
				lblLabelError.setText("");
			}
		});
		btnComparar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				cantVertices = Integer.valueOf(textFieldCantVertices.getText());
				cantAristas = Integer.valueOf(textFieldCantidadAristas.getText());
				cantGrafos =  Integer.valueOf(textFieldCantidadGrafos.getText());
				
				ArrayList<Grafo> grafoKruscalBFS = crearGrafosCantSeleccionada(cantVertices, cantAristas, cantGrafos);
				ArrayList<Grafo> grafoKruscalUionFind = crearGrafosCantSeleccionada(cantVertices, cantAristas, cantGrafos);

				long tiempoNanoSegundosKruskalBFS = calcularKruskalBFS(grafoKruscalBFS);
				long tiempoNanoSegundosKruskalUnionFind = calcularKruskalUnionFind(grafoKruscalUionFind);
			    mostrarResultados(tiempoNanoSegundosKruskalBFS, tiempoNanoSegundosKruskalUnionFind);			
			}

		});
		textFieldCantVertices.addKeyListener(this);
		textFieldCantidadAristas.addKeyListener(this);
		textFieldCantidadGrafos.addKeyListener(this);
	}

   
   private void mostrarResultados(long kruskalBFS, long kruskalUnionFind) {
	   resultadoKruskalBFS.setText(String.valueOf(kruskalBFS)  + " nanosegundos");
	   resultadoKruscalUnionFind.setText(String.valueOf(kruskalUnionFind) + " nanosegundos");
   }
   
	private ArrayList<Grafo> crearGrafosCantSeleccionada(int cantVertices, int cantAristas, int cantGrafos) {
	
		ArrayList<Grafo> grafos = new ArrayList<Grafo>();
		for(int cant = 0; cant < cantGrafos; cant ++) {
			grafos.add(UtilGrafos.obtenerGrafoAleatorio(cantVertices, cantAristas));
		}
		return grafos;
	}
	
	private long calcularKruskalBFS(ArrayList<Grafo> grafos) {
		
		ArrayList<Long> tiempo = new ArrayList<Long>();
		
		for (int g = 0; g < grafos.size(); g++) {
			long start = System.nanoTime();
			kruskalBFS.arbolGeneradorMinimoBFS(grafos.get(g));
			long end = System.nanoTime();
			tiempo.add(end-start);
			
		}
		long tiempoTotal = 0;
		
		for (int i = 0; i < tiempo.size(); i++) {
			tiempoTotal += tiempo.get(i);
		}
		return tiempoTotal;
	}
	
    private long calcularKruskalUnionFind(ArrayList<Grafo> grafos) {
		

		ArrayList<Long> tiempo = new ArrayList<Long>();
		
		for (int g = 0; g < grafos.size(); g++) {
			long start = System.nanoTime();
			kruskalUnionFind.arbolGeneradorMinimoUnionFind(grafos.get(g));
			long end = System.nanoTime();
			tiempo.add(end-start);
			
		}
		long tiempoTotal = 0;
		
		for (int i = 0; i < tiempo.size(); i++) {
			tiempoTotal += tiempo.get(i);
		}
		return tiempoTotal;
	}

	private int obtenerNumeroAleatorioEntero(int min, int max) {
		Random numeroAleatorio = new Random();
		int numero= min + (numeroAleatorio.nextInt(max - min));          
		return numero;
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// no implementado
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// no implementado
	}


	@Override
	public void keyReleased(KeyEvent e) {
		    Matcher matcherVertices = pattern.matcher(textFieldCantVertices.getText());
		    Matcher matcherAristas = pattern.matcher(textFieldCantidadAristas.getText());
		    Matcher matcherGrafo = pattern.matcher(textFieldCantidadGrafos.getText());
		    
		    boolean noNumeroVertices = matcherVertices.find();
		    boolean noNumeroAristas = matcherAristas.find();
		    boolean noNumeroGrafo = matcherGrafo.find();
		    
		    if(noNumeroVertices || noNumeroAristas || noNumeroGrafo) {
		    	btnComparar.setEnabled(false);
		    	lblLabelError.setText("Valor ingresado invalido, solo numeros");
		    } else if (!noNumeroVertices && !noNumeroAristas && !noNumeroGrafo) {
		    	btnComparar.setEnabled(true);
		    	lblLabelError.setText("");
		    }
		    if(textFieldCantVertices.getText().isEmpty() || textFieldCantidadAristas.getText().isEmpty() || textFieldCantidadGrafos.getText().isEmpty()) {
		    	btnComparar.setEnabled(false);
		    	lblLabelError.setText("Ingrese un valor");
		    } 
		    
		    if(!noNumeroVertices &&
		       !noNumeroAristas &&
		       !noNumeroGrafo &&
		       !textFieldCantVertices.getText().isEmpty() &&
		       !textFieldCantidadAristas.getText().isEmpty() &&
		       (Integer.valueOf(textFieldCantidadAristas.getText()) <  
		        Integer.valueOf(textFieldCantVertices.getText()) -1)) {
		    	btnComparar.setEnabled(false);
		    	lblLabelError.setText("Arista debe ser superior a vertice -1");
		    }
		    
		    if(!noNumeroVertices &&
			   !noNumeroAristas &&
			   !noNumeroGrafo &&
		       !textFieldCantVertices.getText().isEmpty() &&
		       !textFieldCantidadAristas.getText().isEmpty() &&
		       !textFieldCantidadGrafos.getText().isEmpty()) {
		    	
			    if( Integer.valueOf(textFieldCantidadAristas.getText()) != MIN_ARISTA_ALEATORIOS_POR_DEFECTO ||
			        Integer.valueOf(textFieldCantVertices.getText()) != MIN_VERTICES_ALEATORIOS_POR_DEFECTO ||
			        Integer.valueOf(textFieldCantidadGrafos.getText()) != MIN_GRAFOS_ALEATORIOS_POR_DEFECTO){
			    	 btnComparar.setEnabled(false);
			        }
		    }
	}

}
