package com.test.Project.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.Project.models.Place;
import com.test.Project.models.Sport;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SportFieldsDTO {
    private Optional<String> name;
    private Optional<SportDTO> sport;
    private Optional<PlaceDTO> place;
    private Optional<Integer> price;

    public String getName() {
        return this.name != null ? this.name.get() : null ;
    }

    public void setName(String name) {
        this.name = Optional.of(name);
    }
    public SportDTO getSport() {
        return this.sport != null ? this.sport.get() : null ;
    }

    public void setSport(SportDTO sport) {
        if (sport == null)
            this.sport = null;
        else
            this.sport = Optional.of(sport);
          }
    public PlaceDTO getPlace() {
        return this.place != null ? this.place.get() : null ;
    }

    public void setPlace(PlaceDTO place) {
        if (place == null)
            this.place = null;
        else
            this.place = Optional.of(place);
    }
    public Integer getPrice() {
        return this.price != null ? this.price.get() : null ;
    }

    public void setPrice(int price) {
        this.price = Optional.of(price);
    }

}
