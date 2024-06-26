package com.ApiBancaDigital.service;

import com.ApiBancaDigital.entity.Person;
import com.ApiBancaDigital.entity.User;
import com.ApiBancaDigital.persistence.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class DataService {

    @Autowired private PersonRepository personRepository;

    public String createData() {

        Person persona1 = new Person();
        persona1.setName("Nombre_1");
        persona1.setLastName("Apellido_1");
        persona1.setNumDni("1.123.456-0");
        persona1.setPhone("099123456");
        persona1.setEmail("persona_one@gmail.com");
        persona1.setBirth(new Date());
        persona1.setState(false);

        Person persona2 = new Person();
        persona2.setName("Nombre_2");
        persona2.setLastName("Apellido_2");
        persona2.setNumDni("2.234.567-0");
        persona2.setPhone("099234567");
        persona2.setEmail("persona_two@gmail.com");
        persona2.setBirth(new Date());
        persona2.setState(false);

        Person persona3 = new Person();
        persona3.setName("Nombre_3");
        persona3.setLastName("Apellido_3");
        persona3.setNumDni("3.345.678-0");
        persona3.setPhone("099345678");
        persona3.setEmail("persona_three@gmail.com");
        persona3.setBirth(new Date());
        persona3.setState(false);

        User user1 = new User();
        user1.setUserName("User_1");
        user1.setUserAlias("Alias_1");
        user1.setPassword("User_1_password");
//        user1.setToken("el_token_del_user_one");
        user1.setFailAttemps(0);
        user1.setState(true);
        user1.setAdmin(false);

        User user2 = new User();
        user2.setUserName("User_2");
        user2.setUserAlias("Alias_2");
        user2.setPassword("User_2_password");
//        user2.setToken("el_token_del_user_two");
        user2.setFailAttemps(0);
        user2.setState(false);
        user2.setAdmin(false);

        persona1.setUser(user1);
        persona2.setUser(user2);

        personRepository.saveAll(List.of(persona1,persona2,persona3));


        return "Data Creada";
    }
}
