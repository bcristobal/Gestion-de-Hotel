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

public class VentanaHabitaciones extends JFrame {
    private static final long serialVersionUID = 1L;

    private JButton bEliminarHabitacion = new JButton("Eliminar");
    private DefaultListModel<String> modeloHabitaciones = new DefaultListModel<>();
    private JList<String> listaHabitaciones = new JList<>(modeloHabitaciones);

    public VentanaHabitaciones() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("Administrar Habitaciones");

        setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),
                (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
        setVisible(true);

        // Agregar la lista de Habitaciones dentro de un JScrollPane para permitir el
        // desplazamiento si hay muchas Habitaciones
        JScrollPane scrollPane = new JScrollPane(listaHabitaciones);
        add(scrollPane, BorderLayout.CENTER);
        
        add(bEliminarHabitacion, BorderLayout.SOUTH);
        
        bEliminarHabitacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        int index = listaHabitaciones.getSelectedIndex();
		        if (index != -1) {
		            modeloHabitaciones.remove(index);
		        }	
			}
		});
    }

//    // Método para agregar Habitaciones a la lista
//    public void agregarHabitacion(String Habitacion) {
//        modeloHabitaciones.addElement(Habitacion);
//    }

//    // Método para eliminar Habitaciones de la lista
//    public void eliminarHabitacioneseleccionada() {
//        int index = listaHabitaciones.getSelectedIndex();
//        if (index != -1) {
//            modeloHabitaciones.remove(index);
//        }
//    }
}
