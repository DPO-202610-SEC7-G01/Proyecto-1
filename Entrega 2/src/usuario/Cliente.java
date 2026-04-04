package usuario;

import java.util.ArrayList;
import java.util.List;

import producto.Juego;

public class Cliente extends Usuario {
	private int puntosFidelidad;
	private int edad;
	private List<Juego> juegosFavoritos;
	public Cliente(int id, String login, String password, String nombre, int edad) {
		super(id, login, password, nombre);
		// TODO Auto-generated constructor stub
		this.edad = edad;
		this.juegosFavoritos = new ArrayList<Juego>();
		this.puntosFidelidad = 0;
	}
	public void agregarJuegoFavorito(Juego juego) {
		this.juegosFavoritos.add(juego);
	}
	public void sumarPuntos() {
		this.puntosFidelidad += 1;
	}
	public int getPuntosFidelidad() {
		return puntosFidelidad;
	}
	public int getEdad() {
		return edad;
	}
	public List<Juego> getJuegosFavoritos() {
		return juegosFavoritos;
	}
	
}
