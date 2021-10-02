package facades;

import dtos.Hobby.HobbiesDTO;
import entities.Hobby;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertNotNull;
@Disabled
public class HobbyFacadeTest {
    private static EntityManagerFactory emf;
    private static HobbyFacade repo;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        repo = HobbyFacade.getInstance(emf);
        EntityManager em = emf.createEntityManager();

        Hobby h1 = new Hobby("svømning","wiki/svømning","Hele kroppen","idræt");
        Hobby h2 = new Hobby("gåture","wiki/gåture","friluft","fritid");
        Hobby h3 = new Hobby("baseball","wiki/baseball","boldspil","idræt");
        try {
            em.getTransaction().begin();
            em.persist(h1);
            em.persist(h2);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    @AfterAll
    static void tearDown() {
        EntityManager em = emf.createEntityManager();
        try  {
            em.getTransaction().begin();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void getAllHobbies() {
        HobbiesDTO hobbies = repo.getAllHobbies();
        assertNotNull(hobbies);
    }

    @org.junit.jupiter.api.Test
    void getInstance() {
    }

    @org.junit.jupiter.api.Test
    void testGetAllHobbies() {
    }

    @org.junit.jupiter.api.Test
    void createHobby() {
    }
}
