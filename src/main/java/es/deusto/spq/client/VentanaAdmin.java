package es.deusto.spq.client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAdmin {

    private JFrame frame;

    private JPanel pNorth = new JPanel(new BorderLayout());
    private JPanel pNorthDrcha = new JPanel(new BorderLayout());
    private JPanel pSouth = new JPanel(new GridLayout(3,1));
    JButton bCerrarSesion = new JButton("Cerrar Sesión");
    JButton bAdminUsuarios = new JButton("Administrar Usuarios");
    JButton bAdminReservas = new JButton("Administrar Reservas");
    JButton bAdminHabitaciones = new JButton("Administrar Habitaciones");

    public VentanaAdmin() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setTitle("DEUSTO HOTEL & SPA ADMIN");
        frame.setVisible(true);
        
        //Panel North
        pNorth.setBackground(Color.LIGHT_GRAY);
        frame.add(pNorth, BorderLayout.NORTH);
        pNorth.add(pNorthDrcha, BorderLayout.EAST);
        pNorthDrcha.setBorder(new EmptyBorder(10, 10, 10, 10));
        pNorth.setBackground(Color.LIGHT_GRAY);
        pNorthDrcha.setBackground(Color.LIGHT_GRAY);
        pNorthDrcha.add(bCerrarSesion, BorderLayout.NORTH);
        
        //Panel South
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
        pSouth.setBorder(borde);
        frame.add(pSouth, BorderLayout.CENTER);
        JPanel pAdminUsuarios = new JPanel();
        JPanel pAdminReservas = new JPanel();
        JPanel pAdminHabitaciones = new JPanel();
        pSouth.add(pAdminUsuarios);
        pSouth.add(pAdminReservas);
        pSouth.add(pAdminHabitaciones);
        pAdminUsuarios.add(bAdminUsuarios);
        pAdminReservas.add(bAdminReservas);
        pAdminHabitaciones.add(bAdminHabitaciones);

        bAdminUsuarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaUsuarios();
			}
		});
        bAdminReservas.addActionListener(new ActionListener() {
					
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new VentanaReservas();
        	}
        });
        bAdminHabitaciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
        		new VentanaHabitaciones();
			}
		});
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaAdmin();
    }
}
