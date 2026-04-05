package interfaz;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import cafe.Cafe;
import cafe.Reserva;
import usuario.Cliente;
import usuario.Mesero;
import usuario.Cocinero;
import usuario.Empleado;
import usuario.Usuario;
import usuario.Administrador;
import producto.Juego;
import producto.Producto;
import producto.Bebida;
import cafe.Transaccion;
import producto.Platillo;

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

         
        Bebida bebidaInicial = new Bebida(
                201, 
                12000, 
                "Café Americano", 
                "Caliente", 
                false
            );
         Platillo platilloInicial = new Platillo(
        	        101, 
        	        25000, 
        	        "Sandwich de Pavo", 
        	        "Gluten, Lácteos"
        	    );
         miCafe.getJuegosVenta().add(juegoInicial);
         miCafe.getJuegosPrestamo().add(juegoInicial);
         miCafe.getMenuBebidas().add(bebidaInicial);
         miCafe.getMenuPlatillos().add(platilloInicial);
         
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
        System.out.println("Registro exitoso con el login: " + login );
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

        System.out.println("Registro exitoso con el login: " + login );
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
    
    public void ingresarJuegoFav() {
        System.out.println("\n--- AGREGAR JUEGO A FAVORITOS ---");
        System.out.print("Ingrese su login de usuario: ");
        String loginBusqueda = lector.nextLine();

        // 1. Buscamos al usuario usando la función auxiliar
        Usuario usuarioEncontrado = buscarUsuario(loginBusqueda);

        if (usuarioEncontrado != null) {
            // 2. Validamos que el café tenga juegos para mostrar
            if (miCafe.getJuegosVenta().isEmpty()) {
                System.out.println("❌No hay juegos registrados en el catálogo del café.");
                return;
            }

            System.out.println("Seleccione el juego que desea agregar:");
            for (int i = 0; i < miCafe.getJuegosVenta().size(); i++) {
                System.out.println(i + ". " + miCafe.getJuegosVenta().get(i).getNombre());
            }

            System.out.print("Ingrese el número del juego: ");
            int indice = lector.nextInt();
            lector.nextLine(); // Limpiar el salto de línea del buffer

            if (indice >= 0 && indice < miCafe.getJuegosVenta().size()) {
                Juego juegoElegido = miCafe.getJuegosVenta().get(indice);

                if (usuarioEncontrado instanceof Cliente) {
                    Cliente c = (Cliente) usuarioEncontrado;
                    c.agregarJuegoFavorito(juegoElegido);
                } else if (usuarioEncontrado instanceof Empleado) {
                    Empleado e = (Empleado) usuarioEncontrado;
                    e.agregarJuegoFavorito(juegoElegido);
                }

                System.out.println( juegoElegido.getNombre() + 
                                   " ha sido añadido a los favoritos de " + 
                                   usuarioEncontrado.getNombre() );
            } else {
                System.out.println("Opción de juego no válida.");
            }
        } else {
            System.out.println(" Error: No se encontró ningún usuario con el login: " + loginBusqueda);
        }
    }

    
    public void simularCompra() {
        System.out.println("\n--- SIMULACIÓN DE COMPRA INTERACTIVA ---");
        System.out.print("Ingrese su login: ");
        String login = lector.nextLine();
        Usuario u = buscarUsuario(login);

        if (u == null) {
            System.out.println("Usuario no encontrado. Por favor, regístrese:");
            registrarUsuarioNuevo();
            return; 
        }

        List<Producto> carrito = new ArrayList<>();
        boolean comprando = true;

        // 1. Bucle de selección de productos
        while (comprando) {
            System.out.println("\n--- CATÁLOGO DISPONIBLE ---");
            System.out.println("1. Ver Juegos de Mesa");
            System.out.println("2. Ver Menú (Platillos y Bebidas)");
            System.out.println("3. Finalizar Compra y Pagar");
            System.out.print("Seleccione una categoría: ");
            
            int cat = lector.nextInt();
            lector.nextLine();

            if (cat == 1) {
                mostrarYAgregar(miCafe.getJuegosVenta(), carrito);
            } else if (cat == 2) {
                List<Producto> menuCompleto = new ArrayList<>();
                menuCompleto.addAll(miCafe.getMenuPlatillos());
                menuCompleto.addAll(miCafe.getMenuBebidas());
                mostrarYAgregar(menuCompleto, carrito);
            } else if (cat == 3) {
                if (carrito.isEmpty()) {
                    System.out.println("El carrito está vacío. Compra cancelada.");
                    return;
                }
                comprando = false;
            }
        }

        // 2. Validación de Amistad (Solo para Clientes)
        if (u instanceof Cliente) {
            Cliente c = (Cliente) u;
            System.out.print("¿Es amigo de algún empleado? (si/no): ");
            if (lector.nextLine().equalsIgnoreCase("si")) {
                if (verificarSiEsAmigo(c)) {
                    c.nuevoAmigo();
                    System.out.println("✨ Descuento de amigo ACTIVADO.");
                } else {
                    System.out.println("❌ No estás en la lista de amigos oficial.");
                }
            }
        }

        // 3. Generación y Registro
        int idT = aleatorio.nextInt(10000);
        Transaccion t = null;
        if (u instanceof Cliente) t = ((Cliente) u).generarTransaccion(carrito, idT);
        else if (u instanceof Empleado) t = ((Empleado) u).generarTransaccion(carrito, idT);

        if (t != null) {
            miCafe.getHistorialTransaccion().add(t);
            imprimirFacturaDetallada(t, u);
        }
    }

    private boolean verificarSiEsAmigo(Cliente cliente) {
        for (Empleado e : miCafe.getEmpleados()) {
            if (e.getAmigos().contains(cliente)) {
                return true;
            }
        }
        return false;
    }
    
    private void imprimirFacturaDetallada(Transaccion t, Usuario u) {
        String verde = "\u001B[32m";
        String cursiva = "\u001B[3m";
        String reset = "\u001B[0m";

        System.out.println("\n========================================");
        System.out.println("           FACTURA DE VENTA           ");
        System.out.println("          ID: " + t.getId());
        System.out.println("========================================");
        System.out.println("Fecha: " + t.getFecha().getTime());
        System.out.println("Cliente: " + u.getNombre());
        System.out.println("----------------------------------------");
        
        double subtotalNeto = 0;
        double totalImpuestos = 0;

        // Listar productos comprados
        for (Producto p : t.getProductos()) {
            double precioBase = p.getPrecio();
            double tasa = p.getTasaImpuesto(); // IVA o Impoconsumo
            double impuestoProducto = precioBase * tasa;
            
            System.out.printf("- %-18s | $%d (Imp: %.0f%%)\n", 
                p.getNombre(), (int)precioBase, tasa * 100);
            
            subtotalNeto += precioBase;
            totalImpuestos += impuestoProducto;
        }

        // Cálculos de totales
        double totalConImpuestos = subtotalNeto + totalImpuestos;
        int totalPagar = t.calcularTotal(); // Este ya trae los descuentos aplicados
        double ahorro = totalConImpuestos - totalPagar;

        System.out.println("----------------------------------------");
        System.out.println("Subtotal (Base):     $" + (int)subtotalNeto);
        System.out.println("Total Impuestos:     $" + (int)totalImpuestos);
        
        if (ahorro > 0) {
            System.out.println(cursiva + "Ahorro aplicado:    -$" + (int)ahorro + reset);
        }
        
        System.out.println("----------------------------------------");
        System.out.println(verde + "TOTAL A PAGAR:       $" + totalPagar + reset);
        System.out.println("========================================\n");
    }
    
    // Método genérico para mostrar cualquier lista de productos y agregarlos al carrito
    private void mostrarYAgregar(List<? extends Producto> lista, List<Producto> carrito) {
        if (lista.isEmpty()) {
            System.out.println("No hay productos en esta categoría.");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            Producto p = lista.get(i);
            System.out.println(i + ". " + p.getNombre() + " ($" + p.getPrecio() + ")");
        }
        
        System.out.print("Seleccione el número del producto para agregar (o -1 para volver): ");
        int sel = lector.nextInt();
        lector.nextLine();

        if (sel >= 0 && sel < lista.size()) {
            carrito.add(lista.get(sel));
            System.out.println("✅ " + lista.get(sel).getNombre() + " añadido al carrito.");
        }
    }
    
    private void afiliarAmigo() {
        System.out.println("\n--- AFILIACIÓN DE AMIGO DE EMPLEADO ---");
        
        // 1. Autenticación del Empleado
        System.out.print("Login del Empleado: ");
        String loginEmp = lector.nextLine();
        System.out.print("Contraseña del Empleado: ");
        String passEmp = lector.nextLine();

        Usuario auth = buscarUsuario(loginEmp);

        // Validamos que el usuario exista, sea un Empleado y la contraseña coincida
        if (auth instanceof Empleado && auth.getPassword().equals(passEmp)) {
            Empleado empleadoActivo = (Empleado) auth;
            
            // 2. Búsqueda del Cliente
            System.out.print("Ingrese el login del Cliente a afiliar: ");
            String loginCli = lector.nextLine();
            Usuario buscado = buscarUsuario(loginCli);
            
            Cliente clienteAAfiliar = null;

            if (buscado instanceof Cliente) {
                clienteAAfiliar = (Cliente) buscado;
            } else {
                System.out.println("El cliente no existe. Iniciando registro...");
                registrarUsuarioNuevo(); 
                // Después de registrar, intentamos recuperarlo (sería el último de la lista)
                List<Cliente> clientes = miCafe.getClientes();
                if (!clientes.isEmpty()) {
                    clienteAAfiliar = clientes.get(clientes.size() - 1);
                }
            }

            // 3. Registro de la amistad
            if (clienteAAfiliar != null) {
                // Agregamos el cliente a la lista del empleado
                empleadoActivo.agregarAmigos(clienteAAfiliar);
                
                // Cambiamos el atributo booleano del cliente a true
                clienteAAfiliar.nuevoAmigo();
                
                System.out.println("\u001B[32m" + "¡Éxito! " + clienteAAfiliar.getNombre() + 
                                   " ahora es amigo de " + empleadoActivo.getNombre() + 
                                   ". Ahora recibirá descuentos en sus compras." + "\u001B[0m");
            }

        } else {
            System.out.println("❌ Error de autenticación: Login o contraseña incorrectos, o el usuario no es un empleado.");
        }
    }
    
    
    public void hacerReserva() {
        System.out.println("\n---  PROCESO DE RESERVA ---");
        System.out.print("¿Para cuántas personas es la reserva?: ");
        int numPersonas = lector.nextInt();
        lector.nextLine(); 

        List<Cliente> listaClientesReserva = new ArrayList<>();

        // 1. Registro/Búsqueda de cada integrante
        for (int i = 1; i <= numPersonas; i++) {
            System.out.print("Ingrese login del cliente " + i + " (o escriba 'nuevo' para registrarlo): ");
            String entrada = lector.nextLine();
            
            Usuario u = buscarUsuario(entrada);
            
            if (entrada.equalsIgnoreCase("nuevo") || u == null || !(u instanceof Cliente)) {
                System.out.println("No se encontró el cliente. Procediendo a registro obligatorio...");
                registrarUsuarioNuevo();
                u = miCafe.getClientes().get(miCafe.getClientes().size() - 1);
            }
            
            listaClientesReserva.add((Cliente) u);
        }

        Calendar fechaReserva = Calendar.getInstance();  
        Reserva nuevaReserva = new Reserva(listaClientesReserva, numPersonas, fechaReserva);
        int totalAntes = miCafe.getReservasPrevias().size();
        
        miCafe.registrarNuevaReserva(nuevaReserva);

        // 5. Verificación de éxito
        if (miCafe.getReservasPrevias().size() > totalAntes) {
            System.out.println("\u001B[32m" + "✅ ¡Reserva Exitosa!" + "\u001B[0m");
            System.out.println("Mesa asignada: " + nuevaReserva.getMesa().getNumSillas());
            System.out.println("Total de reservas actuales en el café: " + miCafe.getReservasPrevias().size());
        } else {
            System.out.println("❌ No se pudo completar la reserva. Verifique disponibilidad de capacidad o mesas.");
            System.out.println("Total de reservas actuales en el café: " + miCafe.getReservasPrevias().size());
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
            System.out.println("6. Afiliar un Amigo");
            System.out.println("7. Hacer una Reserva");
            System.out.println("8. Salir");
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
                        consola.ingresarJuegoFav();
                        break;
                    case 5:
                        consola.simularCompra();
                        break;
                    case 6:
                        consola.afiliarAmigo();
                        break;
                    case 7:
                    	consola.hacerReserva();
                        break;
                    case 8:
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