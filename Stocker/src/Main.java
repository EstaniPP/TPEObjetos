import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import Articulos.Articulo;
import DataBase.DBManager;
import Filtros.FiltroArticulo;
import Filtros.FiltroCompuesto;

public class Main {
	public static void main(String[] args) throws AWTException {
		Robot robot = new Robot();
		while(true) {
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			robot.keyPress(KeyEvent.VK_C);
			System.out.println("Impreso");
		}
	}
}
