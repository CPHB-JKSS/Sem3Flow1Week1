/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;
import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 *
 * @author oerte
 */
public class CustomerFacade {
    // Creates new EntityManagerfactory
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;
    public CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT c from Customer c where c.lastName = ?1", Customer.class);
            return query.setParameter(1, name).getResultList();
        } finally {
            em.close();
        }
    }

    public long getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q1 = em.createQuery("SELECT COUNT(c) FROM Customer c");
            return (long) q1.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("select c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Customer addCustomer(String fName, String lName) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer c = new Customer(fName, lName);
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

    public int deleteCustomerById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery(
                    "delete from Customer c where c.id = ?1");
            return query.setParameter(1, id).executeUpdate();
        } finally {
            em.close();
        }
    }

    public Customer deleteCustomerById_2(int id) {
        EntityManager em = emf.createEntityManager();
        Customer entity = em.find(Customer.class,id);
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            return entity;
        }finally{
            em.close();
        }
    }
}