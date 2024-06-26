package com.ApiBancaDigital.mapper;

import com.ApiBancaDigital.dto.CuentaDTO;
import com.ApiBancaDigital.entity.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CuentaMapper {

    CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

    CuentaDTO EntitytoDTO (Cuenta cuenta);

    Cuenta DTOtoEntity (CuentaDTO cuentaDTO);
}
