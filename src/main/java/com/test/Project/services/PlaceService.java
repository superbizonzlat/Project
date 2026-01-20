package com.test.Project.services;

import com.test.Project.models.Place;
import com.test.Project.models.User;
import com.test.Project.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> findAll()
    {
        return placeRepository.findAll();
    }
}
