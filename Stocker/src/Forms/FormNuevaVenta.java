package Forms;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.events.KeyEvent;

import Articulos.Articulo;
import DataBase.DBManager;
import Filtros.FiltroArticulo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextField;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JCheckBox;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Color;

public class FormNuevaVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	
	DBManager db = new DBManager();
	
	public static void main(String[] args) {
		(new FormNuevaVenta()).setVisible(true);
	}
	
	public FormNuevaVenta() {
		// hago que algunas columnas no sean editables!
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return column == 0 || column == 1;
			}
		};
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNuevaVenta = new JLabel("Nueva venta");
		lblNuevaVenta.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNuevaVenta.setBounds(16, 6, 174, 16);
		contentPane.add(lblNuevaVenta);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(16, 34, 599, 303);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		
		
		model.addColumn("COD. BARRAS");
		model.addColumn("CANTIDAD");
		model.addColumn("ARTICULO");
		model.addColumn("PRECIO UNIT.");
		model.addColumn("TOTAL");
		
		model.addColumn("hidden");
		
		table.removeColumn(table.getColumnModel().getColumn(5));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		table.setModel(model);
		
		//model.addRow(Object[] {"1", "2" ,"3" ,"4", "5"});
		model.addRow(new Object[] {"443", "" ,"" ,"", "", "HOLA"});
		
		
		// agrego tabla al jscrollpanel
		scrollPane.setViewportView(table);
		
		JCheckBox chckbxConsumidorFinal = new JCheckBox("Consumidor final");
		chckbxConsumidorFinal.setSelected(true);
		chckbxConsumidorFinal.setBounds(621, 31, 167, 23);
		contentPane.add(chckbxConsumidorFinal);
		
		JButton btnNewButton = new JButton("CLIENTE");
		btnNewButton.setBounds(627, 86, 150, 56);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("SELECCIONE CLIENTE");
		lblNewLabel.setBounds(630, 66, 150, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnRealizarFactura = new JButton("REALIZAR FACTURA");
		btnRealizarFactura.setForeground(new Color(34, 139, 34));
		btnRealizarFactura.setBounds(627, 281, 150, 56);
		contentPane.add(btnRealizarFactura);
		
		
		table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		table.getActionMap().put("enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// aspectos a tener en cuenta.
				/*
				 * si esta en col 0 y presiona enter pasamos a unidad
				 * llevar una variable con la columna ultima editada
				 * si esta en columna 1 y presiona enter se crea una row nueva
				 * inicializada en vacio y se setea el focus en la columna 0
				 * con row = variable + 1 y columna 0
				 * 
				 * al presionar enter en cod de barras o columna 0
				 * se hace un fetching de bd con el codigo de articulo
				 * y se escribe en la tabla
				 * si no hay codigo de barras (valido) por mas de que se presione enter en
				 * cantidad no va a pasar nada
				 * 
				 * se tiene q poder ir al formulario de productos y seleccionar un producto de ahi
				 * */
				// paro la edicion in order to get the updated barcode from cell
				if(table.isEditing()) {
					table.getCellEditor().stopCellEditing();
				}
				//caso codigo de barras
				if(table.getSelectedColumn() == 0) {
					
					// get producto de BD
					String barcode = (String)table.getValueAt(table.getSelectedRow(), 0);
					FiltroArticulo.codigoBarras fArt = new FiltroArticulo.codigoBarras(barcode);
					Articulo art = Articulo.getArticuloError();
					try {
						art = db.getArticulos(fArt).elementAt(0);
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					
					
					// HACER EQUALS DE ARTICULOS 
					if(art.getIdInterno() == -1) {
						JOptionPane.showMessageDialog(null, "EL PRODUCTO NO EXISTE");
					}else {
						// el producto existe
						model.setValueAt(art.getDescripcion(), table.getSelectedRow(), 2);
					}
					table.changeSelection(0, 1, false, false);
					table.editCellAt(0, 1);
					
				}
				//System.out.println(table.getSelectedColumn());
				
			}				
		});
		// seteo el editable en 0,1
		
		table.changeSelection(0, 0, false, false);
		table.editCellAt(0, 0);
		//table.addRowSelectionInterval(0, 0);
		//table.addColumnSelectionInterval(0, 0);
		//System.out.println(table.getSelectedColumn());
	}
		
		
}
