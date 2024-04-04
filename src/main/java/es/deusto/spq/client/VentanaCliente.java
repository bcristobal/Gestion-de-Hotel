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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class VentanaCliente extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pNorth = new JPanel(new BorderLayout());
	private JPanel pNorthDrch = new JPanel(new BorderLayout());
	private JPanel pSouth = new JPanel(new GridLayout());
	
	private JPanel pIzq = new JPanel(new GridLayout(3, 1));
	private JPanel pDrcha = new JPanel(new BorderLayout());
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuUsuario  = new JMenu("Usuario");
	private JMenuItem menuItemPerfil = new JMenuItem("Perfil");
	private JMenuItem menuItemCerrarSesion = new JMenuItem("Cerrar sesión");
	
	private JButton bUsuario = new JButton("");
	
	private JTextField textBuscar = new JTextField();
	  int minValue = 0;
      int maxValue = 1000;
      int initialValue = 500;
	private JSlider SliderPrecio = new JSlider(minValue, maxValue, initialValue);
    private String[] opcionesHabitaciones = {"Habitación Individual", "Habitación Doble", "Habitación Suite"};
    private JComboBox<String> comboBoxHabitaciones = new JComboBox<>(opcionesHabitaciones);
    private Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
	private ImageIcon imgUsuario = new ImageIcon("src/main/resources/usuario.png");

	public VentanaCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
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
		menuUsuario.add(menuItemCerrarSesion);
		menuItemCerrarSesion.setMnemonic(KeyEvent.VK_S);
		bUsuario.setIcon(redimensionarIcono(imgUsuario, 30, 30));
		bUsuario.setBorder(null);
		
		pNorthDrch.setBorder(new EmptyBorder(10,10,10,10));
		pNorth.setBackground(Color.LIGHT_GRAY);
		pNorthDrch.setBackground(Color.LIGHT_GRAY);
		
		// Panel Izq
		add(pSouth);
		pSouth.add(pIzq);
		pIzq.setBorder(borde);
		JPanel izqBuscar = new JPanel(new BorderLayout());
		pIzq.add(izqBuscar, BorderLayout.NORTH);
		JLabel lblBuscar = new JLabel("Buscar");
		izqBuscar.add(lblBuscar, BorderLayout.WEST);
		izqBuscar.add(textBuscar, BorderLayout.CENTER);
		
//		pIzq.add(lblBuscar);
//		pIzq.add(textBuscar);

		pIzq.add(comboBoxHabitaciones);
		
        // Configurar la apariencia del slider
		SliderPrecio.setMajorTickSpacing(200); // Espaciado principal entre los ticks
		SliderPrecio.setMinorTickSpacing(100); // Espaciado secundario entre los ticks
		SliderPrecio.setPaintTicks(true); // Mostrar los ticks
		SliderPrecio.setPaintLabels(true); // Mostrar las etiquetas de los ticks
		pIzq.add(SliderPrecio);
		
		// Panel Derecho
		pSouth.add(pDrcha);
		pDrcha.setBorder(borde);
		
		bUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent source = (JComponent) e.getSource();
				JPopupMenu popupMenu = menuUsuario.getPopupMenu();
				popupMenu.show(source,0, source.getHeight());
			}
		});
	}
	public static ImageIcon redimensionarIcono(ImageIcon imageIcon, int width, int height) {
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // escalar la imagen
		
		imageIcon = new ImageIcon(newimg);
		
		return(imageIcon);
	}
	
    public static void main(String[] args) {
        new  VentanaCliente();
    }
}
