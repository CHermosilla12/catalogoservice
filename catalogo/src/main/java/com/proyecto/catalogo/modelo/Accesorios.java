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
public class Accesorios extends Producto {

    @NotBlank(message = "El tipo de accesorio es obligatorio")
    private String tipo; // Tipo de accesorio (por ejemplo, "Funda para cartas", "Caja de almacenamiento", etc.)
    @NotBlank(message = "La marca del accesorio es obligatoria")
    private String marca; // Marca del accesorio (por ejemplo, "Ultra Pro", "Dragon Shield", etc.)}
    @NotBlank(message = "La franquicia del accesorio es obligatoria")
    private String franquicia; // Franquicia a la que pertenece el accesorio (por ejemplo, "Pokémon", "Yu-Gi-Oh!", etc.)
}
