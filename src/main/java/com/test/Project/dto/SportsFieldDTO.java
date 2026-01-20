package com.test.Project.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Optional;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SportsFieldDTO {
    private Optional<String> name;
    private Optional<SportDTO> sport;
    private Optional<PlaceDTO> place;


    private Optional<@DecimalMin(message = "min 50",value = "50.00")@Digits(integer = 5,fraction = 2,message = "wrong format") BigDecimal> price;

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
    public BigDecimal getPrice() {
        return this.price != null ? this.price.get() : null ;
    }

    public void setPrice(BigDecimal price) {
        this.price = Optional.of(price);
    }

}
