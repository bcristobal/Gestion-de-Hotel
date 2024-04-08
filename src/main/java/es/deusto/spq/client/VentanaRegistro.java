package es.deusto.spq.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



import javax.swing.*;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import es.deusto.spq.pojo.CustomerData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro {
    private JFrame frame;
    private JTextField nombreTextField, emailTextField, surnameTextField, addressTextField, phoneTextField;
    private JPasswordField contraseñaPasswordField;

    public VentanaRegistro(Container container) {
        frame = new JFrame("Ventana de Registro");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());

        // Panel principal con GroupLayout
        JPanel panelRegistro = new JPanel();
        GroupLayout layout = new GroupLayout(panelRegistro);
        panelRegistro.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        

        // Componentes de registro
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreTextField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();
        JLabel surnameLabel = new JLabel("Apellido:");
        surnameTextField = new JTextField();
        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaPasswordField = new JPasswordField();
        JLabel addressLabel = new JLabel("Dirección:");
        addressTextField = new JTextField();
        JLabel phoneLabel = new JLabel("Teléfono:");
        phoneTextField = new JTextField();
        JButton aceptarButton = new JButton("Aceptar");

        // Configuración del GroupLayout
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(nombreLabel)
                        .addComponent(emailLabel)
                        .addComponent(surnameLabel)
                        .addComponent(contraseñaLabel)
                        .addComponent(addressLabel)
                        .addComponent(phoneLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nombreTextField)
                        .addComponent(emailTextField)
                        .addComponent(surnameTextField)
                        .addComponent(contraseñaPasswordField)
                        .addComponent(addressTextField)
                        .addComponent(phoneTextField)
                        .addComponent(aceptarButton))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nombreLabel)
                        .addComponent(nombreTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(emailTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(surnameLabel)
                        .addComponent(surnameTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(contraseñaLabel)
                        .addComponent(contraseñaPasswordField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addressLabel)
                        .addComponent(addressTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(phoneLabel)
                        .addComponent(phoneTextField))
                .addComponent(aceptarButton)
        );

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreTextField.getText();
                String email = emailTextField.getText();
                String apellido = surnameTextField.getText();
                String contraseña = new String(contraseñaPasswordField.getPassword());
                String dirección = addressTextField.getText();
                String teléfonoStr = phoneTextField.getText();

                // Validar que todos los campos estén llenos
                if (nombre.isEmpty() || email.isEmpty() || apellido.isEmpty() || contraseña.isEmpty() || dirección.isEmpty() || teléfonoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar el formato del correo electrónico
                /* 
                if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                    JOptionPane.showMessageDialog(frame, "El correo electrónico no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                */
                // Validar que el teléfono sea un número válido
                int teléfono;
                try {
                    teléfono = Integer.parseInt(teléfonoStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "El teléfono debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Llamar al método para registrar al cliente en el servidor
                container.registerCustomer(email, nombre, apellido, contraseña, dirección, teléfono);
            }
        });

        frame.add(panelRegistro, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
}
