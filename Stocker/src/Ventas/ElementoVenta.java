package Ventas;

import java.util.HashMap;
import java.util.Vector;

import Articulos.Articulo;
import Cliente.Cliente;

public abstract class ElementoVenta {
	// idVenta tipoVenta total pagado idClienteVenta fechaVenta 
	// fecha en que se realizo la venta
	String fechaVenta;
	//Productos que se vendieron
	Vector<Articulo> articulos;
	//Hash con id de producto y cantidad.
	HashMap<Integer, Integer> cantidadArticulos;
	// si es null es q es consumidor final
	Cliente cliente;
	
	public ElementoVenta() {
		cantidadArticulos = new HashMap<Integer, Integer>();
		articulos = new Vector<Articulo>();
	}
	
	public void setCliente(Cliente c) {
		cliente = c;
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
	// abstractos
	public abstract Double getPrecioTotal();
	public abstract double getPrecioAPagar(Vector<Promocion> promocion);
}
