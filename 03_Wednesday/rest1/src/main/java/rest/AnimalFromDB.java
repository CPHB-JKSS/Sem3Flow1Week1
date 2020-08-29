/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import entity.*;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Joakim
 */
@Path("animals_db")
public class AnimalFromDB {

    @Context
    private UriInfo context;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    @GET
    @Path("/animals")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/randomanimal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a ORDER BY FUNCTION('RAND')", Animal.class);
            query.setMaxResults(1);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @GET
    @Path("animalbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalById(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a where a.id=:id", Animal.class);
            query.setParameter("id", id);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @GET
    @Path("animalbytype/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a where a.type=:type", Animal.class);
            query.setParameter("type", type);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }
}
