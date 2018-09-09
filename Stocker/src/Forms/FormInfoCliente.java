package Forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cliente.Cliente;
import DataBase.DBManager;
import Filtros.FiltroVenta;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormInfoCliente extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public FormInfoCliente(Cliente c) {
		setTitle("Informacion de cliente");
		DBManager db = new DBManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInformacionDeCliente = new JLabel("Informacion de cliente");
		lblInformacionDeCliente.setBounds(5, 5, 743, 16);
		contentPane.add(lblInformacionDeCliente);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 21, 560, 16);
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
		
		JLabel lblLabelnombre = new JLabel(c.getNombre());
		lblLabelnombre.setBounds(105, 34, 125, 16);
		contentPane.add(lblLabelnombre);
		
		JLabel lblLabeltipo = new JLabel(db.getTipoCliente(c.getTipoCliente()).getNombreTipoCliente());
		lblLabeltipo.setBounds(105, 52, 125, 16);
		contentPane.add(lblLabeltipo);
		
		JLabel lblLabeltelefono = new JLabel(c.getTelefono());
		lblLabeltelefono.setBounds(105, 70, 125, 16);
		contentPane.add(lblLabeltelefono);
		
		JLabel lblLabelemail = new JLabel(c.getEmail());
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
		
		JButton btnVerVenta = new JButton("Ver ventas");
		btnVerVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*FiltroVenta.idClienteVenta filtro = new FiltroVenta.idClienteVenta(c.getIdCliente());
				FormVerVentas nuevoFV = new FormVerVentas(filtro);
				nuevoFV.setVisible(true);*/
			}
		});
		btnVerVenta.setEnabled(true);
		btnVerVenta.setBounds(430, 88, 135, 38);
		contentPane.add(btnVerVenta);
	}
}
