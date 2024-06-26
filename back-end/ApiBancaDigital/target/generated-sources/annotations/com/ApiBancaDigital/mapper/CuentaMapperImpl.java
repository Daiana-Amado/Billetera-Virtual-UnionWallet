package com.ApiBancaDigital.mapper;

import com.ApiBancaDigital.dto.CuentaDTO;
import com.ApiBancaDigital.entity.Cuenta;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-16T14:47:41-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class CuentaMapperImpl implements CuentaMapper {

    @Override
    public CuentaDTO EntitytoDTO(Cuenta cuenta) {
        if ( cuenta == null ) {
            return null;
        }

        CuentaDTO cuentaDTO = new CuentaDTO();

        cuentaDTO.setNumCuenta( cuenta.getNumCuenta() );
        cuentaDTO.setFkNumUsuario( cuenta.getFkNumUsuario() );
        cuentaDTO.setSaldoTotal( cuenta.getSaldoTotal() );
        cuentaDTO.setEstado( cuenta.isEstado() );
        cuentaDTO.setFechaApertura( cuenta.getFechaApertura() );

        return cuentaDTO;
    }

    @Override
    public Cuenta DTOtoEntity(CuentaDTO cuentaDTO) {
        if ( cuentaDTO == null ) {
            return null;
        }

        Cuenta.CuentaBuilder cuenta = Cuenta.builder();

        cuenta.numCuenta( cuentaDTO.getNumCuenta() );
        cuenta.fkNumUsuario( cuentaDTO.getFkNumUsuario() );
        cuenta.saldoTotal( cuentaDTO.getSaldoTotal() );
        cuenta.estado( cuentaDTO.isEstado() );
        cuenta.fechaApertura( cuentaDTO.getFechaApertura() );

        return cuenta.build();
    }
}
