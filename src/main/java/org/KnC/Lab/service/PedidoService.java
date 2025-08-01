package org.KnC.Lab.service;

import org.KnC.Lab.model.Pedido;
import org.KnC.Lab.model.LineaPedido;
import org.KnC.Lab.model.Producto;
import org.KnC.Lab.repository.PedidoRepository;
import org.KnC.Lab.repository.LineaPedidoRepository;
import org.KnC.Lab.repository.ProductoRepository;
import org.KnC.Lab.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private LineaPedidoRepository lineaPedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Pedido crearPedido(List<LineaPedidoDTO> lineasDto) {
        List<LineaPedido> lineas = new ArrayList<>();
        double total = 0.0;

        // 1. Validar stock y construir líneas
        for (LineaPedidoDTO dto : lineasDto) {
            Producto prod = productoRepository.findById(dto.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + dto.getProductoId()));

            if (prod.getStock() < dto.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + prod.getNombre());
            }
            // Calcular subtotal y actualizar stock de producto
            double subtotal = prod.getPrecio() * dto.getCantidad();
            LineaPedido linea = new LineaPedido(prod, null, dto.getCantidad(), subtotal);
            lineas.add(linea);
            total += subtotal;

            prod.setStock(prod.getStock() - dto.getCantidad());
            productoRepository.save(prod);
        }

        // 2. Crear Pedido y asociar líneas
        Pedido pedido = new Pedido();
        pedido.setTotal(total);

        // Asocia pedido a cada línea y salva todo en cascada
        for (LineaPedido linea : lineas) {
            linea.setPedido(pedido);
        }
        pedido.setLineas(lineas);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + id));
    }

    // DTO para recibir datos del pedido desde el controller
    public static class LineaPedidoDTO {
        private Long productoId;
        private Integer cantidad;

        // Constructors
        public LineaPedidoDTO() {}

        public LineaPedidoDTO(Long productoId, Integer cantidad) {
            this.productoId = productoId;
            this.cantidad = cantidad;
        }

        // Getters y setters
        public Long getProductoId() { return productoId; }
        public void setProductoId(Long productoId) { this.productoId = productoId; }

        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    }
}