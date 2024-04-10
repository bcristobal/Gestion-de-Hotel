package es.deusto.spq.client;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class VentanaUsuarios extends JFrame {
    private static final long serialVersionUID = 1L;

    private JButton bEliminarUsuario = new JButton("Eliminar");
    private DefaultListModel<String> modeloUsuarios = new DefaultListModel<>();
    private JList<String> listaUsuarios = new JList<>(modeloUsuarios);

    public VentanaUsuarios() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("Administrar Usuarios");

        setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),
                (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
        setVisible(true);

        // Agregar la lista de Usuarios dentro de un JScrollPane para permitir el
        // desplazamiento si hay muchas Usuarios
        JScrollPane scrollPane = new JScrollPane(listaUsuarios);
        add(scrollPane, BorderLayout.CENTER);
        
        add(bEliminarUsuario, BorderLayout.SOUTH);
        
        bEliminarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        int index = listaUsuarios.getSelectedIndex();
		        if (index != -1) {
		            modeloUsuarios.remove(index);
		        }	
			}
		});
    }

//    // Método para agregar Usuarios a la lista
//    public void agregarUsuario(String Usuario) {
//        modeloUsuarios.addElement(Usuario);
//    }

//    // Método para eliminar Usuarios de la lista
//    public void eliminarUsuarioSeleccionada() {
//        int index = listaUsuarios.getSelectedIndex();
//        if (index != -1) {
//            modeloUsuarios.remove(index);
//        }
//    }
}
