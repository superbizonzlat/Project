package com.test.Project.controllers;

import com.test.Project.dto.UserDTO;
import com.test.Project.models.User;
import com.test.Project.security.JWTUtil;
import com.test.Project.security.PersonDetails;
import com.test.Project.services.PersonDetailsService;
import com.test.Project.services.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class AuthController {
    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PersonDetailsService personDetailsService;

    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(RegistrationService registrationService, JWTUtil jwtUtil, AuthenticationManager authenticationManager, PersonDetailsService personDetailsService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.personDetailsService = personDetailsService;
        this.modelMapper = modelMapper;
    }



    @PostMapping("/admin/registration")
    public Map<String,String> create(@RequestBody UserDTO userDTO)
    {

        User user = convertToUser(userDTO);
        registrationService.register(user);
        String token = null; //= jwtUtil.generateToken(user.getLogin());

        return Map.of("jwt-token",token);
    }


    @PostMapping("/login")
    public Map<String,String> performLogin (@RequestBody UserDTO userDTO )
    {
        User user = convertToUser(userDTO);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getLogin(),user.getPassword());
        try {
         user = ((PersonDetails) authenticationManager.authenticate(usernamePasswordAuthenticationToken).getPrincipal()).getPerson();
        } catch (BadCredentialsException e)
        {
            return Map.of("message","Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getLogin(), user.getId());
        return Map.of("jwt-token",token);

    }


    @PatchMapping("/user/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody UserDTO  userDTO, @PathVariable("id") int id)
    {

        personDetailsService.update(userDTO, id);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    private User convertToUser(UserDTO personDTO) {
        return modelMapper.map(personDTO,User.class);
    }

    private UserDTO convertToUserDTO(User person)
    {
        return modelMapper.map(person,UserDTO.class);
    }

}
