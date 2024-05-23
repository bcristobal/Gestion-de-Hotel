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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VentanaReservas extends JFrame {
    private static final long serialVersionUID = 1L;

    private JButton bEliminarReserva = new JButton("Eliminar");
    private JButton bEditarReserva = new JButton("Editar");
    private DefaultListModel<String> modeloReservas = new DefaultListModel<>();
    private JList<String> listaReservas = new JList<>(modeloReservas);
    private static List<String> reservas = new ArrayList<>();

    public VentanaReservas() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("Administrar Reservas");

        setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),
                (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
        setVisible(true);

        for (String reserva : reservas) {
            modeloReservas.addElement(reserva);
        }

        JScrollPane scrollPane = new JScrollPane(listaReservas);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(bEliminarReserva);
        panelBotones.add(bEditarReserva);
        add(panelBotones, BorderLayout.SOUTH);

        bEliminarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaReservas.getSelectedIndex();
                if (index != -1) {
                    modeloReservas.remove(index);
                    reservas.remove(index);
                }
            }
        });

        bEditarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaReservas.getSelectedIndex();
                if (index != -1) {
                    String reservaSeleccionada = modeloReservas.getElementAt(index);
                    editarReserva(reservaSeleccionada);
                }
            }
        });

        listaReservas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                bEditarReserva.setEnabled(listaReservas.getSelectedIndex() != -1);
                bEliminarReserva.setEnabled(listaReservas.getSelectedIndex() != -1);
            }
        });
    }

    private void editarReserva(String reservaSeleccionada) {
        String nuevaReserva = JOptionPane.showInputDialog(this, "Editar Reserva:", reservaSeleccionada);
        if (nuevaReserva != null && !nuevaReserva.isEmpty()) {
            int index = modeloReservas.indexOf(reservaSeleccionada);
            modeloReservas.setElementAt(nuevaReserva, index);
            reservas.set(index, nuevaReserva);
        }
    }

    public void agregarReserva(String informacionReserva) {
        modeloReservas.addElement(informacionReserva);
        reservas.add(informacionReserva);
    }

}
