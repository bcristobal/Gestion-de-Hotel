package es.deusto.spq.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VentanaCliente extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pNorth = new JPanel(new BorderLayout());
	private JPanel pNorthDrch = new JPanel(new BorderLayout());
	private JPanel pSouth = new JPanel(new GridLayout());
	
	private JPanel pIzq = new JPanel(new GridLayout(4, 1));
	private JPanel pDrcha = new JPanel(new BorderLayout());
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuUsuario  = new JMenu("Usuario");
	private JMenuItem menuItemPerfil = new JMenuItem("Perfil");
	private JMenuItem menuItemReservas = new JMenuItem("Reservas");
	private JMenuItem menuItemCerrarSesion = new JMenuItem("Cerrar sesión");
	private JButton bUsuario = new JButton("");
	
	//MenuIzq
	private JButton bReservar = new JButton("Reservar");
	  int minValue = 0;
      int maxValue = 1000;
      int initialValue = 500;
	private JSlider SliderPrecio = new JSlider(minValue, maxValue, initialValue);
    private JLabel precioLabel = new JLabel("Precio/noche: " + initialValue); // Contador de precio
    private String[] opcionesHabitaciones = {"Habitación Individual", "Habitación Doble", "Habitación Suite"};
    private JComboBox<String> comboBoxHabitaciones = new JComboBox<>(opcionesHabitaciones);
    
    private Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
	private ImageIcon imgUsuario = new ImageIcon("src/main/resources/usuario.png");
	
	//ListaHabitaciones
    private DefaultListModel<String> listModel;
    private JList<String> imageList;
    private JPanel imagePanel;
    private JLabel imageLabel;

    private String[] imagePaths = {
            "src/main/resources/hab1.jpg",
            "src/main/resources/hab2.jpg",
            "src/main/resources/hab3.jpg",
    };
	
	public VentanaCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(650, 430);
		setTitle("DEUSTO HOTEL & SPA");

		// Centra la ventana en el centro de la pantlla
		setLocation(	(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
						(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
		setVisible(true);
		
        //Panel Superior
		add(pNorth, BorderLayout.NORTH);
		pNorth.add(pNorthDrch, BorderLayout.EAST);
		pNorthDrch.add(bUsuario, BorderLayout.NORTH);

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
		bUsuario.setIcon(new ImageIcon(imgUsuario.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

		bUsuario.setBorder(null);
		
		pNorthDrch.setBorder(new EmptyBorder(10,10,10,10));
		pNorth.setBackground(Color.LIGHT_GRAY);
		pNorthDrch.setBackground(Color.LIGHT_GRAY);
		
		// Panel Izq
		add(pSouth);
		pSouth.add(pIzq);
		pIzq.setBorder(borde);

		pIzq.add(comboBoxHabitaciones);
        // Configurar la apariencia del slider
		SliderPrecio.setMajorTickSpacing(200); // Espaciado principal entre los ticks
		SliderPrecio.setMinorTickSpacing(100); // Espaciado secundario entre los ticks
		SliderPrecio.setPaintTicks(true); // Mostrar los ticks
		SliderPrecio.setPaintLabels(true); // Mostrar las etiquetas de los ticks
        pIzq.add(precioLabel); // Agregar el contador de precio
		pIzq.add(SliderPrecio);
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
        imageList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = imageList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        loadImage(imagePaths[selectedIndex]);
                    }
                }
            }
        });

        imagePanel = new JPanel();
        imageLabel = new JLabel();
        imagePanel.add(imageLabel);
        JScrollPane scrollPane = new JScrollPane(imageList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pDrcha.add(scrollPane, BorderLayout.NORTH);
        pDrcha.add(imagePanel);

        // Listener para el slider que actualiza el contador de precio
        SliderPrecio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = SliderPrecio.getValue();
                precioLabel.setText("Precio: " + value);
            }
        });
        
		bUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent source = (JComponent) e.getSource();
				JPopupMenu popupMenu = menuUsuario.getPopupMenu();
				popupMenu.show(source,0, source.getHeight());
			}
		});
		bReservar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaReservar();
			}
		});
		menuItemReservas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaReservas();
			}
		});
	}
	 private void loadImage(String path) {
	        ImageIcon imageIcon = new ImageIcon(path);
	        Image image = imageIcon.getImage().getScaledInstance(300, 180, Image.SCALE_SMOOTH);
	        imageLabel.setIcon(new ImageIcon(image));
	    }

	
    public static void main(String[] args) {
        new  VentanaCliente();
    }
}
