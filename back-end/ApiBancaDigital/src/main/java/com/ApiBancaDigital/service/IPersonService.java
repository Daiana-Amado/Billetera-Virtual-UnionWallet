package com.ApiBancaDigital.service;

import com.ApiBancaDigital.dto.EmailDTO;
import com.ApiBancaDigital.dto.FullPersonDTO;
import com.ApiBancaDigital.dto.PersonDTO;
import com.ApiBancaDigital.dto.PersonResponseDTO;
import com.ApiBancaDigital.dto.UpdatePersonDTO;
import org.springframework.http.ResponseEntity;

public interface IPersonService {
    
    public ResponseEntity<PersonResponseDTO> findPersonByNum(int personNum);
    
    public ResponseEntity<PersonResponseDTO> findPersonByEmail(String personEmail);
    
    public ResponseEntity<PersonResponseDTO> getPersons();
    
    public ResponseEntity<PersonResponseDTO> createPerson(PersonDTO newPersonDto);
    
    public ResponseEntity<PersonResponseDTO> deletePerson(String personEmail);
    
    public ResponseEntity<PersonResponseDTO> updatePerson(UpdatePersonDTO updatePersonDTO, EmailDTO personEmailDTO);
    
    public ResponseEntity<PersonResponseDTO> createFullPerson(FullPersonDTO newFullPersonDto);
    

}