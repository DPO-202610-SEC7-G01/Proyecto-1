package cafe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import producto.Juego;
import usuario.empleado;
import usuario.Usuario;
import usuario.Mesero;
import usuario.Cliente;
import usuario.Cocinero;

public class Cafe {

	private int capacidad;
	private ArrayList<Mesa> mesas;
	private ArrayList<Cliente> clientes;
	private ArrayList<empleado> empleados;
	private ArrayList<Reserva> reservasPrevias;
	private ArrayList <Juego> juegos;
	private HashMap<Calendar, HashMap<Juego, Reserva>> historialUsoJuegos;
	private ArrayList<Transaccion> historialTransaccion;
	
	private Map<Calendar, empleado> turnoEmpleados;

	// Constructor
	public Cafe(int capacidad) {
		super();
		this.capacidad = capacidad;
		this.mesas = new ArrayList<Mesa>();
		this.clientes = new ArrayList<Cliente>();
		this.empleados = new ArrayList<empleado>();
		this.reservasPrevias = new ArrayList<Reserva>();
		this.historialUsoJuegos = new HashMap<Calendar, HashMap<Juego,Reserva>>();
		this.juegos = new ArrayList<Juego>();
		this.historialTransaccion = new ArrayList<Transaccion>();
		this.turnoEmpleados = new HashMap<Calendar, empleado>();
		
	}

	// Getters

	public int getCapacidad() {
		return capacidad;
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


	public ArrayList<empleado> getEmpleados() {
		return empleados;
	}

	
	public ArrayList<Reserva> getReservasPrevias() {
		return reservasPrevias;
	}

	public HashMap<Calendar, HashMap<Juego,Reserva>> getHistorialUsoJuegos() {
		return historialUsoJuegos;
	}


	public ArrayList<Transaccion> getHistorialTransaccion() {
		return historialTransaccion;
	}

	public Map<Calendar, empleado> getTurnoEmpleados() {
		return turnoEmpleados;
	}
	
	public ArrayList<Juego> getJuegos(){
		return this.juegos;
	}

	// Métodos
	public void registrarNuevaReserva(Reserva r) {
		if ((verificarDisponibilidad(r.getFecha(), r.getNumPersonas())) && asignarMesa(r)) {
			reservasPrevias.add(r);
			Cliente cliente = r.getCliente();
			cliente.sumarPuntos();
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

	public void agregarEmpleado(empleado e) {
		this.empleados.add(e);
		//Calendar turno = e.getTurno();
		//this.turnoEmpleados.put(turno, e);
	}
	
	public void agregarMesa(Mesa mesa) {
		this.mesas.add(mesa);
	}
	
	public void agregarUsuario(Cliente cliente) {
		this.clientes.add(cliente);
	}
	public void agregarJuego(Juego juego) {
		this.juegos.add(juego);
	}
	public boolean reservarJuego(Juego juego, Reserva r) {
		if (juegos.contains(juego)) {
			Calendar fecha = r.getFecha();
			if(historialUsoJuegos.containsKey(fecha)) {
				HashMap<Juego, Reserva> juegosReservados = historialUsoJuegos.get(fecha);
				
				if (juegosReservados.containsKey(juego)) {
						return false;		
				}
				else {
					juegosReservados.put(juego, r);
					return true;
				}
			}
		}
		return false;
		
	}
	public double calcularIngresosTotales(Calendar fechaInicio, Calendar fechaFin) {
		double total = 0;
		for (Transaccion t: historialTransaccion) {
	        Calendar fecha = t.getFecha();
	        if((fecha.equals(fechaInicio)|| fecha.after(fechaInicio)) && (fecha.equals(fechaFin) || fecha.before(fechaFin))) {
	        	total += t.calcularTotal();
	        }
		}
		return total;
	}
	
}
