package virtualMarket.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ManageCustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfAddress;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public ManageCustomerFrame(CustomerPanelFrame cpf) {
		setTitle("Manage Customers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 23, 70, 14);
		contentPane.add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(90, 20, 190, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(90, 48, 190, 20);
		contentPane.add(tfEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 51, 70, 14);
		contentPane.add(lblEmail);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(90, 79, 190, 20);
		contentPane.add(tfAddress);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 82, 70, 14);
		contentPane.add(lblAddress);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 20, 200, 520);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(190, 125, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnDisplay = new JButton("DISPLAY");
		btnDisplay.setBounds(10, 125, 129, 23);
		contentPane.add(btnDisplay);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.setBounds(190, 214, 90, 23);
		contentPane.add(btnRemove);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 186, 70, 14);
		contentPane.add(lblId);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(90, 183, 190, 20);
		contentPane.add(textField);
	}
}
