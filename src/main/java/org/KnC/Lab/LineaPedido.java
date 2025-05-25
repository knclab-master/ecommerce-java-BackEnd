package org.KnC.Lab;

import static org.KnC.Lab.Main.clear;

public class LineaPedido {
    private Producto producto;
    private int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public String mostrarInfo() {
        return producto.getNombre() + " x " + cantidad + " Unid. @ $" + producto.getPrecio() + " = $" + getSubtotal();
    }
}
