package com.test.Project.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class BookingDTO {

    private Optional<ClientDTO> clientDTO;

    private Optional<SportsFieldDTO> sportsFieldDTO;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Optional<LocalDateTime> begin_at;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Optional<LocalDateTime> end_at;

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
