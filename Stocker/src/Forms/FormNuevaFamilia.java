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


public class FormNuevaFamilia extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreFamilia;
	/**
	 * Create the frame.
	 */
	public FormNuevaFamilia(FamiliaArticulo fam) {
		DBManager db = new DBManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 316, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreFamilia = new JLabel("NOMBRE FAMILIA");
		lblNombreFamilia.setBounds(14, 13, 107, 16);
		contentPane.add(lblNombreFamilia);
		
		txtNombreFamilia = new JTextField();
		txtNombreFamilia.setColumns(10);
		txtNombreFamilia.setBounds(12, 31, 273, 26);
		contentPane.add(txtNombreFamilia);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 70, 273, 35);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fam == null) {
					FamiliaArticulo temp = new FamiliaArticulo(txtNombreFamilia.getText());
					db.addFamiliaArticulo(temp);
					JOptionPane.showMessageDialog(null, "Articulo agregado con exito.");
					cancel();
				}else {
					FamiliaArticulo updateado = new FamiliaArticulo(txtNombreFamilia.getText());
					JOptionPane.showMessageDialog(null, "Articulo modificado con exito");
					cancel();
				}
			}
		});
		button.setActionCommand("OK");
		panel.add(button);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.setActionCommand("Cancel");
		panel.add(button_1);
	}
	
	public void cancel() {
		this.dispose();
	}
}