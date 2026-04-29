package com.proyecto.catalogo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;
    
    @Max(value = 255, message = "La descripción del producto no puede exceder los 255 caracteres")
    private String descripcion;

    @Positive(message = "El precio del producto debe ser un valor positivo")
    @NotBlank(message = "El precio del producto es obligatorio")
    private Integer precio;
}