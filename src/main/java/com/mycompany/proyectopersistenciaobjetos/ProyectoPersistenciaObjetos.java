/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectopersistenciaobjetos;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProyectoPersistenciaObjetos {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPersistenciaObjetosPU")) {
            try (EntityManager em = emf.createEntityManager()) {
                
                // --- 1. POBLAR DATOS (Para que las consultas funcionen) ---
                em.getTransaction().begin();
                
                Categoria catBlanca = new Categoria("Línea Blanca");
                Categoria catTV = new Categoria("Televisores");
                Proveedor prov1 = new Proveedor("Mabe Ecuador");
                Distribuidor dist1 = new Distribuidor("Distribuidora Cuenca");
                
                em.persist(catBlanca);
                em.persist(catTV);
                em.persist(prov1);
                em.persist(dist1);
                
                Producto p1 = new Producto("Refrigeradora Indurama 300L", new BigDecimal("850.00"), catBlanca, prov1);
                Producto p2 = new Producto("Cocina Indurama 4 Hornillas", new BigDecimal("620.00"), catBlanca, prov1);
                Producto p3 = new Producto("Televisor Indurama 55\"", new BigDecimal("1040.00"), catTV, null); // Sin proveedor
                
                em.persist(p1);
                em.persist(p2);
                em.persist(p3);
                
                // Agregamos un mantenimiento
                Mantenimiento m1 = new Mantenimiento("Cambio de pantalla", LocalDate.now(), p3);
                em.persist(m1);
                
                em.getTransaction().commit();

                // --- 2. CONSULTAS JPQL (UNIDAD 3) ---

                // CONSULTA 1: Filtro por categoría y ordenamiento por precio
                System.out.println("\n--- Consulta 1: Productos Línea Blanca (Ordenados por precio) ---");
                String jpql1 = "SELECT p FROM Producto p WHERE p.categoria.descripcion = :cat ORDER BY p.precio DESC";
                List<Producto> lista1 = em.createQuery(jpql1, Producto.class)
                                          .setParameter("cat", "Línea Blanca")
                                          .getResultList();
                for (Producto p : lista1) {
                    System.out.println(p.getNombre() + " - $" + p.getPrecio());
                }

                // CONSULTA 2: Paginación (Primeros 2 productos)
                System.out.println("\n--- Consulta 2: Paginación (Primeros 2 productos activos) ---");
                String jpql2 = "SELECT p FROM Producto p WHERE p.activo = true ORDER BY p.nombre ASC";
                List<Producto> lista2 = em.createQuery(jpql2, Producto.class)
                                          .setFirstResult(0) // Desde el registro 0
                                          .setMaxResults(2)  // Traer solo 2
                                          .getResultList();
                for (Producto p : lista2) {
                    System.out.println(p.getNombre());
                }

                // CONSULTA 3: LEFT JOIN FETCH (Productos con o sin proveedor)
                System.out.println("\n--- Consulta 3: Productos con Proveedores (LEFT JOIN) ---");
                String jpql3 = "SELECT p FROM Producto p LEFT JOIN FETCH p.proveedor";
                List<Producto> lista3 = em.createQuery(jpql3, Producto.class).getResultList();
                for (Producto p : lista3) {
                    String nombreProv = (p.getProveedor() != null) ? p.getProveedor().getNombre() : "Sin Proveedor";
                    System.out.println(p.getNombre() + " -> Proveedor: " + nombreProv);
                }

                System.out.println("\n¡Ejecución completada con éxito!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
