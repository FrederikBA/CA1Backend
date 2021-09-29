package dtos.Hobby;

import entities.Hobby;

import java.util.List;

public class HobbiesDTO {

    private List<HobbyDTO> hobbies;

    public HobbiesDTO(List<Hobby> hobbies) {
        this.hobbies = HobbyDTO.getFromList(hobbies);
    }

    public List<HobbyDTO> getAll() {
        return hobbies;
    }

    public void setAll(List<HobbyDTO> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "HobbiesDTO{" +
                "hobbies=" + hobbies +
                '}';
    }

}
