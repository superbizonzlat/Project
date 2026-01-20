package com.test.Project.repositories;

import com.test.Project.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Integer> {
    Optional<Place> findPlaceByNameContainingIgnoreCase(String name);
    Place findByName(String name);
}
