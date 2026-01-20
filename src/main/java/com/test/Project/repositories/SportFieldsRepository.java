package com.test.Project.repositories;

import com.test.Project.models.SportsField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SportFieldsRepository extends JpaRepository<SportsField, Integer> {
    void deleteByName(String name);
    Optional<SportsField> findSportsFieldByName(String name);
}
