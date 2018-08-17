package Ventas;

import java.util.HashMap;
import java.util.Vector;

import Articulos.Articulo;
import Articulos.FamiliaArticulo;

public class Promocion {
	
	
	FamiliaArticulo familia;
	Double descuento;
	int idPromocion;
	
	public Promocion(FamiliaArticulo familia, Double descuento) {
		this.familia = familia;
		this.descuento = descuento;
	}
	
	public Promocion(int idPromocion, FamiliaArticulo familia, Double descuento) {
		this.familia = familia;
		this.descuento = descuento;
		this.idPromocion = idPromocion;
	}
	
	public FamiliaArticulo getFamilia() {
		return familia;
	}
	
	public int getIdPromocion() {
		return idPromocion;
	}
	
	public double getDescuento(){
		return descuento;
	}
	public double getDescuento(Venta venta) {
		double desc = 0;
		Vector<Articulo> vect = venta.getArticulos();
		for(int i =0;i<vect.size();i++) {
			if(familia.getIdFamilia() == vect.get(i).getFamilia()){
				desc += descuento*venta.getCantidadArticulo(vect.get(i));
			}
		}
		return desc;
	}
}
