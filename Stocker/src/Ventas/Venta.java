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
	public Venta() {
		super();
	}
	public Venta(String fechaventa, Cliente cliente) {
		this();
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
	
	
}
