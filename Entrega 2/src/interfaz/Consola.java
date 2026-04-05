package interfaz;

import java.util.Scanner;
import java.util.Random;
import cafe.Cafe;
import usuario.Cliente;

public class Consola {
    private Cafe miCafe;
    private Scanner lector;
    private Random aleatorio;

    public Consola() {
        this.miCafe = new Cafe(50);
        this.lector = new Scanner(System.in);
        this.aleatorio = new Random();
    }

    public void registrarUsuarioNuevo() {
        System.out.println("\n--- REGISTRO DE NUEVO CLIENTE ---");
        
        int id = aleatorio.nextInt(1001);
        System.out.println("ID asignado automáticamente: " + id);

        System.out.print("Ingrese Nombre completo: ");
        String nombre = lector.nextLine();

        String primeraPalabra = nombre.split(" ")[0].toLowerCase();
        String login = primeraPalabra + id;
        System.out.println("Login generado automáticamente: " + login);

        System.out.print("Ingrese Password: ");
        String password = lector.nextLine();

        System.out.print("Ingrese Edad: ");
        int edad = lector.nextInt();
        lector.nextLine(); 
        
        System.out.print("Ingrese alérgenos (separados por coma): ");
        String alergenos = lector.nextLine();
        
        Cliente nuevoCliente = new Cliente(id, login, password, nombre, edad, alergenos);
        miCafe.agregarUsuario(nuevoCliente);

        System.out.println("✅ Cliente '" + nombre + "' registrado exitosamente con login: " + login);
    }

    public static void main(String[] args) {
        Consola consola = new Consola();
        
        consola.registrarUsuarioNuevo();
        
        int total = consola.miCafe.getClientes().size();
        System.out.println("Total de clientes en el café: " + total);
    }
}