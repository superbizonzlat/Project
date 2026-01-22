package com.test.Project.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class BookingDTO {

    private Optional<ClientDTO> clientDTOOptional;

    private Optional<SportsFieldDTO> sportsFieldDTOOptional;

    private Optional<LocalDateTime> begin_at;

    private Optional<LocalDateTime> end_at;

    public ClientDTO getClientDTO() {
        return clientDTOOptional.orElse(null);
    }

    public void setClientDTO(ClientDTO clientDTO) {
        if(clientDTO != null) this.clientDTOOptional = Optional.of(clientDTO);
    }

    public SportsFieldDTO getSportsFieldDTO() {
        return sportsFieldDTOOptional.orElse(null);
    }

    public void setSportsFieldDTO(SportsFieldDTO sportsFieldDTO) {
        if(sportsFieldDTO!= null) this.sportsFieldDTOOptional = Optional.of(sportsFieldDTO);
    }

    public Optional<LocalDateTime> getBegin_at() {
        return begin_at;
    }

    public void setBegin_at(Optional<LocalDateTime> begin_at) {
        this.begin_at = begin_at;
    }

    public Optional<LocalDateTime> getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Optional<LocalDateTime> end_at) {
        this.end_at = end_at;
    }
}
