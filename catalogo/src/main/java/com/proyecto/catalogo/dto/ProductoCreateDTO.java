package com.proyecto.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCreateDTO {
    private String nombre;
    private String descripcion;
    private Integer precio;
}
