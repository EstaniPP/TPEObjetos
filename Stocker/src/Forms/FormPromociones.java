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
import Ventas.Promocion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FormPromociones extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel(); 
	JScrollPane scrollPane = new JScrollPane();
	// llamado a bd
	DBManager db = new DBManager();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FormPromociones() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 484, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministrarPromociones = new JLabel("Administrar promociones");
		lblAdministrarPromociones.setBounds(12, 13, 410, 16);
		contentPane.add(lblAdministrarPromociones);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 31, 443, 12);
		contentPane.add(separator);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 255, 256);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAgregarPromociones = new JButton("AGREGAR PROMOCION");
		btnAgregarPromociones.setForeground(new Color(0, 128, 0));
		btnAgregarPromociones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new FormNuevaPromocion(null, FormPromociones.this)).setVisible(true);
			}
		});
		btnAgregarPromociones.setBounds(279, 42, 177, 38);
		contentPane.add(btnAgregarPromociones);
		
		JButton btnModificarPromociones = new JButton("MODIFICAR PROMOCION");
		btnModificarPromociones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					(new FormNuevaPromocion((Promocion) model.getValueAt(table.getSelectedRow(), 3), FormPromociones.this)).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una promocion.");
				}
			}
		});
		btnModificarPromociones.setBounds(279, 93, 177, 38);
		contentPane.add(btnModificarPromociones);
		
		JButton btnEliminarPromociones = new JButton("ELIMINAR PROMOCION");
		btnEliminarPromociones.setForeground(Color.RED);
		btnEliminarPromociones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una promocion.");
				}else {
					Promocion eliminado = (Promocion) model.getValueAt(table.getSelectedRow(), 3);
					int dialogResult = JOptionPane.showConfirmDialog(null, "Estas a punto de borrar la promocion de " + eliminado.getFamilia().getNombreFamilia() , "Confirmar borrado", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
						try {
							db.deletePromocion(eliminado);
							JOptionPane.showMessageDialog(null, "Promocion eliminada con exito");
							fillTable(db.getPromociones());
						}catch (NumberFormatException | SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnEliminarPromociones.setForeground(Color.RED);
		btnEliminarPromociones.setBounds(279, 144, 177, 38);
		contentPane.add(btnEliminarPromociones);
		
		
		
		//Modelo de la tabla
		
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(model);
		
		scrollPane.setViewportView(table);
		
		model.addColumn("ID PROMO");
		model.addColumn("FAMILIA");
		model.addColumn("DESCUENTO");
		
		model.addColumn("hidden");
		
		table.removeColumn(table.getColumnModel().getColumn(3));
		//Fin modelo de la tabal
		//LLenado inicial de la tabla
		{
			Vector<Promocion> vector = db.getPromociones();
			fillTable(vector);
		}
		//Fin llenado inicial de la tabla
		setTitle("Promociones");
		
	}
	
	public void fillTable(Vector<Promocion> vector) {
		// elimino todas las filas de la tabla
		
		model.setRowCount(0);
		// cargo todo en la tabla
		if(vector != null) {
			for(int i = 0; i < vector.size(); i++) {
				Object[] p = {vector.get(i).getIdPromocion(), vector.get(i).getFamilia().getNombreFamilia(), vector.get(i).getDescuento(), vector.get(i)};
				model.addRow(p);
			}
		}
		table.setModel(model);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane.setViewportView(table);
	}
}