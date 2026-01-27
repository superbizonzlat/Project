package com.test.Project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class BookingDTO {

    private Optional<ClientDTO> clientDTO;

    private Optional<SportsFieldDTO> sportsFieldDTO;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "this value not null")
    private Optional< LocalDateTime> begin_at;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "this value not null")
    private Optional< LocalDateTime> end_at;

    public ClientDTO getClientDTO() {
        return clientDTO.orElse(null);
    }

    public void setClientDTOOptional(ClientDTO clientDTO) {
        if(clientDTO != null) this.clientDTO = Optional.of(clientDTO);
    }

    public SportsFieldDTO getSportsFieldDTO() {
        return sportsFieldDTO.orElse(null);
    }

    public void setSportsFieldDTO(SportsFieldDTO sportsFieldDTO) {
        if(sportsFieldDTO!= null) this.sportsFieldDTO = Optional.of(sportsFieldDTO);
    }

    public LocalDateTime getBegin_at() {
        return this.begin_at != null ? this.begin_at.get() : null ;
    }

    public void setBegin_at(LocalDateTime begin_at) {
        if(begin_at!= null) this.begin_at = Optional.of(begin_at);
    }

    public LocalDateTime getEnd_at() {
         return this.end_at != null ? this.end_at.get() : null ;
    }

    public void setEnd_at(LocalDateTime end_at) {
        if(end_at!= null) this.end_at = Optional.of(end_at);
    }
}
