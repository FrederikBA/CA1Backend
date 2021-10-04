package facades;

import entities.Phone;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class PhoneFacade {
    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;


    public static CityInfoFacade getCityInfoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }

    public List<Phone> getAllPhones (){

        return null; //return list of all phones
    }

//GetAll
// getByID
//CReate
    //delete
    // edit


}
