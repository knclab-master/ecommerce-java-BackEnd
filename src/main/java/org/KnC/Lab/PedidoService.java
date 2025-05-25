package org.KnC.Lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {
    private List<Pedido> pedidos;
    private ProductoService productoService;

    public PedidoService(ProductoService productoService) {
        this.productoService = productoService;
        this.pedidos = new ArrayList<>();
    }

    public void agregarPedidoDePrueba() {
        Producto p1 = productoService.buscarPorId(1); // Café Premium
        Producto p2 = productoService.buscarPorId(2); // Coca-Cola 2.25L

        Pedido pedido = new Pedido();
        pedido.agregarLinea(new LineaPedido(p1, 2));
        p1.setStock(p1.getStock() - 2); // Descontar stock
        pedido.agregarLinea(new LineaPedido(p2, 1));
        p2.setStock(p2.getStock() - 1); // Descontar stock
        pedidos.add(pedido);
        System.out.println("Pedido de prueba precargado con éxito.");
    }


    public void crearPedido(Scanner scanner) {
        Pedido nuevoPedido = new Pedido();
        System.out.print("¿Cuántos productos desea agregar al pedido? ");
        int cantidadItems;

        try {
            cantidadItems = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida.");
            return;
        }

        for (int i = 0; i < cantidadItems; i++) {
            System.out.print("Ingrese ID del producto #" + (i + 1) + ": ");
            int id;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID inválido.");
                continue;
            }

            Producto producto = productoService.buscarPorId(id);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad deseada: ");
            int cantidad;
            try {
                cantidad = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Cantidad inválida.");
                continue;
            }

            if (cantidad <= 0) {
                System.out.println("Cantidad debe ser mayor a 0.");
                continue;
            }

            if (cantidad > producto.getStock()) {
                System.out.println("Stock insuficiente para el producto: " + producto.getNombre());
                continue;
            }

            producto.setStock(producto.getStock() - cantidad); // descontar stock
            LineaPedido linea = new LineaPedido(producto, cantidad);
            nuevoPedido.agregarLinea(linea);
        }

        if (nuevoPedido.getLineas().isEmpty()) {
            System.out.println("No se creó el pedido porque no se agregaron productos válidos.");
            return;
        }

        pedidos.add(nuevoPedido);
        System.out.println("Pedido creado con éxito. Total: $" + nuevoPedido.calcularTotal());
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.println(p.mostrarInfo());
            System.out.println("------------------------");
        }
    }
}
