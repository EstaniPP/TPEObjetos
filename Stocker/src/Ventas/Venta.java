package Ventas;
import java.util.HashMap;
import java.util.Vector;

import Articulos.*;
import Cliente.Cliente;
import DataBase.DBManager;

public class Venta{
	
	int idVenta;
	// fecha en que se realizo la venta
	String fechaVenta;
	//Productos que se vendieron
	Vector<Articulo> articulos;
	//Hash con id de producto y cantidad.
	HashMap<Integer, Integer> cantidadArticulos;
	// si es null es q es consumidor final
	Cliente cliente;
	
	
	public Venta() {
		cantidadArticulos = new HashMap<Integer, Integer>();
		articulos = new Vector<Articulo>();
	}
	public Venta(String fechaventa, Cliente cliente) {
		this();
		this.fechaVenta = fechaventa;
		this.cliente = cliente;
	}
	// OJO
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
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
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getFechaVenta() {
		return fechaVenta;
	}
	
	public Vector<Articulo> getArticulosVenta(){
		return articulos;
	}
	
	public VentaHistorica getHistorica(Vector<Promocion> promociones) {
		Vector<ArticuloHistorico> historicos = new Vector<ArticuloHistorico>();
		for(Articulo a: articulos) {
			//public ArticuloHistorico(String descripcion, double precioUnitario, int cantidad) {
			historicos.add(new ArticuloHistorico(a.getDescripcion(), a.getPrecioUnitario(), cantidadArticulos.get(a.getIdInterno())));
		}
		return new VentaHistorica(this.idVenta, this.getPrecioTotal(), this.getPrecioAPagar(promociones), this.getFechaVenta(), historicos, this.cliente);
	}
}
