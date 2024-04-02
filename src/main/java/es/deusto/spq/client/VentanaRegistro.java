package es.deusto.spq.client;
import javax.swing.*;

public class VentanaRegistro {
    public static void main(String[] args) {
        // Crear una nueva ventana
        JFrame ventana = new JFrame("Ventana de Registro");

        // Configurar el tamaño de la ventana
        ventana.setSize(400, 300);

        // Configurar la operación por defecto al cerrar la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel para agregar componentes
        JPanel panel = new JPanel();

        // Crear un botón
        JButton boton = new JButton("Registrar");

        // Agregar el botón al panel
        panel.add(boton);

        // Agregar el panel a la ventana
        ventana.add(panel);


        // Hacer visible la ventana
        ventana.setVisible(true);
    }
}
