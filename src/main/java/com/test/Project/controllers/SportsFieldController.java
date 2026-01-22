package com.test.Project.controllers;

import com.test.Project.dto.SportsFieldDTO;
import com.test.Project.models.SportsField;
import com.test.Project.services.SportsFieldService;
import com.test.Project.util.SportFieldsException;
import com.test.Project.util.SportsFieldNotCreatedException;
import com.test.Project.util.SportsFieldValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sports_field")
public class SportsFieldController {

    private final SportsFieldService sportsFieldService;
    private final ModelMapper modelMapper;
    private final SportsFieldValidator sportsFieldValidator;
    @Autowired
    public SportsFieldController(SportsFieldService sportsFieldService, ModelMapper modelMapper, SportsFieldValidator sportsFieldValidator) {
        this.sportsFieldService = sportsFieldService;
        this.modelMapper = modelMapper;
        this.sportsFieldValidator = sportsFieldValidator;
    }

    @GetMapping("/all")
    public List<SportsFieldDTO> getAllSportFields()
    {

        /*Function<Double, Long> function = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        System.out.println(function.apply(5.24));

        Function<SportsField,SportsFieldDTO> function1 = new Function<SportsField, SportsFieldDTO>() {
            @Override
            public SportsFieldDTO apply(SportsField sportsField) {
                return convertToSportFieldsDTO(sportsField);
            }
        };
        //return sportFieldsService.findAll().stream().map(this::convertToSportFieldsDTO)
         //       .collect(Collectors.toList());*/
       return sportsFieldService.findAll().stream().map(new Function<SportsField, SportsFieldDTO>() {
            @Override
            public SportsFieldDTO apply(SportsField sportsField) {
                return convertToSportFieldsDTO(sportsField);
            }
        }).collect(Collectors.toList());


    }

    @PostMapping()
    public HttpStatus createSportFields(@RequestBody @Valid SportsFieldDTO sportsFieldDTO, BindingResult result)
    {
        sportsFieldValidator.validate(sportsFieldDTO,result);

        if(result.hasErrors())
        {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError error:errors)
                stringBuilder.append(error.getField()).
                        append(" - ").
                        append(error.getDefaultMessage())
                        .append(";");
            throw new SportsFieldNotCreatedException(stringBuilder.toString());
        }

        sportsFieldService.save(convertToSportFields(sportsFieldDTO));
        return HttpStatus.CREATED;
    }

    @PostMapping("/delete")
    public HttpStatus deleteSportFields(@RequestBody SportsFieldDTO sportsFieldDTO)
    {
        sportsFieldService.delete(sportsFieldDTO);
        return HttpStatus.OK;
    }

    @PostMapping("/update/{id}")
    public HttpStatus updateSportFields (@RequestBody SportsFieldDTO sportsFieldDTO, @PathVariable("id") int id)
    {
        sportsFieldService.update(sportsFieldDTO,id);
        return HttpStatus.OK;
    }


    private SportsField convertToSportFields(SportsFieldDTO sportsFieldDTO) {
        return modelMapper.map(sportsFieldDTO, SportsField.class);
    }

    private SportsFieldDTO convertToSportFieldsDTO (SportsField sportsField) {
        return modelMapper.map(sportsField, SportsFieldDTO.class);
    }



    @ExceptionHandler
    private ResponseEntity<String> handleException(SportsFieldNotCreatedException e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(SportFieldsException e)
    {
        return new ResponseEntity<>("sportFields not found", HttpStatus.BAD_REQUEST);
    }

}
