/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionpasteleria;

/**
 *
 * @author Usuario
 */
import java.util.ArrayList;
import java.util.Scanner;

class Producto {
    private String nombre;
    private double precio;
    private int cantidadDisponible;

    public Producto(String nombre, double precio, int cantidadDisponible) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void venderProducto(int cantidad) {
        if (cantidad <= cantidadDisponible) {
            cantidadDisponible -= cantidad;
            System.out.println("Producto vendido: " + nombre + " x" + cantidad);
        } else {
            System.out.println("No hay suficiente cantidad disponible de " + nombre);
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Precio: $" + precio + ", Cantidad Disponible: " + cantidadDisponible;
    }
}

public class GestionPasteleria {
    private ArrayList<Producto> inventario;

    public GestionPasteleria() {
        inventario = new ArrayList<>();
    }

    public void agregarProducto(String nombre, double precio, int cantidadDisponible) {
        Producto nuevoProducto = new Producto(nombre, precio, cantidadDisponible);
        inventario.add(nuevoProducto);
        System.out.println("Producto agregado: " + nuevoProducto);
    }

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

    public void venderProducto(String nombre, int cantidad) {
        boolean encontrado = false;
        for (Producto producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                producto.venderProducto(cantidad);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Producto no encontrado en el inventario.");
        }
    }

    public static void main(String[] args) {
        GestionPasteleria gestionPasteleria = new GestionPasteleria();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario de la pastelería");
            System.out.println("3. Vender producto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después del número

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el precio del producto: $");
                    double precio = scanner.nextDouble();
                    System.out.print("Ingrese la cantidad disponible del producto: ");
                    int cantidad = scanner.nextInt();
                    gestionPasteleria.agregarProducto(nombre, precio, cantidad);
                    break;
                case 2:
                    gestionPasteleria.mostrarInventario();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del producto a vender: ");
                    String productoVender = scanner.nextLine();
                    System.out.print("Ingrese la cantidad a vender: ");
                    int cantidadVender = scanner.nextInt();
                    gestionPasteleria.venderProducto(productoVender, cantidadVender);
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}