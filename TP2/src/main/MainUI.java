package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MainUI {

	private JFrame frmArbolGeneradorMinimo;
	private JTextField textFieldCantVertices;
	private JTextField textFieldCantidadAristas;
	private JTextField textFieldCantidadGrafos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmArbolGeneradorMinimo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmArbolGeneradorMinimo = new JFrame();
		frmArbolGeneradorMinimo.setTitle("Arbol Generador Minimo");
		frmArbolGeneradorMinimo.setBounds(100, 100, 450, 300);
		frmArbolGeneradorMinimo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArbolGeneradorMinimo.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kruskal BFS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(63, 208, 82, 14);
		frmArbolGeneradorMinimo.getContentPane().add(lblNewLabel);
		
		JLabel lblKruskalUnionFind = new JLabel("Kruskal Union Find");
		lblKruskalUnionFind.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKruskalUnionFind.setBounds(249, 208, 128, 14);
		frmArbolGeneradorMinimo.getContentPane().add(lblKruskalUnionFind);
		

		JLabel lblNewLabel_1 = new JLabel("Tiempos de ejecution del Algoritmo \r\nde Kruskal");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(48, 11, 329, 14);
		frmArbolGeneradorMinimo.getContentPane().add(lblNewLabel_1);
		

		JLabel lblNewLabel_2 = new JLabel("Cantidad Vertices");
		lblNewLabel_2.setBounds(72, 98, 127, 17);
		frmArbolGeneradorMinimo.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cantidad Aristas");
		lblNewLabel_2_1.setBounds(72, 125, 127, 17);
		frmArbolGeneradorMinimo.getContentPane().add(lblNewLabel_2_1);
		
		JLabel labelTiempoKruskal = new JLabel("");
		labelTiempoKruskal.setBounds(83, 98, 46, 14);
		frmArbolGeneradorMinimo.getContentPane().add(labelTiempoKruskal);
		
		JLabel labelTiempoUnionFind = new JLabel("");
		labelTiempoUnionFind.setBounds(277, 98, 46, 14);
		frmArbolGeneradorMinimo.getContentPane().add(labelTiempoUnionFind);
		
		
		JButton btnComparar = new JButton("Comparar");
		btnComparar.setBounds(148, 166, 112, 20);
		frmArbolGeneradorMinimo.getContentPane().add(btnComparar);
	
		JButton btnRandom = new JButton("Aleatorio");
		btnRandom.setBounds(322, 89, 89, 23);
		frmArbolGeneradorMinimo.getContentPane().add(btnRandom);
				
		JLabel lblNewLabel_2_2 = new JLabel("Cantidad de Grafos");
		lblNewLabel_2_2.setBounds(72, 70, 127, 17);
		frmArbolGeneradorMinimo.getContentPane().add(lblNewLabel_2_2);
		
		JLabel resultadoKruskalBFS = new JLabel("");
		resultadoKruskalBFS.setBounds(34, 233, 133, 17);
		frmArbolGeneradorMinimo.getContentPane().add(resultadoKruskalBFS);
		
		JLabel resultadoKruscalUnionFind = new JLabel("");
		resultadoKruscalUnionFind.setBounds(238, 233, 128, 17);
		frmArbolGeneradorMinimo.getContentPane().add(resultadoKruscalUnionFind);
		
		
		
		textFieldCantVertices = new JTextField();
		textFieldCantVertices.setBounds(209, 95, 86, 20);
		frmArbolGeneradorMinimo.getContentPane().add(textFieldCantVertices);
		textFieldCantVertices.setColumns(10);
		

		textFieldCantidadAristas = new JTextField();
		textFieldCantidadAristas.setColumns(10);
		textFieldCantidadAristas.setBounds(209, 122, 86, 20);
		frmArbolGeneradorMinimo.getContentPane().add(textFieldCantidadAristas);
		

		textFieldCantidadGrafos = new JTextField();
		textFieldCantidadGrafos.setColumns(10);
		textFieldCantidadGrafos.setBounds(209, 68, 86, 20);
		frmArbolGeneradorMinimo.getContentPane().add(textFieldCantidadGrafos);
		
		new MainModel(
				textFieldCantVertices, 
				textFieldCantidadAristas,
				textFieldCantidadGrafos,
				btnRandom,
				btnComparar,
				resultadoKruskalBFS,
				resultadoKruscalUnionFind
				);

		
	}
	

}
