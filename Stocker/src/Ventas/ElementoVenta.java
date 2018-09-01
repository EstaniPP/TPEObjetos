package Ventas;

import java.util.HashMap;
import java.util.Vector;

import Articulos.Articulo;
import Articulos.ArticuloHistorico;
import Cliente.Cliente;

// TODOS LOS METODOS Q TENGAN Q VER CON ARTICULO LOS TENGO Q PONER ABSTRACTOS
// VENTA NO TIENE FECHA


public abstract class ElementoVenta {
	// idVenta tipoVenta total pagado idClienteVenta fechaVenta 
	
	Cliente cliente;
	
	public ElementoVenta() {
		
	}
	
	public ElementoVenta(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setCliente(Cliente c) {
		cliente = c;
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
	
	public Vector<ArticuloHistorico> getArticulosVenta(){
		return articulos;
	}
	
	// abstractos
	public abstract Double getPrecioTotal();
	public abstract double getPrecioAPagar(Vector<Promocion> promocion);
}
