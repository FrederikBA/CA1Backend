package facades;

import dtos.Hobby.HobbyDTO;
import dtos.Person.PersonDTO;
import dtos.Person.PersonsDTO;
import dtos.PhoneDTO;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
/*
    public PersonDTO addPerson(PersonDTO personDTO) {
        EntityManager em = emf.createEntityManager();
        Person person = new Person(personDTO.getEmail(), personDTO.getFirstName(), personDTO.getLastName());
        try {


            //Add Hobby
            for (HobbyDTO hobbyDTO : personDTO.getHobbies()) {
                TypedQuery<Hobby> query = em.createQuery("SELECT h from Hobby h WHERE h.name = :hobby", Hobby.class);
                query.setParameter("hobby", hobbyDTO.getName());
                Hobby tmpHobby = query.getSingleResult();
                person.addHobby(tmpHobby);
            }

            //Add Phone
            for (PhoneDTO phoneDTO : personDTO.getPhones()) {
                TypedQuery<Phone> query = em.createQuery("SELECT p from Phone p WHERE p.number = :number", Phone.class);
                query.setParameter("number", phoneDTO.getNumber());
                Phone tmpPhone = query.getSingleResult();
                person.addPhone(tmpPhone);
            }

            //Add Address
            Address address = new Address(personDTO.getAddress().getStreet(), personDTO.getAddress().getAdditionalInfo());
            CityInfo cityInfo = em.find(CityInfo.class, personDTO.getAddress().getCityInfo().getZipCode());
            address.setCityInfo(cityInfo);
            person.setAddress(address);

            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();

            return new PersonDTO(person);

        } finally {
            em.close();
        }
    }
*/

    public PersonDTO addPerson(PersonDTO personDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Person person = new Person(personDTO.getEmail(), personDTO.getFirstName(), personDTO.getLastName());

        if ((person.getFirstName().length() == 0) || (person.getLastName().length() == 0)) {
            throw new WebApplicationException("Name is missing", 400);
        }

        // Add Hobby to person
        for (HobbyDTO hobbyDTO : personDTO.getHobbies()) {
            try {
                Hobby hobby = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :hobby", Hobby.class)
                        .setParameter("hobby", hobbyDTO.getName())
                        .getSingleResult();
                person.addHobby(hobby);
            } catch (NoResultException err) {
                throw new WebApplicationException("Hobby: " + hobbyDTO.getName() + ", does not exist", 400);
            }
        }

        // Add each phone to the person
        for (PhoneDTO phoneDTO : personDTO.getPhones()) {

            try {
                Phone phoneAlreadyInUse = em
                        .createQuery("SELECT p FROM Phone p WHERE p.number = :number", Phone.class)
                        .setParameter("number", phoneDTO.getNumber())
                        .getSingleResult();

                throw new WebApplicationException(
                        "Phone with number: " + phoneAlreadyInUse.getNumber() + ", is already beeing used", 400);
            } catch (NoResultException e) {
                Phone phoneToAdd = new Phone(phoneDTO.getNumber(), phoneDTO.getDescription());
                person.addPhone(phoneToAdd);
            }

        }

        // Create address
        Address address = new Address(personDTO.getAddress().getStreet(),
                personDTO.getAddress().getAdditionalInfo());
        TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c WHERE c.zipCode = :zipCode",CityInfo.class);
        query.setParameter("zipCode",personDTO.getAddress().getCityInfo().getZipCode());
        CityInfo cityInfo = query.getSingleResult();
        if (cityInfo == null) {
            throw new WebApplicationException(
                    "No such zipCode exists: " + personDTO.getAddress().getCityInfo().getZipCode(), 400);
        }
        address.setCityInfo(cityInfo);
        person.setAddress(address);

        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    public PersonDTO getPersonByPhoneNumber(String number) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p from Person p JOIN p.phones ph WHERE ph.number = :number", Person.class);
            query.setParameter("number", number);
            Person person = query.getSingleResult();
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

    public PersonsDTO getPersonsByCity(int zipCode) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address a WHERE a.cityInfo.zipCode = :zipCode", Person.class);
            query.setParameter("zipCode", zipCode);
            List<Person> personList = query.getResultList();
            return new PersonsDTO(personList);
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
