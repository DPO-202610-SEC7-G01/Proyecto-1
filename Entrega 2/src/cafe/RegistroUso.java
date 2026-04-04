package cafe;

import java.util.Calendar;

import usuario.Cliente;

public class RegistroUso {
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private Cliente cliente;
	private Reserva reserva;
	public RegistroUso(Calendar fechaInicio, Calendar fechaFin, Cliente cliente, Reserva reserva) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cliente = cliente;
		this.reserva = reserva;
	}
	public Calendar getFechaInicio() {
		return fechaInicio;
	}
	public Calendar getFechaFin() {
		return fechaFin;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public Reserva getReserva() {
		return reserva;
	}
	
}
