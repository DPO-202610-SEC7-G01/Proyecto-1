package interfaz;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import cafe.Cafe;
import usuario.Cliente;
import usuario.Mesero;
import usuario.Cocinero;
import usuario.Empleado;
import usuario.Usuario;
import usuario.Administrador;
import producto.Juego;
import producto.Producto;
import cafe.Transaccion;

public class Consola {
    private Cafe miCafe;
    private Scanner lector;
    private Random aleatorio;

    public Consola() {
        this.miCafe = new Cafe(50);
        this.lector = new Scanner(System.in);
        this.aleatorio = new Random();
        Juego juegoInicial = new Juego(
                501,              // id
                150000,           // precio
                "Catan",          // nombre
                1995,             // anioPublicacion
                "Devir",          // empresaMatriz
                4,                // numJugadores
                "apto 5 anios",   // restriccionEdad
                "Tablero"         // categoria
            );

         miCafe.getJuegosVenta().add(juegoInicial);
    }

    public void registrarAdministrador() {
        System.out.print("Nombre completo: ");
        String nombre = lector.nextLine();

        int id = aleatorio.nextInt(1001);
        String loginBase = nombre.split(" ")[0].toLowerCase() + id;
        
        while (buscarUsuario(loginBase) != null) {
            id = aleatorio.nextInt(1001);
            loginBase = nombre.split(" ")[0].toLowerCase() + id;
        }
        
        final String login = loginBase; 
        System.out.print("Ingrese Password: ");
        String password = lector.nextLine();
        if (miCafe.getAdmin() != null){
            System.out.println("Hubo un cambio exitoso de la administración");
        }
        miCafe.cambiarAdmin(new Administrador(id, login, password, nombre, miCafe));
        System.out.println("✅ Registro exitoso. Login: " + login + " (ID: " + id + ")");
    }
    
    public void registrarUsuarioNuevo() {
        System.out.println("\n--- REGISTRO DE NUEVO USUARIO ---");
        System.out.println("1. Cliente | 2. Mesero | 3. Cocinero ");
        System.out.print("Seleccione: ");
        int tipo = lector.nextInt();
        lector.nextLine();

        System.out.print("Nombre completo: ");
        String nombre = lector.nextLine();

        // 1. Generar Login y Verificar Unicidad
        int id = aleatorio.nextInt(1001);
        String loginBase = nombre.split(" ")[0].toLowerCase() + id;
        
        // Si el login ya existe (por pura mala suerte del azar), generamos otro
        while (buscarUsuario(loginBase) != null) {
            id = aleatorio.nextInt(1001);
            loginBase = nombre.split(" ")[0].toLowerCase() + id;
        }
        
        final String login = loginBase; // Lo hacemos final para usarlo con seguridad
        System.out.print("Ingrese Password: ");
        String password = lector.nextLine();

        // 2. Creación según el tipo
        switch (tipo) {
            case 1:
                System.out.print("Edad: ");
                int edad = lector.nextInt();
                lector.nextLine();
                System.out.print("Alérgenos: ");
                String alergenos = lector.nextLine();
                
                Cliente nuevoC = new Cliente(id, login, password, nombre, edad, alergenos);
                miCafe.agregarUsuario(nuevoC);
                break;

            case 2:
                miCafe.getEmpleados().add(new Mesero(id, login, password, nombre));
                break;

            case 3:
                miCafe.getEmpleados().add(new Cocinero(id, login, password, nombre));
                break;

           
            default:
                System.out.println("❌ Opción inválida.");
                return;
        }

        System.out.println("✅ Registro exitoso. Login: " + login + " (ID: " + id + ")");
    }

    public void cambioContraseña() {
        System.out.println("\n--- CAMBIO DE CONTRASEÑA ---");
        System.out.print("Ingrese su login de usuario: ");
        String loginBusqueda = lector.nextLine();

        Usuario usuarioEncontrado = buscarUsuario(loginBusqueda);

        if (usuarioEncontrado != null) {
            System.out.print("Ingrese la nueva contraseña: ");
            String nuevaPass = lector.nextLine();
            
            usuarioEncontrado.setPassword(nuevaPass);
            
            System.out.println("Contraseña actualizada para el usuario: " + usuarioEncontrado.getNombre());
        } else {
            System.out.println("Error: No se encontró ningún usuario con el login: " + loginBusqueda);
        }
    }

    private Usuario buscarUsuario(String login) {
        for (Cliente c : miCafe.getClientes()) {
            if (c.getLogin().equals(login)) return c;
        }
        for (Empleado e : miCafe.getEmpleados()) {
            if (e.getLogin().equals(login)) return e;
        }
        return null;
    }
    
    public void simularCompra() {
        System.out.println("\n--- SIMULACIÓN DE COMPRA ---");
        System.out.print("Ingrese su login: ");
        String login = lector.nextLine();
        Usuario u = buscarUsuario(login);

        if (u != null) {
            List<Producto> carrito = new ArrayList<>();
            if (!miCafe.getJuegosVenta().isEmpty()) {
                carrito.add(miCafe.getJuegosVenta().get(0)); 
            }

            Transaccion t = null;
            int idT = aleatorio.nextInt(10000);

            // Polimorfismo: Llamamos al método generarTransaccion de cada clase
            if (u instanceof Cliente) {
                t = ((Cliente) u).generarTransaccion(carrito, idT);
            } else if (u instanceof Empleado) {
                t = ((Empleado) u).generarTransaccion(carrito, idT);
            }

            if (t != null) {
                miCafe.getHistorialTransaccion().add(t);
                System.out.println("\n--- FACTURA GENERADA ---");
                System.out.println("Cliente: " + u.getNombre());
                System.out.println("Total a pagar: $" + t.calcularTotal());
            }
        } else {
            System.out.println("Usuario no encontrado. Por favor creelo:");
            registrarUsuarioNuevo();
        }
    }
    
    public static void main(String[] args) {
        Consola consola = new Consola();
        Scanner lectorMenu = new Scanner(System.in);
        int opcion = 0;

        System.out.println("BIENVENIDO A DULCES N DADOS ");

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("0. Registrar nuevo admin");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Ver total de clientes");
            System.out.println("3. Cambiar Contraseña");
            System.out.println("4. Ingreso de juegos favoritos");
            System.out.println("5. Comprar Productos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = lectorMenu.nextInt();
                lectorMenu.nextLine(); 

                switch (opcion) {
                	case 0:
                		consola.registrarAdministrador();
                		break;
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
                lectorMenu.nextLine(); 
                opcion = 0;
            }

        } while (opcion != 3);
        
        lectorMenu.close();
    }
}