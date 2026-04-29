package com.proyecto.catalogo.service;
import com.proyecto.catalogo.repository.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.proyecto.catalogo.modelo.*;

@Service
public class ServiceCarta {
    private final RepositoryCarta repositoryCarta;

    public ServiceCarta(RepositoryCarta repositoryCarta) {
        this.repositoryCarta = repositoryCarta;
    }
    
    public Producto CrearCarta(Carta carta){
        return repositoryCarta.save(carta);
    }

    // Método para validar manualmente un producto antes de guardarlo
    public List<String> ValidacionManualCarta(Carta carta) {
        List<String> errores = new java.util.ArrayList<>();
        if (carta.getNombre() == null || carta.getNombre().isEmpty()) {
            errores.add("El nombre del producto es obligatorio.");
        }
        if (carta.getPrecio() <= 0 ) {
            errores.add("El precio del producto es obligatorio y debe ser un valor positivo.");
        }
        if (carta.getDescripcion() == null || carta.getDescripcion().isEmpty()) {
            errores.add("La descripción del producto es obligatoria.");
        }
        return errores;
    }
    // Métodos para obtener Todos los productos o específicos
    public List<Producto> obtenerTodasLasCartas() {
        return repositoryCarta.findAll();
    }

    public Optional<Producto> obtenerCartaPorID(Long id){
        return repositoryCarta.findById(id);
    }

    // Métodos para eliminar productos por su ID o nombre
    public boolean eliminarCarta(Long id) {
        if (repositoryCarta.existsById(id)){
            repositoryCarta.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public Carta actualizarCarta(Long id, Carta cartaActualizado) {
        Optional<Producto> cartaExistente = repositoryCarta.findById(id);
        if (cartaExistente.isPresent()) {
            Carta cartaNueva = (Carta) cartaExistente.get();
            cartaNueva.setNombre(cartaActualizado.getNombre());
            cartaNueva.setPrecio(cartaActualizado.getPrecio());
            return repositoryCarta.save(cartaNueva);
        } else {
            return null;
        }
    }

}