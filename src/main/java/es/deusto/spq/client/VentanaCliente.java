package es.deusto.spq.client;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class VentanaCliente {
    private JFrame frame;
    private JPanel pNorth, pNorthDrch, pSouth, pIzq, pDrcha, imagePanel;
    private JMenuBar menuBar;
    private JMenu menuUsuario;
    private JMenuItem menuItemPerfil, menuItemReservas, menuItemCerrarSesion;
    private JButton bUsuario, bReservar;
    private JSlider SliderPrecio;
    private JLabel precioLabel, imageLabel;
    private JComboBox<String> comboBoxHabitaciones;
    private DefaultListModel<String> listModel;
    private JList<String> imageList;

    private String[] opcionesHabitaciones = {"Habitación Individual", "Habitación Doble", "Habitación Suite"};
    private String[] imagePaths = {"src/main/resources/hab1.jpg", "src/main/resources/hab2.jpg", "src/main/resources/hab3.jpg"};
	private ImageIcon imgUsuario = new ImageIcon("src/main/resources/usuario.png");

    public VentanaCliente(Container container) {
        initialize(container);
    }
    
    private void initialize(Container container) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(650, 430);
        frame.setTitle("DEUSTO HOTEL & SPA");
        frame.setVisible(true);
        
        // Panel Superior
        pNorth = new JPanel(new BorderLayout());
        pNorthDrch = new JPanel(new BorderLayout());
        frame.add(pNorth, BorderLayout.NORTH);
        pNorth.add(pNorthDrch, BorderLayout.EAST);

        bUsuario = new JButton();
        menuBar = new JMenuBar();
        menuUsuario = new JMenu("Usuario");
        menuItemPerfil = new JMenuItem("Perfil");
        menuItemReservas = new JMenuItem("Reservas");
        menuItemCerrarSesion = new JMenuItem("Cerrar sesión");

        bUsuario.setBorder(null);
    	bUsuario.setIcon(new ImageIcon(imgUsuario.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        menuBar.setVisible(false);
        menuBar.add(menuUsuario);
        menuUsuario.setMnemonic(KeyEvent.VK_F);
        menuUsuario.add(menuItemPerfil);
        menuItemPerfil.setMnemonic(KeyEvent.VK_S);
        menuUsuario.addSeparator();
        menuUsuario.add(menuItemReservas);
        menuItemReservas.setMnemonic(KeyEvent.VK_S);
        menuUsuario.addSeparator();
        menuUsuario.add(menuItemCerrarSesion);
        menuItemCerrarSesion.setMnemonic(KeyEvent.VK_S);
        
        pNorthDrch.setBorder(new EmptyBorder(10, 10, 10, 10));
        pNorth.setBackground(Color.LIGHT_GRAY);
        pNorthDrch.setBackground(Color.LIGHT_GRAY);
        pNorthDrch.add(bUsuario, BorderLayout.NORTH);
        frame.setJMenuBar(menuBar);
        
        // Panel Izquierdo
        pSouth = new JPanel(new GridLayout());
        pIzq = new JPanel(new GridLayout(5, 1));
        pDrcha = new JPanel(new BorderLayout());
        frame.add(pSouth);

        Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
        pIzq.setBorder(borde);
        pSouth.add(pIzq);
        comboBoxHabitaciones = new JComboBox<>(opcionesHabitaciones);

        int minValue = 0;
        int maxValue = 1000;
        int initialValue = 500;
        SliderPrecio = new JSlider(minValue, maxValue, initialValue);
        SliderPrecio.setMajorTickSpacing(200);
        SliderPrecio.setMinorTickSpacing(100);
        SliderPrecio.setPaintTicks(true);
        SliderPrecio.setPaintLabels(true);

        precioLabel = new JLabel("Precio/noche: " + initialValue);
        pIzq.add(comboBoxHabitaciones);
        pIzq.add(precioLabel);
        pIzq.add(SliderPrecio);
        
        JPanel panelFechas = new JPanel(new GridLayout(2,2));
        JLabel labelFechaEntrada = new JLabel("Fecha entrada:");
        JLabel labelFechaSalida = new JLabel("Fecha salida:");
        SpinnerDateModel dateModelEntrada = new SpinnerDateModel();
        JSpinner spinnerFechaEntrada = new JSpinner(dateModelEntrada);
        spinnerFechaEntrada.setEditor(new JSpinner.DateEditor(spinnerFechaEntrada, "dd/MM/yyyy"));
        SpinnerDateModel dateModelSalida = new SpinnerDateModel();
        JSpinner spinnerFechaSalida = new JSpinner(dateModelSalida);
        spinnerFechaSalida.setEditor(new JSpinner.DateEditor(spinnerFechaSalida, "dd/MM/yyyy"));
        panelFechas.add(labelFechaEntrada);
        panelFechas.add(spinnerFechaEntrada);
        panelFechas.add(labelFechaSalida);
        panelFechas.add(spinnerFechaSalida);
        
        pIzq.add(panelFechas);
        
        bReservar = new JButton("Reservar");
        pIzq.add(bReservar);
        
        // Panel Derecho
        pSouth.add(pDrcha);
        pDrcha.setBorder(borde);
        listModel = new DefaultListModel<>();
        for (int i = 0; i < imagePaths.length; i++) {
            listModel.addElement("hab " + (i + 1));
        }
        imageList = new JList<>(listModel);
        imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        imagePanel = new JPanel();
        imageLabel = new JLabel();
        imagePanel.add(imageLabel);
        JScrollPane scrollPane = new JScrollPane(imageList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pDrcha.add(scrollPane, BorderLayout.NORTH);
        pDrcha.add(imagePanel);
        
        // Listeners
        bUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent source = (JComponent) e.getSource();
                JPopupMenu popupMenu = menuUsuario.getPopupMenu();
                popupMenu.show(source, 0, source.getHeight());
            }
        });

        bReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener las fechas seleccionadas
            	java.sql.Date fechaEntrada = new java.sql.Date(((java.util.Date) spinnerFechaEntrada.getValue()).getTime());
            	java.sql.Date fechaSalida = new java.sql.Date(((java.util.Date) spinnerFechaSalida.getValue()).getTime());
                // Abrir la ventana VentanaReservar con las fechas seleccionadas
                new VentanaReservar(fechaEntrada, fechaSalida);
            }
        });
        
        menuItemReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaReservas();
            }
        });

        // Listener para el slider que actualiza el contador de precio
        SliderPrecio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = SliderPrecio.getValue();
                precioLabel.setText("Precio: " + value);
            }
        });
        
        // Listener para seleccionar una imagen de la lista
        imageList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = imageList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        loadImage(imagePaths[selectedIndex]);
                        // Obtener el nombre de la habitación seleccionada
                        String habitacionSeleccionada = listModel.getElementAt(selectedIndex);
                        // Actualizar el título de la ventana VentanaReservar
//                        new VentanaReservar(habitacionSeleccionada);
                    }
                }
            }
        });
    }

    // Método para cargar una imagen en el panel de la derecha
    private void loadImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage().getScaledInstance(300, 180, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaCliente(null);
    }
}
