import {Box, Typography, Paper} from '@mui/material';
import PropTypes from 'prop-types';

export const DetailView = ({cuenta}) => {
    
    const accountStatus = cuenta.estado ? 'activo' : 'inactivo';

    const formattedDate = new Date(cuenta.fechaApertura).toLocaleDateString('es-ES', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    });
    return (
        <Paper elevation={4} sx={{ display:'flex',  justifyContent:'center', mb:15}}>
            <Box sx={{m:5, display:'flex', flexDirection:'column', height:1}}>
                 {/* tipo de cuenta */}
                <Typography sx={{fontSize:20,mb:2}}><span style={{fontWeight: 'bold'}}>Tipo de cuenta: </span>{cuenta.tipoCuenta}</Typography>
                {/* numero de cuenta */}
                <Typography  sx={{fontSize:20, mb:2}}> <span style={{fontWeight: 'bold'}}>Cuenta n°: </span>{cuenta.numCuenta}</Typography>
                {/* saldo de la cuenta */}
                <Typography  sx={{fontSize:20, mb:2}}> <span style={{fontWeight: 'bold'}}>Saldo : $</span>{cuenta.saldoTotal}</Typography>
                {/* fecha de apertura */}
                <Typography sx={{fontSize:20, mb:2}} > <span style={{fontWeight: 'bold'}}>Fecha de apertura: </span>{formattedDate}</Typography>
                {/* estado de la cuenta */}
                <Typography sx={{fontSize:20, mb:2}} > <span style={{fontWeight: 'bold'}}>Estado: </span>{accountStatus}</Typography>
                 {/* estado de la cuenta */}
                <Typography sx={{fontSize:20, mb:2}} > <span style={{fontWeight: 'bold'}}>Puntos: </span>{cuenta.puntos}</Typography>
            </Box>      
        </Paper>            
    );
};

DetailView.propTypes = {
    cuenta:PropTypes.object
};
