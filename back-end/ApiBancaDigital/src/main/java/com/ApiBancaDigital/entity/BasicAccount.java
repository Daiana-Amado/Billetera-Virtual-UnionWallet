package com.ApiBancaDigital.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "basic_account")
@Entity
public class BasicAccount{

    @Id
    @Column(name = "num_cuenta")
    private Long numCuenta;



}
