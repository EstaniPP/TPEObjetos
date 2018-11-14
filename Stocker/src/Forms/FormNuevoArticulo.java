package Forms;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Articulos.Articulo;
import Articulos.FamiliaArticulo;
import DataBase.DBManager;
import Filtros.FiltroArticulo;

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
	public FormNuevoArticulo(Articulo art, FormArticulos fa) {
		setTitle("Agregar articulo");
		setBounds(100, 100, 291, 327);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		{
			JLabel lblCodigoDeBarras = new JLabel("Codigo de barras");
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
			JLabel lblDescripcion = new JLabel("Descripcion");
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
			JLabel lblPrecioUnitario = new JLabel("Precio unitario");
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
			JLabel lblTipoDeCliente = new JLabel("Familia");
			lblTipoDeCliente.setBounds(8, 146, 107, 16);
			contentPanel.add(lblTipoDeCliente);
		}
		
		combotipo = new JComboBox();
		Vector<FamiliaArticulo> familias = db.getFamilias();
		String[] aux = new String[familias.size()+1];
		FamiliaArticulo ninguna = new FamiliaArticulo(-1,"Ninguna");
		aux[0] = ninguna.getNombreFamilia();
		for(int i=0; i<familias.size(); i++) {
			aux[i+1] =familias.get(i).getNombreFamilia();
		}
		
		combotipo.setModel(new DefaultComboBoxModel(aux));
		combotipo.setBounds(6, 165, 258, 27);
		
		contentPanel.add(combotipo);
		
		JLabel lblStock = new JLabel("Cantidad");
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
						if(!textCodigoBarras.getText().isEmpty() &&  !textDescripcion.getText().isEmpty() && !textPrecioUnitario.getText().isEmpty()) {
							if(!textField.getText().isEmpty() && isDouble(textPrecioUnitario.getText())){
									if (isDouble(textField.getText())) {
										int idfam = -1;
										if(combotipo.getSelectedIndex()!=0){
											idfam = familias.get(combotipo.getSelectedIndex()-1).getIdFamilia();
										}
										Vector<Articulo> codigoUnico = new Vector<Articulo>();
										try {
											FiltroArticulo.codigoBarras f = new FiltroArticulo.codigoBarras(textCodigoBarras.getText());
											codigoUnico = db.getArticulos(f);
										}catch(SQLException e2) {}
										
										
											if(art == null) {
												if(codigoUnico.size()==0) {
													Articulo temp = new Articulo(textCodigoBarras.getText(),textDescripcion.getText(),idfam,Double.valueOf(textPrecioUnitario.getText()),Integer.valueOf(textField.getText()));
													db.addArticulo(temp);
													JOptionPane.showMessageDialog(null, "Articulo agregado con exito.");
													cancel();
												}else {
													JOptionPane.showMessageDialog(null, "El codigo de barras ya existe.");
												}
											}else {
												Articulo updateado = new Articulo(art.getIdInterno(),textCodigoBarras.getText(),textDescripcion.getText(),idfam,Double.valueOf(textPrecioUnitario.getText()),Integer.valueOf(textField.getText()));
												art.update(updateado); 						
												db.updateArticulo(updateado);
												JOptionPane.showMessageDialog(null, "Articulo modificado con exito");
												cancel();
											}
											try {
												fa.fillTable(db.getArticulos(null));
											}catch(SQLException e2){
												e2.printStackTrace();
											}
									}else {
										JOptionPane.showMessageDialog(null, "El stock debe ser un numero valido.");
									}
							}else {
								JOptionPane.showMessageDialog(null, "El precio unitario debe ser un numero valido.");	
							}
						}else {
							JOptionPane.showMessageDialog(null, "Debe completar todos los campos.");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		if(art != null) {
			textCodigoBarras.setText(art.getCodigoBarras());
			textDescripcion.setText(art.getDescripcion());
			textPrecioUnitario.setText(String.valueOf(art.getPrecioUnitario()));
			if(art.getFamilia()==-1) {
				combotipo.setSelectedIndex(0);
			}else {
				int i=0;
				while(i<aux.length-1 && familias.get(i).getIdFamilia() != art.getFamilia()) {
					i++;
				}
				combotipo.setSelectedIndex(i+1);
			}
			textField.setText(String.valueOf(art.getStock()));		
		}
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
