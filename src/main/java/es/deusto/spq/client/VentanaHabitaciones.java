package es.deusto.spq.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import es.deusto.spq.pojo.RoomData;

public class VentanaHabitaciones extends JFrame {
    private static final long serialVersionUID = 1L;

    private JButton bEliminarHabitacion = new JButton("Eliminar");
    private DefaultListModel<String> modeloHabitaciones = new DefaultListModel<>();
    private JList<String> listaHabitaciones = new JList<>(modeloHabitaciones);
    @SuppressWarnings("unused")
    private List<RoomData> habitaciones; // Variable de instancia para almacenar las habitaciones

    public VentanaHabitaciones(List<RoomData> habitaciones) {
        this.habitaciones = habitaciones; // Guardar la lista de habitaciones recibida
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("Administrar Habitaciones");

        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);

        // Agregar las habitaciones recibidas a la lista de Habitaciones
        for (RoomData habitacion : habitaciones) {
            agregarHabitacion(habitacion);
        }

        // Agregar la lista de Habitaciones dentro de un JScrollPane para permitir el desplazamiento si hay muchas Habitaciones
        JScrollPane scrollPane = new JScrollPane(listaHabitaciones);
        add(scrollPane, BorderLayout.CENTER);

        add(bEliminarHabitacion, BorderLayout.SOUTH);

        bEliminarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaHabitaciones.getSelectedIndex();
                if (index != -1) {
                    // Eliminar del modelo
                    modeloHabitaciones.remove(index);
                    // Eliminar del objeto List<RoomData>
                    habitaciones.remove(index);
                }
            }
        });
    }

    // Método para agregar la información de la habitación a la lista de habitaciones
    public void agregarHabitacion(RoomData habitacion) {
        String informacionHabitacion = "Número: " + habitacion.getNumber() + ", Tipo: " + habitacion.getType() + ", Precio: " + habitacion.getPrice();
        modeloHabitaciones.addElement(informacionHabitacion);
    }
}

