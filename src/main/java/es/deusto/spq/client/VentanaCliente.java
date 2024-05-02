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
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import es.deusto.spq.pojo.RoomData;

public class VentanaCliente extends JFrame {
    private static final long serialVersionUID = 1L;

    

    private JPanel pNorth = new JPanel(new BorderLayout());
    private JPanel pNorthDrch = new JPanel(new BorderLayout());
    private JPanel pSouth = new JPanel(new GridLayout());

    private JPanel pIzq = new JPanel(new GridLayout(4, 1));
    private JPanel pDrcha = new JPanel(new BorderLayout());

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuUsuario = new JMenu("Usuario");
    private JMenuItem menuItemPerfil = new JMenuItem("Perfil");
    private JMenuItem menuItemReservas = new JMenuItem("Reservas");
    private JMenuItem menuItemCerrarSesion = new JMenuItem("Cerrar sesión");
    private JButton bUsuario = new JButton("");

    // MenuIzq
    private JButton bReservar = new JButton("Reservar");
    int minValue = 0;
    int maxValue = 1000;
    int initialValue = 500;
    private JSlider SliderPrecio = new JSlider(minValue, maxValue, initialValue);
    private JLabel precioLabel = new JLabel("Precio/noche: " + initialValue);
    private String[] opcionesHabitaciones = { "Seleccionar habitación", "Habitación Individual", "Habitación Doble",
            "Habitación Suite" };
    private JComboBox<String> comboBoxHabitaciones = new JComboBox<>(opcionesHabitaciones);

    private Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
    private ImageIcon imgUsuario = new ImageIcon("src/main/resources/usuario.png");

    // ListaHabitaciones
    private DefaultListModel<RoomData> listModel;
    private JList<RoomData> imageList;
    private JPanel imagePanel;
    private JLabel imageLabel;

    private String[] imagePaths = { "src/main/resources/hab1.jpg", "src/main/resources/hab2.jpg",
            "src/main/resources/hab3.jpg","src/main/resources/hab3.jpg","src/main/resources/hab3.jpg","src/main/resources/hab3.jpg" };

    public VentanaCliente(Container container) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 430);
        setTitle("DEUSTO HOTEL & SPA");

        setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),
                (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2));
        setVisible(true);

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

        pNorthDrch.setBorder(new EmptyBorder(10, 10, 10, 10));
        pNorth.setBackground(Color.LIGHT_GRAY);
        pNorthDrch.setBackground(Color.LIGHT_GRAY);

        add(pSouth);
        pSouth.add(pIzq);
        pIzq.setBorder(borde);

        pIzq.add(comboBoxHabitaciones);
        SliderPrecio.setMajorTickSpacing(200);
        SliderPrecio.setMinorTickSpacing(100);
        SliderPrecio.setPaintTicks(true);
        SliderPrecio.setPaintLabels(true);
        pIzq.add(precioLabel);
        pIzq.add(SliderPrecio);
        pIzq.add(bReservar);

        pSouth.add(pDrcha);
        pDrcha.setBorder(borde);

        listModel = new DefaultListModel<>();

        imageList = new JList<>(listModel);
        imageList.setCellRenderer(new RoomListRenderer());
        imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        imagePanel = new JPanel();
        imageLabel = new JLabel();
        imagePanel.add(imageLabel);
        JScrollPane scrollPane = new JScrollPane(imageList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pDrcha.add(scrollPane, BorderLayout.NORTH);
        pDrcha.add(imagePanel);


        

        SliderPrecio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = SliderPrecio.getValue();
                precioLabel.setText("Precio: " + value);
            }
        });

        comboBoxHabitaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBoxHabitaciones.getSelectedItem();
                listModel.clear();
                if (!selectedOption.equals("Seleccionar tipo habitación")) {
                    if (selectedOption.equals("Habitación Individual")) {
                        for (int i = 0; i < 2 && i < imagePaths.length; i++) {
                            listModel.addElement(container.getRooms().get(i));
                        }
                    } else if (selectedOption.equals("Habitación Doble")) {
                        for (int i = 2; i < 4 && i < imagePaths.length; i++) {
                            listModel.addElement(container.getRooms().get(i));
                        }
                    } else if (selectedOption.equals("Habitación Suite")) {
						int totalRooms = container.getRooms().size();
						if (totalRooms >= 5) {
							listModel.addElement(container.getRooms().get(4));
						}
					}
					
                }
            }
        });

        bUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent source = (JComponent) e.getSource();
                JPopupMenu popupMenu = menuUsuario.getPopupMenu();
                popupMenu.show(source, 0, source.getHeight());
            }
        });
        menuItemCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                Main.main(null); // Open the Main class
            }
        });

bReservar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                RoomData room = imageList.getSelectedValue();
				
				new VentanaReservar(container, room);
			}
		});
        
        



        menuItemReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaReservas();
            }
        });
    }

    @SuppressWarnings("unused")
    private void loadImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage().getScaledInstance(300, 180, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
    }
    
}

class RoomListRenderer extends JLabel implements ListCellRenderer<RoomData> {
    private static final long serialVersionUID = 1L;

    public RoomListRenderer() {
        setOpaque(true);
    }

    @Override
    public java.awt.Component getListCellRendererComponent(JList<? extends RoomData> list, RoomData room, int index,
            boolean isSelected, boolean cellHasFocus) {
        setText(room.getDescription());
        setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
        setForeground(isSelected ? Color.BLACK : Color.BLACK);
        return this;
    }
}

