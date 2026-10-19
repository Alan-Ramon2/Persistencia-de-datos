/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectopersistenciaobjetos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author Alan
 */
public class ProyectoPersistenciaObjetos {

       public static void main(String[] args) {
        // El nombre debe coincidir EXACTAMENTE con el que pusiste en el persistence.xml
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPersistenciaObjetosPU")) {
            try (EntityManager manager = emf.createEntityManager()) {
                manager.getTransaction().begin();
                
                // CREAMOS UN PRODUCTO DE INDURAMA
                Producto pr1 = new Producto("Televisor Indurama 55\"", 10, 1040.1);
                
                // Guardamos el producto en la base de datos
                manager.persist(pr1); 
                
                // Hacemos commit para confirmar la transacción
                manager.getTransaction().commit();
                System.out.println("¡Producto de Indurama guardado exitosamente!");
                
            } catch (Exception e) {
                System.err.println("Error en la transacción: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Error al crear el EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
