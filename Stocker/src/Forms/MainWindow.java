package Forms;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import DataBase.DBManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JMenu;

public class MainWindow{

	private JFrame frmStocker;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		DBManager db = new DBManager();
		if(db.conexionExitosa()==false) {
			JOptionPane.showMessageDialog(null, "No se ha podido establecer conexion con el servidor");
		}else {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainWindow window = new MainWindow();
						window.frmStocker.setLocationRelativeTo(null);
						window.frmStocker.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
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
				FormArticulos fventas = new FormArticulos();
				fventas.setLocationRelativeTo(null);
				fventas.setVisible(true);
			}
		});
		btnStock.setBounds(128, 154, 117, 60);
		frmStocker.getContentPane().add(btnStock);
		
		JButton button = new JButton("CLIENTES");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormClientes fventas = new FormClientes();
				fventas.setLocationRelativeTo(null);
				fventas.setVisible(true);
			}
		});
		button.setBounds(354, 154, 117, 60);
		frmStocker.getContentPane().add(button);
		
		JButton button_1 = new JButton("VENTAS");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormVentas fventas = new FormVentas();
				fventas.setLocationRelativeTo(null);
				fventas.setVisible(true);
			}
		});
		button_1.setBounds(241, 154, 117, 60);
		frmStocker.getContentPane().add(button_1);
		
		JMenuBar menuBar = new JMenuBar();
		frmStocker.setJMenuBar(menuBar);
		
		JMenu mnStocker = new JMenu("Administrar");
		menuBar.add(mnStocker);
		
		JMenuItem mntmConfiguracion = new JMenuItem("Promociones");
		mntmConfiguracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				(new FormPromociones()).setVisible(true);
			}
		});
		mnStocker.add(mntmConfiguracion);
		 
		JMenuItem mntmFamiliasDeArticulos = new JMenuItem("Familias de articulos");
		mntmFamiliasDeArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				(new FormFamilias()).setVisible(true);
			}
		});
		mnStocker.add(mntmFamiliasDeArticulos);
		
		JMenuItem mntmTiposDeClientes = new JMenuItem("Tipos de clientes");
		mntmTiposDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new FormTipos()).setVisible(true);
			}
		});
		mnStocker.add(mntmTiposDeClientes);
	}
}
