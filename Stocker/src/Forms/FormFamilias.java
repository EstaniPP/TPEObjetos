package Forms;
import DataBase.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Articulos.Articulo;
import Articulos.FamiliaArticulo;
import DataBase.DBManager;
import Filtros.FiltroArticulo;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

public class FormFamilias extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel(); 
	JScrollPane scrollPane = new JScrollPane();
	// llamado a bd
	DBManager db = new DBManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFamilias frame = new FormFamilias();
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
	public FormFamilias() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministrarFamilias = new JLabel("Administrar familias");
		lblAdministrarFamilias.setBounds(12, 13, 410, 16);
		contentPane.add(lblAdministrarFamilias);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 31, 410, 12);
		contentPane.add(separator);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 255, 256);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAgregarFamilia = new JButton("Agregar familia");
		btnAgregarFamilia.setBounds(279, 42, 143, 38);
		contentPane.add(btnAgregarFamilia);
		
		JButton btnModificarFamilia = new JButton("Modificar familia");
		btnModificarFamilia.setBounds(279, 93, 143, 38);
		contentPane.add(btnModificarFamilia);
		
		JButton btnEliminarFamilia = new JButton("Eliminar familia");
		btnEliminarFamilia.setBounds(279, 144, 143, 38);
		contentPane.add(btnEliminarFamilia);
		
		
		
		//Modelo de la tabla
		
		model.addColumn("ID FAMILIA");
		model.addColumn("NOMBRE FAMILIA");
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		//Fin modelo de la tabal
		//LLenado inicial de la tabla
		{
			Vector<FamiliaArticulo> vector = db.getFamilias();
			fillTable(vector);
		}
		//Fin llenado inicial de la tabla
		
		
	}
	
	public void fillTable(Vector<FamiliaArticulo> vector) {

		// llamado a bd
		setTitle("FAMILIA ARTICULOS");		
		// elimino todas las filas de la tabla
		int j = model.getRowCount(); 
		for(int i = 0; i < j; i++) {
			model.removeRow(0);
		}
		// cargo todo en la tabla
		if(vector != null) {
			for(int i = 0; i < vector.size(); i++) {
				String[] p = {String.valueOf(vector.get(i).getIdFamilia()),vector.get(i).getNombreFamilia()};
				model.addRow(p);
			}
		}
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
}
