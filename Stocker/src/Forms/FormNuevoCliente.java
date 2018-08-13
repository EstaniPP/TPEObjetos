package Forms;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cliente.Cliente;
import Cliente.TipoCliente;
import DataBase.DBManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class FormNuevoCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textnombre;
	private JTextField texttelefono;
	private JTextField textemail;
	private JComboBox combotipo;
	DBManager db = new DBManager();
	Hashtable<String, Integer> tiposClientes = new Hashtable<String, Integer>();
	public void cancel() {
		this.dispose();
	}
	/**
	 * Create the dialog.
	 */
	public FormNuevoCliente(Cliente c) {
		

		setBounds(100, 100, 291, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("NOMBRE");
			lblNombre.setBounds(8, 6, 61, 16);
			contentPanel.add(lblNombre);
		}
		
		{
			textnombre = new JTextField();
			textnombre.setBounds(6, 21, 258, 26);
			contentPanel.add(textnombre);
			textnombre.setColumns(10);
		}
		{
			JLabel lblTelefono = new JLabel("TELEFONO");
			lblTelefono.setBounds(8, 52, 107, 16);
			contentPanel.add(lblTelefono);
		}
		{
			texttelefono = new JTextField();
			texttelefono.setColumns(10);
			texttelefono.setBounds(6, 70, 258, 26);
			contentPanel.add(texttelefono);
		}
		{
			JLabel lblEmail = new JLabel("EMAIL");
			lblEmail.setBounds(8, 99, 107, 16);
			contentPanel.add(lblEmail);
		}
		{
			textemail = new JTextField();
			textemail.setColumns(10);
			textemail.setBounds(6, 118, 258, 26);
			contentPanel.add(textemail);
		}
		{
			JLabel lblTipoDeCliente = new JLabel("TIPO DE CLIENTE");
			lblTipoDeCliente.setBounds(8, 146, 107, 16);
			contentPanel.add(lblTipoDeCliente);
		}
		
		
		
		combotipo = new JComboBox();
		Vector<TipoCliente> tipos = db.getTiposCliente();
		String[] aux = new String[tipos.size()];
		
		for(int i=0; i<tipos.size(); i++) {
			aux[i] = tipos.get(i).getNombreTipoCliente();
			tiposClientes.put(tipos.get(i).getNombreTipoCliente(), tipos.get(i).getIdTipoCliente());
		}
		
		combotipo.setModel(new DefaultComboBoxModel(aux));
		combotipo.setBounds(6, 165, 258, 27);
		
		contentPanel.add(combotipo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// chequeo q no sea edicion
						int tipoClienteCombo = tiposClientes.get(combotipo.getItemAt(combotipo.getSelectedIndex()));
						if(c == null) {
							// aniadir el cliente a la bd
							//String nombre, String telefono, String email, int tipoCliente
							Cliente temp = new Cliente(textnombre.getText(), texttelefono.getText(), textemail.getText(), tipoClienteCombo);
							//System.out.println(temp.getTelefono());
							db.addCliente(temp);
							JOptionPane.showMessageDialog(null, "Cliente agregado con exito.");
							cancel();
						}else {
							//Cliente(int idCliente, String nombre, String telefono, String email, int tipoCliente) 
							Cliente updateado = new Cliente(c.getIdCliente(), textnombre.getText(), texttelefono.getText(), textemail.getText(), tipoClienteCombo);
							db.updateCliente(updateado);
							JOptionPane.showMessageDialog(null, "Cliente modificado con exito");
							cancel();
						}
						
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
		if(c != null) {
			textnombre.setText(c.getNombre());
			texttelefono.setText(c.getTelefono());
			textemail.setText(c.getEmail());
			//combotipo.setSelectedIndex(c.getTipoCliente() - 1);
			//combotipo.get
			for(int i = 0; i < combotipo.getItemCount(); i++) {
				if(c.getTipoCliente() == tiposClientes.get(combotipo.getItemAt(i))) {
					combotipo.setSelectedIndex(i);
				}
			}
		}
	}
}
