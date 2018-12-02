package Forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Connection extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private File f = new File("connection.txt");
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection frame = new Connection();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Connection() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 164, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHost = new JLabel("HOST");
		lblHost.setBounds(6, 6, 61, 16);
		contentPane.add(lblHost);
		
		JLabel lblUser = new JLabel("PORT");
		lblUser.setBounds(6, 51, 61, 16);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("DBNAME");
		lblPassword.setBounds(6, 100, 104, 16);
		contentPane.add(lblPassword);
		
		JLabel lblDbname = new JLabel("USER");
		lblDbname.setBounds(6, 156, 104, 16);
		contentPane.add(lblDbname);
		
		textField = new JTextField();
		textField.setBounds(6, 22, 152, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(6, 67, 152, 26);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(6, 118, 152, 26);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(6, 172, 152, 26);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("CONFIGURAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					f.createNewFile();
					BufferedWriter bw = new BufferedWriter(new FileWriter(f));
					bw.write(textField.getText());
					bw.newLine();
					bw.write(textField_1.getText());
					bw.newLine();
					bw.write(textField_2.getText());
					bw.newLine();
					bw.write(textField_3.getText());
					bw.newLine();
					bw.write(textField_4.getText());
					bw.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				Connection.this.dispose();
			}
		});
		btnNewButton.setBounds(6, 249, 152, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblPort = new JLabel("PASSWORD");
		lblPort.setBounds(6, 195, 104, 16);
		contentPane.add(lblPort);
	
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(6, 211, 152, 26);
		contentPane.add(textField_4);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			// formato host\nport\ndbname\nuser\npassword
			// host
			textField.setText(br.readLine());
			textField_1.setText(br.readLine());
			textField_2.setText(br.readLine());
			textField_3.setText(br.readLine());
			textField_4.setText(br.readLine());
			
			br.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("no se encontro connection.txt");
			e1.printStackTrace();
		}
	}
}
