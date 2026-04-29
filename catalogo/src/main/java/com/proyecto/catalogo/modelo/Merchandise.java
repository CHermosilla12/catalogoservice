package com.proyecto.catalogo.modelo;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Merchandise extends Producto {

    @NotBlank(message = "El tipo de merchandise es obligatorio")
    private String tipo; // Tipo de merchandise (por ejemplo, "Figura de acción", "Camiseta", etc.)
    @NotBlank(message = "La marca del merchandise es obligatoria")
    private String marca; // Marca del merchandise (por ejemplo, "Funko", "Bandai", etc.)
    @NotBlank(message = "El material del merchandise es obligatorio")
    private String material; // Material del merchandise (por ejemplo, "Plástico", "Algodón", etc.)
}
