package modelo.usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import modelo.Cafe;
import modelo.producto.Juego;

public class Administrador extends Usuario {
	private Cafe miCafe;
	
	//Constructor
	public Administrador(int id, String login, String password, String nombre, Cafe cafe) {
		super(id, login, password, nombre);
		this.miCafe = cafe;
	}
	
	//Métodos
	public void moverJuego(Juego juego) {
	    ArrayList<Juego> listaVenta = miCafe.getJuegosVenta();
	    ArrayList<Juego> listaPrestamo = miCafe.getJuegosPrestamo();
	    if (listaVenta.contains(juego)) {
	        
	        listaVenta.remove(juego);
	        listaPrestamo.add(juego);
	        juego.setPrestado(false); 
	        
	        System.out.println("El juego '" + juego.getNombre() + "' ha sido movido de Venta a Préstamo.");
	    } else {
	        System.out.println("Error: El juego no se encuentra en la lista de ventas.");
	    }
	}
	
	public boolean gestionarPrestamo(Usuario usuario, Juego juego, Calendar fecha) {
	    HashMap<Calendar, HashMap<Usuario, Juego>> historial = miCafe.getHistorialUsoJuegos();

	    if (historial.containsKey(fecha)) {
	        HashMap<Usuario, Juego> prestamosDelDia = historial.get(fecha);
	        
	        if (prestamosDelDia.containsValue(juego)) {
	            return false; 
	        }
	    }

	    juego.setPrestado(true);
	    historial.putIfAbsent(fecha, new HashMap<>());
	    historial.get(fecha).put(usuario, juego);

	    return true;
	}



  

	public boolean procesarCambioTurno(Empleado solicitante, Empleado companero, Calendar fechaS, Calendar fechaC) {
	    
	    // 1. Validaciones de existencia y cargo (Igual que antes)
	    if (!solicitante.getTurno().contains(fechaS) || !companero.getTurno().contains(fechaC)) {
	        return false;
	    }

	    if (!solicitante.getClass().equals(companero.getClass())) {
	        return false;
	    }

	    // 2. SIMULACIÓN: Guardamos quiénes están actualmente para poder revertir si falla
	    Empleado originalS = this.miCafe.getTurnoEmpleados().get(fechaS);
	    Empleado originalC = this.miCafe.getTurnoEmpleados().get(fechaC);

	    this.miCafe.getTurnoEmpleados().put(fechaS, companero);
	    this.miCafe.getTurnoEmpleados().put(fechaC, solicitante);

	    // 3. VERIFICACIÓN: ¿El café sigue siendo apto para abrir en ambas fechas?
	    boolean esAptoS = this.miCafe.aptoApertura(fechaS);
	    boolean esAptoC = this.miCafe.aptoApertura(fechaC);

	    if (esAptoS && esAptoC) {
	        solicitante.cambioDeTurno(fechaS, fechaC);
	        companero.cambioDeTurno(fechaC, fechaS);
	        return true;
	    } else {
	        this.miCafe.getTurnoEmpleados().put(fechaS, originalS);
	        this.miCafe.getTurnoEmpleados().put(fechaC, originalC);
	        return false;
	    }
	}

}	

