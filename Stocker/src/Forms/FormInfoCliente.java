package Forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cliente.Cliente;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class FormInfoCliente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Create the frame.
	 */
	public FormInfoCliente(Cliente c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInformacionDeCliente = new JLabel("Informacion de cliente");
		lblInformacionDeCliente.setBounds(5, 5, 743, 16);
		contentPane.add(lblInformacionDeCliente);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 21, 743, 16);
		contentPane.add(separator);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(15, 34, 78, 16);
		contentPane.add(lblNombre);
		
		JLabel lblTipoCliente = new JLabel("Tipo cliente:");
		lblTipoCliente.setBounds(15, 52, 78, 16);
		contentPane.add(lblTipoCliente);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(15, 70, 78, 16);
		contentPane.add(lblTelefono);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(15, 88, 56, 16);
		contentPane.add(lblEmail);
		
		JLabel lblLabelnombre = new JLabel("label_nombre");
		lblLabelnombre.setBounds(105, 34, 125, 16);
		contentPane.add(lblLabelnombre);
		
		JLabel lblLabeltipo = new JLabel("label_tipo");
		lblLabeltipo.setBounds(105, 52, 125, 16);
		contentPane.add(lblLabeltipo);
		
		JLabel lblLabeltelefono = new JLabel("label_telefono");
		lblLabeltelefono.setBounds(105, 70, 125, 16);
		contentPane.add(lblLabeltelefono);
		
		JLabel lblLabelemail = new JLabel("label_email");
		lblLabelemail.setBounds(105, 88, 125, 16);
		contentPane.add(lblLabelemail);
		
		JLabel lblTotalGastado = new JLabel("Total gastado:");
		lblTotalGastado.setBounds(286, 34, 135, 16);
		contentPane.add(lblTotalGastado);
		
		JLabel lblCantidadDeCompras = new JLabel("Cantidad de compras:");
		lblCantidadDeCompras.setBounds(286, 52, 135, 16);
		contentPane.add(lblCantidadDeCompras);
		
		JLabel lblNewLabel = new JLabel("label_totalgastado");
		lblNewLabel.setBounds(433, 34, 135, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblLabelcantcompras = new JLabel("label_cantcompras");
		lblLabelcantcompras.setBounds(433, 52, 135, 16);
		contentPane.add(lblLabelcantcompras);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(5, 173, 347, 16);
		contentPane.add(separator_1);
		
		JLabel lblCompras = new JLabel("Compras");
		lblCompras.setBounds(5, 154, 56, 16);
		contentPane.add(lblCompras);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(394, 173, 347, 16);
		contentPane.add(separator_2);
		
		JLabel lblProductosCompra = new JLabel("Productos compra");
		lblProductosCompra.setBounds(394, 154, 125, 16);
		contentPane.add(lblProductosCompra);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 183, 347, 242);
		contentPane.add(panel);
		
		table = new JTable();
		panel.add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(394, 183, 347, 242);
		contentPane.add(panel_1);
		
		table_1 = new JTable();
		panel_1.add(table_1);
	}
}
