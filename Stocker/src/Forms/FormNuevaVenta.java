package Forms;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.events.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextField;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class FormNuevaVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	
	public static void main(String[] args) {
		(new FormNuevaVenta()).setVisible(true);
	}
	
	public FormNuevaVenta() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 797, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNuevaVenta = new JLabel("Nueva venta");
		lblNuevaVenta.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNuevaVenta.setBounds(16, 6, 174, 16);
		contentPane.add(lblNuevaVenta);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(16, 34, 599, 303);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(model);
		
		
		model.addColumn("COD. BARRAS");
		model.addColumn("CANTIDAD");
		model.addColumn("ARTICULO");
		model.addColumn("PRECIO UNIT.");
		model.addColumn("TOTAL");
		
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		table.setModel(model);
		
		//model.addRow(Object[] {"1", "2" ,"3" ,"4", "5"});
		model.addRow(new Object[] {"1", "2" ,"3" ,"4", "5"});
		
		
		
		JTextField tf = new JTextField();
		table.getColumnModel().getColumn(0).setCellEditor(new javax.swing.DefaultCellEditor(tf));
		
		tf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("SORETE");
			}
		});
		
		// agrego tabla al jscrollpanel
		scrollPane.setViewportView(table);
		
		JCheckBox chckbxConsumidorFinal = new JCheckBox("Consumidor final");
		chckbxConsumidorFinal.setSelected(true);
		chckbxConsumidorFinal.setBounds(621, 31, 167, 23);
		contentPane.add(chckbxConsumidorFinal);
		
		JButton btnNewButton = new JButton("CLIENTE");
		btnNewButton.setBounds(627, 86, 150, 56);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("SELECCIONE CLIENTE");
		lblNewLabel.setBounds(630, 66, 150, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnRealizarFactura = new JButton("REALIZAR FACTURA");
		btnRealizarFactura.setForeground(new Color(34, 139, 34));
		btnRealizarFactura.setBounds(627, 281, 150, 56);
		contentPane.add(btnRealizarFactura);
	}
}
