package com.proyecto.catalogo.service;
import com.proyecto.catalogo.repository.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.proyecto.catalogo.dto.ProductoCreateDTO;
import com.proyecto.catalogo.dto.ProductoDTO;
import com.proyecto.catalogo.modelo.*;

@Service
public class ServiceProducto {
    private final RepositoryProducto repositoryProducto;

    public ServiceProducto(RepositoryProducto repositoryProducto) {
        this.repositoryProducto = repositoryProducto;
    }
    
    public Producto CrearProducto(Producto producto){
        return repositoryProducto.save(producto);
    }

    public ProductoDTO findDtoById(Long id) {
        Producto producto = repositoryProducto.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio());
    }

    public ProductoDTO crearProductoDTO(ProductoCreateDTO productoCreateDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoCreateDTO.getNombre());
        producto.setDescripcion(productoCreateDTO.getDescripcion());
        producto.setPrecio(productoCreateDTO.getPrecio());
        Producto nuevoProducto = repositoryProducto.save(producto);
        return new ProductoDTO(nuevoProducto.getId(), nuevoProducto.getNombre(), nuevoProducto.getDescripcion(), nuevoProducto.getPrecio());
    }

    // Método para validar manualmente un producto antes de guardarlo
    public List<String> ValidacionManualProducto(Producto producto) {
        List<String> errores = new java.util.ArrayList<>();
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            errores.add("El nombre del producto es obligatorio.");
        }
        if (producto.getPrecio() == null) {
            errores.add("El precio del producto es obligatorio.");
        } else if (producto.getPrecio() <= 0) {
            errores.add("El precio del producto debe ser un valor positivo.");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().length() > 500 || producto.getDescripcion().isEmpty()) {
            errores.add("La descripción del producto es obligatoria y no puede exceder los 500 caracteres.");
        }
        return errores;
    }
    // Métodos para obtener Todos los productos o específicos
    public List<Producto> obtenerTodosLosProductos() {
        return repositoryProducto.findAll();
    }

    public Optional<Producto> obtenerProductoPorID(Long id){
        return repositoryProducto.findById(id);
    }

    // Métodos para eliminar productos por su ID o nombre
    public boolean eliminarProducto(Long id) {
        if (repositoryProducto.existsById(id)){
            repositoryProducto.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Optional<Producto> productoExistente = repositoryProducto.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
            return repositoryProducto.save(producto);
        } else {
            return null;
        }
    }

}
