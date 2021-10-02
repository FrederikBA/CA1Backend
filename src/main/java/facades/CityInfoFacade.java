package facades;

import dtos.CityInfoDTO;
import dtos.RenameMeDTO;
import entities.CityInfo;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CityInfoFacade {
    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;


    public static CityInfoFacade getCityInfoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }


    public List<CityInfoDTO> getAllCityInfo() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c", CityInfo.class);
            List<CityInfo> cityInfoResultList = query.getResultList();
            em.getTransaction().commit();

            return (List<CityInfoDTO>) (List<?>) cityInfoResultList;
        } finally {
            em.close();
        }
    }

    public CityInfoDTO getCityById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            CityInfo cityInfo = em.find(CityInfo.class, id);
            return new CityInfoDTO(cityInfo);
        } finally {
            em.close();
        }
    }

    public CityInfoDTO getCityByName(String name) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c WHERE c.city = :name", CityInfo.class);
            query.setParameter("name", name);
            CityInfo cityInfo = query.getSingleResult();
            return new CityInfoDTO(cityInfo);
        } finally {
            em.close();
        }
    }

    //TODO  not able to read zipcodes numbered 0800 fix it
    public List<Integer> getDanishZipCodes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Integer> query = em.createQuery("SELECT c.zipCode FROM CityInfo c WHERE c.zipCode > 999 AND c.zipCode NOT BETWEEN 3900 AND 3999 AND c.zipCode < 10000", Integer.class);
            List<Integer> danishZipCode = query.getResultList();
            return danishZipCode;
        } finally {
            em.close();
        }
    }


}


