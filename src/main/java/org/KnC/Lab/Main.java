package org.KnC.Lab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);






        boolean continuar = true;

        while (continuar) {
            clear(30, false);
            System.out.println("╔════════════════════════════╗");
            System.out.println("║         E-COMMERCE         ║");
            System.out.println("║                            ║");
            System.out.println("║       (Menú principal)     ║");
            System.out.println("╚════════════════════════════╝\n");
            System.out.println("0) Precargar productos y pedido DEMO\n────────────────────────────────────");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear un pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            System.out.println("────────────────────────────────────");
            System.out.print("Elija una opción: ");

            String opcion = scanner.nextLine();
            clear(30, false);
            switch (opcion) {
                case "0":
                    // Precargar productos para prueba
                    productoService.agregarProducto("Café Premium", 2200.0, 19);
                    productoService.agregarProducto("Coca-Cola 2.25L", 3999.0, 32);
                    productoService.agregarProducto("Yerba Mate 1Kg", 1450.0, 12);
                    productoService.agregarProducto("Leche Entera 1L", 1310.0, 23);
                    // Precargar pedido
                    pedidoService.agregarPedidoDePrueba();
                    break;
                case "1":
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Precio: ");
                    double precio;
                    try {
                        precio = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Precio inválido.");
                        break;
                    }

                    System.out.print("Stock: ");
                    int stock;
                    try {
                        stock = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Stock inválido.");
                        break;
                    }

                    productoService.agregarProducto(nombre, precio, stock);
                    break;

                case "2":
                    productoService.listarProductos();
                    break;

                case "3":
                    System.out.print("Ingrese ID del producto a buscar: ");
                    int idBusqueda;
                    try {
                        idBusqueda = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido.");
                        break;
                    }

                    Producto encontrado = productoService.buscarPorId(idBusqueda);
                    if (encontrado != null) {
                        System.out.println("Producto encontrado:");
                        System.out.println(encontrado.mostrarInfo());

                        System.out.print("¿Desea actualizarlo? (s/n): ");
                        String respuesta = scanner.nextLine();
                        if (respuesta.equalsIgnoreCase("s")) {

                            double nuevoPrecio;
                            int nuevoStock;

                            try {
                                System.out.print("Nuevo precio: ");
                                nuevoPrecio = Double.parseDouble(scanner.nextLine());

                                System.out.print("Nuevo stock: ");
                                nuevoStock = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Dato inválido. No se realizaron cambios.");
                                break;
                            }

                            productoService.actualizarProducto(idBusqueda, nuevoPrecio, nuevoStock);
                        }
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;


                case "4":
                    System.out.print("Ingrese ID del producto a eliminar: ");
                    int idEliminar;
                    try {
                        idEliminar = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido.");
                        break;
                    }
                    productoService.eliminarProducto(idEliminar);
                    break;

                case "5":
                    pedidoService.crearPedido(scanner);
                    break;

                case "6":
                    pedidoService.listarPedidos();
                    break;

                case "7":
                    continuar = false;
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
            if (continuar==true){
                clear(30,true);
            }else {
                cerrar();
            }
        }

        scanner.close();
    }

    //  Funcion para limpiar pantalla dando saltos de líneas.
    public static void clear (int lineas, boolean timeout) {
        Scanner sca = new Scanner(System.in);
        if (timeout){
            System.out.print("\n\n\n«« Presione ENTER para volver al menú inicial »» ");
            sca.hasNextLine();
            sca.nextLine();
        }
        for(int i=0;i<=lineas;i++){
            System.out.println("");
        }
    }

    public static void cerrar () {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\n\n\n«« Presione ENTER para SALIR »» ");
        String accion = sc.nextLine();
        System.exit(0);
        sc.close();
    }
}
