package es.deusto.spq.client;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaReservas extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton eliminarReserva = new JButton("Eliminar");
	
	
	public VentanaReservas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setTitle("DEUSTO HOTEL & SPA");

		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		
		add(eliminarReserva, BorderLayout.SOUTH);
		
		
	}
}
