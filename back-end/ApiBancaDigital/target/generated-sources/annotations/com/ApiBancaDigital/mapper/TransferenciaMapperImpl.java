package com.ApiBancaDigital.mapper;

import com.ApiBancaDigital.dto.TransferenciaDTO;
import com.ApiBancaDigital.entity.Transferencia;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-04T15:22:00-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransferenciaMapperImpl implements TransferenciaMapper {

    @Override
    public TransferenciaDTO EntitytoDTO(Transferencia transferencia) {
        if ( transferencia == null ) {
            return null;
        }

        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();

        transferenciaDTO.setId( transferencia.getId() );
        transferenciaDTO.setTipoMoneda( transferencia.getTipoMoneda() );
        transferenciaDTO.setFkOrigenCuentaBancaria( transferencia.getFkOrigenCuentaBancaria() );
        transferenciaDTO.setFkDestinoCuentaBancaria( transferencia.getFkDestinoCuentaBancaria() );
        transferenciaDTO.setMonto( transferencia.getMonto() );
        transferenciaDTO.setNumReferencia( transferencia.getNumReferencia() );
        transferenciaDTO.setFechaEjecucion( transferencia.getFechaEjecucion() );

        return transferenciaDTO;
    }

    @Override
    public Transferencia DTOtoEntity(TransferenciaDTO trasnferenciaDTO) {
        if ( trasnferenciaDTO == null ) {
            return null;
        }

        Transferencia.TransferenciaBuilder transferencia = Transferencia.builder();

        transferencia.id( trasnferenciaDTO.getId() );
        transferencia.tipoMoneda( trasnferenciaDTO.getTipoMoneda() );
        transferencia.fkOrigenCuentaBancaria( trasnferenciaDTO.getFkOrigenCuentaBancaria() );
        transferencia.fkDestinoCuentaBancaria( trasnferenciaDTO.getFkDestinoCuentaBancaria() );
        transferencia.monto( trasnferenciaDTO.getMonto() );
        transferencia.numReferencia( trasnferenciaDTO.getNumReferencia() );
        transferencia.fechaEjecucion( trasnferenciaDTO.getFechaEjecucion() );

        return transferencia.build();
    }
}
