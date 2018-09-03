package Forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Articulos.Articulo;
import Articulos.ArticuloHistorico;
import DataBase.DBManager;
import Ventas.Venta;
import Ventas.VentaHistorica;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class FormVerVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	JScrollPane scrollPane;

	public FormVerVenta(VentaHistorica v) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 531, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		model.addColumn("DESCRIPCION");
		model.addColumn("PRECIO UNITARIO");
		model.addColumn("CANTIDAD");
		model.addColumn("TOTAL");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 114, 493, 283);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblIdVenta = new JLabel("Id venta:");
		lblIdVenta.setBounds(12, 36, 66, 16);
		contentPane.add(lblIdVenta);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(12, 62, 66, 16);
		contentPane.add(lblCliente);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(12, 91, 66, 16);
		contentPane.add(lblFecha);
		
		JLabel lblCostoTotal = new JLabel("Costo total:");
		lblCostoTotal.setBounds(261, 36, 107, 16);
		contentPane.add(lblCostoTotal);
		
		JLabel lblPrecioPagado = new JLabel("Precio pagado:");
		lblPrecioPagado.setBounds(261, 62, 107, 16);
		contentPane.add(lblPrecioPagado);
		
		JLabel lblCantidadArticulos = new JLabel("Cantidad articulos:");
		lblCantidadArticulos.setBounds(261, 91, 107, 16);
		contentPane.add(lblCantidadArticulos);
		
		JLabel lblInformacionDeVenta = new JLabel("Informacion de venta:");
		lblInformacionDeVenta.setBounds(12, 13, 126, 16);
		contentPane.add(lblInformacionDeVenta);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 31, 489, 12);
		contentPane.add(separator);
		
		JLabel lblLabelidventa = new JLabel(v.getIdVenta()+"");
		lblLabelidventa.setBounds(90, 36, 86, 16);
		contentPane.add(lblLabelidventa);
		
		JLabel lblLabelcliente = new JLabel(v.getCliente().getNombre());
		lblLabelcliente.setBounds(90, 62, 86, 16);
		contentPane.add(lblLabelcliente);
		
		JLabel lblLabelfecha = new JLabel(v.getFechaVenta());
		lblLabelfecha.setBounds(90, 91, 86, 16);
		contentPane.add(lblLabelfecha);
		
		JLabel lblLabelcosto = new JLabel(v.getPrecioTotal()+"");
		lblLabelcosto.setBounds(380, 36, 92, 16);
		contentPane.add(lblLabelcosto);
		
		JLabel lblLabelpagado = new JLabel(v.getPrecioAPagar()+"");
		lblLabelpagado.setBounds(380, 62, 92, 16);
		contentPane.add(lblLabelpagado);
		
		Vector<ArticuloHistorico> aux = v.getArticulos();
		int cant=0;
		for(int i=0;i<aux.size();i++) {
			cant+= aux.get(i).getCantidad();
		}
		JLabel lblLabelcantidad = new JLabel(cant+"");
		lblLabelcantidad.setBounds(380, 91, 92, 16);
		contentPane.add(lblLabelcantidad);
		fillTable(aux);
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
	
	
	public void fillTable(Vector<ArticuloHistorico> vector) {
		
		// cargo todo en la tabla
		if(vector != null) {
			for(int i = 0; i < vector.size(); i++) {
				Object[] p = new Object[] {vector.elementAt(i).getDescripcion(),
											vector.elementAt(i).getPrecioUnitario(),
											vector.elementAt(i).getCantidad(),
											vector.elementAt(i).getPrecioUnitario()*vector.elementAt(i).getCantidad()
				};
				model.addRow(p);
			}
		}
		table.setModel(model);
		scrollPane.setViewportView(table);
	}	
}
