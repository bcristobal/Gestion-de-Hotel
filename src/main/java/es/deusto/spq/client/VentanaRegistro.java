package es.deusto.spq.client;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaRegistro {
    private JFrame frame;

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
        JTextField nombreTextField = new JTextField();
        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoTextField = new JTextField();
        JLabel usuarioLabel = new JLabel("Dni:");
        JTextField usuarioTextField = new JTextField();
        JLabel edadLabel = new JLabel("Edad:");
        JTextField edadTextField = new JTextField();
        JLabel contraseñaLabel = new JLabel("Contraseña:");
        JPasswordField contraseñaPasswordField = new JPasswordField();

        // Configuración del GroupLayout
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(nombreLabel)
                .addComponent(apellidoLabel)
                .addComponent(usuarioLabel)
                .addComponent(contraseñaLabel)
                .addComponent(edadLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nombreTextField)
                .addComponent(apellidoTextField)
                .addComponent(usuarioTextField)
                .addComponent(contraseñaPasswordField)
                .addComponent(edadTextField))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nombreLabel)
                .addComponent(nombreTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(apellidoLabel)
                .addComponent(apellidoTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(usuarioLabel)
                .addComponent(usuarioTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(contraseñaLabel)
                .addComponent(contraseñaPasswordField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(edadLabel)
                .addComponent(edadTextField))
        );

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

