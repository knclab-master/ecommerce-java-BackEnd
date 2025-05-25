package org.KnC.Lab;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private List<Producto> productos;

    public ProductoService() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(String nombre, double precio, int stock) {
        if (precio < 0) {
            System.out.println("El precio no puede ser negativo.");
            return;
        }
        if (stock < 0) {
            System.out.println("El stock no puede ser negativo.");
            return;
        }

        Producto nuevo = new Producto(nombre, precio, stock);
        productos.add(nuevo);
        System.out.println("Producto agregado con éxito. ID asignado: " + nuevo.getId());
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("Listado de productos:");
        for (Producto p : productos) {
            System.out.println("ID: " + p.getId());
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Precio: $" + p.getPrecio());
            System.out.println("Stock disponible: " + p.getStock());
            System.out.println("------------------------");
        }
    }

    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Producto buscarPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public boolean actualizarProducto(int id, double nuevoPrecio, int nuevoStock) {
        Producto p = buscarPorId(id);
        if (p != null) {
            if (nuevoStock < 0) {
                System.out.println("El stock no puede ser negativo.");
                return false;
            }
            p.setPrecio(nuevoPrecio);
            p.setStock(nuevoStock);
            System.out.println("Producto actualizado con éxito.");
            return true;
        } else {
            System.out.println("Producto no encontrado.");
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        Producto p = buscarPorId(id);
        if (p != null) {
            productos.remove(p);
            System.out.println("Producto eliminado correctamente.");
            return true;
        } else {
            System.out.println("Producto no encontrado.");
            return false;
        }
    }

    public List<Producto> getListaProductos() {
        return productos;
    }
}