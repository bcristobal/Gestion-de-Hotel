package es.deusto.spq.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        frame = new JFrame("Login");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Create and set properties for username label
        JLabel lblUsername = createLabel("Email", 58, 100);
        frame.getContentPane().add(lblUsername);

        // Create and set properties for username text field
        textField = createTextField(152, 95);
        frame.getContentPane().add(textField);

        // Create and set properties for password label
        JLabel lblPassword = createLabel("Password", 58, 140);
        frame.getContentPane().add(lblPassword);

        // Create and set properties for password field
        passwordField = createPasswordField(152, 135);
        frame.getContentPane().add(passwordField);

        // Create and set properties for login button
        JButton btnLogin = createButton("Login", 152, 171);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        frame.getContentPane().add(btnLogin);

        // Create and set properties for back button
        JButton backButton = createButton("Volver", 152, 210);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open main window when clicking "Volver"
                new Main(container);
                frame.dispose();
            }
        });
        frame.getContentPane().add(backButton);

        // Add KeyListener to the password field
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to create a JLabel
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 82, 16);
        return label;
    }

    // Method to create a JTextField
    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 130, 26);
        textField.setColumns(10);
        return textField;
    }

    // Method to create a JPasswordField
    private JPasswordField createPasswordField(int x, int y) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, 130, 26);
        return passwordField;
    }

    // Method to create a JButton
    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 130, 29);
        return button;
    }

    // Method to perform the login action
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
