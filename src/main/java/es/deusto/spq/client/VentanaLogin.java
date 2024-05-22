package es.deusto.spq.client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VentanaLogin {
    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    private Container container;

    public VentanaLogin(Container container) {
        this.container = container;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Login");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Create and set properties for username label
        JLabel lblUsername = new JLabel("Email");
        lblUsername.setBounds(58, 100, 82, 16);
        frame.getContentPane().add(lblUsername);

        // Create and set properties for username text field
        textField = new JTextField();
        textField.setBounds(152, 95, 130, 26);
        textField.setColumns(10);
        frame.getContentPane().add(textField);

        // Create and set properties for password label
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(58, 140, 82, 16);
        frame.getContentPane().add(lblPassword);

        // Create and set properties for password field
        passwordField = new JPasswordField();
        passwordField.setBounds(152, 135, 130, 26);
        frame.getContentPane().add(passwordField);

        // Create and set properties for login button
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(152, 171, 130, 29);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        frame.getContentPane().add(btnLogin);

        // Create and set properties for back button
        JButton backButton = new JButton("Volver");
        backButton.setBounds(152, 210, 130, 29);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana principal al hacer clic en "Volver"
                new Main(container);
                frame.dispose();
            }
        });
        frame.getContentPane().add(backButton);

        // ActionListener para el campo de contraseña
        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Método para realizar el login
    private void login() {
        @SuppressWarnings("deprecation")
        Boolean isLogged = container.loginCustomer(textField.getText(), passwordField.getText());
        if (isLogged) {
            new VentanaCliente(container);
            frame.setVisible(false);
        }
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
