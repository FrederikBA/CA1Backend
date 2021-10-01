package facades;

import dtos.Hobby.HobbyDTO;
import dtos.Person.PersonDTO;
import dtos.Person.PersonsDTO;
import dtos.PhoneDTO;
import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class PersonFacadeTest {
    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static Person person1, person2;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup
    @BeforeEach
    public void setUp() {


        CityInfo cityInfo = new CityInfo(3000, "TestCity");

        Person person1 = new Person("Testmail@mail.dk", "TestFirstName", "TestLastName");
        Phone phone1 = new Phone("12345678", "TestPhone");
        person1.addPhone(phone1);
        Hobby hobby = new Hobby("Turisme", "https://da.wikipedia.org/wiki/Turisme", "General", "Fritid");
        person1.addHobby(hobby);
        Address address1 = new Address("Testgade 48", "This is a street");
        address1.setCityInfo(cityInfo);
        person1.setAddress(address1);

        Person person2 = new Person("Moreexamples@mail.dk", "MoreFirstName", "MoreLastName");
        Phone phone2 = new Phone("87654321", "ChinesePhone");
        person2.addPhone(phone2);
        person2.addHobby(hobby);
        Address address2 = new Address("Nygade 3", "This is an Alley");
        address2.setCityInfo(cityInfo);
        person2.setAddress(address2);


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
            em.persist(cityInfo);
            em.persist(hobby);
            em.persist(person1);
            em.persist(person2);

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
        int expected = 2;
        int actual = facade.getAllPersons().getSize();
        assertEquals(expected, actual);
    }

    //TODO Fails because persons are persisted at a random order(?)

    @Test
    public void getPersonByIdTest() {
        String expected = "TestFirstName";
        String actual = facade.getPersonById(1).getFirstName();
        assertEquals(expected, actual);
    }


    @Test
    public void getPersonsByHobby() {
        int expected = 2;
        int actual = facade.getPersonsByHobby("Turisme").getSize();
        assertEquals(expected, actual);
    }

    @Test
    public void getPersonCountByHobbyTest() {
        long expected = 2;
        long actual = facade.getNumberOfPeopleByHobby("Turisme");
        assertEquals(expected, actual);
    }

    @Test
    public void getPersonsByCityTest() {
        int expected = 2;
        int actual = facade.getPersonsByCity(3000).getSize();
        assertEquals(expected, actual);
    }

    @Test
    public void getPersonByPhoneNumber() {
        int expected = 1;
        int actual = facade.getPersonByPhoneNumber("12345678").getHobbies().size();
        assertEquals(expected, actual);
    }

    //TODO Create addPersonTest + deletePersonTest + editPersonTest
/*
    @Test
    public void addPersonTest() {
        PersonDTO p;
        Person person = new Person("Testmail@mail.dk", "TestFirstName", "TestLastName");
        Phone phone = new Phone("12345678", "TestPhone");
        person.addPhone(phone);
        Hobby hobby = new Hobby("Turisme", "https://da.wikipedia.org/wiki/Turisme", "General", "Fritid");
        person.addHobby(hobby);
        Address address = new Address("Testgade 48", "This is a street");
        address.setCityInfo(new CityInfo(1234, "TestCity"));
        person.setAddress(address);

        PersonDTO createdPerson = new PersonDTO(person);

        p = facade.addPerson(createdPerson);

        for (HobbyDTO hobbyDTO : p.getHobbies()) {
            assertNotNull(hobbyDTO);
        }

        for (PhoneDTO phoneDTO : p.getPhones()) {
            assertNotNull(phoneDTO);
        }

        assertEquals("Testmail@mail.dk", p.getEmail());
        assertEquals("Testgade 48", createdPerson.getAddress().getStreet());

        assertEquals(6, facade.getAllPersons().getSize());
    }
*/
}