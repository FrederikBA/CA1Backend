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
    @Produces("application/json")
    @Path("/all")
    public String getAllPersons() {
        return GSON.toJson(FACADE.getAllPersons());
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String getById(@PathParam("id") int id) {
        return GSON.toJson(FACADE.getPersonById(id));
    }

    @GET
    @Produces("application/json")
    @Path("/hobby/{hobby}")
    public String getByHobby(@PathParam("hobby") String hobby) {
        return GSON.toJson(FACADE.getPersonsByHobby(hobby));
    }

    @GET
    @Produces("application/json")
    @Path("/number/{number}")
    public String getByNumber(@PathParam("number") String number) {
        return GSON.toJson(FACADE.getPersonByPhoneNumber(number));
    }

    @GET
    @Produces("application/json")
    @Path("/city/{zipCode}")
    public String getByZip(@PathParam("zipCode") int zipCode) {
        return GSON.toJson(FACADE.getPersonsByCity(zipCode));
    }

    @GET
    @Produces("application/json")
    @Path("/hobby/count/{hobby}")
    public String getHobbyCount(@PathParam("hobby") String hobby) {
        return GSON.toJson(FACADE.getNumberOfPeopleByHobby(hobby));
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String addPerson(String person) {
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO pNew = FACADE.addPerson(p);
        return GSON.toJson(pNew);
    }

    @PUT
    @Path("/{id}")
    public String editPerson(@PathParam("id") long id, String person) {
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        p.setId(id);
        PersonDTO pEdited = FACADE.editPerson(p);
        return GSON.toJson(pEdited);
    }

    @GET
    @Produces("application/json")
    @Path("/numberbyhobby/{hobby}")
    public String getNumberOfPersonsByHobby(@PathParam("hobby") String hobby) {
        long count = FACADE.getNumberOfPeopleByHobby(hobby);
        return "{\"count\":" + count + "}";
    }
}
