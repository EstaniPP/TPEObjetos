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

public class FormClientes extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public FormClientes() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 97, 467, 200);
		contentPane.add(scrollPane);
		 String[] colName = new String[] { "Product Name" ,"Price" };
	        Object[][] products = new Object[][] { 
	                { "Galleta" ,"$80" },
	                { "Malta" ,"$40" }
	               
	            };
		table = new JTable(products, colName);
		scrollPane.setViewportView(table);
	}
}
