package dtos.CityInfo;

import dtos.Person.PersonDTO;
import dtos.Phone.PhoneDTO;
import entities.CityInfo;
import entities.Phone;

import java.util.ArrayList;
import java.util.List;

public class CityInfosDTO {

    List<CityInfoDTO> all = new ArrayList();

    public CityInfosDTO(List<CityInfo> cityInfoEntities) {
        cityInfoEntities.forEach((p) -> {
            all.add(new CityInfoDTO(p));
        });


    }
    public int getSize() {
        int counter = 0;
        for (CityInfoDTO p : all) {
            counter++;
        }
        return counter;
    }
}
