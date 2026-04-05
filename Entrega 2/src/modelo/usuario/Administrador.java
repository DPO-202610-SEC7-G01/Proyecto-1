package modelo.usuario;

import java.util.Calendar;

import modelo.Cafe;

public class Administrador extends Usuario {
	private Cafe miCafe;
	
	//Constructor
	public Administrador(int id, String login, String password, String nombre, Cafe cafe) {
		super(id, login, password, nombre);
		this.miCafe = cafe;
	}
	
	//Métodos
	public void procesarCambioTurno(Empleado solicitante, Empleado companero, Calendar fechaS, Calendar fechaC) {
	    
	    // 1. Validaciones de existencia y cargo (Igual que antes)
	    if (!solicitante.getTurno().contains(fechaS) || !companero.getTurno().contains(fechaC)) {
	        return;
	    }

	    if (!solicitante.getClass().equals(companero.getClass())) {
	        return;
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
	    } else {
	        this.miCafe.getTurnoEmpleados().put(fechaS, originalS);
	        this.miCafe.getTurnoEmpleados().put(fechaC, originalC);
	    }
	}

	
}
