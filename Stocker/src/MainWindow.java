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

public class MainWindow {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//headers for the table
        String[] columns = new String[] {
            "Id", "Name", "Hourly Rate", "Part Time"
        };
         
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {1, "John", 40.0, false },
            {2, "Rambo", 70.0, false },
            {3, "Zorro", 60.0, true },
        };
        //create table with data
		JTable table = new JTable(data, columns);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = table.getSelectedRow();
				System.out.println(table.getValueAt(row, 0));
				
			}
		});
		table.setShowGrid(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		
		
		
		table.setBounds(6, 42, 419, 200);
		frame.getContentPane().add(table);
		
		TableColumn tblclmnUser = new TableColumn();
		tblclmnUser.setResizable(false);
		tblclmnUser.setResizable(true);
		//tblclmnUser.setWidth(100);
		tblclmnUser.setText("Name");
		
	}
}
