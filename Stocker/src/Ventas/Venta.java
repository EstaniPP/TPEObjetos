package Ventas;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import Articulos.*;
import Articulos.Articulo;
import Cliente.Cliente;
import DataBase.DBManager;

public class Venta extends ElementoVenta{
	// id del auto increment de la base de datos
	int idVenta;
	

	public Venta() {
		super();
	}
	public Venta(String fechaventa, Cliente cliente) {
		this();
		this.fechaVenta = fechaventa;
		this.cliente = cliente;
	}
	// OJO
	public void setIdVenta(int id) {
		idVenta = id;
	}
	public int getIdVenta() {
		return idVenta;
	}
	public void setCliente(Cliente c) {
		cliente = c;
	}
	
	public Double getPrecioTotal() {
		Double total = 0.0;
		for(int i=0;i<articulos.size();i++) {
			total += articulos.get(i).getPrecioUnitario()*cantidadArticulos.get(articulos.get(i).getIdInterno());
		}
		return total;
	}
	
	public Vector<Articulo> getArticulos() {
		return articulos;
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
