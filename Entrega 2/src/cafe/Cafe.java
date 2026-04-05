package cafe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import producto.Juego;
import usuario.Empleado;
import usuario.Mesero;
import usuario.Cliente;
import usuario.Cocinero;
import usuario.Administrador;

public class Cafe {

	private int capacidad;
	private Administrador admin;
	private ArrayList<Mesa> mesas;
	private ArrayList<Cliente> clientes;
	private ArrayList<Empleado> empleados;
	private ArrayList<Reserva> reservasPrevias;
	private ArrayList<Juego> juegosPrestamo;
	private ArrayList<Juego> juegosVenta;
	private HashMap<Calendar, HashMap<Integer, Reserva>> historialUsoJuegos;
	private HashMap<Integer, ArrayList<Juego>> juegosCliente;
	private ArrayList<Transaccion> historialTransaccion;

	private Map<Calendar, Empleado> turnoEmpleados;

	// Constructor
	public Cafe(int capacidad) {
		super();
		this.capacidad = capacidad;
		this.mesas = new ArrayList<Mesa>();
		this.clientes = new ArrayList<Cliente>();
		this.empleados = new ArrayList<Empleado>();
		this.reservasPrevias = new ArrayList<Reserva>();
		this.historialUsoJuegos = new HashMap<Calendar, HashMap<Integer, Reserva>>();
		this.juegosPrestamo = new ArrayList<Juego>();
		this.juegosVenta = new ArrayList<Juego>();
		this.juegosCliente = new HashMap<Integer, ArrayList<Juego>>();
		this.historialTransaccion = new ArrayList<Transaccion>();
		this.turnoEmpleados = new HashMap<Calendar, Empleado>();
		this.admin = null;
	}

	// Getters

	public int getCapacidad() {
		return capacidad;
	}
	
	public Administrador getAdmin() {
		return admin;
	}

	public void actualizarCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public ArrayList<Mesa> getMesas() {
		return mesas;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public ArrayList<Reserva> getReservasPrevias() {
		return reservasPrevias;
	}

	public HashMap<Calendar, HashMap<Integer, Reserva>> getHistorialUsoJuegos() {
		return historialUsoJuegos;
	}

	public ArrayList<Transaccion> getHistorialTransaccion() {
		return historialTransaccion;
	}

	public Map<Calendar, Empleado> getTurnoEmpleados() {
		return turnoEmpleados;
	}

	public ArrayList<Juego> getJuegosPrestamo() {
		return juegosPrestamo;
	}

	public ArrayList<Juego> getJuegosVenta() {
		return juegosVenta;
	}

	// Métodos
	public void cambiarAdmin(Administrador adminNuevo) {
		admin= adminNuevo;
	}
	
	public void registrarNuevaReserva(Reserva r) {
		if ((verificarDisponibilidad(r.getFecha(), r.getNumPersonas())) && asignarMesa(r)) {
			reservasPrevias.add(r);
			Cliente cliente = r.getCliente(); //Son Varios Clientes
			cliente.sumarPuntos(); //Se debe meter a sumar individualmente y decir cuanto se le va a sumar al cliente por la reserva 
		}
	}

	public boolean verificarDisponibilidad(Calendar fecha, int numPersonas) {
		if ((numPersonas <= capacidad || numPersonas > 0)) {
			return true;
		}
		return false;
	}

	public boolean aptoApertura(Calendar fechaConsulta) {
		int cocineros = 0;
		int meseros = 0;

		for (Map.Entry<Calendar, Empleado> entrada : turnoEmpleados.entrySet()) {
			Calendar fechaTurno = entrada.getKey();
			Empleado e = entrada.getValue();
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
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}

	public boolean asignarMesa(Reserva r) {
		for (Mesa mesita : mesas) {
			if (mesita.isDisponible() && r.getNumPersonas() <= mesita.getNumSillas()) {
				mesita.setDisponible(false);
				r.setMesa(mesita);
				return true;
			}
		}
		return false;
	}

	public void agregarEmpleado(Empleado e) {
		this.empleados.add(e);
		// Calendar turno = e.getTurno();
		// this.turnoEmpleados.put(turno, e);
	}

	public void agregarMesa(Mesa mesa) {
		this.mesas.add(mesa);
	}

	public void agregarUsuario(Cliente cliente) {
		this.clientes.add(cliente);
	}

	public void agregarJuegoPrestamo(Juego juego) {
		this.juegosPrestamo.add(juego);
	}

	public void agregarJuegoVenta(Juego juego) {
		this.juegosVenta.add(juego);
	}

	public boolean reservarJuego(Juego juego, Reserva r) {
		if (!juegosPrestamo.contains(juego))
			return false;

		Calendar fecha = r.getFecha();
		int id = r.getClientes().getId(); //Es una lista de clientes 
		int edad = r.getCliente().getEdad();
		int numPersonas = r.getNumPersonas();

		// Validaciones
		if (!juego.esAptoParaEdad(edad))
			return false;
		if (juego.getNumJugadores() < numPersonas)
			return false;

		// Crear fecha si no existe
		historialUsoJuegos.putIfAbsent(fecha, new HashMap<Integer, Reserva>());
		juegosCliente.putIfAbsent(id, new ArrayList<Juego>());

		HashMap<Integer, Reserva> juegosReservados = historialUsoJuegos.get(fecha);
		ArrayList<Juego> juegosDelCliente = juegosCliente.get(id);

		// Ya está reservado ese juego
		if (juegosReservados.containsKey(juego.getId()))
			return false;

		// Cliente ya tiene 2 juegos
		if (juegosDelCliente.size() >= 2)
			return false;

		juegosDelCliente.add(juego);
		juegosReservados.put(juego.getId(), r);
		return true;

	}

	public double calcularIngresosTotales(Calendar fechaInicio, Calendar fechaFin) {
		double total = 0;
		for (Transaccion t : historialTransaccion) {
			Calendar fecha = t.getFecha();
			if ((fecha.equals(fechaInicio) || fecha.after(fechaInicio))
					&& (fecha.equals(fechaFin) || fecha.before(fechaFin))) {
				total += t.calcularTotal();
			}
		}
		return total;
	}

}
