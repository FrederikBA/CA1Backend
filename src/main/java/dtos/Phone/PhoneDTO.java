package dtos.Phone;

import entities.Phone;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneDTO {
    private Long id;
    private String number;
    private String description;

    public static List<PhoneDTO> getFromList(List<Phone> phones) {
        return phones.stream()
                .map(phone -> new PhoneDTO(phone))
                .collect(Collectors.toList());
    }

    public PhoneDTO(Phone phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
