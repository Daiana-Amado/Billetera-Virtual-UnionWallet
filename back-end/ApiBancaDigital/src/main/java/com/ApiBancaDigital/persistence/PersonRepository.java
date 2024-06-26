package com.ApiBancaDigital.persistence;

import com.ApiBancaDigital.dto.PersonDTO;
import com.ApiBancaDigital.entity.Person;
import com.ApiBancaDigital.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE p.email = :email")
    Person findByEmail(@Param("email")String email);
    Boolean existsByEmail (String email);
    Boolean existsByNumDni (String dni);
}
