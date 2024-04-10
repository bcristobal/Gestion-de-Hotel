package es.deusto.spq.client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLoginAdmin {
    
    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;

    public VentanaLoginAdmin (Container container) {
        initialize(container);
    }

    private void initialize(Container container) {
        frame = new JFrame();
        frame.setTitle("Login Administrador");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        


        // Create and set properties for username label
        JLabel lblUsername = new JLabel("Email");
        lblUsername.setBounds(58, 100, 82, 16); // Modified
        frame.getContentPane().add(lblUsername);

        // Create and set properties for username text field
        textField = new JTextField();
        textField.setBounds(152, 95, 130, 26); // Modified
        textField.setColumns(10);
        frame.getContentPane().add(textField);

        // Create and set properties for password label
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(58, 140, 82, 16); // Modified
        frame.getContentPane().add(lblPassword);

        // Create and set properties for password field
        passwordField = new JPasswordField();
        passwordField.setBounds(152, 135, 130, 26); // Modified
        frame.getContentPane().add(passwordField);

        // Create and set properties for login button
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(152, 171, 130, 29); // Modified
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                @SuppressWarnings("deprecation")
                Boolean isLoged = container.loginAdmin(textField.getText(), passwordField.getText());
                if (isLoged) {
                    //TODO: Open admin window
                    frame.setVisible(false);
                }     
            }
        });

        frame.getContentPane().add(btnLogin);

        // Make the frame visible
        frame.setVisible(true);
    }
    
    public void mostrarVentana() {
        frame.setVisible(true);
    }

}
