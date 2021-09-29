package facades;

import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class CityInfoFacadeTest {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    CityInfoFacade facade = CityInfoFacade.getCityInfoFacade(emf);

/*
    @Test
    public void getDanishZipCodesTest(){
        int expected = 2;
        int actual = facade.getDanishZipCodes().size();
        assertEquals(expected,actual);
    }
*/
}