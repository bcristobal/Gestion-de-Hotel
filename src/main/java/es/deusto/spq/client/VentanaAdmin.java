package es.deusto.spq.client;

import javax.swing.JFrame;

public class VentanaAdmin {

    private JFrame frame;
	
	public VentanaAdmin() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setTitle("DEUSTO HOTEL & SPA ADMIN");
		frame.setVisible(true);
	}
	
    public void mostrarVentana() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaAdmin();
    }
}
