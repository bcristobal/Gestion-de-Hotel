package es.deusto.spq.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;

    public Main() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Main");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Botón para iniciar sesión como usuario
        JButton loginButton = new JButton("Iniciar Sesión como Usuario");
        loginButton.setBounds(100, 50, 250, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Coloca aquí el código para abrir la ventana de inicio de sesión como usuario
                // Por ejemplo:
                // new VentanaLogin().mostrarVentana();
            }
        });
        frame.getContentPane().add(loginButton);

        // Botón para registrarse
        JButton registerButton = new JButton("Registrarse");
        registerButton.setBounds(100, 100, 250, 30);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Coloca aquí el código para abrir la ventana de registro
                // Por ejemplo:
                // new VentanaRegistro().mostrarVentana();
            }
        });
        frame.getContentPane().add(registerButton);

        // Botón para iniciar sesión como administrador
        JButton adminButton = new JButton("Iniciar Sesión como Administrador");
        adminButton.setBounds(100, 150, 250, 30);
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Coloca aquí el código para abrir la ventana de inicio de sesión como administrador
                // Por ejemplo:
                // new VentanaAdministrador().mostrarVentana();
            }
        });
        frame.getContentPane().add(adminButton);

        // Hacer visible la ventana principal
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
