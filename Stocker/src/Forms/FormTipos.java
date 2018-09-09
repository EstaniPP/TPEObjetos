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
import Cliente.TipoCliente;
import DataBase.DBManager;
import Filtros.FiltroArticulo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FormTipos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel(); 
	JScrollPane scrollPane = new JScrollPane();
	// llamado a bd
	DBManager db = new DBManager();

	public FormTipos() {
		setBounds(100, 100, 443, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		JLabel lblAdministrarFamilias = new JLabel("Administrar tipos de cliente");
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
		
		JButton btnAgregarFamilia = new JButton("AGREGAR TIPO");
		btnAgregarFamilia.setForeground(new Color(0, 128, 0));
		btnAgregarFamilia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new FormNuevoTipo(null, FormTipos.this)).setVisible(true);
			}
		});
		btnAgregarFamilia.setBounds(279, 42, 143, 38);
		contentPane.add(btnAgregarFamilia);
		
		JButton btnModificarFamilia = new JButton("MODIFICAR TIPO");
		btnModificarFamilia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					//(new FormNuevoTipo(db.getTipoCliente(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0))), FormTipos.this)).setVisible(true);
					(new FormNuevoTipo((TipoCliente) model.getValueAt(table.getSelectedRow(), 3), FormTipos.this)).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo.");
				}
			}
		});
		btnModificarFamilia.setBounds(279, 93, 143, 38);
		contentPane.add(btnModificarFamilia);
		
		JButton btnEliminarFamilia = new JButton("ELIMINAR TIPO");
		btnEliminarFamilia.setForeground(Color.RED);
		btnEliminarFamilia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de cliente.");
				}else {
					TipoCliente eliminado = new TipoCliente(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)), 0.0, "");
					int dialogResult = JOptionPane.showConfirmDialog(null, "Estas a punto de borrar el tipo " + eliminado.getNombreTipoCliente() , "Confirmar borrado", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
						try {
							db.deleteTipoCliente(eliminado);
							JOptionPane.showMessageDialog(null, "Tipo eliminado con exito");
							fillTable(db.getTiposCliente());
						}catch (NumberFormatException | SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnEliminarFamilia.setForeground(Color.RED);
		btnEliminarFamilia.setBounds(279, 144, 143, 38);
		contentPane.add(btnEliminarFamilia);
		
		
		
		//Modelo de la tabla
		
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(model);
		
		scrollPane.setViewportView(table);
		
		model.addColumn("ID TIPO");
		model.addColumn("NOMBRE");
		model.addColumn("DESCUENTO");
		
		model.addColumn("hidden");
		
		table.removeColumn(table.getColumnModel().getColumn(3));
		//Fin modelo de la tabal
		//LLenado inicial de la tabla
		{
			Vector<TipoCliente> vector = db.getTiposCliente();
			fillTable(vector);
		}
		//Fin llenado inicial de la tabla
		setTitle("Tipos de clientes");
		
	}
	
	public void fillTable(Vector<TipoCliente> vector) {
		// elimino todas las filas de la tabla
		
		model.setRowCount(0);
		// cargo todo en la tabla
		if(vector != null) {
			for(int i = 0; i < vector.size(); i++) {
				Object[] p = {String.valueOf(vector.elementAt(i).getIdTipoCliente()) , String.valueOf(vector.elementAt(i).getNombreTipoCliente()), String.valueOf(vector.elementAt(i).getDescuento()), vector.elementAt(i) };
				model.addRow(p);
			}
		}
		table.setModel(model);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane.setViewportView(table);
	}
}
