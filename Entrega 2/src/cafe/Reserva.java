package cafe;

import java.util.Calendar;
import java.util.List;

import producto.Juego;
import producto.Producto;
import producto.Bebida;
import producto.Platillo;

import java.util.ArrayList;
import usuario.Cliente;
import usuario.Mesero;
import cafe.Cafe;

public class Reserva {
	private Mesa mesa;
	private int numPersonas;
	private Calendar fecha;
	private double totalFactura;
	private List<Cliente> clientes;
	private List<Producto> transaccion;
	private List<Juego> juegosPrestados; 
	private Cafe miCafe;
	private Mesero meseroAsignado;

	
	
	//Constructor
	public Reserva(List<Cliente> clientes, int numPersonas, Calendar fecha) {
		super();
		this.clientes = clientes;
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
		return clientes;
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
	

	public Mesero getMeseroAsignado() {
	    return meseroAsignado;
	}
	//Métodos
	public void cambiarMesero(Mesero nuevoMesero) {
	    this.meseroAsignado = nuevoMesero;
	}
	
    public void addTransaccion(Producto p) {
        this.transaccion.add(p); 
        double impuesto = p.getPrecio() * p.getTasaImpuesto();
        this.totalFactura += (p.getPrecio() + impuesto);
    }
	

	
	public boolean tieneMenoresDeEdad() {
	    for (Cliente c : this.clientes) { 
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
	

	public void agregarAlPrestamo(Juego juego) {
        if (miCafe.reservarJuego(juego, this)) {
            this.juegosPrestados.add(juego);
        }
        
	} 


   
	
	
}
