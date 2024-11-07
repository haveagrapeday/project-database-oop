package database;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class LAB9_7gui {
    private static List<String> accountData = new ArrayList<>(); // Store data for display in table
    private static String accountInfo;

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Show Detail of Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setLayout(new BorderLayout());

        // Label at the top center
        JLabel titleLabel = new JLabel("Account Money", JLabel.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        // Main panel with titled border
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Enter Data Account Money", TitledBorder.LEFT, TitledBorder.TOP));
        mainPanel.setLayout(new GridLayout(10, 1, 5, 5));

        // ID and Money panel
        JPanel idMoneyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField idField = new JTextField(5);
        JTextField moneyField = new JTextField(5);
        idMoneyPanel.add(new JLabel("ID:"));
        idMoneyPanel.add(idField);
        idMoneyPanel.add(new JLabel("MONEY:"));
        idMoneyPanel.add(moneyField);
        idMoneyPanel.add(new JLabel("BATH:"));

        // Annual Interest Rate panel
        JPanel ratePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField rateField = new JTextField(10);
        ratePanel.add(new JLabel("ANNUAL INTEREST RATE:"));
        ratePanel.add(rateField);

        // Day Open Account panel
        JPanel dayOpenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> dayOpenComboBox = new JComboBox<>();
        JComboBox<String> monthOpenComboBox = new JComboBox<>();
        JComboBox<String> yearOpenComboBox = new JComboBox<>();
        fillDayComboBox(dayOpenComboBox);
        fillMonthComboBox(monthOpenComboBox);
        fillYearComboBox(yearOpenComboBox, 1980, 2024);
        dayOpenPanel.add(new JLabel("DAY OPEN ACCOUNT:"));
        dayOpenPanel.add(dayOpenComboBox);
        dayOpenPanel.add(monthOpenComboBox);
        dayOpenPanel.add(yearOpenComboBox);

        // First Name panel
        JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField firstNameField = new JTextField(10);
        firstNamePanel.add(new JLabel("FIRST NAME:"));
        firstNamePanel.add(firstNameField);

        // Last Name panel
        JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField lastNameField = new JTextField(10);
        lastNamePanel.add(new JLabel("LAST NAME:"));
        lastNamePanel.add(lastNameField);

        // Birth Day panel with age calculation
        JPanel birthDayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> dayBirthComboBox = new JComboBox<>();
        JComboBox<String> monthBirthComboBox = new JComboBox<>();
        JComboBox<String> yearBirthComboBox = new JComboBox<>();
        fillDayComboBox(dayBirthComboBox);
        fillMonthComboBox(monthBirthComboBox);
        fillYearComboBox(yearBirthComboBox, 1980, 2024);
        birthDayPanel.add(new JLabel("BIRTH DAY:"));
        birthDayPanel.add(dayBirthComboBox);
        birthDayPanel.add(monthBirthComboBox);
        birthDayPanel.add(yearBirthComboBox);

        // Age panel
        JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField ageField = new JTextField(5);
        ageField.setEditable(false);
        agePanel.add(new JLabel("AGE:"));
        agePanel.add(ageField);
        agePanel.add(new JLabel("YEAR"));

        // ActionListener to calculate age automatically
        ActionListener ageCalculator = e -> {
            try {
                int day = Integer.parseInt((String) dayBirthComboBox.getSelectedItem());
                int month = Integer.parseInt((String) monthBirthComboBox.getSelectedItem());
                int year = Integer.parseInt((String) yearBirthComboBox.getSelectedItem());

                LocalDate birthDate = LocalDate.of(year, month, day);
                LocalDate currentDate = LocalDate.now();
                int age = Period.between(birthDate, currentDate).getYears();

                ageField.setText(String.valueOf(age));
            } catch (Exception ex) {
                ageField.setText(""); // Clear age field if date is incomplete or invalid
            }
        };
        dayBirthComboBox.addActionListener(ageCalculator);
        monthBirthComboBox.addActionListener(ageCalculator);
        yearBirthComboBox.addActionListener(ageCalculator);

        // Adding panels to main panel
        mainPanel.add(idMoneyPanel);
        mainPanel.add(ratePanel);
        mainPanel.add(dayOpenPanel);
        mainPanel.add(firstNamePanel);
        mainPanel.add(lastNamePanel);
        mainPanel.add(birthDayPanel);
        mainPanel.add(agePanel);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("SAVE");
        JButton showButton = new JButton("SHOW");
        JButton deleteButton = new JButton("DELETE");
        JButton updateButton = new JButton("UPDATE");
        buttonPanel.add(saveButton);
        buttonPanel.add(showButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        // ActionListener for SAVE button
        saveButton.addActionListener(e -> {
            String id = idField.getText();
            String money = moneyField.getText();
            String rate = rateField.getText();
            String dayOpen = (String) dayOpenComboBox.getSelectedItem();
            String monthOpen = (String) monthOpenComboBox.getSelectedItem();
            String yearOpen = (String) yearOpenComboBox.getSelectedItem();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String dayBirth = (String) dayBirthComboBox.getSelectedItem();
            String monthBirth = (String) monthBirthComboBox.getSelectedItem();
            String yearBirth = (String) yearBirthComboBox.getSelectedItem();
            String age = ageField.getText();

            String accountInfo = String.format("ID: %s, Money: %s, Rate: %s, Open Date: %s/%s/%s, Name: %s %s, Birth: %s/%s/%s, Age: %s years",
                    id, money, rate, dayOpen, monthOpen, yearOpen, firstName, lastName, dayBirth, monthBirth, yearBirth, age);

            accountData.add(accountInfo); // Save data to list
            JOptionPane.showMessageDialog(frame, "Data saved successfully!");
        });

        // ActionListener for SHOW button
        showButton.addActionListener(e -> {
            String[] columns = {"Account Details"};
            String[][] data = accountData.stream().map(account -> new String[]{account}).toArray(String[][]::new);
            JTable table = new JTable(data, columns);
            JScrollPane scrollPane = new JScrollPane(table);

            JFrame tableFrame = new JFrame("Account Details");
            tableFrame.setSize(400, 300);
            tableFrame.add(scrollPane);
            tableFrame.setVisible(true);
        });

        // ActionListener for DELETE button
        deleteButton.addActionListener(e -> {
            String idToDelete = idField.getText();
            accountData.removeIf(account -> account.contains("ID: " + idToDelete));
            JOptionPane.showMessageDialog(frame, "Account with ID " + idToDelete + " deleted.");
        });

        // ActionListener for UPDATE button
        updateButton.addActionListener(e -> {
            String idToUpdate = idField.getText();
            accountData.removeIf(account -> account.contains("ID: " + idToUpdate));

            String money = moneyField.getText();
            String rate = rateField.getText();
            String dayOpen = (String) dayOpenComboBox.getSelectedItem();
            String monthOpen = (String) monthOpenComboBox.getSelectedItem();
            String yearOpen = (String) yearOpenComboBox.getSelectedItem();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String dayBirth = (String) dayBirthComboBox.getSelectedItem();
            String monthBirth = (String) monthBirthComboBox.getSelectedItem();
            String yearBirth = (String) yearBirthComboBox.getSelectedItem();
            String age = ageField.getText();

            String updatedAccountInfo = String.format("ID: %s, Money: %s, Rate: %s, Open Date: %s/%s/%s, Name: %s %s, Birth: %s/%s/%s, Age: %s years",
                    idToUpdate, money, rate, dayOpen, monthOpen, yearOpen, firstName, lastName, dayBirth, monthBirth, yearBirth, age);

            accountData.add(accountInfo); // Save data to list
            JOptionPane.showMessageDialog(frame, "Data saved successfully!");
        });

        // ActionListener for SHOW button to display saved data in a table
        showButton.addActionListener(e -> {
            String[] columns = {"Account Details"};
            String[][] data = accountData.stream().map(account -> new String[]{account}).toArray(String[][]::new);
            JTable table = new JTable(data, columns);
            JScrollPane scrollPane = new JScrollPane(table);

            // Display in a new JFrame
            JFrame tableFrame = new JFrame("Account Details");
            tableFrame.setSize(400, 300);
            tableFrame.add(scrollPane);
            tableFrame.setVisible(true);
        });

        // Adding panels to frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Set frame visibility
        frame.setVisible(true);
    }

    private static void fillDayComboBox(JComboBox<String> comboBox) {
        for (int i = 1; i <= 31; i++) {
            comboBox.addItem(String.valueOf(i));
        }
    }

    private static void fillMonthComboBox(JComboBox<String> comboBox) {
        for (int i = 1; i <= 12; i++) {
            comboBox.addItem(String.valueOf(i));
        }
    }

    private static void fillYearComboBox(JComboBox<String> comboBox, int startYear, int endYear) {
        for (int i = startYear; i <= endYear; i++) {
            comboBox.addItem(String.valueOf(i));
        }
    }
}
