package Billing;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmartBillingApp extends JFrame {
    private JTextField productField, qtyField, priceField, taxField, discountField;
    private JTextField customerNameField, customerPhoneField;
    private JTable table;
    private DefaultTableModel model;
    private JLabel subtotalLabel, grandTotalLabel, billNoLabel;
    private static int billCounter = 1000; // Auto-increment bill number start
    private double subtotal = 0.0, grandTotal = 0.0;

    public SmartBillingApp() {
        setTitle("Smart Billing App");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Top Panel: Customer Info + Bill No
        JPanel customerPanel = new JPanel(new GridLayout(2, 3, 10, 5));
        billNoLabel = new JLabel("Bill No: " + billCounter);
        customerNameField = new JTextField();
        customerPhoneField = new JTextField();
        customerPanel.add(new JLabel("Customer Name:"));
        customerPanel.add(customerNameField);
        customerPanel.add(billNoLabel);
        customerPanel.add(new JLabel("Phone:"));
        customerPanel.add(customerPhoneField);
        add(customerPanel, BorderLayout.NORTH);

        // 🔹 Middle Panel: Product Entry
        JPanel inputPanel = new JPanel();
        productField = new JTextField(10);
        qtyField = new JTextField(5);
        priceField = new JTextField(5);
        JButton addButton = new JButton("Add Product");
        JButton removeButton = new JButton("Remove Selected");
        inputPanel.add(new JLabel("Product:"));
        inputPanel.add(productField);
        inputPanel.add(new JLabel("Qty:"));
        inputPanel.add(qtyField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        add(inputPanel, BorderLayout.CENTER);

        // 🔹 Table
        String[] cols = {"Product", "Qty", "Price", "Subtotal"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        // 🔹 Bottom Panel: Totals & Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());
        taxField = new JTextField("0", 5);
        discountField = new JTextField("0", 5);
        subtotalLabel = new JLabel("Subtotal: 0.0");
        grandTotalLabel = new JLabel("Grand Total: 0.0");
        JButton updateButton = new JButton("Update Total");
        JButton printButton = new JButton("Print Bill");

        bottomPanel.add(new JLabel("Tax %:"));
        bottomPanel.add(taxField);
        bottomPanel.add(new JLabel("Discount %:"));
        bottomPanel.add(discountField);
        bottomPanel.add(updateButton);
        bottomPanel.add(subtotalLabel);
        bottomPanel.add(grandTotalLabel);
        bottomPanel.add(printButton);
        add(bottomPanel, BorderLayout.PAGE_END);

        // 🔹 Actions
        addButton.addActionListener(e -> addProduct());
        removeButton.addActionListener(e -> removeSelected());
        updateButton.addActionListener(e -> updateTotals());
        printButton.addActionListener(e -> printBill());
    }

    private void addProduct() {
        String product = productField.getText();
        int qty = Integer.parseInt(qtyField.getText());
        double price = Double.parseDouble(priceField.getText());
        double sub = qty * price;
        model.addRow(new Object[]{product, qty, price, sub});
        updateTotals();
    }

    private void removeSelected() {
        int row = table.getSelectedRow();
        if (row != -1) {
            model.removeRow(row);
            updateTotals();
        }
    }

    private void updateTotals() {
        subtotal = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            subtotal += (double) model.getValueAt(i, 3);
        }
        double tax = Double.parseDouble(taxField.getText());
        double discount = Double.parseDouble(discountField.getText());
        double taxAmount = subtotal * tax / 100;
        double discountAmount = subtotal * discount / 100;
        grandTotal = subtotal + taxAmount - discountAmount;
        subtotalLabel.setText("Subtotal: " + subtotal);
        grandTotalLabel.setText("Grand Total: " + grandTotal);
    }

    private void printBill() {
        StringBuilder bill = new StringBuilder();
        String dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        bill.append("        SMART BILLING STORE\n");
        bill.append("    123 Market Road, Mumbai\n");
        bill.append(" Phone: +91-9876543210\n");
        bill.append("====================================\n");
        bill.append("Bill No: ").append(billCounter).append("\n");
        bill.append("Customer: ").append(customerNameField.getText()).append("\n");
        bill.append("Phone: ").append(customerPhoneField.getText()).append("\n");
        bill.append("Date & Time: ").append(dateTime).append("\n");
        bill.append("====================================\n");
        bill.append(String.format("%-10s %-5s %-8s %-8s\n", "Product", "Qty", "Price", "Subtotal"));
        bill.append("------------------------------------\n");

        for (int i = 0; i < model.getRowCount(); i++) {
            bill.append(String.format("%-10s %-5s %-8s %-8s\n",
                    model.getValueAt(i, 0),
                    model.getValueAt(i, 1),
                    model.getValueAt(i, 2),
                    model.getValueAt(i, 3)));
        }

        bill.append("------------------------------------\n");
        bill.append("Subtotal: ").append(subtotal).append("\n");
        bill.append("Tax (").append(taxField.getText()).append("%): ")
            .append(subtotal * Double.parseDouble(taxField.getText()) / 100).append("\n");
        bill.append("Discount (").append(discountField.getText()).append("%): ")
            .append(subtotal * Double.parseDouble(discountField.getText()) / 100).append("\n");
        bill.append("GRAND TOTAL: ").append(grandTotal).append("\n");
        bill.append("====================================\n");
        bill.append("         THANK YOU! VISIT AGAIN\n");
        bill.append("====================================\n");

        // Show Bill in Dialog
        JTextArea textArea = new JTextArea(bill.toString());
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Bill", JOptionPane.INFORMATION_MESSAGE);

        // 🔹 Save to File
        try {
            // Default: project folder
            FileWriter writer = new FileWriter("Bill_" + billCounter + ".txt");

            // 🔹 If you want Desktop save, uncomment this 👇
            // String userHome = System.getProperty("user.home");
            // FileWriter writer = new FileWriter(userHome + "/Desktop/Bill_" + billCounter + ".txt");

            writer.write(bill.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        billCounter++; // increment bill number
        billNoLabel.setText("Bill No: " + billCounter);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SmartBillingApp().setVisible(true));
    }
}
