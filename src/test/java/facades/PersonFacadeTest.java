package facades;

import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class PersonFacadeTest {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    PersonFacade facade = PersonFacade.getPersonFacade(emf);
/*
    @Test
    public void getAllPersonsTest() {
        int expected = 5;
        int actual = facade.getAllPersons().getSize();
        assertEquals(expected, actual);
    }

    @Test
    public void getPersonByIdTest() {
        String expected = "Jønke";
        String actual = facade.getPersonById(1).getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    public void getPersonsByHobby() {
        int expected = 2;
        int actual = facade.getPersonsByHobby("Fencing").getSize();
        assertEquals(expected, actual);
    }

    @Test
    public void getPersonCountByHobbyTest() {
        long expected = 2;
        long actual = facade.getNumberOfPeopleByHobby("Fencing");
        assertEquals(expected, actual);
    }

    @Test
    public void getPersonsByCityTest() {
        int expected = 2;
        int actual = facade.getPersonsByCity(4500).getSize();
        assertEquals(expected,actual);
    }
    */
}