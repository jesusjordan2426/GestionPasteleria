package com.mycompany.gestionpasteleria;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que representa un producto en la pastelería.
 * Contiene información sobre el nombre, precio y cantidad disponible del producto.
 */
class Producto {
    private String nombre;
    private double precio;
    private int cantidadDisponible;

    /**
     * Constructor de la clase Producto.
     *
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param cantidadDisponible Cantidad inicial disponible del producto.
     */
    public Producto(String nombre, double precio, int cantidadDisponible) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * Devuelve el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el precio del producto.
     *
     * @return Precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve la cantidad disponible del producto.
     *
     * @return Cantidad disponible del producto.
     */
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * Reduce la cantidad disponible del producto al vender una cantidad específica.
     * Verifica si hay suficiente stock antes de realizar la venta.
     *
     * @param cantidad Cantidad a vender.
     */
    public void venderProducto(int cantidad) {
        if (cantidad <= cantidadDisponible) {
            cantidadDisponible -= cantidad;
            System.out.println("Producto vendido: " + nombre + " x" + cantidad);
        } else {
            System.out.println("No hay suficiente cantidad disponible de " + nombre);
        }
    }

    /**
     * Devuelve una representación en forma de cadena del producto.
     *
     * @return Cadena con información del producto.
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Precio: $" + precio + ", Cantidad Disponible: " + cantidadDisponible;
    }
}

/**
 * Clase principal para gestionar el inventario de una pastelería.
 * Permite agregar, mostrar y vender productos del inventario.
 */
public class GestionPasteleria {
    private ArrayList<Producto> inventario;

    /**
     * Constructor de la clase GestionPasteleria.
     * Inicializa una lista vacía para el inventario.
     */
    public GestionPasteleria() {
        inventario = new ArrayList<>();
    }

    /**
     * Agrega un nuevo producto al inventario.
     *
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param cantidadDisponible Cantidad inicial del producto.
     */
    public void agregarProducto(String nombre, double precio, int cantidadDisponible) {
        Producto nuevoProducto = new Producto(nombre, precio, cantidadDisponible);
        inventario.add(nuevoProducto);
        System.out.println("Producto agregado: " + nuevoProducto);
    }

    /**
     * Muestra todos los productos en el inventario.
     * Si el inventario está vacío, notifica al usuario.
     */
    public void mostrarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario de la pastelería:");
            for (Producto producto : inventario) {
                System.out.println(producto);
            }
        }
    }

    /**
     * Busca un producto en el inventario por su nombre.
     *
     * @param nombre Nombre del producto a buscar.
     * @return El producto encontrado, o null si no existe.
     */
    private Producto buscarProducto(String nombre) {
        for (Producto producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto; // Producto encontrado
            }
        }
        return null; // Producto no encontrado
    }

    /**
     * Vende una cantidad específica de un producto.
     * Utiliza el método buscarProducto para encontrarlo.
     *
     * @param nombre Nombre del producto a vender.
     * @param cantidad Cantidad a vender.
     */
    public void venderProducto(String nombre, int cantidad) {
        Producto producto = buscarProducto(nombre);
        if (producto != null) {
            producto.venderProducto(cantidad);
        } else {
            System.out.println("Producto no encontrado en el inventario.");
        }
    }

    /**
     * Método principal que ejecuta el menú interactivo de la aplicación.
     * Permite al usuario realizar operaciones como agregar, mostrar y vender productos.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        GestionPasteleria gestionPasteleria = new GestionPasteleria();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Mostrar menú de opciones
            System.out.println("\nMenu:");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario de la pastelería");
            System.out.println("3. Vender producto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después del número

            switch (opcion) {
                case 1: // Agregar producto
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingrese el precio del producto: $");
                    double precio = scanner.nextDouble();
                    if (precio <= 0) {
                        System.out.println("El precio debe ser mayor que 0.");
                        break;
                    }

                    System.out.print("Ingrese la cantidad disponible del producto: ");
                    int cantidad = scanner.nextInt();
                    if (cantidad < 0) {
                        System.out.println("La cantidad no puede ser negativa.");
                        break;
                    }

                    gestionPasteleria.agregarProducto(nombre, precio, cantidad);
                    break;

                case 2: // Mostrar inventario
                    gestionPasteleria.mostrarInventario();
                    break;

                case 3: // Vender producto
                    System.out.print("Ingrese el nombre del producto a vender: ");
                    String productoVender = scanner.nextLine();

                    System.out.print("Ingrese la cantidad a vender: ");
                    int cantidadVender = scanner.nextInt();
                    if (cantidadVender <= 0) {
                        System.out.println("La cantidad a vender debe ser mayor que 0.");
                        break;
                    }

                    gestionPasteleria.venderProducto(productoVender, cantidadVender);
                    break;

                case 4: // Salir
                    System.out.println("Saliendo del programa.");
                    System.exit(0);

                default: // Opción inválida
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
