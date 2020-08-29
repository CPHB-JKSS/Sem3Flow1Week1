/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;
import entity.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author oerte
 */
public class Facade {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade cf = new CustomerFacade().getCustomerFacade(emf);
        // Find a customer by ID
        Customer c = cf.findByID(1);
        System.out.println(c.getFirstName());
        // Find a customer by name
        List<Customer> nameList = cf.findByLastName("Oertel");
        nameList.forEach(cl -> {
            System.out.println(cl.toString());
        });
        // Returns the count of cutomers in the database
        int count = (int) cf.getNumberOfCustomers();
        System.out.println(count);
        // Get all the Customer from the database
        List<Customer> allCustomer = cf.allCustomers();
        allCustomer.forEach(cl -> {
            System.out.println(cl);
        });
        // Add an Customer
        Customer c1 = cf.addCustomer("Richard", "Dunmur");
        System.out.println("Added Customer: " + c1);
//        // Delete an Customer by ID
//        int c2 = cf.deleteCustomerById(6);
//        System.out.println("Deletet Customer: " + c2);
//        
//        // Delete Customer entity
//        Customer c3 = cf.deleteCustomerById_2(18);
//        System.out.println("Delete by Entity: " + c3);
    }
}