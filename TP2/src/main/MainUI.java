package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class MainUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kruskal BFS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(73, 188, 82, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblKruskalUnionFind = new JLabel("Kruskal Union Find");
		lblKruskalUnionFind.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKruskalUnionFind.setBounds(249, 188, 128, 14);
		frame.getContentPane().add(lblKruskalUnionFind);
		
		JButton btnNewButton = new JButton("Comparar");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(156, 134, 112, 20);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Tiempos de ejecution del Algoritmo \r\nde Kruskal");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(48, 11, 329, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel labelTiempoKruskal = new JLabel("");
		labelTiempoKruskal.setBounds(83, 98, 46, 14);
		frame.getContentPane().add(labelTiempoKruskal);
		
		JLabel labelTiempoUnionFind = new JLabel("");
		labelTiempoUnionFind.setBounds(277, 98, 46, 14);
		frame.getContentPane().add(labelTiempoUnionFind);
		
		textField = new JTextField();
		textField.setBounds(210, 54, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad Vertices");
		lblNewLabel_2.setBounds(73, 57, 127, 17);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cantidad Aristas");
		lblNewLabel_2_1.setBounds(73, 84, 127, 17);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(210, 81, 86, 20);
		frame.getContentPane().add(textField_1);
	}
}
