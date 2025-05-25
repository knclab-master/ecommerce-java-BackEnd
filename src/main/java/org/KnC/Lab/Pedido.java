package org.KnC.Lab;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 1;
    private int id;
    private List<LineaPedido> lineas;

    public Pedido() {
        this.id = contador++;
        this.lineas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void agregarLinea(LineaPedido linea) {
        // Validar que haya suficiente stock
        if (linea.getCantidad() > linea.getProducto().getStock()) {
            System.out.println("No hay suficiente stock para " + linea.getProducto().getNombre() + ". La línea no se añadirá al pedido.");
            return; // No se agrega la línea al pedido si no hay suficiente stock
        }

        // Reducir el stock del producto
        linea.getProducto().setStock(linea.getProducto().getStock() - linea.getCantidad());

        // Agregar la línea al pedido
        lineas.add(linea);
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public String mostrarInfo() {
        StringBuilder sb = new StringBuilder("Pedido #" + id + "\n");
        for (LineaPedido linea : lineas) {
            sb.append("- ").append(linea.mostrarInfo()).append("\n");
        }
        sb.append("Total: $").append(calcularTotal());
        return sb.toString();
    }
}