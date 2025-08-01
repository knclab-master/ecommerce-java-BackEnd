package org.KnC.Lab.controller;

import org.KnC.Lab.model.Pedido;
import org.KnC.Lab.service.PedidoService;
import org.KnC.Lab.service.PedidoService.LineaPedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    // Crear un pedido
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody List<LineaPedidoDTO> lineas) {
        Pedido creado = pedidoService.crearPedido(lineas);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // Listar todos los pedidos
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    // Obtener un pedido por ID
    @GetMapping("/{id}")
    public Pedido obtenerPedido(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id);
    }
}