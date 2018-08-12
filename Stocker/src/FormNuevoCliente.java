import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class FormNuevoCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormNuevoCliente dialog = new FormNuevoCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormNuevoCliente() {
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
			textField = new JTextField();
			textField.setBounds(6, 21, 258, 26);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblTelefono = new JLabel("TELEFONO");
			lblTelefono.setBounds(8, 52, 107, 16);
			contentPanel.add(lblTelefono);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(6, 70, 258, 26);
			contentPanel.add(textField_1);
		}
		{
			JLabel lblEmail = new JLabel("EMAIL");
			lblEmail.setBounds(8, 99, 107, 16);
			contentPanel.add(lblEmail);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(6, 118, 258, 26);
			contentPanel.add(textField_2);
		}
		{
			JLabel lblTipoDeCliente = new JLabel("TIPO DE CLIENTE");
			lblTipoDeCliente.setBounds(8, 146, 107, 16);
			contentPanel.add(lblTipoDeCliente);
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(6, 165, 258, 27);
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
