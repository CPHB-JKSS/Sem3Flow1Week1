/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import model.*;

/**
 * REST Web Service
 *
 * @author Joakim
 */
@Path("animals")
public class AnimalDemo {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalDemo
     */
    public AnimalDemo() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalDemo
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        //TODO return proper representation object
        return "Vuf... (Message from a dog)";
    }

    @GET
    @Path("/animal_list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalListJson() {
        return "[\"Dog\", \"Cat\", \"Mouse\", \"Bird\"]";
    }

    @GET
    @Path("/animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalJson() {
        AnimalNoDB animal = new AnimalNoDB("Kat", "Meow");
        return new Gson().toJson(animal);
    }

    /**
     * PUT method for updating or creating an instance of AnimalDemo
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
