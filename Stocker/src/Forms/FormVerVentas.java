package Forms;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Cliente.Cliente;
import DataBase.DBManager;
import Filtros.FiltroVenta;
import Ventas.VentaHistorica;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class FormVerVentas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	JLabel lblNewLabel;
	JScrollPane scrollPane = new JScrollPane();
	DBManager db = new DBManager();
	Cliente cliente = null;

	/**
	 * Launch the application.
	 */
	
	
	public void setCliente(Cliente c) {
		cliente = c;
		lblNewLabel.setText(c.getNombre());
		try {
			fillTable(db.getVentas(new FiltroVenta.idClienteVenta(c.getIdCliente())));
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FormVerVentas() throws SQLException {
		setTitle("Ver ventas");
		model = new DefaultTableModel() {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 79, 475, 241);
		contentPane.add(scrollPane);
		
		scrollPane.setColumnHeaderView(table);
		
		model.addColumn("CLIENTE");
		model.addColumn("FECHA");
		model.addColumn("TOTAL");
		model.addColumn("PAGADO");
		
		model.addColumn("hidden");
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		fillTable(db.getVentas(null));
		
		table.removeColumn(table.getColumnModel().getColumn(4));
		
		JButton btnVerVenta = new JButton("VER VENTA");
		btnVerVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new FormVerVenta((VentaHistorica) model.getValueAt(table.getSelectedRow(), 4))).setVisible(true);
			}
		});
		btnVerVenta.setBounds(504, 79, 160, 45);
		contentPane.add(btnVerVenta);
		
		JButton btnVerVentasDe = new JButton("SELECCIONAR CLIENTE");
		btnVerVentasDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormClientes fC = new FormClientes(FormVerVentas.this);
				fC.setVisible(true);
			}
		});
		btnVerVentasDe.setBounds(17, 41, 171, 29);
		contentPane.add(btnVerVentasDe);
		
		lblNewLabel = new JLabel("Sin cliente seleccionado");
		lblNewLabel.setBounds(190, 46, 160, 16);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(17, 29, 644, 12);
		contentPane.add(separator);
		
		JLabel lblVentasRealizadas = new JLabel("Ventas realizadas");
		lblVentasRealizadas.setBounds(17, 11, 644, 16);
		contentPane.add(lblVentasRealizadas);
	}
	
	public void fillTable(Vector<VentaHistorica> vector) {
		model.setRowCount(0);
		if(vector != null) {
			for(VentaHistorica v : vector) {
				Object[] p = new Object[] {
						v.getCliente().getNombre(), 
						v.getFechaVenta(), 
						v.getPrecioTotal(), 
						v.getPrecioAPagar(), 
						v};
				model.addRow(p);
			}
		}
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
	}
}
