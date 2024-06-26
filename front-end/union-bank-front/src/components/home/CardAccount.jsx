import { Card, CardContent, Typography, Link, Box, useMediaQuery, } from '@mui/material';
import PropTypes from 'prop-types';
import theme from '../../theme/Theme';

export const CardBankAccount= ({cuenta}) => {    
    
    const isMobile = useMediaQuery('(max-width:740px)');
    const width = !isMobile ? 400 : 300;
    
    if (!cuenta) {
        console.log('Cuenta is undefined or null.');
        return <Typography>Cuenta no disponible</Typography>;
    }

    if (!cuenta.numCuenta) {
        console.log('numCuenta is undefined');
        return <Typography>Información de cuenta no disponible</Typography>;
    }
    const numCuentaString = String(cuenta.numCuenta);    
    const lastThreeDigits = numCuentaString.slice(-3); 
    const hiddenDigits = numCuentaString.slice(0, -3).replace(/\d/g, '*');   
    const maskedAccountNumber = hiddenDigits + lastThreeDigits; 

    return (
        <Card sx={{maxHeight:230, width, margin: 1, mb:3 ,padding:1, background: `linear-gradient(to right,  ${theme.palette.secondary.light}, ${theme.palette.secondary.main} )` }} >
        <CardContent>
            <Typography  sx={{fontWeight:'bold', mb:3}} color="primary.main" variant="h5" gutterBottom>
                { `Tipo de Cuenta: ${cuenta.tipoCuenta}`}
            </Typography>
            <Typography sx={{mb:1, fontSize:18}}  variant="subtitle1" color="primary.dark">
                <span style={{fontWeight: 'bold'}}>Número de Cuenta: </span> 
                {maskedAccountNumber}
            </Typography>
            <Typography sx={{mb:1, fontSize:18}}  variant="subtitle1"color="primary.dark">
            <span  style={{fontWeight:'bold'}}> Saldo: $</span> 
                {cuenta.saldoTotal}  
            </Typography>
            <Typography sx={{mb:1, fontSize:18}}  variant="subtitle1" color="primary.dark">
            <span  style={{fontWeight:'bold'}}> Puntos: </span> 
                {cuenta.puntos}
            </Typography >
            <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
            <Link href={`/accountDetail/${cuenta.numCuenta}`} variant="body2">
            <Typography sx={{fontWeight:'bold'}}>Ver más</Typography>                 
            </Link>
            </Box>
        </CardContent>
        </Card>
    );
};

CardBankAccount.propTypes = {
    cuenta:PropTypes.object
};