import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class FormNuevoArticulo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCodigoBarras;
	private JTextField textDescripcion;
	private JTextField textPrecioUnitario;
	private JComboBox combotipo;
	DBManager db = new DBManager();
	private JTextField textField;
	
	public void cancel() {
		this.dispose();
	}
	/**
	 * Create the dialog.
	 */
	public FormNuevoArticulo() {
		

		setBounds(100, 100, 291, 327);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCodigoDeBarras = new JLabel("CODIGO DE BARRAS");
			lblCodigoDeBarras.setBounds(8, 6, 253, 16);
			contentPanel.add(lblCodigoDeBarras);
		}
		
		{
			textCodigoBarras = new JTextField();
			textCodigoBarras.setBounds(6, 21, 258, 26);
			contentPanel.add(textCodigoBarras);
			textCodigoBarras.setColumns(10);
		}
		{
			JLabel lblDescripcion = new JLabel("DESCRIPCION");
			lblDescripcion.setBounds(8, 52, 107, 16);
			contentPanel.add(lblDescripcion);
		}
		{
			textDescripcion = new JTextField();
			textDescripcion.setColumns(10);
			textDescripcion.setBounds(6, 70, 258, 26);
			contentPanel.add(textDescripcion);
		}
		{
			JLabel lblPrecioUnitario = new JLabel("PRECIO UNITARIO");
			lblPrecioUnitario.setBounds(8, 99, 107, 16);
			contentPanel.add(lblPrecioUnitario);
		}
		{
			textPrecioUnitario = new JTextField();
			textPrecioUnitario.setColumns(10);
			textPrecioUnitario.setBounds(6, 118, 258, 26);
			contentPanel.add(textPrecioUnitario);
		}
		{
			JLabel lblTipoDeCliente = new JLabel("FAMILIA");
			lblTipoDeCliente.setBounds(8, 146, 107, 16);
			contentPanel.add(lblTipoDeCliente);
		}
		
		combotipo = new JComboBox();
		Vector<FamiliaArticulo> familias = db.getFamilias();
		String[] aux = new String[familias.size()];
		for(int i=0; i<familias.size(); i++) {
			aux[i] =familias.get(i).getNombreFamilia();
		}
		combotipo.setModel(new DefaultComboBoxModel(aux));
		combotipo.setBounds(6, 165, 258, 27);
		
		contentPanel.add(combotipo);
		
		JLabel lblStock = new JLabel("STOCK");
		lblStock.setBounds(8, 195, 107, 16);
		contentPanel.add(lblStock);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(6, 213, 258, 26);
		contentPanel.add(textField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// aniadir el articulo a la bd
						// String codigoBarras;String descripcion;int familia;double precioUnitario;int stock;
						Articulo temp = new Articulo(textCodigoBarras.getText(),textDescripcion.getText(),familias.get(combotipo.getSelectedIndex()).getIdFamilia(),Double.valueOf(textPrecioUnitario.getText()),Integer.valueOf(textField.getText()));
						//System.out.println(temp.getTelefono());
						db.addArticulo(temp);
						JOptionPane.showMessageDialog(null, "Cliente agregado con exito.");
						cancel();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
