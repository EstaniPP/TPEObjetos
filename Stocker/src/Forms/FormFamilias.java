package Forms;
import DataBase.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Articulos.Articulo;
import Articulos.FamiliaArticulo;
import Cliente.TipoCliente;
import DataBase.DBManager;
import Filtros.FiltroArticulo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	 * Create the frame.
	 */
	public FormFamilias() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		btnAgregarFamilia.setForeground(new Color(0, 128, 0));
		btnAgregarFamilia.setBounds(279, 42, 143, 38);
		contentPane.add(btnAgregarFamilia);
		btnAgregarFamilia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormNuevaFamilia nuevoA = new FormNuevaFamilia(null,FormFamilias.this);
				nuevoA.setVisible(true);
			}
		});
		
		JButton btnModificarFamilia = new JButton("Modificar familia");
		btnModificarFamilia.setBounds(279, 93, 143, 38);
		contentPane.add(btnModificarFamilia);
		btnModificarFamilia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					FormNuevaFamilia nuevoA = new FormNuevaFamilia((FamiliaArticulo) model.getValueAt(table.getSelectedRow(), 2),FormFamilias.this);
					nuevoA.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una familia.");
				}
			}
		});
		
		
		JButton btnEliminarFamilia = new JButton("Eliminar familia");
		btnEliminarFamilia.setBounds(279, 144, 143, 38);
		contentPane.add(btnEliminarFamilia);
		btnEliminarFamilia.setForeground(Color.RED);
		btnEliminarFamilia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				int selectedRow = table.getSelectedRow();
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una familia.");
				}else {
					FamiliaArticulo eliminado = (FamiliaArticulo) model.getValueAt(table.getSelectedRow(), 2);
					int dialogResult = JOptionPane.showConfirmDialog(null, "Estas a punto de borrar la familia " + eliminado.getNombreFamilia() , "Confirmar borrado", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
						try {
							db.deleteFamilia(eliminado);
							JOptionPane.showMessageDialog(null, "Familia eliminada con exito");
							fillTable(db.getFamilias());
						}catch (NumberFormatException | SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		
		
		//Modelo de la tabla
		
		model.addColumn("ID FAMILIA");
		model.addColumn("NOMBRE FAMILIA");
		model.addColumn("hidden");
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.removeColumn(table.getColumnModel().getColumn(2));
		
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
				Object[] p = {vector.get(i).getIdFamilia(),vector.get(i).getNombreFamilia(),vector.get(i)};
				model.addRow(p);
			}
		}
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
}
