package es.deusto.spq.client;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.deusto.spq.pojo.RoomData;

public class RoomDataDialog {

    protected static RoomData createRoomData() {
        // Create input fields
        JTextField numberField = new JTextField(5);
        JTextField capacityField = new JTextField(5);
        JTextField typeField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        JTextField descriptionField = new JTextField(20);

        // Create a panel to hold the input fields
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Room Number:"));
        panel.add(numberField);
        panel.add(new JLabel("Capacity:"));
        panel.add(capacityField);
        panel.add(new JLabel("Type:"));
        panel.add(typeField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        // Show the dialog and get the result
        int result = JOptionPane.showConfirmDialog(null, panel, 
                 "Enter Room Data", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int number = Integer.parseInt(numberField.getText());
                int capacity = Integer.parseInt(capacityField.getText());
                String type = typeField.getText();
                double price = Double.parseDouble(priceField.getText());
                String description = descriptionField.getText();

                // Create and return a new RoomData object
                RoomData roomData = new RoomData();
                roomData.setNumber(number);
                roomData.setCapacity(capacity);
                roomData.setType(type);
                roomData.setPrice(price);
                roomData.setDescription(description);
                return roomData;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter numeric values for number, capacity, and price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null; // Return null if the dialog was cancelled or there was an error
    }

}
