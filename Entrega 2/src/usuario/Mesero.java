package usuario;

import java.util.ArrayList;
import java.util.List;
import producto.JuegoDificil;
import producto.Platillo;
import producto.Bebida;
import producto.Juego;
import cafe.Reserva;


public class Mesero extends Empleado{
	private List<JuegoDificil> juegosConocidos;
	
	//Constructor
	public Mesero(int id, String login, String password, String nombre) {
		super(id, login, password, nombre);
		this.juegosConocidos= new ArrayList<>();
	}
	
	//Métodos
	public void aprenderJuegoDificil(JuegoDificil juego) {
		juegosConocidos.add(juego);
	}
	
	public boolean autorizarPrestamo(Reserva r, Juego juego) {
	    // 1. Validar número de jugadores
	    if (r.getNumPersonas() < juego.getNumJugadores() || r.getNumPersonas() > juego.getNumJugadores()) {
	        return false;
	    }

	    // 2. Validar restricción de edad
	    if (juego.getRestriccionEdad().equals("apto adultos") && r.tieneMenoresDeEdad()) {
	        return false;
	    }

	    // 3. Validar bebidas calientes vs Juego de Acción
	    if (juego.getCategoria().equals("Acción") && r.tieneBebidasCalientes()) {
	        return false;
	    }

	    r.agregarAlPrestamo(juego);
	    return true;
	}
	
	public void servirPlatillos(Reserva r, Platillo p) {
	    boolean aptoParaTodos = true;

	    // 1. Validación de alérgenos
	    for (Cliente c : r.getClientes()) {
	        for (String ingrediente : p.getAlergeneos().split(",")) {
	            if (c.getAlergenos().contains(ingrediente.trim())) {
	                aptoParaTodos = false;
	                break; 
	            }
	        }
	        if (!aptoParaTodos) break;
	    }

	    // 2. Si es apto, se agrega a la lista de transacciones (que recibe Productos)
	    if (aptoParaTodos) {
	        r.addTransaccion(p); 
	    } 
	}
	
	public void servirBebidas(Reserva r, Bebida b) {
	    // REGLA 1: Menores de edad y Alcohol
	    if (r.tieneMenoresDeEdad() && b.isTieneAlcohol()) {
	        return;
	    }

	    // REGLA 2: Juegos de Acción y Bebidas Calientes
	    boolean tieneJuegoAccion = false;
	    for (Juego j : r.getJuegosPrestados()) {
	        // Comparamos la categoría del juego
	        if (j.getCategoria().equalsIgnoreCase("Acción")) {
	            tieneJuegoAccion = true;
	            break;
	        }
	    }

	    if (tieneJuegoAccion && b.getTemperatura().equalsIgnoreCase("Caliente")) {
	        return;
	    }

	    // 3. Si pasa las reglas, se agrega como Producto
	    r.addTransaccion(b);
	}

}
