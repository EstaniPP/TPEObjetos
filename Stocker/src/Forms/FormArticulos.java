package Forms;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Articulos.ArticuloVenta;
import Cliente.Cliente;
import DataBase.DBManager;
import Filtros.FiltroArticulo;
import Filtros.FiltroCliente;
import Filtros.FiltroArticulo.codigoBarras;
import Filtros.FiltroArticulo.descripcion;
import Filtros.FiltroArticulo.idInterno;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		setBounds(100, 100, 1032, 448);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscarArticulos = new JLabel("Administrar articulos");
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
				if(e.getKeyCode() == 10) {
					// se presiono enter
					Vector<ArticuloVenta> vector = new Vector<ArticuloVenta>();
					// creo el filtro x nombre
					FiltroArticulo.descripcion f1;
					if(!textField.getText().isEmpty()) {
						f1 = new FiltroArticulo.descripcion(textField.getText());
					}else {
						f1 = null;
					}
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
				if(e.getKeyCode() == 10) {
					// se presiono enter
					Vector<ArticuloVenta> vector = new Vector<ArticuloVenta>();
					// creo el filtro x ID
					FiltroArticulo.idInterno f1;
					if(!textField_1.getText().isEmpty()) {
						f1 = new FiltroArticulo.idInterno(Integer.valueOf(textField_1.getText()));
					}else {
						f1 = null;
					}
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
				if(e.getKeyCode() == 10) {
					Vector<ArticuloVenta> vector = new Vector<ArticuloVenta>();
					// creo el filtro x nombre
					FiltroArticulo.codigoBarras f1;
					if(!textField_2.getText().isEmpty()) {
						System.out.println(textField_2.getText());
						f1 = new FiltroArticulo.codigoBarras(textField_2.getText());
						System.out.println(f1.getStatement());
					}else {
						f1 = null;
					}
					try {
						vector = db.getArticulos(f1);
					}catch(SQLException e1) {
						e1.getStackTrace();
					}
					System.out.println(vector.size());
					fillTable(vector);
				}
			}
		});
				
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 98, 835, 293);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Modificar articulo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// modificar seleccionado
				int selectedRow = table_1.getSelectedRow();
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un articulo.");
				}else {
					// obtengo el id del articulo seleccionado
					int idInterno = (Integer)table_1.getValueAt(selectedRow, 0);
					// obtengo articulo desde bd
					Vector<ArticuloVenta> vect = new Vector<ArticuloVenta>();
					try {
						vect = db.getArticulos(new FiltroArticulo.idInterno(idInterno));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(vect.size() == 0) {
						JOptionPane.showMessageDialog(null, "Se produjo un error");
					}else {
						FormNuevoArticulo nuevoC = new FormNuevoArticulo((ArticuloVenta)table_1.getModel().getValueAt(table_1.getSelectedRow(), 6),FormArticulos.this);
						nuevoC.setVisible(true);
					}
				}
			}
		});
		
		
		
		
		btnNewButton.setBounds(859, 149, 143, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar articulo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormNuevoArticulo nuevoA = new FormNuevoArticulo(null,FormArticulos.this);
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
		model.addColumn("hidden");
		
		table_1 = new JTable();
		table_1.setCellSelectionEnabled(true);
		table_1.setModel(model);
		scrollPane.setViewportView(table_1);
		table_1.removeColumn(table_1.getColumnModel().getColumn(6));
		
		//Fin modelo de tabla
		
		JButton btnEliminarArticulo = new JButton("Eliminar articulo");
		btnEliminarArticulo.setForeground(Color.RED);
		btnEliminarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// eliminar seleccionado
				int selectedRow = table_1.getSelectedRow();
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un articulo.");
				}else {
					ArticuloVenta aux = (ArticuloVenta)table_1.getModel().getValueAt(table_1.getSelectedRow(), 6);
					int dialogResult = JOptionPane.showConfirmDialog(null, "Estas a punto de borrar a " + aux.getDescripcion(), "Confirmar borrado", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
						try {
								db.deleteArticulo(aux);
								JOptionPane.showConfirmDialog(null, "Articulo eliminado", "OK", JOptionPane.DEFAULT_OPTION);
								((DefaultTableModel) table_1.getModel()).removeRow(table_1.getSelectedRow());
						}catch(SQLException e2){
								e2.printStackTrace();
						}
					}
				}
			}
		});
		btnEliminarArticulo.setBounds(859, 200, 143, 38);
		contentPane.add(btnEliminarArticulo);
		
		{
			Vector<ArticuloVenta> vector = new Vector<ArticuloVenta>();
			FiltroArticulo.descripcion f1 = null;
			try {
				vector = db.getArticulos(f1);
			}catch(SQLException e1) {
				e1.getStackTrace();
			}
			fillTable(vector);
		}
	}
	
	public void fillTable(Vector<ArticuloVenta> vector) {
		
		// llamado a bd
		DBManager db = new DBManager();
		// llamado a bd
		setTitle("ARTICULOS");		
		// elimino todas las filas de la tabla
		int j = model.getRowCount(); 
		for(int i = 0; i < j; i++) {
			model.removeRow(0);
		}
		// cargo todo en la tabla
		if(vector != null) {
			for(int i = 0; i < vector.size(); i++) {
				Object[] p = new Object[] {vector.elementAt(i).getIdInterno(),
											vector.elementAt(i).getCodigoBarras(), 
											vector.elementAt(i).getDescripcion(), 
											db.getFamiliaArticulo(vector.elementAt(i).getFamilia()).getNombreFamilia(), 
											vector.elementAt(i).getPrecioUnitario(),
											vector.elementAt(i).getStock(),vector.elementAt(i)};
				model.addRow(p);
			}
		}
		table_1.setModel(model);
		scrollPane.setViewportView(table_1);
	}
}