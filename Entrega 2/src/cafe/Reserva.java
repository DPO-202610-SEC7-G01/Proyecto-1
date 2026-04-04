package cafe;

import java.util.Calendar;

import usuario.Cliente;

public class Reserva {
	private Cliente cliente;
	private int numPersonas;
	private Calendar fecha;
	private Mesa mesa;
	public Reserva(Cliente cliente, int numPersonas, Calendar fecha) {
		super();
		this.cliente = cliente;
		this.numPersonas = numPersonas;
		this.fecha = fecha;
	}
	public int getNumPersonas() {
		return numPersonas;
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	
	
	
}
