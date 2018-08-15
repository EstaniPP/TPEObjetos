package Forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Articulos.Articulo;
import Articulos.FamiliaArticulo;
import Cliente.TipoCliente;
import DataBase.DBManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;


public class FormNuevoTipo extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreFamilia;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public FormNuevoTipo(TipoCliente tc, FormTipos tipos) {
		DBManager db = new DBManager();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 309, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreFamilia = new JLabel("NOMBRE FAMILIA");
		lblNombreFamilia.setBounds(14, 13, 148, 16);
		contentPane.add(lblNombreFamilia);
		
		txtNombreFamilia = new JTextField();
		txtNombreFamilia.setColumns(10);
		txtNombreFamilia.setBounds(12, 31, 141, 26);
		contentPane.add(txtNombreFamilia);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 73, 286, 35);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tc == null) {
					TipoCliente temp = new TipoCliente(Double.valueOf(textField.getText()), txtNombreFamilia.getText());
					db.addTipoCliente(temp);
					JOptionPane.showMessageDialog(null, "Tipo de cliente agregado con exito.");
					cancel();
				}else {
					TipoCliente updateado = new TipoCliente(tc.getIdTipoCliente(), Double.valueOf(textField.getText()), txtNombreFamilia.getText());
					db.updateTipoCliente(updateado);
					JOptionPane.showMessageDialog(null, "Tipo de cliente modificado con exito");
					cancel();
				}
				tipos.fillTable(db.getTiposCliente());
			}
		});
		button.setActionCommand("OK");
		panel.add(button);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		button_1.setActionCommand("Cancel");
		panel.add(button_1);
		
		JLabel lblDescuento = new JLabel("DESCUENTO");
		lblDescuento.setBounds(162, 13, 148, 16);
		contentPane.add(lblDescuento);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(159, 31, 141, 26);
		contentPane.add(textField);
		
		if(tc != null) {
			txtNombreFamilia.setText(tc.getNombreTipoCliente());
			textField.setText(String.valueOf(tc.getDescuento()));
		}
	}
	
	public void cancel() {
		this.dispose();
	}
}
