package Ventas;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import Articulos.*;
import Cliente.Cliente;
import DataBase.DBManager;

public class Venta{
	
	// fecha en que se realizo la venta
	String fechaVenta;
	//Productos que se vendieron
	Vector<Articulo> articulos;
	//Hash con id de producto y cantidad.
	HashMap<Integer, Integer> cantidadArticulos;
	// si es null es q es consumidor final
	Cliente cliente;
	
	public Venta(String fechaventa, Cliente cliente) {
		this.fechaVenta = fechaventa;
		this.cliente = cliente;
	}
	// OJO
	
	public Double getPrecioTotal() {
		Double total = 0.0;
		for(int i=0;i<articulos.size();i++) {
			total += articulos.get(i).getPrecioUnitario()*cantidadArticulos.get(articulos.get(i).getIdInterno());
		}
		return total;
	}
	
	public double getPrecioAPagar(Vector<Promocion> promocion) {
		DBManager db = new DBManager();
		Double precio = this.getPrecioTotal();
		System.out.println(precio);
		for(int i=0;i<promocion.size();i++) {
			precio -= promocion.get(i).getDescuento(this);
		}
		if(cliente.getIdCliente() != -1) {
			precio = (precio*(100-(db.getTipoCliente(cliente.getTipoCliente()).getDescuento())))/100.0;
		}
		return precio;
	}

	public boolean agregarArticulo(Articulo art, Integer cant) {
		if(cantidadArticulos.containsKey(art.getIdInterno())) {
			cantidadArticulos.put(art.getIdInterno(), cant+cantidadArticulos.get(art.getIdInterno()));
		}else {
			articulos.add(art);
			cantidadArticulos.put(art.getIdInterno(), cant);
		}
		return true;
	}
	
	public Vector<Articulo> getArticulos() {
		return articulos;
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
	
	public Cliente getCliente() {
		return cliente;
	}
	public String getFechaVenta() {
		return fechaVenta;
	}
	
	public Vector<Articulo> getArticulosVenta(){
		return articulos;
	}
}
