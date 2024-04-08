package es.deusto.spq.client;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro {
    private JFrame frame;
    private JTextField nombreTextField, emailTextField, surnameTextField, addressTextField, phoneTextField;
    private JPasswordField contraseñaPasswordField;

    public VentanaRegistro() {
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
                int teléfono = Integer.parseInt(phoneTextField.getText());
                // Aquí puedes hacer lo que necesites con los datos recopilados
                // Por ejemplo, puedes llamar a un método de registro
            }
        });

        frame.add(panelRegistro, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaRegistro();
    }
}
