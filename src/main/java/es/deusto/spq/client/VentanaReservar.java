package es.deusto.spq.client;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaReservar extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton bAceptar = new JButton("Aceptar");
	
	public VentanaReservar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 300);
		setTitle("DEUSTO HOTEL & SPA");

		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		
		
		
		add(bAceptar, BorderLayout.SOUTH);
		
		
	}
}
