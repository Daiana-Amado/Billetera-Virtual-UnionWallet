package com.ApiBancaDigital.controller;

import com.ApiBancaDigital.dto.EmailDTO;
import com.ApiBancaDigital.dto.FullPersonDTO;
import com.ApiBancaDigital.dto.PersonDTO;
import com.ApiBancaDigital.dto.PersonResponseDTO;
import com.ApiBancaDigital.dto.UpdatePersonDTO;
import com.ApiBancaDigital.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/api/v1/person")
@CrossOrigin(origins = "*")
public class PersonController {
    
    @Autowired  private  PersonService personService;
    

    @GetMapping(path="{personNum}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Retorna la peronsa segun id")
    public ResponseEntity<PersonResponseDTO> findPersonByNum(@PathVariable("personNum")  int personNum){
        return personService.findPersonByNum(personNum);
    }
    
    @GetMapping(path="/findPersonByEmail")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Retorna la peronsa segun Email")
    public ResponseEntity<PersonResponseDTO> findPersonByEmail(@RequestParam String personEmail){
        return personService.findPersonByEmail(personEmail);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Lista de todos las personas existentes")
    public ResponseEntity<PersonResponseDTO> getPersons(){
        return personService.getPersons();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "EndPoint para crear una Persona utilizando un Objeto PersonDTO")
    public ResponseEntity<PersonResponseDTO> createPerson(@RequestBody @Valid PersonDTO newPersonDto){
        return personService.createPerson(newPersonDto);
    }
    
    @PostMapping(path="/createFullPerson")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "EndPoint para crear una Persona y un usuario utilizando un Objeto FullPersonDTO")
    public ResponseEntity<PersonResponseDTO> createFullPerson(@RequestBody @Valid FullPersonDTO newFullPersonDto){
        return personService.createFullPerson(newFullPersonDto);
    }
    
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "EndPoint para borrar una Persona utilizando un Objeto PersonDTO")
    public ResponseEntity<PersonResponseDTO> deletePerson(@RequestParam String personEmail){
        return personService.deletePerson(personEmail);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Modifica, si existe, el eMail o el numero de telefono con el numero de persona deseado")
    public ResponseEntity<PersonResponseDTO> updatePerson(
            @RequestBody @Valid UpdatePersonDTO updatePersonDTO,
            @RequestParam EmailDTO personEmail)
            {
        return personService.updatePerson(updatePersonDTO, personEmail);
    }

}