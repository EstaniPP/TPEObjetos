package Ventas;

import java.util.HashMap;
import java.util.Vector;

import Articulos.Articulo;
import Articulos.FamiliaArticulo;

public class Promocion {
	
	
	HashMap<Integer,Integer> promos;
	
	public Promocion() {
		promos = new HashMap<Integer,Integer>();
	}
	
	public void setPromo(FamiliaArticulo familia, Integer descuento) {
		if(promos.containsKey(familia)){
			promos.put(familia.getIdFamilia(),descuento+promos.get(familia));
		}else {
			promos.put(familia.getIdFamilia(),descuento);
		}
	}
	
	public double getDescuento(Venta venta) {
		double descuento = 0;
		Vector<Articulo> vect = venta.getArticulos();
		for(int i =0;i<vect.size();i++) {
			if(promos.containsKey(vect.get(i).getFamilia())){
				descuento += promos.get(vect.get(i).getFamilia())*venta.getCantidadArticulo(vect.get(i));
			}
		}
		return descuento;
	}
}
