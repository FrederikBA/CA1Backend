package dtos;

import entities.Address;
import entities.CityInfo;

import java.util.List;

public class CityInfoDTO {
    private Long id;
    private int zipcode;
    private String city;


    public CityInfoDTO(CityInfo cityInfo) {
        this.id = cityInfo.getId();
        this.zipcode = cityInfo.getZipCode();
        this.city = cityInfo.getCity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
