package com.test.Project.services;


import com.test.Project.dto.UserDTO;
import com.test.Project.models.User;
import com.test.Project.repositories.PeopleRepository;
import com.test.Project.security.PersonDetails;
import com.test.Project.security.PersonNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository, ModelMapper modelMapper) {
        this.peopleRepository = peopleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = peopleRepository.findByLogin(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new PersonDetails(user.get());
    }

    public List<User> findAll()
    {
        return peopleRepository.findAll();
    }

    @Transactional
    public void update(UserDTO updateUser, int id)
    {
        User user = findOne(id);

        modelMapper.map(updateUser,user);
        peopleRepository.save(user);
    }

    public User findOne(int id)
    {
        Optional<User> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }
}
