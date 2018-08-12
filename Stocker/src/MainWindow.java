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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class MainWindow {

	private JFrame frmStocker;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmStocker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStocker = new JFrame();
		frmStocker.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		frmStocker.setTitle("STOCKER");
		frmStocker.setResizable(false);
		frmStocker.setBounds(100, 100, 600, 300);
		frmStocker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStocker.getContentPane().setLayout(null);
		
		JLabel lblStocker = new JLabel("STOCKER");
		lblStocker.setFont(new Font("Verdana", Font.BOLD, 50));
		lblStocker.setBounds(166, 39, 267, 103);
		frmStocker.getContentPane().add(lblStocker);
		
		JButton btnStock = new JButton("STOCK");
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new FormArticulos()).setVisible(true);
			}
		});
		btnStock.setBounds(128, 154, 117, 60);
		frmStocker.getContentPane().add(btnStock);
		
		JButton button = new JButton("CLIENTES");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new FormClientes()).setVisible(true);
			}
		});
		button.setBounds(354, 154, 117, 60);
		frmStocker.getContentPane().add(button);
		
		JButton button_1 = new JButton("VENTAS");
		button_1.setBounds(241, 154, 117, 60);
		frmStocker.getContentPane().add(button_1);
		
		JMenuBar menuBar = new JMenuBar();
		frmStocker.setJMenuBar(menuBar);
		
		JMenu mnStocker = new JMenu("Stocker");
		menuBar.add(mnStocker);
		
		JMenuItem mntmConfiguracion = new JMenuItem("Configuracion");
		mnStocker.add(mntmConfiguracion);
		
		
		
	}
}
