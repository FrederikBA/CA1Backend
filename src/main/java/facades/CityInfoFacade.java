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

    //Private Constructor to ensure Singleton
    private CityInfoFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CityInfoFacade getCityInfoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<CityInfoDTO> getAllCityInfo() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<CityInfo> query = em.createQuery("SELECT C FROM CityInfo c", CityInfo.class);
            List<CityInfo> cityInfoResultList = query.getResultList();
            em.getTransaction().commit();

            return (List<CityInfoDTO>) (List<?>) cityInfoResultList;
        } finally {
            em.close();
            emf.close();
        }
    }

    public CityInfoDTO getById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            CityInfo cityInfo = em.find(CityInfo.class, id);
            return new CityInfoDTO(cityInfo);
        } finally {
            em.close();
            emf.close();
        }
        //list of all zipcodes in denmark
        //getbyId
        //getbyName
        //metoder der kører cityInfoScrpt


    }
}
