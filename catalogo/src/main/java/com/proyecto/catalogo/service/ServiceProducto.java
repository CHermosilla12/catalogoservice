package com.proyecto.catalogo.service;
import com.proyecto.catalogo.repository.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.catalogo.dto.ProductoCreateDTO;
import com.proyecto.catalogo.dto.ProductoDTO;
import com.proyecto.catalogo.modelo.*;
import com.proyecto.catalogo.exception.*;
@Service
public class ServiceProducto {
    
    private static final Logger log = LoggerFactory.getLogger(ServiceProducto.class);

    @Autowired
    private RepositoryProducto repositoryProducto;

    public ServiceProducto(RepositoryProducto repositoryProducto) {
        this.repositoryProducto = repositoryProducto;
    }

    public ProductoDTO findDtoById(Long id) {
        log.info("Buscando producto id={}", id);
        Producto prod = repositoryProducto.findById(id)
        .orElseThrow(() -> new NoEncontradoException("Producto no encontrado con id: " + id));
        log.info("Producto encontrado: nombre={}, precio={}", prod.getNombre(), prod.getPrecio());
        return toDTO(prod);
    }

    public ProductoDTO crearProductoDTO(ProductoCreateDTO producto) {
        log.info("Creando producto nombre={}", producto.getNombre());
        Producto p = new Producto();
        p.setNombre(producto.getNombre());
        p.setDescripcion(producto.getDescripcion());
        p.setPrecio(producto.getPrecio());
        Producto guardado = repositoryProducto.save(p);
        log.info("Producto creado id={}", guardado.getId());
        return toDTO(guardado);
    }

    private ProductoDTO toDTO(Producto prod){
        return new ProductoDTO(prod.getId(),prod.getNombre(),prod.getDescripcion(),prod.getPrecio());
    }
    // Método para validar manualmente un producto antes de guardarlo
    public List<String> ValidacionManualProductoDTO(Producto producto) {
        List<String> errores = new java.util.ArrayList<>();
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            errores.add("El nombre del producto es obligatorio.");
            log.warn("Validación fallida: nombre del producto es obligatorio.");
        }
        if (producto.getPrecio() == null) {
            errores.add("El precio del producto es obligatorio.");
            log.warn("Validación fallida: precio del producto es obligatorio.");
        } else if (producto.getPrecio() <= 0) {
            errores.add("El precio del producto debe ser un valor positivo.");
            log.warn("Validación fallida: precio del producto debe ser positivo.");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().length() > 500 || producto.getDescripcion().isEmpty()) {
            errores.add("La descripción del producto es obligatoria y no puede exceder los 500 caracteres.");
            log.warn("Validación fallida: descripción del producto es obligatoria y no puede exceder los 500 caracteres.");
        }
        return errores;
    }
    // Métodos para obtener Todos los productos o específicos
    //public List<Producto> obtenerTodosLosProductos() {
    //    return repositoryProducto.findAll();
    //}

    public List<ProductoDTO> findAllDTO(){
    log.info("Catalogo de productos: ");
      return repositoryProducto.findAll().stream()
        .map(this::toDTO)
        .toList();
    }

    //public Optional<Producto> obtenerProductoPorID(Long id){
    //    return repositoryProducto.findById(id);
    //}

    // Métodos para eliminar productos por su ID o nombre
    public boolean eliminarProducto(Long id) {
        if (repositoryProducto.existsById(id)){
            repositoryProducto.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public ProductoDTO ActualizarProductoDTO(Long id, ProductoCreateDTO dto) {
        log.info("Actualizando producto id={}", id);
        Producto productoExistente = repositoryProducto.findById(id)
                .orElseThrow(() -> new NoEncontradoException("Producto no encontrado con id: " + id));
        productoExistente.setNombre(dto.getNombre());
        productoExistente.setDescripcion(dto.getDescripcion());
        productoExistente.setPrecio(dto.getPrecio());
        log.info("Producto actualizado id={}", productoExistente.getId());
        return toDTO(repositoryProducto.save(productoExistente));
    }

}
