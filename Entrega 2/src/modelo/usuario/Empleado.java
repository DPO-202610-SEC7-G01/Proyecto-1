package modelo.usuario;


import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import modelo.Transaccion;
import modelo.Cafe;
import modelo.producto.Juego;
import modelo.producto.Platillo;
import modelo.producto.Producto;

public class Empleado extends Usuario {
	private int puntosFidelidad;
	private List <Calendar> turno;
	private List<Cliente> amigos;
	// La referencia debe estar precisamente directa en el café pero no acá
    private List<Juego> juegosFavoritos;
    private Cafe miCafe;
	
	//Constructor
    public Empleado(int id, String login, String password, String nombre) {
        super(id, login, password, nombre);
        this.puntosFidelidad = 0;
        this.turno = new ArrayList<>();
        this.amigos = new ArrayList<>();
        this.juegosFavoritos = new ArrayList<>();
    }
	
	//Getters y Setters
    public int getPuntosFidelidad() {
		return puntosFidelidad;
	}
	public List<Calendar> getTurno() {
		return turno;
	}
	public List<Cliente> getAmigos() {
		return amigos;
	}
	public List<Juego> getJuegosFavoritos() {
		return juegosFavoritos;
	}

	//Métodos 
	public void sumarPuntosFidelidad(int puntosFidelidad) {
		this.puntosFidelidad += puntosFidelidad;
	}
	
	public Transaccion generarTransaccion(List<Producto> productosComprados, int idNuevaTransaccion) {
	    Calendar hoy = Calendar.getInstance();
	    Transaccion factura = new Transaccion(idNuevaTransaccion, hoy, productosComprados, this, false);    
	    this.sumarPuntosFidelidad(productosComprados.size() ); 
	    return factura; // Existe un método que calcula el monto final c: 
	}
	
	public void agregarAmigos(Cliente cliente) {
		amigos.add(cliente);
	}
	
	public void agregarJuegoFavorito(Juego juegoFav) {
		juegosFavoritos.add(juegoFav);
	}

	
	public void cambioDeTurno(Calendar miFecha, Calendar nuevaFecha) {
	    int indiceEncontrado = -1;

	    for (int i = 0; i < turno.size(); i++) {
	        Calendar fechaActual = turno.get(i);
	        
	        if (fechaActual.get(Calendar.YEAR) == miFecha.get(Calendar.YEAR) &&
	            fechaActual.get(Calendar.DAY_OF_YEAR) == miFecha.get(Calendar.DAY_OF_YEAR)) {
	            indiceEncontrado = i;
	            break;
	        }
	    }

	    if (indiceEncontrado != -1) {
	        turno.set(indiceEncontrado, nuevaFecha);
	    }
	}
	
	public void pedirCambioTurno(Administrador admin, Calendar miFecha, Calendar nuevaFecha, Empleado companero) {
	    admin.procesarCambioTurno(this, companero, miFecha, nuevaFecha);
	}
	
	
	public void sugerencias(Platillo p){ 
		miCafe.agregarSugerencias(p);
	}
	
	

	
}
