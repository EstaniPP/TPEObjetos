package Forms;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FormNuevaVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;

	
	public FormNuevaVenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNuevaVenta = new JLabel("Nueva venta");
		lblNuevaVenta.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNuevaVenta.setBounds(16, 6, 174, 16);
		contentPane.add(lblNuevaVenta);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 36, 518, 200);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
	}
}
