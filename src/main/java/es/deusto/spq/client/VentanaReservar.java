package es.deusto.spq.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import es.deusto.spq.pojo.RoomData;

public class VentanaReservar extends JFrame {

    private JLabel checkinLabel;
    private JSpinner checkinField;
    private JLabel daysLabel;
    private JTextField daysField;
    private JButton submitButton;

    public VentanaReservar(Container container, RoomData roomData) {
        super("Reserve Room");
        setSize(300, 200);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));

        checkinLabel = new JLabel("Check-in Date:");
        checkinField = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(checkinField, "dd/MM/yyyy");
        checkinField.setEditor(dateEditor);
        daysLabel = new JLabel("Number of Days:");
        daysField = new JTextField();

        formPanel.add(checkinLabel);
        formPanel.add(checkinField);
        formPanel.add(daysLabel);
        formPanel.add(daysField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date checkin = (Date) checkinField.getValue();
                System.out.println(checkin.toString());
                    int days = Integer.parseInt(daysField.getText());
                    Boolean isBook = container.bookRoom(roomData.getNumber(), checkin, days);
                    if (isBook) {
                        JOptionPane.showMessageDialog(null, "Room booked successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Room could not be booked", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formPanel.add(submitButton);

        add(formPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
