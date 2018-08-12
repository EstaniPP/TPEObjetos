import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Vector;

public class FormClientes extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public FormClientes() {
		
		// llamado a bd
		DBManager db = new DBManager();
		// llamado a bd
		setTitle("CLIENTES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 663, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscarClientes = new JLabel("Buscar clientes");
		lblBuscarClientes.setBounds(6, 6, 121, 16);
		contentPane.add(lblBuscarClientes);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// se presiono enter
					Vector<Cliente> vector = new Vector<Cliente>();
					try {
						vector = db.getClientes(null);
					}catch(SQLException e1) {
						e1.getStackTrace();
					}
					System.out.println(vector.size());
				}
			}
		});
		textField.setToolTipText("NOMBRE");
		textField.setBounds(6, 51, 217, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 24, 525, 12);
		contentPane.add(separator);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(6, 38, 61, 16);
		contentPane.add(lblNombre);
		
		JLabel lblIdentificador = new JLabel("ID");
		lblIdentificador.setBounds(224, 38, 156, 16);
		contentPane.add(lblIdentificador);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("NOMBRE");
		textField_1.setColumns(10);
		textField_1.setBounds(224, 51, 217, 30);
		contentPane.add(textField_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 81, 428, 200);
		contentPane.add(scrollPane);
		
		String[] columnas = new String[] {"ID", "NOMBRE", "TELEFONO", "EMAIL", "TIPO"};
		Object[][] clientes = new Object[][] {};
		table = new JTable(clientes, columnas);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		
	}
}
