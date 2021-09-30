/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.Person.PersonDTO;
import dtos.RenameMeDTO;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;


public class Populator {
    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.getTransaction().commit();
    }

    public static void testAdd() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade facade = PersonFacade.getPersonFacade(emf);

        Person person = new Person("Testmail@mail.dk", "TestFirstName", "TestLastName");
        Phone phone = new Phone("12345678", "TestPhone");
        person.addPhone(phone);
        Hobby hobby = new Hobby("Turisme", "https://da.wikipedia.org/wiki/Turisme", "General", "Fritid");
        person.addHobby(hobby);
        Address address = new Address("Testgade 48", "This is a street");
        address.setCityInfo(new CityInfo(1800, "Frederiksberg C"));
        person.setAddress(address);

        PersonDTO createdPerson = new PersonDTO(person);

        facade.addPerson(createdPerson);
    }

    public static void main(String[] args) {
        //populate();
        testAdd();
    }
}
