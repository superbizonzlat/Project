package com.test.Project.services;

import com.test.Project.dto.SportsFieldDTO;
import com.test.Project.models.Place;
import com.test.Project.models.Sport;
import com.test.Project.models.SportsField;
import com.test.Project.repositories.PlaceRepository;
import com.test.Project.repositories.SportsFieldRepository;
import com.test.Project.repositories.SportRepository;
import com.test.Project.util.SportsFieldException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SportsFieldService {

    private final SportsFieldRepository sportsFieldRepository;
    private final PlaceRepository placeRepository;

    private  final SportRepository sportRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SportsFieldService(SportsFieldRepository sportsFieldRepository, PlaceRepository placeRepository, SportRepository sportRepository, ModelMapper modelMapper) {
        this.sportsFieldRepository = sportsFieldRepository;
        this.placeRepository = placeRepository;
        this.sportRepository = sportRepository;
        this.modelMapper = modelMapper;
    }

    public boolean findOne(String name)
    {
        Optional<SportsField> foundSportFields= sportsFieldRepository.findSportsFieldByName(name);
        return foundSportFields.isPresent();
    }

    public List<SportsField> findAll()
    {
        return sportsFieldRepository.findAll();
    }
    @Transactional
    public void save(SportsField sportsField)
    {
        Optional<Place> optionalPlace = placeRepository.findPlaceByNameContainingIgnoreCase(sportsField.getPlace().getName());
        Place place = optionalPlace.orElse(null);
        Optional<Sport> optionalSport = sportRepository.findSportByNameContainingIgnoreCase(sportsField.getSport().getName());
        Sport sport = optionalSport.orElse(null);
        if(sport != null && place != null)
        {
            sportsField.setPlace(place);
            place.setSportFields(new ArrayList<SportsField>(Collections.singletonList(sportsField)));
            sportsField.setSport(sport);
            sport.setSportFields(new ArrayList<SportsField>(Collections.singletonList(sportsField)));
        }
        sportsFieldRepository.save(sportsField);

    }

    @Transactional
    public void delete(SportsFieldDTO sportsFieldDTO)
    {

        sportsFieldRepository.deleteByName(sportsFieldDTO.getName());

    }
    @Transactional
    public void update(SportsFieldDTO sportsFieldDTO, int id)
    {
        Optional<SportsField> sportFieldsOptional = sportsFieldRepository.findById(id);
        SportsField sportsField = sportFieldsOptional.orElseThrow(SportsFieldException::new);
        if (sportsFieldDTO.getSport() != null)
        {
           Sport sport = sportRepository.findSportByNameContainingIgnoreCase(sportsFieldDTO.getSport().getName()).get();
           sportsField.setSport(sport);
           sportsFieldDTO.setSport(null);
        }
        if (sportsFieldDTO.getPlace() != null)
        {
            Place place = placeRepository.findPlaceByNameContainingIgnoreCase(sportsFieldDTO.getPlace().getName()).get();
            sportsField.setPlace(place);
            sportsFieldDTO.setPlace(null);
        }
        modelMapper.map(sportsFieldDTO, sportsField);
    }
}
