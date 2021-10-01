package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Person.PersonDTO;
import entities.Person;
import facades.FacadeExample;
import utils.EMF_Creator;
import facades.PersonFacade;


import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE = PersonFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public String getAllPersons() {
        return GSON.toJson(FACADE.getAllPersons());
    }

    @GET
    @Produces("application/json")
    @Path("/teststring")
    public String testString() {
        List<String> stringList = new ArrayList<>();
        stringList.add("One");
        stringList.add("Two");
        stringList.add("Three");
        return GSON.toJson(stringList);
    }
}