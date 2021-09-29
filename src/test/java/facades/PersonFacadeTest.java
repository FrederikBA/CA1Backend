package facades;

import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class PersonFacadeTest {
    private static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    private static PersonFacade facade;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup
    @BeforeEach
    public void setUp() {

        Person p1 = new Person("Rasmush22@live.dk", "Rasmus", "Hansen");
        Person p2 = new Person("Jønkemail.com", "Jønke", "larsen");
        Person p3 = new Person("Janusmail@mail.dk", "Janus", "Stivang");
        Person p4 = new Person("Kianmail@mail.dk", "Kian", "Cronfalk");
        Person p5 = new Person("Frederikmail@mail.dk", "Frederik", "Andersen");

        Hobby h1 = new Hobby("svømning", "wiki.com", "hole body", "swimming");
        Hobby h2 = new Hobby("Fencing", "wiki.com", "arms", "sword play");

        Phone nokia = new Phone("11223344", "This phone is old");
        Phone huawei = new Phone("66666666", "This phone is chinese");
        Phone iPhone = new Phone("99887766", "This phone is new");

        Address lyngbyHovedgade = new Address("Hos Frederik", "Det er dyrt");
        Address gyldendalsvej = new Address("Hos Rasmus", "Der bor Rasmus");
        Address damsboVaenge = new Address("Hos Janus", "Det er langt væk");

        CityInfo c1 = new CityInfo(2500, "Søborg");
        CityInfo c2 = new CityInfo(4500, "Haslev");


        damsboVaenge.addPerson(p3);
        damsboVaenge.addPerson(p4);
        lyngbyHovedgade.addPerson(p5);

        p1.addPhone(nokia);
        p1.addPhone(iPhone);
        p2.addPhone(huawei);


        p1.addHobby(h1);
        p1.addHobby(h2);
        p2.addHobby(h2);

        c1.addAddress(lyngbyHovedgade);
        c1.addAddress(gyldendalsvej);
        c2.addAddress(damsboVaenge);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table PERSON AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table HOBBY AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table PHONE AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table ADDRESS AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table CITYINFO AUTO_INCREMENT = 1").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table PERSON AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table HOBBY AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table PHONE AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table ADDRESS AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table CITYINFO AUTO_INCREMENT = 1").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table PERSON AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table HOBBY AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table PHONE AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table ADDRESS AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table CITYINFO AUTO_INCREMENT = 1").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table PERSON AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table HOBBY AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table PHONE AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table ADDRESS AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table CITYINFO AUTO_INCREMENT = 1").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
            em.createNativeQuery("alter table PERSON AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table HOBBY AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table PHONE AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table ADDRESS AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("alter table CITYINFO AUTO_INCREMENT = 1").executeUpdate();

            em.persist(p1);
            em.persist(p2);
            em.persist(damsboVaenge);
            em.persist(lyngbyHovedgade);
            em.persist(gyldendalsvej);
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }


    @Test
    public void getAllPersonsTest() {
        int expected = 5;
        int actual = facade.getAllPersons().getSize();
        assertEquals(expected, actual);
    }

    //TODO Fails because persons are persisted at a random order(?)
    /*
    @Test
    public void getPersonByIdTest() {
        String expected = "Janus";
        String actual = facade.getPersonById(1).getFirstName();
        assertEquals(expected, actual);
    }
*/
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
        assertEquals(expected, actual);
    }

    @Test
    public void getPersonByPhoneNumber() {
        int expected = 2;
        int actual = facade.getPersonByPhoneNumber("11223344").getHobbies().size();
        assertEquals(expected, actual);
    }

    //TODO Create addPersonTest + deletePersonTest + editPersonTest

}