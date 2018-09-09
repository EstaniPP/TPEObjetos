package Forms;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTable;

import javafx.scene.control.TableColumn;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.border.BevelBorder;

import com.sun.javafx.tk.Toolkit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JMenu;

public class FormVentas extends JFrame {


	/**
	 * Create the application.
	 */
	public FormVentas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setTitle("Seccion ventas");
		this.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 600, 300);
		this.getContentPane().setLayout(null);
		
		JLabel lblStocker = new JLabel("SECCION VENTAS");
		lblStocker.setFont(new Font("Verdana", Font.BOLD, 50));
		lblStocker.setBounds(52, 39, 542, 103);
		this.getContentPane().add(lblStocker);
		
		
		JButton btnStock = new JButton("NUEVA VENTA");
		btnStock.setForeground(new Color(0, 128, 0));
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormNuevaVenta nv = new FormNuevaVenta();
				nv.setLocationRelativeTo(null);
				nv.setVisible(true);
			}
		});
		btnStock.setBounds(171, 154, 117, 60);
		this.getContentPane().add(btnStock);
		
		JButton btnVerVentas = new JButton("VER VENTAS");
		btnVerVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					(new FormVerVentas()).setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVerVentas.setBounds(300, 154, 117, 60);
		this.getContentPane().add(btnVerVentas);
		
		
		
	}
}
