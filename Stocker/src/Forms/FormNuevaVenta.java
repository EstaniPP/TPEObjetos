package Forms;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import Articulos.Articulo;
import Cliente.Cliente;
import DataBase.DBManager;
import Filtros.FiltroArticulo;
import Ventas.Venta;
import Ventas.VentaHistorica;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JSeparator;


public class FormNuevaVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	Articulo articulo;
	DBManager db = new DBManager();
	private JTextField txtcod;
	private JTextField txtcant;
	JLabel clientelbl;
	Cliente clienteVenta = Cliente.getConsumidorFinal();
	private JTextField total;
	
	public static void main(String[] args) {
		//FormNuevaVenta fnv = new FormNuevaVenta();
		//fnv.setVisible(true);
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
		setTitle("Venta nueva");
		setLocationRelativeTo(null);
		// hago que algunas columnas no sean editables!
		
		setBounds(100, 100, 804, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);		
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return column == 1;
			}
		};
		
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
		
		
		
		clientelbl = new JLabel("Seleccione cliente");
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
					Articulo aT = (Articulo) table.getModel().getValueAt(i, 5);
					int cant = Integer.valueOf((String)table.getModel().getValueAt(i, 1));
					v.agregarArticulo(aT, cant);
				}
				
				v.setCliente(FormNuevaVenta.this.clienteVenta);
				VentaHistorica venta = null;
				
				try {
					venta = db.addVenta(v);
				}catch(SQLException e123) {
					e123.printStackTrace();
				}
				(new FormVerVenta(venta)).setVisible(true);
				FormNuevaVenta.this.dispose();
				//System.out.println(venta.getIdVenta());
			}
		});
		btnRealizarFactura.setForeground(new Color(34, 139, 34));
		btnRealizarFactura.setBounds(627, 281, 150, 56);
		contentPane.add(btnRealizarFactura);
		
		JLabel lblCodigoDeBarras = new JLabel("Codigo de barras");
		lblCodigoDeBarras.setBounds(19, 32, 100, 16);
		contentPane.add(lblCodigoDeBarras);

		txtcant = new JTextField();
		txtcant.setEnabled(false);
		txtcant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == 10 && isDouble(txtcant.getText())) {
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
					txtcant.setEnabled(false);
				}
			}
		});
		txtcant.setColumns(10);
		txtcant.setBounds(122, 47, 103, 26);
		contentPane.add(txtcant);

		table.getModel().addTableModelListener(new TableModelListener() {
		      public void tableChanged(TableModelEvent e) {
		    	  if(e.getColumn() == 1) {
		    		  table.setValueAt(Double.valueOf(String.valueOf(table.getValueAt(e.getLastRow(), 1))) * Double.valueOf(String.valueOf(table.getValueAt(e.getLastRow(), 3))), e.getLastRow(), 4);
		    		  FormNuevaVenta.this.calcularTotal(false);
		    	  }
		      }
		});
	
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(124, 32, 85, 16);
		contentPane.add(lblCantidad);
		
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
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(460, 340, 54, 16);
		contentPane.add(lblTotal);
		
		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isDouble(txtcant.getText())) {
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
					txtcant.setEnabled(false);
					btnAgregar.setEnabled(false);
				}
			}
		});
		btnAgregar.setBounds(237, 48, 97, 25);
		contentPane.add(btnAgregar);
		btnAgregar.setEnabled(false);
		
		
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
						txtcant.setEnabled(true);
						btnAgregar.setEnabled(true);
						txtcant.requestFocus();
					}
				}
			}
		});
		
		txtcod.setColumns(10);
		txtcod.setBounds(16, 47, 103, 26);
		contentPane.add(txtcod);
		
		JLabel lblCrearVenta = new JLabel("Crear venta");
		lblCrearVenta.setBounds(16, 1, 761, 16);
		contentPane.add(lblCrearVenta);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(16, 19, 761, 12);
		contentPane.add(separator);
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
	
	private static boolean isDouble(String s) {
	      boolean isValidInteger = false;
	      try
	      {
	         Double.parseDouble(s);
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	      }
	 
	      return isValidInteger;
	 }
}
