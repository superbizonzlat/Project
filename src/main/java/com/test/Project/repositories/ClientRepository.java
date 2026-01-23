package com.test.Project.repositories;

import com.test.Project.models.Client;
import com.test.Project.models.SportsField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    @Query("From Client  where telephone like concat('_',:d) or telephone like concat('__',:d) ")
    Optional<Client> findClientByTelephone(@Param("d") String telephone);
    Optional<Client> findClientByMailIgnoreCase(String mail);

}
