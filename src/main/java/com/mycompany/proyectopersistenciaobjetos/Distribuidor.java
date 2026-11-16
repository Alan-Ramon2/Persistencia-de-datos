package com.mycompany.proyectopersistenciaobjetos;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tb_distribuidor")
@Getter @Setter @NoArgsConstructor
public class Distribuidor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "distribuidores")
    private List<Producto> productos;

    public Distribuidor(String nombre) {
        this.nombre = nombre;
    }
}
