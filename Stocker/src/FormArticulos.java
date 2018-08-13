import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FormArticulos extends JFrame {

	/**
	 * Launch the application.
	 */
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	DefaultTableModel model = new DefaultTableModel(); 
	JScrollPane scrollPane = new JScrollPane();
	private JTable table_1;

	/**
	 * Create the frame.
	 */
	public FormArticulos() {
		
		// llamado a bd
		DBManager db = new DBManager();
		// llamado a bd
		setTitle("ARTICULOS");		
		
		//Elementos del frame
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1032, 481);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscarArticulos = new JLabel("Buscar articulos");
		lblBuscarArticulos.setBounds(12, 13, 121, 16);
		contentPane.add(lblBuscarArticulos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 31, 990, 12);
		contentPane.add(separator);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(12, 40, 135, 16);
		contentPane.add(lblDescripcion);
		
		textField = new JTextField();
		textField.setToolTipText("DESCRIPCION");
		textField.setColumns(10);
		textField.setBounds(12, 55, 150, 30);
		contentPane.add(textField);	
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10 && !textField.getText().isEmpty()) {
					// se presiono enter
					Vector<Articulo> vector = new Vector<Articulo>();
					// creo el filtro x nombre
					FiltroArticulo.descripcion f1 = new FiltroArticulo.descripcion(textField.getText());
					try {
						vector = db.getArticulos(f1);
					}catch(SQLException e1) {
						e1.getStackTrace();
					}
					fillTable(vector);
				}
			}
		});		
		
		JLabel label_1 = new JLabel("ID");
		label_1.setBounds(172, 40, 137, 16);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("ID");
		textField_1.setColumns(10);
		textField_1.setBounds(172, 55, 150, 30);
		contentPane.add(textField_1);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10 && !textField_1.getText().isEmpty()) {
					// se presiono enter
					Vector<Articulo> vector = new Vector<Articulo>();
					// creo el filtro x ID
					FiltroArticulo.idInterno f1 = new FiltroArticulo.idInterno(Integer.valueOf(textField_1.getText()));
					try {
						vector = db.getArticulos(f1);
					}catch(SQLException e1) {
						e1.getStackTrace();
					}
					fillTable(vector);
				}
			}
		});
		
		JLabel lblCodigoDeBarras = new JLabel("Codigo de barras");
		lblCodigoDeBarras.setBounds(332, 40, 137, 16);
		contentPane.add(lblCodigoDeBarras);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("CODIGO DE BARRAS");
		textField_2.setColumns(10);
		textField_2.setBounds(332, 55, 137, 30);
		contentPane.add(textField_2);
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10 && !textField_2.getText().isEmpty()) {
					// se presiono enter
					Vector<Articulo> vector = new Vector<Articulo>();
					// creo el filtro x nombre
					FiltroArticulo.codigoBarras f1 = new FiltroArticulo.codigoBarras(textField_2.getText());
					try {
						vector = db.getArticulos(f1);
					}catch(SQLException e1) {
						e1.getStackTrace();
					}
					fillTable(vector);
				}
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 98, 835, 308);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Modificar articulo");
		btnNewButton.setBounds(859, 149, 143, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar articulo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormNuevoArticulo nuevoA = new FormNuevoArticulo();
				nuevoA.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(859, 98, 143, 38);
		contentPane.add(btnNewButton_1);		
		
		
		//Modelo de la tabla
		
		model.addColumn("ID");
		model.addColumn("CODIGO DE BARRAS");
		model.addColumn("DESCRIPCION");
		model.addColumn("FAMILIA");
		model.addColumn("PRECIO X U");
		model.addColumn("STOCK");
		
		table_1 = new JTable();
		table_1.setCellSelectionEnabled(true);
		table_1.setModel(model);
		scrollPane.setViewportView(table_1);
	}
	
	public void fillTable(Vector<Articulo> vector) {
		
		// llamado a bd
		DBManager db = new DBManager();
		// llamado a bd
		setTitle("ARTICULOS");		
		// elimino todas las filas de la tabla
		for(int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);
		}
		// cargo todo en la tabla
		if(vector != null) {
			for(int i = 0; i < vector.size(); i++) {
				String[] p = new String[] {((Integer)vector.elementAt(i).getIdInterno()).toString(),
											vector.elementAt(i).getCodigoBarras(), 
											vector.elementAt(i).getDescripcion(), 
											/*db.getFamilia(vector.elementAt(i).getFamilia())*/"a", 
											((Double)vector.elementAt(i).getPrecioUnitario()).toString(),
											((Integer)vector.elementAt(i).getStock()).toString()};
				model.addRow(p);
			}
		}
		table_1.setModel(model);
		scrollPane.setViewportView(table_1);
	}
}