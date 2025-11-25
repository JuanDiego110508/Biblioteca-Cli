package main.java;

import java.time.LocalDate;
import java.util.Scanner;

import main.java.repository.LibroRepository;
import main.java.repository.SocioRepository;
import main.java.repository.PrestamoRepository;

import main.java.model.Libro;
import main.java.model.Socio;
import main.java.model.Prestamo;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibroRepository libroRepo = new LibroRepository();
    private static final SocioRepository socioRepo = new SocioRepository();
    private static final PrestamoRepository prestamoRepo = new PrestamoRepository();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("Biblioteca CLI - Menu Principal");
            System.out.println("1. Gestionar Libros");
            System.out.println("2. Gestionar Socios");
            System.out.println("3. Gestionar Prestamos");
            System.out.println("0. Salir");
            System.out.println("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1: 
                    menuLibros(); break;
                case 2: 
                    menuSocios(); break;
                case 3: 
                    menuPrestamos(); break;
                case 0: 
                    System.out.println("Saliendo..."); break;
                default: 
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    // Submenu Libros
    private static void menuLibros() {
        int opcion;

        do {
            System.out.println(" Gestion de Libros ");
            System.out.println("1. Registrar Libro ");
            System.out.println("2. Listar Libros ");
            System.out.println("3. Consultar Libro por ISBN ");
            System.out.println("4. Actualizar Libro ");
            System.out.println("5. Eliminar Libro ");
            System.out.println("0. Volver al Menu Principal ");
            System.out.println("Opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarLibro();
                    break;
                case 2:
                    libroRepo.listarLibros().forEach(System.out::println);
                    break;
                case 3:
                    consultarLibro();
                    break;
                case 4:
                    actualizarLibro();
                    break;
                case 5:
                    eliminarLibro();
                    break;
                case 0: break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private static void registrarLibro() {
    System.out.println("ISBN: ");
    String isbn = scanner.nextLine();

    System.out.println("Título: ");
    String titulo = scanner.nextLine();

    System.out.println("Autor ID: ");
    int autorId = scanner.nextInt();

    System.out.println("Año publicación: ");
    int año = scanner.nextInt();

    System.out.println("Cantidad total: ");
    int total = scanner.nextInt();

    System.out.println("Cantidad disponible: ");
    int disponible = scanner.nextInt();
    scanner.nextLine(); // limpiar buffer

    Libro libro = new Libro(isbn, titulo, autorId, año, total, disponible);
    libroRepo.agregarLibro(libro);
    System.out.println("Libro registrado.");
}

    private static void consultarLibro() {
        System.out.println("ISBN a buscar: ");
        String isbn = scanner.nextLine();
        Libro libro = libroRepo.obtenerLibro(isbn);
        
        System.out.println(libro != null ? libro : "No encontrado.");
    }

    private static void actualizarLibro() {
        System.out.println("ISBN del libro a actualizar: ");
        String isbn = scanner.nextLine();

        Libro libro = libroRepo.obtenerLibro(isbn);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        System.out.println("Nuevo titulo: ");
        libro.setTitulo(scanner.nextLine());

        System.out.println("Nuevo autor ID: ");
        libro.setAutorId(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Nuevo año: ");
        libro.setAñoPublicacion(scanner.nextInt());
        scanner.nextLine();

        libroRepo.actualizarLibro(libro);
        System.out.println("Libro actualizado.");
    }

    private static void eliminarLibro() {
        System.out.print("ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();
        libroRepo.eliminarLibro(isbn);
        System.out.println("Libro eliminado.");
    }

    //  Submenu socios

    private static void menuSocios() {
        int opcion;

        do {
            System.out.println("\n Gestión de Socios");
            System.out.println("1. Registrar socio");
            System.out.println("2. Listar socios");
            System.out.println("3. Buscar socio por ID");
            System.out.println("4. Actualizar socio");
            System.out.println("5. Eliminar socio");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion) {
                case 1: registrarSocio(); break;
                case 2: socioRepo.listarSocios().forEach(System.out::println); break;
                case 3: consultarSocio(); break;
                case 4: actualizarSocio(); break;
                case 5: eliminarSocio(); break;
                case 0: break;
                default: System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);
    }

    private static void registrarSocio() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("DNI: ");
        String dni = scanner.nextLine();

        System.out.println("Telefono: ");
        String telefono = scanner.nextLine();

        Socio socio = new Socio(0, nombre, apellido, dni, telefono);
        socioRepo.agregarSocio(socio);

        System.out.println("Socio registrado.");
    }

    private static void consultarSocio() {
        System.out.print("ID del socio: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Socio socio = socioRepo.obtenerSocio(id);
        System.out.println(socio != null ? socio : "No encontrado");
    }

    private static void actualizarSocio() {
        System.out.print("ID del socio a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Socio socio = socioRepo.obtenerSocio(id);

        if (socio == null) {
            System.out.println("Socio no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        socio.setNombre(scanner.nextLine());

        System.out.print("Nuevo Apellido: ");
        socio.setApellido(scanner.nextLine());

        System.out.print("Nuevo DNI: ");
        socio.setDni(scanner.nextLine());

        System.out.print("Nuevo teléfono: ");
        socio.setTelefono(scanner.nextLine());

        socioRepo.actualizarSocio(socio);

        System.out.println("Socio actualizado.");
    }

    private static void eliminarSocio() {
    System.out.print("ID socio a eliminar: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    socioRepo.eliminarSocio(id);
    System.out.println("Socio eliminado.");
}

    //     Submenu prestamos
    private static void menuPrestamos() {
        int opcion;

        do {
            System.out.println("\n--- Gestión de Préstamos ---");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Listar préstamos");
            System.out.println("3. Registrar devolución");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion) {
                case 1: registrarPrestamo(); break;
                case 2: prestamoRepo.listarPrestamos().forEach(System.out::println); break;
                case 3: registrarDevolucion(); break;
            }

        } while (opcion != 0);
    }

    private static void registrarPrestamo() {

    // --- INGRESAR SOCIO ---
    System.out.print("ID socio: ");
    int socioId = scanner.nextInt();
    scanner.nextLine();

    Socio socio = socioRepo.obtenerSocio(socioId);

    if (socio == null) {
        System.out.println(" El socio no existe.");
        return;
    }

    // --- INGRESAR LIBRO ---
    System.out.print("ISBN del libro: ");
    String isbn = scanner.nextLine();

    Libro libro = libroRepo.obtenerLibro(isbn);

    if (libro == null) {
        System.out.println(" El libro no existe.");
        return;
    }

    if (libro.getCantidadDisponible() <= 0) {
        System.out.println(" No hay ejemplares disponibles de este libro.");
        return;
    }

    // --- FECHAS ---
    System.out.print("Fecha préstamo (YYYY-MM-DD): ");
    LocalDate fechaPrestamo = LocalDate.parse(scanner.nextLine());

    System.out.print("Fecha devolución prevista (YYYY-MM-DD): ");
    LocalDate fechaPrevista = LocalDate.parse(scanner.nextLine());

    // --- CREAR OBJETO PRESTAMO ---
    Prestamo prestamo = new Prestamo(
        isbn,
        socioId,
        fechaPrestamo,
        fechaPrevista,
        null,
        "PRESTADO"
    );

    // --- GUARDAR ---
    prestamoRepo.agregarPrestamo(prestamo);

    // --- RESTAR DISPONIBILIDAD ---
    libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
    libroRepo.actualizarLibro(libro);

    System.out.println("✔ Préstamo registrado exitosamente.");
}

    private static void registrarDevolucion() {
        System.out.print("ID préstamo: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        LocalDate hoy = LocalDate.now();

        prestamoRepo.registrarDevolucion(id, hoy);
        System.out.println("Devolución registrada.");
    }
}

