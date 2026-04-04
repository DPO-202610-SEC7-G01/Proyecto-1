package usuario;

import java.util.ArrayList;
import java.util.List;

import producto.Bebida;
import producto.Platillo;

public class Cocinero extends empleado{
	private List<Platillo> platillosConocidos;
	private List<Bebida> bebidasConocidas;
	public Cocinero(int id, String login, String password, String nombre) {
		super(id, login, password, nombre);
		this.bebidasConocidas = new ArrayList<Bebida>();
		this.platillosConocidos = new ArrayList<Platillo>();
		
	}
	public List<Platillo> getPlatillosConocidos() {
		return platillosConocidos;
	}
	public List<Bebida> getBebidasConocidas() {
		return bebidasConocidas;
	}
	public void aprenderPLatillo(Platillo platillo) {
		this.platillosConocidos.add(platillo);
	}
	public void aprenderBebida(Bebida bebida) {
		this.bebidasConocidas.add(bebida);
	}
	

}
