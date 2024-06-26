package com.ApiBancaDigital.controller;

import com.ApiBancaDigital.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/createData")
@CrossOrigin(origins = "*")
public class DataController {

    @Autowired private  DataService dataService;


    @PostMapping
    public String createData(){
        return dataService.createData();
    }

}
