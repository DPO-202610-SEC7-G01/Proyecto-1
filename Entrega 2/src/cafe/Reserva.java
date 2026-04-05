package cafe;

import java.util.Calendar;
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
	private List<Producto> transaccion;
	private List<Juego> juegosPrestados; 

	
	
	//Constructor
	public Reserva(Cliente cliente, int numPersonas, Calendar fecha) {
		super();
		this.cliente = new ArrayList<Cliente>();
		this.numPersonas = numPersonas;
		this.fecha = fecha;
		this.transaccion = new ArrayList<Producto>();
		
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
	public List<Producto> getFactura() {
		return transaccion;
	}
	
	public double getTotalFactura() {
		return totalFactura;
	}

	public List<Juego> getJuegosPrestados(){
		return juegosPrestados;
	}
	
	public void setMesa(Mesa nuevaMesa) {
		this.mesa= nuevaMesa;
	}
	//Métodos
	public void addTransaccion(Producto p) {
	    // CORRECCIÓN: Usar .add() que es el método de Java para listas
	    this.transaccion.add(p); 
	    double precioFinal = p.getPrecio() + p.getTasaImpuesto();
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
	    for (Producto p : this.transaccion) {
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
	public void agregarAlPrestamo(Juego juego) {
		//Se mete a 	private HashMap<Calendar, HashMap<Integer, Reserva>> historialUsoJuegos; del café y alista el registro
		
	}
	
	
	
}
