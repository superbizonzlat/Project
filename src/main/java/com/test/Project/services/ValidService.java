package com.test.Project.services;


import com.test.Project.models.User;
import com.test.Project.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public ValidService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public Optional<User> getPerson(String username)
    {
        return peopleRepository.findByLogin(username);
    }

}
