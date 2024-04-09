package es.deusto.spq.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaReservar extends JFrame {

    private static final long serialVersionUID = 1L;

    private String habitacionSeleccionada;
    private Date fechaEntrada;
    private Date fechaSalida;
    
    private JLabel habitacionLabel;
    private JLabel fechaEntradaLabel;
    private JLabel fechaSalidaLabel;
    private JLabel precioTotalLabel;

    private JButton bAceptar = new JButton("Aceptar");

    public VentanaReservar(String habitacionSeleccionada) {
        this.habitacionSeleccionada = habitacionSeleccionada;
        initialize();
    }

    public VentanaReservar(Date fechaEntrada, Date fechaSalida) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 200);
        setTitle("Confirmar Reserva");
        setVisible(true);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        JPanel panelFechas = new JPanel(new GridLayout(1, 2));
        
        habitacionLabel = new JLabel("Habitación: " + habitacionSeleccionada);
        panel.add(habitacionLabel, BorderLayout.NORTH);

        if (fechaEntrada != null && fechaSalida != null) {
            fechaEntradaLabel = new JLabel("Fecha entrada: " + fechaEntrada);
            fechaSalidaLabel = new JLabel("Fecha salida: " + fechaSalida);
            panelFechas.add(fechaEntradaLabel, BorderLayout.CENTER);
            panelFechas.add(fechaSalidaLabel, BorderLayout.CENTER);
            panel.add(panelFechas);
            // Calcular el precio total
            long diffInMillies = Math.abs(fechaSalida.getTime() - fechaEntrada.getTime());
            long diff = diffInMillies / (1000 * 60 * 60 * 24);
            double precioPorNoche = getPrecioPorNoche(); // Implementa este método para obtener el precio por noche de la habitación seleccionada
            double precioTotal = diff * precioPorNoche;
            precioTotalLabel = new JLabel("Precio total: " + precioTotal);
            panel.add(precioTotalLabel, BorderLayout.SOUTH);
        }

        add(panel, BorderLayout.CENTER);
        add(bAceptar, BorderLayout.SOUTH);

        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // Implementa este método para obtener el precio por noche de la habitación seleccionada
    private double getPrecioPorNoche() {
        // Aquí debes implementar la lógica para obtener el precio por noche de la habitación seleccionada
        return 100.0; // Ejemplo de precio por noche
    }
}
