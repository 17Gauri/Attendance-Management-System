import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AttendanceManagementGUI extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField idField, nameField;

    public AttendanceManagementGUI() {
        setTitle("Attendance Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Table Model
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Attendance"}, 0);
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        inputPanel.add(addButton);
        
        JButton presentButton = new JButton("Mark Present");
        presentButton.addActionListener(e -> markAttendance("Present"));
        inputPanel.add(presentButton);
        
        JButton absentButton = new JButton("Mark Absent");
        absentButton.addActionListener(e -> markAttendance("Absent"));
        inputPanel.add(absentButton);
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        inputPanel.add(exitButton);
        
        // Layout
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void addStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        
        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both ID and Name.");
            return;
        }
        
        tableModel.addRow(new Object[]{id, name, "Absent"});
        idField.setText("");
        nameField.setText("");
    }
    
    private void markAttendance(String status) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.setValueAt(status, selectedRow, 2);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to mark attendance.");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AttendanceManagementGUI().setVisible(true);
        });
    }
}
