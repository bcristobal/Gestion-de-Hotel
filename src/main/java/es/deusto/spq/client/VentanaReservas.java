package es.deusto.spq.client;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class VentanaReservas extends JFrame {
    private static final long serialVersionUID = 1L;

    private JButton bEliminarReserva = new JButton("Eliminar");
    private DefaultListModel<String> modeloReservas = new DefaultListModel<>();
    private JList<String> listaReservas = new JList<>(modeloReservas);

    // Lista estática de reservas
    private static List<String> reservas = new ArrayList<>();

    public VentanaReservas() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("Administrar Reservas");

        setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),
                (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
        setVisible(true);

        // Cargar las reservas al modelo de la lista
        for (String reserva : reservas) {
            modeloReservas.addElement(reserva);
        }

        // Agregar la lista de reservas dentro de un JScrollPane para permitir el desplazamiento si hay muchas reservas
        JScrollPane scrollPane = new JScrollPane(listaReservas);
        add(scrollPane, BorderLayout.CENTER);

        add(bEliminarReserva, BorderLayout.SOUTH);

        bEliminarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaReservas.getSelectedIndex();
                if (index != -1) {
                    // Remover reserva del modelo y de la lista estática
                    modeloReservas.remove(index);
                    reservas.remove(index);
                }
            }
        });
    }

    // Método para agregar la información de la reserva a la lista de reservas
    public void agregarReserva(String informacionReserva) {
        modeloReservas.addElement(informacionReserva);
        // Agregar reserva a la lista estática
        reservas.add(informacionReserva);
    }
}
