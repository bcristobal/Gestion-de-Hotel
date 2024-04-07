package es.deusto.spq.client;
import es.deusto.spq.client.VentanaLogin;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaRegistro {
    private JFrame frame;
    private JTextField nombreTextField;
    private JPasswordField contraseñaPasswordField;

    public VentanaRegistro() {
        frame = new JFrame("Ventana de Registro");
        frame.setSize(400, 250); 
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
        JLabel nombreLabel = new JLabel("Usuario:");
        nombreTextField = new JTextField();
        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaPasswordField = new JPasswordField();
        JButton aceptarButton = new JButton("Aceptar");

        // Configuración del GroupLayout
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(nombreLabel)
                        .addComponent(contraseñaLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nombreTextField)
                        .addComponent(contraseñaPasswordField))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nombreLabel)
                        .addComponent(nombreTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(contraseñaLabel)
                        .addComponent(contraseñaPasswordField))
        );

        // Agregar el evento de clic al botón Aceptar
        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar cuando se hace clic en Aceptar
                String nombre = nombreTextField.getText();
                char[] contraseña = contraseñaPasswordField.getPassword();

                System.out.println("Nombre: " + nombre);
                System.out.println("Contraseña: " + new String(contraseña));


                VentanaLogin ventanaLogin = new VentanaLogin();
                ventanaLogin.mostrarVentana();


                frame.dispose(); 
            }
        });

        // Configurar la posición del botón en la esquina inferior derecha
        frame.add(aceptarButton, BorderLayout.SOUTH);

        // Agregar el panel al centro
        frame.add(panelRegistro, BorderLayout.CENTER);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
    
    

    public static void main(String[] args) {
        VentanaRegistro ventanaRegistro = new VentanaRegistro();
        ventanaRegistro.mostrarVentana();
    }
}
