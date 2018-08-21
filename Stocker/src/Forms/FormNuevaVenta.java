package Forms;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.events.KeyEvent;
import com.sun.glass.events.WindowEvent;
import com.sun.xml.internal.ws.api.Component;

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
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class FormNuevaVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	Articulo articulo;
	DBManager db = new DBManager();
	private JTextField txtcod;
	private JTextField txtcant;
	
	public static void main(String[] args) {
		FormNuevaVenta fnv = new FormNuevaVenta();
		fnv.setVisible(true);
	}
	
	public void agregarEstilo(DefaultTableModel model) {
		model.addColumn("COD. BARRAS");
		model.addColumn("CANTIDAD");
		model.addColumn("ARTICULO");
		model.addColumn("PRECIO UNIT.");
		model.addColumn("TOTAL");
		
		model.addColumn("hidden");
		
		table.removeColumn(table.getColumnModel().getColumn(5));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
	}
	public FormNuevaVenta() {
		// hago que algunas columnas no sean editables!
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return column == 1;
			}
		};
		
		JLabel lblNuevaVenta = new JLabel("Nueva venta");
		lblNuevaVenta.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNuevaVenta.setBounds(16, 6, 174, 16);
		contentPane.add(lblNuevaVenta);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(16, 86, 599, 251);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		
		
		agregarEstilo(model);
		
		table.setModel(model);
		
		
		
		// agrego tabla al jscrollpanel
		scrollPane.setViewportView(table);
		
		JButton btncliente = new JButton("CLIENTE");
		btncliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormClientes fc = new FormClientes(FormNuevaVenta.this);
				fc.setVisible(true);
			}
		});
		btncliente.setEnabled(false);
		btncliente.setBounds(627, 86, 150, 56);
		contentPane.add(btncliente);
		
		JCheckBox chckbxConsumidorFinal = new JCheckBox("Consumidor final");
		chckbxConsumidorFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxConsumidorFinal.isSelected()) {
					btncliente.setEnabled(false);
				}else {
					btncliente.setEnabled(true);
				}
			}
		});
		
		chckbxConsumidorFinal.setSelected(true);
		chckbxConsumidorFinal.setBounds(621, 31, 167, 23);
		contentPane.add(chckbxConsumidorFinal);
		
		
		
		JLabel lblNewLabel = new JLabel("SELECCIONE CLIENTE");
		lblNewLabel.setBounds(630, 66, 150, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnRealizarFactura = new JButton("REALIZAR FACTURA");
		btnRealizarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRealizarFactura.setForeground(new Color(34, 139, 34));
		btnRealizarFactura.setBounds(627, 281, 150, 56);
		contentPane.add(btnRealizarFactura);
		
		JLabel label = new JLabel("COD. BARRAS");
		label.setBounds(19, 34, 85, 16);
		contentPane.add(label);

		txtcant = new JTextField();
		txtcant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == 10) {
					FormNuevaVenta.this.model.addRow(new Object[] {
							articulo.getCodigoBarras(),
							txtcant.getText(),
							articulo.getDescripcion(),
							articulo.getPrecioUnitario(),
							Double.valueOf(txtcant.getText()) * articulo.getPrecioUnitario(),
							articulo
					});
					
					txtcod.requestFocus();
					txtcod.setText("");
					txtcant.setText("");
				}
			}
		});
		txtcant.setColumns(10);
		txtcant.setBounds(122, 47, 103, 26);
		contentPane.add(txtcant);
		
		
		
		
		txtcod = new JTextField();
		
		txtcod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// chequeo si existe
					articulo = Articulo.getArticuloError();
					Vector<Articulo> vect = new Vector<Articulo>();
					try {
						vect = db.getArticulos(new FiltroArticulo.codigoBarras(txtcod.getText()));
					}catch(SQLException esql1) {
						esql1.printStackTrace();
					}
					if(vect.size() == 0) {
						JOptionPane.showMessageDialog(null, "EL PRODUCTO NO EXISTE");
					}else {
						articulo = vect.elementAt(0);
						txtcant.requestFocus();
					}
				}
			}
		});
		
		txtcod.setColumns(10);
		txtcod.setBounds(16, 47, 103, 26);
		contentPane.add(txtcod);
		
	
		JLabel label_1 = new JLabel("CANTIDAD");
		label_1.setBounds(124, 34, 85, 16);
		contentPane.add(label_1);
		
		JButton btnEliminarProdSelec = new JButton("QUITAR PRODUCTO");
		btnEliminarProdSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();
				if(selected == -1) {
					JOptionPane.showMessageDialog(null, "SELECCIONE UN PRODUCTO PARA QUITAR");
				}else{
					FormNuevaVenta.this.model.removeRow(selected);
					txtcod.requestFocus();
				}
				
			}
		});
		btnEliminarProdSelec.setForeground(Color.RED);
		btnEliminarProdSelec.setBounds(627, 223, 150, 56);
		contentPane.add(btnEliminarProdSelec);
	}
	
	@Override
	public void setVisible(boolean value) {
	    super.setVisible(value);
	    txtcod.requestFocusInWindow();
	}
}
