import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

public class FormClientes extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	// tablas
	private JTable table;
	DefaultTableModel model;
	JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	
	
	
	public FormClientes() {
		
		// llamado a bd
		DBManager db = new DBManager();
		
		model = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		// llamado a bd
		setTitle("CLIENTES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 401);
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
				if(e.getKeyCode() == 10 && !textField.getText().isEmpty()) {					
					// se presiono enter
					Vector<Cliente> vector = new Vector<Cliente>();
					// creo el filtro x nombre
					FiltroCliente.nombre f1 = new FiltroCliente.nombre(textField.getText());
					try {
						vector = db.getClientes(f1);
					}catch(SQLException e1) {
						e1.getStackTrace();
					}
					
					fillTable(vector);
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
		lblNombre.setBounds(8, 36, 61, 16);
		contentPane.add(lblNombre);
		
		JLabel lblIdentificador = new JLabel("ID");
		lblIdentificador.setBounds(225, 36, 156, 16);
		contentPane.add(lblIdentificador);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(textField_1.getText());
				if(e.getKeyCode() == 10 && !textField_1.getText().isEmpty()) {
					// se presiono enter
					Vector<Cliente> vector = new Vector<Cliente>();
					// creo el filtro x nombre
					FiltroCliente.idCliente f1 = new FiltroCliente.idCliente(Integer.valueOf(textField_1.getText()));
					try {
						vector = db.getClientes(f1);
					}catch(SQLException e1) {
						e1.getStackTrace();
					}
					fillTable(vector);
					
				}
			}
		});
		
		textField_1.setToolTipText("NOMBRE");
		textField_1.setColumns(10);
		textField_1.setBounds(224, 51, 217, 30);
		contentPane.add(textField_1);
		
		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 81, 428, 278);
		contentPane.add(scrollPane);
		
		
		
		model.addColumn("ID");
		model.addColumn("NOMBRE");
		model.addColumn("TELEFONO");
		model.addColumn("EMAIL");
		model.addColumn("TIPO");
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		
		JButton btnNewButton = new JButton("NUEVO CLIENTE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormNuevoCliente nuevoC = new FormNuevoCliente(null);
				nuevoC.setVisible(true);
			}
		});
		btnNewButton.setBounds(450, 55, 117, 45);
		contentPane.add(btnNewButton);
		
		JButton btnModificarSeleccionado = new JButton("MODIFICAR");
		btnModificarSeleccionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// modificar seleccionado
				int selectedRow = table.getSelectedRow();
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.");
				}else {
					// obtengo el id del cliente seleccionado
					int idCliente = Integer.valueOf((String) table.getValueAt(selectedRow, 0));
					//System.out.print(idCliente);
					// obtengo cliente desde bd
					Vector<Cliente> vect = new Vector<Cliente>();
					try {
						vect = db.getClientes(new FiltroCliente.idCliente(idCliente));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(vect.size() == 0) {
						JOptionPane.showMessageDialog(null, "Se produjo un error");
					}else {
						FormNuevoCliente nuevoC = new FormNuevoCliente(vect.elementAt(0));
						nuevoC.setVisible(true);
					}
				}
				//System.out.println(table.getSelectedRow());
			}
		});
		btnModificarSeleccionado.setBounds(450, 99, 117, 45);
		contentPane.add(btnModificarSeleccionado);
		fillTable(null);
		
		
	}
	public void fillTable(Vector<Cliente> vector) {
		model.setRowCount(0);
		// cargo todo en la tabla
		if(vector != null) {
			for(int i = 0; i < vector.size(); i++) {
				String[] p = new String[] {((Integer)vector.elementAt(i).getIdCliente()).toString(),
											vector.elementAt(i).getNombre(), 
											vector.elementAt(i).getTelefono(), 
											vector.elementAt(i).getEmail(), 
											((Integer) vector.elementAt(i).getTipoCliente()).toString()};
				model.addRow(p);
			}
		}
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
}
