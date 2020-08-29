/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.*;

/**
 *
 * @author Joakim
 */
public class EntityTested {

    public static void main(String[] args) {
        Customer cust1 = new Customer("Ulrik", "Jensen");
        Customer cust2 = new Customer("Ole", "Henriksen");
        Customer cust3 = new Customer("Uffe", "Holm");
        Customer cust4 = new Customer("Søren", "Pape");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(cust1);
        em.persist(cust2);
        em.persist(cust3);
        em.persist(cust4);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }
}
