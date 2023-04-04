import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormRegistration {
    private JFrame frame;
    private JPanel panel;
    private JLabel labelName, labelSurname, labelFatherName;
    private JTextField fieldName, fieldSurname, fieldFatherName, oneField;

    private String[] fullName;

    public static void main(String[] args) {
        FormRegistration homework = new FormRegistration();
        homework.go();
    }

    public void go() {
        frame = new JFrame("My window");
        panel = new JPanel();
        panel.setBackground(Color.pink);
        fullName = new String[3];
        createAdvancedFields(fullName);
        JButton button = createButton();

        frame.getContentPane().add(panel);
        frame.getContentPane().add(BorderLayout.SOUTH, button);


        frame.setSize(300, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.revalidate();

    }

    private void createAdvancedFields(String[] fullName) {
        GridBagLayout bagLayout = new GridBagLayout();
        panel.setLayout(bagLayout);
        labelSurname = new JLabel("Фамилия: ");
        labelName = new JLabel("Имя: ");
        labelFatherName = new JLabel("Отчество: ");

        GridBagConstraints forLabelSurname = new GridBagConstraints();
        forLabelSurname.gridx = 0;
        forLabelSurname.gridy = 0;
        GridBagConstraints forFieldSurname = new GridBagConstraints();
        forFieldSurname.gridx = 1;
        forFieldSurname.gridy = 0;
        GridBagConstraints forLabelName = new GridBagConstraints();
        forLabelName.gridx = 0;
        forLabelName.gridy = 1;
        GridBagConstraints forFieldName = new GridBagConstraints();
        forFieldName.gridx = 1;
        forFieldName.gridy = 1;
        GridBagConstraints forLabelFatherName = new GridBagConstraints();
        forLabelFatherName.gridx = 0;
        forLabelFatherName.gridy = 2;
        GridBagConstraints forFieldFatherName = new GridBagConstraints();
        forFieldFatherName.gridx = 1;
        forFieldFatherName.gridy = 2;

        fieldName = new JTextField(10);
        fieldSurname = new JTextField(10);
        fieldFatherName = new JTextField(10);

        panel.add(labelSurname, forLabelSurname);
        panel.add(labelName, forLabelName);
        panel.add(labelFatherName, forLabelFatherName);
        panel.add(fieldSurname, forFieldSurname);
        panel.add(fieldName, forFieldName);
        panel.add(fieldFatherName, forFieldFatherName);
    }

    private JButton createButton() {
        JButton button = new JButton("Collapse");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (button.getText().equals("Collapse")) {
                    fullName[0] = fieldSurname.getText();
                    fullName[1] = fieldName.getText();
                    if (!fieldFatherName.getText().isEmpty()) {
                        fullName[2] = fieldFatherName.getText();
                    }
                    if (validate()) {
                        createOneInputField(fullName);
                        button.setText("Expand");
                    }

                } else {
                    panel.removeAll();
                    fullName = oneField.getText().split("\s");
                    if (validate()) {
                        createAdvancedFields(fullName);
                        fieldSurname.setText(fullName[0]);
                        fieldName.setText(fullName[1]);
                        if (fullName.length == 2) {
                            fieldFatherName.setText("");
                        } else {
                            fieldFatherName.setText(fullName[2]);
                        }
                        button.setText("Collapse");
                        frame.repaint();

                    }
                }
            }
        });
        return button;
    }

    private void createOneInputField(String[] fullName) {
        panel.removeAll();
        panel.add(new JLabel("Ф.И.О: "));
        oneField = new JTextField(15);
        String surname = fullName[0];
        String name = fullName[1];
        String fatherName = null;
        if (fullName.length > 2) {
            fatherName = fullName[2];
        }
        oneField.setText(surname + " " + name + (fatherName == null ? "" : " " + fatherName));
        panel.add(oneField);
        frame.repaint();
    }

    private boolean validate() {
        String name = fullName[0];
        String surname = fullName[1];

        if (isNullOrEmpty(name) || isNullOrEmpty(surname)) {
            JOptionPane.showMessageDialog(null, "Пожалуйста, введите имя и фамилию");
            return false;
        } else {
            return true;
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}

