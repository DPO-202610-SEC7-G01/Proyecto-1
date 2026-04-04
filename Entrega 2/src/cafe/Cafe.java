package cafe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import usuario.empleado;
import usuario.Usuario;

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

	public void setCapacidad(int capacidad) {
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
	
	
}
