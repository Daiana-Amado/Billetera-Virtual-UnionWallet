package com.ApiBancaDigital.mapper;


import com.ApiBancaDigital.dto.TransferenciaDTO;
import com.ApiBancaDigital.entity.Transferencia;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferenciaMapper {


    TransferenciaMapper INSTANCE = Mappers.getMapper(TransferenciaMapper.class);

    TransferenciaDTO EntitytoDTO (Transferencia transferencia);

    Transferencia DTOtoEntity (TransferenciaDTO trasnferenciaDTO);


}
