package Ventas;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import Articulos.*;
import Articulos.Articulo;

public abstract class Venta {
	static final int IDVENTANUEVA= -1;
	// id del auto increment de la base de datos
	int idVenta;
	// tipo de venta que se realizo (con o sin cliente)
	int tipoVenta;
	// fecha en que se realizo la venta
	Date fechaVenta;
	//Productos que se vendieron
	ArrayList<Articulo> articulos;
	//Hash con id de producto y cantidad.
	HashMap<Integer, Integer> cantidadArticulos;
	//id del cliente que realizo la compra (si es '0' es una compra sin cliente)
	int idClienteVenta;
	
	public Venta(int idVenta, int tipoVenta, Date fechaventa, int idClienteVenta) {
		this.tipoVenta = tipoVenta;
		this.fechaVenta = fechaventa;
		this.idClienteVenta = idClienteVenta;
		this.idVenta = idVenta;
	}
	
	public abstract float getPrecioAPagar();
	
	public float getPrecioTotal() {
		float total = 0;
		for(int i=0;i<articulos.size();i++) {
			total += articulos.get(i).getPrecioUnitario()*cantidadArticulos.get(articulos.get(i).getIdInterno());
		}
		return total;
	}
	
	public boolean agregarArticulo(Articulo art, Integer cant) {
		if(articulos.contains(art)) {
			cantidadArticulos.put(art.getIdInterno(), cant+cantidadArticulos.get(art.getIdInterno()));
		}else {
			articulos.add(art);
			cantidadArticulos.put(art.getIdInterno(), cant);
		}
		return true;
	}
	
	public boolean borrarArticulo(Articulo art) {
		if(articulos.contains(art)) {
			articulos.remove(art);
			cantidadArticulos.remove(art.getIdInterno());
			return true;
		}else {
			return false;
		}
		
	}
	
}
