package cafe;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import producto.Juego;
import producto.Producto;
import producto.Bebida;

import java.util.ArrayList;
import usuario.Cliente;

public class Reserva {
	private Mesa mesa;
	private int numPersonas;
	private Calendar fecha;
	private double totalFactura;
	private List<Cliente> cliente;
	private List<Producto> transacciones;

	
	
	//Constructor
	public Reserva(Cliente cliente, int numPersonas, Calendar fecha) {
		super();
		this.cliente = new ArrayList<Cliente>();
		this.numPersonas = numPersonas;
		this.fecha = fecha;
		this.transacciones = new ArrayList<Transaccion>();
		
	}
	
	//Getters y Setters
	public int getNumPersonas() {
		return numPersonas;
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	
	public List<Cliente> getClientes() {
		return cliente;
	}
	public Mesa getMesa() {
		return mesa;
	}
	
	
	//Métodos
	public void addTransaccion(Producto p) {
	    // Calculamos el precio con impuestos (IVA o Impoconsumo) y descuentos
	    double precioFinal = p.getPrecio() + p.getTasaImpuesto();
	    this.transacciones.add(p); 
	    totalFactura += precioFinal;
	}
	
	public boolean tieneMenoresDeEdad() {
	    for (Cliente c : this.cliente) {
	        if (c.getEdad() < 18) {
	            return true; 
	        }
	    }
	    return false; 
	}
	
	public boolean tieneBebidasCalientes() {
	    for (Producto p : this.transacciones) {
	        if (p instanceof Bebida) {
	            Bebida b = (Bebida) p; 
	            if (b.getTemperatura().equalsIgnoreCase("Caliente")) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	//Por implementar
	public void agregarAlPrestamo() {
		//Se mete a 	private HashMap<Calendar, HashMap<Integer, Reserva>> historialUsoJuegos; del café y alista el registro
		
	}
	
	
	
}
