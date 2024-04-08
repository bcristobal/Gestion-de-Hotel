package es.deusto.spq.client;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import es.deusto.spq.pojo.RoomData;
import es.deusto.spq.server.jdo.Booking;
import es.deusto.spq.server.jdo.Customer;
import es.deusto.spq.server.jdo.Room;

public class VentanaReservas extends JFrame {
    private static final long serialVersionUID = 1L;

    private JButton bEliminarReserva = new JButton("Eliminar");
    private DefaultListModel<String> modeloReservas = new DefaultListModel<>();
    private JList<String> listaReservas = new JList<>(modeloReservas);

    public VentanaReservas() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("DEUSTO HOTEL & SPA");

        setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),
                (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
        setVisible(true);

        // Agregar la lista de reservas dentro de un JScrollPane para permitir el
        // desplazamiento si hay muchas reservas
        JScrollPane scrollPane = new JScrollPane(listaReservas);
        add(scrollPane, BorderLayout.CENTER);
        
        add(bEliminarReserva, BorderLayout.SOUTH);
        
        bEliminarReserva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        int index = listaReservas.getSelectedIndex();
		        if (index != -1) {
		            modeloReservas.remove(index);
		        }	
			}
		});
    }

//    // Método para agregar reservas a la lista
//    public void agregarReserva(String reserva) {
//        modeloReservas.addElement(reserva);
//    }

//    // Método para eliminar reservas de la lista
//    public void eliminarReservaSeleccionada() {
//        int index = listaReservas.getSelectedIndex();
//        if (index != -1) {
//            modeloReservas.remove(index);
//        }
//    }
}
