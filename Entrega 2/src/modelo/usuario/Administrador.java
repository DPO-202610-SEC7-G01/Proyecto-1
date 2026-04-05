package usuario;

import java.util.Calendar;

import cafe.Cafe;

public class Administrador extends Usuario {
	private Cafe cafe;
	public Administrador(int id, String login, String password, String nombre, Cafe cafe) {
		super(id, login, password, nombre);
		this.cafe = cafe;
	}
	
	public void procesarCambioTurno(Empleado solicitante, Empleado companero, Calendar fechaS, Calendar fechaC) {
	    
	    // 1. Validaciones de existencia y cargo (Igual que antes)
	    if (!solicitante.getTurno().contains(fechaS) || !companero.getTurno().contains(fechaC)) {
	        return;
	    }

	    if (!solicitante.getClass().equals(companero.getClass())) {
	        return;
	    }

	    // 2. SIMULACIÓN: Guardamos quiénes están actualmente para poder revertir si falla
	    Empleado originalS = this.cafe.getTurnoEmpleados().get(fechaS);
	    Empleado originalC = this.cafe.getTurnoEmpleados().get(fechaC);

	    this.cafe.getTurnoEmpleados().put(fechaS, companero);
	    this.cafe.getTurnoEmpleados().put(fechaC, solicitante);

	    // 3. VERIFICACIÓN: ¿El café sigue siendo apto para abrir en ambas fechas?
	    boolean esAptoS = this.cafe.aptoApertura(fechaS);
	    boolean esAptoC = this.cafe.aptoApertura(fechaC);

	    if (esAptoS && esAptoC) {
	        solicitante.cambioDeTurno(fechaS, fechaC);
	        companero.cambioDeTurno(fechaC, fechaS);
	    } else {
	        this.cafe.getTurnoEmpleados().put(fechaS, originalS);
	        this.cafe.getTurnoEmpleados().put(fechaC, originalC);
	    }
	}

	
}
