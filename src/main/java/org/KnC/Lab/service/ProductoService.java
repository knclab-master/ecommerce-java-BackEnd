package org.KnC.Lab.service;

import org.KnC.Lab.model.Producto;
import org.KnC.Lab.model.Categoria;
import org.KnC.Lab.repository.ProductoRepository;
import org.KnC.Lab.repository.CategoriaRepository;
import org.KnC.Lab.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Producto guardar(Producto producto) {
        // Validar que la categoría exista (si tiene)
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + producto.getCategoria().getId()));
            producto.setCategoria(categoria);
        }
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, Producto productoActualizado) {
        Producto existente = obtenerPorId(id);
        existente.setNombre(productoActualizado.getNombre());
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setStock(productoActualizado.getStock());
        existente.setImagenUrl(productoActualizado.getImagenUrl());

        // Validar y setear categoría
        if (productoActualizado.getCategoria() != null && productoActualizado.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(productoActualizado.getCategoria().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + productoActualizado.getCategoria().getId()));
            existente.setCategoria(categoria);
        } else {
            existente.setCategoria(null);
        }
        return productoRepository.save(existente);
    }

    public void eliminar(Long id) {
        Producto existente = obtenerPorId(id);
        productoRepository.delete(existente);
    }
}