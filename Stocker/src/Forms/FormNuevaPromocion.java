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
import Ventas.Promocion;

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


public class FormNuevaPromocion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public FormNuevaPromocion(Promocion promo, FormPromociones promos) {
		setTitle("Agregar promocion");
		DBManager db = new DBManager();
		setBounds(100, 100, 333, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		JComboBox combotipo = new JComboBox();
		combotipo.setBounds(14, 31, 133, 26);
		contentPane.add(combotipo);
		Vector<FamiliaArticulo> familias = db.getFamilias();
		String[] aux = new String[familias.size()];
		for(int i=0; i<familias.size(); i++) {
			aux[i] =familias.get(i).getNombreFamilia();
		}
		combotipo.setModel(new DefaultComboBoxModel(aux));
		
		JLabel lblNombreFamilia = new JLabel("Nombre familia");
		lblNombreFamilia.setBounds(14, 13, 148, 16);
		contentPane.add(lblNombreFamilia);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 73, 286, 35);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().isEmpty()) {
					if(isDouble(textField.getText())){
						Vector<FamiliaArticulo> familias = db.getFamilias();
						if(promo == null) {
							Promocion temp = new Promocion(familias.get(combotipo.getSelectedIndex()), Double.valueOf(textField.getText()));
							db.addPromocion(temp);
							JOptionPane.showMessageDialog(null, "Promocion agregada con exito.");
							cancel();
						}else {
							Promocion updateado = new Promocion(promo.getIdPromocion(),familias.get(combotipo.getSelectedIndex()), Double.valueOf(textField.getText()));
							db.updatePromocion(updateado);
							JOptionPane.showMessageDialog(null, "Promocion modificada con exito");
							cancel();
						}
						promos.fillTable(db.getPromociones());
					}else {
						JOptionPane.showMessageDialog(null, "El descuento debe ser un numero.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos.");
				}
			}
		});
		button.setActionCommand("OK");
		panel.add(button);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancelar.setActionCommand("Cancel");
		panel.add(btnCancelar);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setBounds(162, 13, 148, 16);
		contentPane.add(lblDescuento);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(159, 31, 141, 26);
		contentPane.add(textField);
		
		if(promo != null) {
			int i=0;
			while(i<aux.length && familias.get(i).getIdFamilia() != promo.getFamilia().getIdFamilia()) {
				i++;
			}
				combotipo.setSelectedIndex(i);
			textField.setText(String.valueOf(promo.getDescuento()));
		}
	}
	
	public void cancel() {
		this.dispose();
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

