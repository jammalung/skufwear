import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Project {
    private JPanel Panel;
    private JComboBox<String> titleComboBox;
    private JLabel Brand;
    private JLabel Size;
    private JComboBox<String> titleComboBox2;
    private JComboBox<String> titleComboBox3;
    private JButton saveButton;
    private JTextField textField1;
    private JLabel errorLabel;
    private JLabel successLabel;
    private JRadioButton omivaRadioButton;
    private JRadioButton courierRadioButton;
    private JRadioButton DPDRadioButton;
    private JLabel name;

    public Project() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String brand = (String) titleComboBox.getSelectedItem();
                String size = (String) titleComboBox2.getSelectedItem();
                String color = (String) titleComboBox3.getSelectedItem();
                String customText = textField1.getText();
                String deliveryMethod = "";

                if (omivaRadioButton.isSelected()) {
                    deliveryMethod = "Omniva";
                } else if (courierRadioButton.isSelected()) {
                    deliveryMethod = "Courier";
                } else if (DPDRadioButton.isSelected()) {
                    deliveryMethod = "DPD";
                } else {
                    errorLabel.setText("Please enter delivery type before saving.");
                    errorLabel.setForeground(Color.RED);
                    return; // Прекращаем выполнение метода, чтобы избежать сохранения данных при отсутствии выбора доставки
                }

                if (customText.isEmpty()) {
                    errorLabel.setText("Please enter address before saving.");
                    errorLabel.setForeground(Color.RED);
                } else {
                    errorLabel.setText("");
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        appendToFile("selected_options.txt", "Brand: " + brand + ", Size: " + size + ", Color: " + color + ", Address: " + customText + ", Delivery Method: " + deliveryMethod);

                        successLabel.setText("Order saved");
                        successLabel.setForeground(Color.GREEN);
                    }
                }
            }
        });

        omivaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (omivaRadioButton.isSelected()) {
                    courierRadioButton.setSelected(false);
                    DPDRadioButton.setSelected(false);
                }
            }
        });

        courierRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (courierRadioButton.isSelected()) {
                    omivaRadioButton.setSelected(false);
                    DPDRadioButton.setSelected(false);
                }
            }
        });

        DPDRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DPDRadioButton.isSelected()) {
                    omivaRadioButton.setSelected(false);
                    courierRadioButton.setSelected(false);
                }
            }
        });
    }

    private void appendToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content);
            writer.newLine();
            System.out.println("Selected options appended to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createUIComponents() {
        titleComboBox = new JComboBox<>();
        titleComboBox.addItem("Adidas");
        titleComboBox.addItem("Armani");
        titleComboBox.addItem("Gap");
        titleComboBox.addItem("Nike");
        titleComboBox.addItem("Tommy Hilfiger");
        titleComboBox.addItem("Under Armour");
        titleComboBox.addItem("Zara");

        titleComboBox2 = new JComboBox<>();
        titleComboBox2.addItem("XS");
        titleComboBox2.addItem("S ");
        titleComboBox2.addItem("M");
        titleComboBox2.addItem("L");
        titleComboBox2.addItem("XL");
        titleComboBox2.addItem("2XL");

        titleComboBox3 = new JComboBox<>();
        titleComboBox3.addItem("White");
        titleComboBox3.addItem("Black");
        titleComboBox3.addItem("Brown");
        titleComboBox3.addItem("Orange");
        titleComboBox3.addItem("Red");
        titleComboBox3.addItem("Green");
        titleComboBox3.addItem("Blue");
        titleComboBox3.addItem("Cyan");
        titleComboBox3.addItem("Yellow");
        titleComboBox3.addItem("Purple");
        titleComboBox3.addItem("Pink");


        errorLabel = new JLabel();
        successLabel = new JLabel();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Project");
        frame.setContentPane(new Project().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}









