import * as React from 'react';
import Box from '@mui/material/Box';
import { Typography} from "@mui/material";
import theme from '../../../theme/Theme';
import InstagramIcon from '@mui/icons-material/Instagram';
import FacebookIcon from '@mui/icons-material/Facebook';
import YouTubeIcon from '@mui/icons-material/YouTube';

const FooterLayout = () => {
    return (
        <Box  
            component={"footer"}                         
            sx={{
                position: 'fixed',
                bottom: 0,
                left: 0,
                width: '100%',
                backgroundColor: theme.palette.primary.main, 
                zIndex: 1200, 
                textAlign: 'center',
                padding: '1rem 0',   
                mt:10,
                display: 'flex',
                flexDirection:'row',
                justifyContent:'space-evenly',
                alignItems:'center'
            }}            
        >
            <Typography sx={{color:'white'}}>Creative Commons  © 2024  Union Wallet. Todos los derechos reservados.  </Typography>      
            <Box 
                sx={{
                    display: 'flex',
                    flexDirection:'column',
                    justifyContent:'space-between',
                    alignItems:'center',
                }}
            >
                <Typography sx={{color:'white', mb:1}}>¡Siguenos en redes, como UnionWallet! </Typography>
                <Box   
                    sx={{
                        display: 'flex',
                        flexDirection:'row',
                        justifyContent:'space-between',
                        alignItems:'center',
                        width:'50%',
                    }}
                >
                    <InstagramIcon sx={{color:'white'}}/> 
                    <FacebookIcon sx={{color:'white'}}/>  
                    <YouTubeIcon sx={{color:'white'}}/> 
                </Box>
            </Box>               
        </Box>
    );
};
export default FooterLayout;
