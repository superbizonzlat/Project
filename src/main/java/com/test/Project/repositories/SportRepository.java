package com.test.Project.repositories;

import com.test.Project.models.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SportRepository extends JpaRepository<Sport,Integer> {
    Optional<Sport> findSportByNameContainingIgnoreCase(String name);
}
