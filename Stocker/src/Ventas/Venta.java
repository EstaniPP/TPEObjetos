package Ventas;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import Articulos.*;
import Articulos.Articulo;
import Cliente.Cliente;
import DataBase.DBManager;

public class Venta {
	static final int IDVENTANUEVA= -1;
	// id del auto increment de la base de datos
	int idVenta;
	// fecha en que se realizo la venta
	Date fechaVenta;
	//Productos que se vendieron
	Vector<Articulo> articulos;
	//Hash con id de producto y cantidad.
	HashMap<Integer, Integer> cantidadArticulos;
	// si es null es q es consumidor final
	Cliente cliente;
	
	public Venta(int idVenta, Date fechaventa, Cliente cliente) {
		this.fechaVenta = fechaventa;
		this.cliente = cliente;
		this.idVenta = idVenta;
	}
	
	public float getPrecioTotal() {
		float total = 0;
		for(int i=0;i<articulos.size();i++) {
			total += articulos.get(i).getPrecioUnitario()*cantidadArticulos.get(articulos.get(i).getIdInterno());
		}
		return total;
	}
	
	public Vector<Articulo> getArticulos() {
		return articulos;
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
	public int getCantidadArticulo(Articulo art) {
		if(cantidadArticulos.containsKey(art.getIdInterno())){
			return cantidadArticulos.get(art.getIdInterno());
		}else {
			return 0;
		}
	}
	
	public double getPrecioAPagar(Promocion promocion) {
		DBManager db = new DBManager();
		if(cliente == null) {
			return this.getPrecioTotal();
		}else {
			return (getPrecioTotal() - promocion.getDescuento(this))*(db.getTipoCliente(cliente.getTipoCliente()).getDescuento() /100);
		}
	}
}
