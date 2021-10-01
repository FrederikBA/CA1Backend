package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;

@Path("/test")
public class TestResource {
    private static final EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade facade = PersonFacade.getInstance(emf);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

}