package com.test.Project.repositories;


import com.test.Project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<User,Integer> {
    Optional<User> findByLogin(String username);

}
