package cafe;

import java.util.Calendar;
import java.util.List;

import producto.Juego;

import java.util.ArrayList;
import usuario.Cliente;

public class Reserva {
	private Cliente cliente;
	private int numPersonas;
	private Calendar fecha;
	private Mesa mesa;
	private List<Transaccion> transacciones;
	
	public Reserva(Cliente cliente, int numPersonas, Calendar fecha) {
		super();
		this.cliente = cliente;
		this.numPersonas = numPersonas;
		this.fecha = fecha;
		this.transacciones = new ArrayList<Transaccion>();
		
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
	public void addTransaccion(Transaccion t) {
		this.transacciones.add(t);
	}
	
	
	
	
}
