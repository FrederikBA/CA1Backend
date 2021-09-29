package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Hobby;
import entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class PersonFacade {
    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    public PersonDTO addPerson(String email, String fName, String lName) {
        EntityManager em = emf.createEntityManager();
        try {
            Person person = new Person(email, fName, lName);
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }

    public PersonsDTO getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            List<Person> personList = query.getResultList();
            return new PersonsDTO(personList);
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Person person = em.find(Person.class, id);
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }

    public PersonsDTO getPersonsByHobby(String hobby) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :hobby", Person.class);
            query.setParameter("hobby", hobby);
            List<Person> personList = query.getResultList();
            return new PersonsDTO(personList);
        } finally {
            em.close();
        }
    }

    public Long getNumberOfPeopleByHobby(String hobby) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(p.id) FROM Person p JOIN p.hobbies h WHERE h.name = :hobby", Long.class);
            query.setParameter("hobby", hobby);
            Long numberOfPeople = query.getSingleResult();
            return numberOfPeople;
        } finally {
            em.close();
        }
    }

    public PersonDTO deletePerson(long id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new WebApplicationException(String.format("Person with id: (%d) not found", id), 404);
        }
        try {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }
}
