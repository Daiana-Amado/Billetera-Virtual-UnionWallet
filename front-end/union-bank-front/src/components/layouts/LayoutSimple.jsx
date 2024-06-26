import { AppBar, Box, Container } from '@mui/material';
import PropTypes from 'prop-types';
import Navbar from './navbar/Nabvar';

//to user without login 
export const LayoutSimple = ({children}) => {
    return (
        <Box >
            <AppBar position="fixed" >
                <Navbar/>
            </AppBar>
            <Container sx={{display:'flex', alignContent:'center', alignItems:'center',  height:'100vh'}}>
                {children}
            </Container>
        </Box>
    );    
}
LayoutSimple.propTypes = {
    children: PropTypes.node
};