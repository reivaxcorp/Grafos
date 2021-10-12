package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import grafos.Grafo;
import grafos.UtilGrafos;

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
	
	private KruskalBFS kruskalBFS;
	private KruskalUnionFind kruskalUnionFind;
	
	public MainModel(
			JTextField textFieldCantVertices,
			JTextField textFieldCantidadAristas, 
			JTextField textFieldCantidadGrafos,
			JButton btnRandom, 
			JButton btnComparar,
			JLabel resultadoKruskalBFS,
			JLabel resultadoKruscalUnionFind
			) {
		
		this.cantVertices = MIN_VERTICES_ALEATORIOS_POR_DEFECTO;
		this.cantAristas = MIN_ARISTA_ALEATORIOS_POR_DEFECTO;
		this.cantGrafos = MIN_GRAFOS_ALEATORIOS_POR_DEFECTO;
		
		this.textFieldCantVertices = textFieldCantVertices;
		this.textFieldCantidadAristas = textFieldCantidadAristas;
		this.textFieldCantidadGrafos = textFieldCantidadGrafos;
		
		this.kruskalBFS = new KruskalBFS();
		this.kruskalUnionFind = new KruskalUnionFind();
		
		

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
						MIN_ARISTA_ALEATORIOS_POR_DEFECTO,
						MAX_ARISTA_ALEATORIOS_POR_DEFECTO
						);
				int grafosRandom = obtenerNumeroAleatorioEntero(
						MIN_ARISTA_ALEATORIOS_POR_DEFECTO,
						MAX_ARISTA_ALEATORIOS_POR_DEFECTO
						);
				textFieldCantVertices.setText(String.valueOf(verticesRandom));
				textFieldCantidadAristas.setText(String.valueOf(aristasRandom));
				textFieldCantidadGrafos.setText(String.valueOf(grafosRandom));
				
				cantVertices = verticesRandom;
				cantAristas = aristasRandom;
				cantGrafos = grafosRandom;
				
			}
		});
		btnComparar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Grafo> grafos = crearGrafosCantSeleccionada();
			}

		});
		textFieldCantVertices.addKeyListener(this);
	}


	private ArrayList<Grafo> crearGrafosCantSeleccionada() {
		ArrayList<Grafo> grafos = new ArrayList<Grafo>();
		for(int cant = 0; cant < cantGrafos; cant ++) {
			grafos.add(UtilGrafos.obtenerGrafoAleatorio(cantVertices, cantAristas));
		}
		return grafos;
	}
	
	private int calcularKruskalBFS(ArrayList<Grafo> grafos) {
		
		return 0;
	}
	
    private int calcularKruskalUnionFind(ArrayList<Grafo> grafos) {
    	return 0;
	}

	private int obtenerNumeroAleatorioEntero(int min, int max) {
		Random numeroAleatorio = new Random();
		int numero= min + (numeroAleatorio.nextInt(max - min));
		return numero;
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
