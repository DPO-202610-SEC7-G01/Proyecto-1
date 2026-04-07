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
    public boolean gestionarPrestamo(Empleado empleado, Juego juego, Calendar fecha) {
       for (Reserva r : miCafe.getReservasPrevias()) {
            if (r.getJuego().equals(juego) && esMismaFecha(r.getFecha(), fecha)) {
                return false; 
            }
        }

        juego.setPrestado(true);
        miCafe.getHistorialUsoJuegos().putIfAbsent(fecha, new HashMap<>());
        
        Reserva nuevoRegistro = new Reserva(empleado, juego, fecha);
        miCafe.getHistorialUsoJuegos().get(fecha).put(juego.getId(), nuevoRegistro);

        return true;
    }

    private boolean esMismaFecha(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
               c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
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

