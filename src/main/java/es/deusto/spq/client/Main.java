package es.deusto.spq.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private static final String HOSTNAME = "localhost"; // Cambia localhost al nombre del host real si es necesario
    private static final String PORT = "8080"; // Cambia 8080 al puerto real si es necesario

    public Main(Container container) {
        initialize(container);
    }

    private void initialize(Container container) {
        frame = new JFrame();
        frame.setTitle("Main");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Botón para iniciar sesión como usuario
        JButton loginButton = new JButton("Iniciar Sesión como Usuario");
        loginButton.setBounds(100, 50, 250, 30);
        loginButton.addActionListener(e -> {
            // Coloca aquí el código para abrir la ventana de inicio de sesión como usuario
            
            new VentanaLogin(container);
        });
        frame.getContentPane().add(loginButton);

        // Botón para registrarse
        JButton registerButton = new JButton("Registrarse");
        registerButton.setBounds(100, 100, 250, 30);
        registerButton.addActionListener(e -> {
            // Coloca aquí el código para abrir la ventana de registro
            // Por ejemplo:
            // new VentanaRegistro().mostrarVentana();
        });
        frame.getContentPane().add(registerButton);

        // Botón para iniciar sesión como administrador
        JButton adminButton = new JButton("Iniciar Sesión como Administrador");
        adminButton.setBounds(100, 150, 250, 30);
        adminButton.addActionListener(e -> {
            // Abre la ventana de inicio de sesión como administrador
            
            new VentanaLoginAdmin(container);
        });
        frame.getContentPane().add(adminButton);

        // Hacer visible la ventana principal
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Container container = new Container(HOSTNAME, PORT);
                Main window = new Main(container);
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

