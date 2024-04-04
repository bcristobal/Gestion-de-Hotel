package es.deusto.spq.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaCliente extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel pNorth = new JPanel(new GridLayout());
	
	JButton bPerfil = new JButton("Perfil");
	JButton bCerrarSesion = new JButton("Cerrar Sesión");
	
	public VentanaCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		setTitle("DEUSTO HOTEL & SPA");

		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		
		add(pNorth, BorderLayout.NORTH);
		pNorth.add(bPerfil);
		pNorth.add(bCerrarSesion);

	}
	
    public static void main(String[] args) {
        new  VentanaCliente();
    }
}
