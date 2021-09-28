/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.Hobby;
import entities.Person;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();



        Person p1 = new Person("Rasmush22@live.dk", "rasmus", "Hansen");
        Person p2 = new Person("Jønkemail.com", "Jønke", "larsen");

        Hobby h1 = new Hobby("svømning", "bare svømme svømme svømme");
        Hobby h2 = new Hobby("Fencing", "sword play");


        p1.addHobby(h1);
        p1.addHobby(h2);
        p2.addHobby(h2);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
    
    public static void main(String[] args) {
        populate();
    }
}
