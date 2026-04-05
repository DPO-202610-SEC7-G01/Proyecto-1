package interfaz;

import java.util.Scanner;
import java.util.Random;
import cafe.Cafe;
import usuario.Cliente;
import usuario.Empleado;
import usuario.Usuario;

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

        System.out.println("Cliente '" + nuevoCliente.getNombre() + "' registrado exitosamente con login: " + nuevoCliente.getLogin());
    }

    public void cambioContraseña() {
        System.out.println("\n--- CAMBIO DE CONTRASEÑA ---");
        System.out.print("Ingrese su login de usuario: ");
        String loginBusqueda = lector.nextLine();

        Usuario usuarioEncontrado = null;

        for (Cliente c : miCafe.getClientes()) {
            if (c.getLogin().equals(loginBusqueda)) {
                usuarioEncontrado = c;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            for (Empleado e : miCafe.getEmpleados()) {
                if (e.getLogin().equals(loginBusqueda)) {
                    usuarioEncontrado = e;
                    break;
                }
            }
        }

        if (usuarioEncontrado != null) {
            System.out.print("Ingrese la nueva contraseña: ");
            String nuevaPass = lector.nextLine();
            
            usuarioEncontrado.setPassword(nuevaPass);
            
            System.out.println("Contraseña actualizada para el usuario: " + usuarioEncontrado.getNombre());
        } else {
            System.out.println("Error: No se encontró ningún usuario con el login: " + loginBusqueda);
        }
    }
    
    public static void main(String[] args) {
        Consola consola = new Consola();
        Scanner lectorMenu = new Scanner(System.in);
        int opcion = 0;

        System.out.println("BIENVENIDO A DULCES N DADOS ");

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Ver total de clientes");
            System.out.println("3. Cambiar Contraseña");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = lectorMenu.nextInt();
                lectorMenu.nextLine(); 

                switch (opcion) {
                    case 1:
                        consola.registrarUsuarioNuevo();
                        break;
                    case 2:
                        int total = consola.miCafe.getClientes().size();
                        System.out.println("Actualmente hay " + total + " clientes en el sistema.");
                        break;
                    case 3:
                        consola.cambioContraseña();
                        break;
                    case 4:
                        System.out.println(" Saliendo del sistema... ¡Hasta luego!");
                        break;
                      
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println(" Error: Por favor ingrese un número válido.");
                lectorMenu.nextLine(); // Limpiar el error del scanner
                opcion = 0;
            }

        } while (opcion != 3);
        
        lectorMenu.close();
    }
}