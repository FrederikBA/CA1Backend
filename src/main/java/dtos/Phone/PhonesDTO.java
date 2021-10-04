package dtos.Phone;

import dtos.Person.PersonDTO;
import entities.Person;
import entities.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhonesDTO {
    List<PhoneDTO> all = new ArrayList();

    public PhonesDTO(List<Phone> phoneEntities) {
        phoneEntities.forEach((p) -> {
            all.add(new PhoneDTO(p));
        });
    }

}
