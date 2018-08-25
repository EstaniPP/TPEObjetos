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
	
}
