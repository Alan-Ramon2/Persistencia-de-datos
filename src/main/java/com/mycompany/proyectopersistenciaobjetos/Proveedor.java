package com.mycompany.proyectopersistenciaobjetos;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tb_proveedor")
@Getter @Setter @NoArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos;

    public Proveedor(String nombre) {
        this.nombre = nombre;
    }
}
