package cafe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import usuario.empleado;
import usuario.Usuario;
import usuario.Mesero;   
import usuario.Cocinero;

public class Cafe {
	
	private int capacidad;
	private ArrayList<Mesa> mesas;
	private ArrayList<Usuario> usuarios;
	private ArrayList<empleado> empleados;
	private ArrayList<Reserva> reservasPrevias;
	private ArrayList<RegistroUso> historialUsoJuegos;
	private Map<Integer, Transaccion> historialTransaccion;
	private Map<Integer, Reserva> historialReserva;
	private Map<Calendar, empleado> turnoEmpleados;
	
	
	//Constructor
	public Cafe(int capacidad, ArrayList<Mesa> mesas, ArrayList<Usuario> usuarios, ArrayList<empleado> empleados,
			ArrayList<Reserva> reservasPrevias, ArrayList<RegistroUso> historialUsoJuegos,
			Map<Integer, Transaccion> historialTransaccion, Map<Integer, Reserva> historialReserva,
			Map<Calendar, empleado> turnoEmpleados) {
		super();
		this.capacidad = capacidad;
		this.mesas = mesas;
		this.usuarios = usuarios;
		this.empleados = empleados;
		this.reservasPrevias = reservasPrevias;
		this.historialUsoJuegos = historialUsoJuegos;
		this.historialTransaccion = historialTransaccion;
		this.historialReserva = historialReserva;
		this.turnoEmpleados = turnoEmpleados;
	}
	
	//Getters y Setters

	public int getCapacidad() {
		return capacidad;
	}

	public void actualizarCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public ArrayList<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(ArrayList<Mesa> mesas) {
		this.mesas = mesas;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<empleado> empleados) {
		this.empleados = empleados;
	}

	public ArrayList<Reserva> getReservasPrevias() {
		return reservasPrevias;
	}

	public void setReservasPrevias(ArrayList<Reserva> reservasPrevias) {
		this.reservasPrevias = reservasPrevias;
	}

	public ArrayList<RegistroUso> getHistorialUsoJuegos() {
		return historialUsoJuegos;
	}

	public void setHistorialUsoJuegos(ArrayList<RegistroUso> historialUsoJuegos) {
		this.historialUsoJuegos = historialUsoJuegos;
	}

	public Map<Integer, Transaccion> getHistorialTransaccion() {
		return historialTransaccion;
	}

	public void setHistorialTransaccion(Map<Integer, Transaccion> historialTransaccion) {
		this.historialTransaccion = historialTransaccion;
	}

	public Map<Integer, Reserva> getHistorialReserva() {
		return historialReserva;
	}

	public void setHistorialReserva(Map<Integer, Reserva> historialReserva) {
		this.historialReserva = historialReserva;
	}

	public Map<Calendar, empleado> getTurnoEmpleados() {
		return turnoEmpleados;
	}

	public void setTurnoEmpleados(Map<Calendar, empleado> turnoEmpleados) {
		this.turnoEmpleados = turnoEmpleados;
	}
	
	
	//Métodos 
	public void registrarNuevaReserva(Reserva r) {
		if (verificarDisponibilidad(r.getFecha(), r.getNumPersonas()) ) {
		reservasPrevias.add(r);
		}
	}
	
	public boolean verificarDisponibilidad(Calendar fecha, int numPersonas) {
		if (numPersonas <= capacidad || numPersonas>0) {
			return true;
		}
		return false;
	}
	
	public boolean aptoApertura(Calendar fechaConsulta) {
	    int cocineros = 0;
	    int meseros = 0;

	    for (Map.Entry<Calendar, empleado> entrada : turnoEmpleados.entrySet()) {
	        Calendar fechaTurno = entrada.getKey();
	        empleado e = entrada.getValue();
	        if (esMismaFecha(fechaTurno, fechaConsulta)) {
	            if (e instanceof Mesero) {
	                meseros++;
	            } else if (e instanceof Cocinero) {
	                cocineros++;
	            }
	        }
	    }
	    return (cocineros >= 1 && meseros >= 2);
	}

	private boolean esMismaFecha(Calendar cal1, Calendar cal2) {
	    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
	           cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}
	
	public void asignarMesa(Reserva r) {
		for (Mesa mesita: mesas) {
			if (mesita.isDisponible() && r.getNumPersonas()<=mesita.getNumSillas()) {
				mesita.setDisponible(false);
			}
		}
	}
	
	public void agregarEmpleado(empleado e) {
		empleados.add(e);
	}
	
	public double calcularIngresosTotales(Calendar fechaInicio, Calendar fechaFin) {
		for (Map.Entry<Integer, empleado> entrada : historialTransaccion.entrySet()) {
	        Calendar fechaTurno = entrada.getKey();
	        empleado e = entrada.getValue();
	        if (esMismaFecha(fechaTurno, fechaInicio) ) {
		return 0.0;
	}
	
}
