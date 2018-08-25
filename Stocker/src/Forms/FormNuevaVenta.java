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

import Articulos.ArticuloVenta;
import Cliente.Cliente;
import DataBase.DBManager;
import Filtros.FiltroArticulo;
import Ventas.Venta;

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
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class FormNuevaVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	ArticuloVenta articulo;
	DBManager db = new DBManager();
	private JTextField txtcod;
	private JTextField txtcant;
	JLabel clientelbl;
	Cliente clienteVenta = Cliente.getClienteError();
	private JTextField total;
	
	public static void main(String[] args) {
		FormNuevaVenta fnv = new FormNuevaVenta();
		fnv.setVisible(true);
	}
	
	public void setCliente(Cliente c) {
		clienteVenta = c;
		clientelbl.setText(c.getNombre());
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
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 797, 423);
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
		
		scrollPane.setBounds(16, 86, 599, 242);
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
		
		
		
		clientelbl = new JLabel("SELECCIONE CLIENTE");
		clientelbl.setBounds(630, 66, 150, 16);
		contentPane.add(clientelbl);
		
		JButton btnRealizarFactura = new JButton("REALIZAR FACTURA");
		btnRealizarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// realizar venta
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate date = java.time.LocalDate.now();
				String fecha = date.format(formatters);
				// guardo en una venta
				Venta v = new Venta(fecha, clienteVenta);
				// recorro toda la tabla
				for(int i = 0; i < table.getModel().getRowCount(); i++) {
					ArticuloVenta aT = (ArticuloVenta) table.getModel().getValueAt(i, 5);
					int cant = Integer.valueOf((String)table.getModel().getValueAt(i, 1));
					v.agregarArticulo(aT, cant);
				}
				v.setCliente(FormNuevaVenta.this.clienteVenta);
				try {
					System.out.println(db.addVenta(v).getIdVenta());
				}catch(SQLException e123) {
					e123.printStackTrace();
				}
				
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
					// actualizo el total
					FormNuevaVenta.this.calcularTotal(false);
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
					articulo = ArticuloVenta.getArticuloError();
					Vector<ArticuloVenta> vect = new Vector<ArticuloVenta>();
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
		
		
		table.getModel().addTableModelListener(new TableModelListener() {
		      public void tableChanged(TableModelEvent e) {
		    	  if(e.getColumn() == 1) {
		    		  table.setValueAt(Double.valueOf(String.valueOf(table.getValueAt(e.getLastRow(), 1))) * Double.valueOf(String.valueOf(table.getValueAt(e.getLastRow(), 3))), e.getLastRow(), 4);
		    		  FormNuevaVenta.this.calcularTotal(false);
		    	  }
		      }
		});
	
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
					FormNuevaVenta.this.calcularTotal(false);
					txtcod.requestFocus();
				}
				
			}
		});
		btnEliminarProdSelec.setForeground(Color.RED);
		btnEliminarProdSelec.setBounds(627, 223, 150, 56);
		contentPane.add(btnEliminarProdSelec);
		
		// calcular total
		
		
		
		total = new JTextField();
		total.setEnabled(false);
		total.setColumns(10);
		total.setBounds(513, 335, 103, 26);
		contentPane.add(total);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(460, 340, 54, 16);
		contentPane.add(lblTotal);
	}
	
	@Override
	public void setVisible(boolean value) {
	    super.setVisible(value);
	    txtcod.requestFocusInWindow();
	}
	
	private double calcularTotal(boolean param) {
		double calculo = 0;
		for(int i = 0; i < table.getModel().getRowCount(); i++) {
			calculo += (Double)table.getModel().getValueAt(i, 4);
		}
		if(!param) {
			DecimalFormat df = new DecimalFormat("#.##");
			total.setText(String.valueOf(df.format(calculo)));
		}
		return calculo;
	}
}
