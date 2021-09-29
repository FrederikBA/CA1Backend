/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;


public class Populator {
    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();


        Person p1 = new Person("Rasmush22@live.dk", "rasmus", "Hansen");
        Person p2 = new Person("Jønkemail.com", "Jønke", "larsen");
        Person p3 = new Person("Janusmail@mail.dk", "Janus", "Stivang");
        Person p4 = new Person("Kianmail@mail.dk", "Kian", "Cronfalk");
        Person p5 = new Person("Frederikmail@mail.dk", "Frederik", "Andersen");

        Hobby h1 = new Hobby("svømning", "wiki.com", "hole body", "swimming");
        Hobby h2 = new Hobby("Fencing", "wiki.com","arms", "sword play");

        Phone nokia = new Phone("11223344", "This phone is old");
        Phone huawei = new Phone("666666", "This phone is chinese");
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

    public static void main(String[] args) {
        populate();
    }
}
