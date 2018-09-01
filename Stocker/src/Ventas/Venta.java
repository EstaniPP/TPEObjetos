package Ventas;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import Articulos.*;
import Cliente.Cliente;
import DataBase.DBManager;

public class Venta extends ElementoVenta{
	
	// fecha en que se realizo la venta
		String fechaVenta;
		//Productos que se vendieron
		Vector<ArticuloHistorico> articulos;
		//Hash con id de producto y cantidad.
		HashMap<Integer, Integer> cantidadArticulos;
		// si es null es q es consumidor final
		
	public Venta() {
		super();
	}
	public Venta(String fechaventa, Cliente cliente) {
		super(fechaventa, cliente);
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
	
	public VentaHistorica getHistorica() {
		VentaHistorica v = new VentaHistorica(this.getFechaVenta(), this.getCliente());
		for(Articulo a: this.getArticulos()) {
			
		}
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
	
	public Vector<ArticuloHistorico> getArticulos() {
		return articulos;
	}
	
	
}
