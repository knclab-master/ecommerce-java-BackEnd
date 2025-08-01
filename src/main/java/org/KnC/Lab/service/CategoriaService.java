package org.KnC.Lab.service;

import org.KnC.Lab.model.Categoria;
import org.KnC.Lab.repository.CategoriaRepository;
import org.KnC.Lab.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
    }

    public Categoria crear(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Long id, Categoria categoriaActualizada) {
        Categoria existente = obtenerPorId(id);
        existente.setNombre(categoriaActualizada.getNombre());
        existente.setDescripcion(categoriaActualizada.getDescripcion());
        return categoriaRepository.save(existente);
    }

    public void eliminar(Long id) {
        Categoria existente = obtenerPorId(id);
        categoriaRepository.delete(existente);
    }
}