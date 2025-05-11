package virtualMarket.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import virtualMarket.customer.CustomerSys;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageCustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfAddress;
	private JTextField textField;
	ManageCustomerFrame thisFrame;

	/**
	 * Create the frame.
	 */

	public ManageCustomerFrame(CustomerPanelFrame cpf) {
		thisFrame = this;
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

		JButton btnAdd = new JButton("ADD");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
	                if (tfName.getText().isEmpty() || tfEmail.getText().isEmpty() || tfAddress.getText().isEmpty()) {
	                    textArea.setText("Please fill in all fields!");
	                    return;
	                }

	                if (CustomerSys.findName(tfName.getText())) {
	                    textArea.setText("Customer '" +tfName.getText() + "' already exists!");
	                } else {
	                    if (CustomerSys.addCustomer(tfName.getText(),tfEmail.getText(),  tfAddress.getText())) {
	                        textArea.setText("Customer '" + tfName.getText()+ "' successfully added.");
	                    } else {
	                        textArea.setText("Failed to add customer '" + tfName.getText() + "'. Please try again.");
	                    }
	                }
	            }
	        });

		btnAdd.setBounds(190, 125, 89, 23);
		contentPane.add(btnAdd);

		JButton btnDisplay = new JButton("DISPLAY");
		btnDisplay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textArea.setText(CustomerSys.display());

			}
		});
		btnDisplay.setBounds(10, 125, 129, 23);
		contentPane.add(btnDisplay);

		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(CustomerSys.removeCustomer(Integer.parseInt(textField.getText()))) {
					JOptionPane.showConfirmDialog(thisFrame, "Removed user!");
				}else 
					JOptionPane.showConfirmDialog(thisFrame, "User can not be removed!");
				
			}
		});

		btnRemove.setBounds(190, 236, 90, 23);
		contentPane.add(btnRemove);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 208, 70, 14);
		contentPane.add(lblId);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(90, 205, 190, 20);
		contentPane.add(textField);
		
		JButton btnGoBack = new JButton("<--");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerSys.writeUsedCustomerIDs();
				cpf.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setBounds(10, 517, 90, 23);
		contentPane.add(btnGoBack);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfName.setText("");
				tfEmail.setText("");
				tfAddress.setText("");
				textField.setText("");
				textArea.setText("");
			}
		});

		btnClear.setBounds(191, 159, 89, 23);
		contentPane.add(btnClear);
	}
}