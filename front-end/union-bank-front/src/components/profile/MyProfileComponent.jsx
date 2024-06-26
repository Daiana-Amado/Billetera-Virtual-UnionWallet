import { Paper, Container, Typography, useMediaQuery, CircularProgress, Box } from "@mui/material";
import { useUser } from "../../context/UserContext";
import { useEffect } from "react";

export const MyProfileComponent = () => {
    const { setUserContext, userContext } = useUser();
    const isMobile = useMediaQuery('(max-width:600px)');

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const day = date.getDate();
        const month = date.getMonth() + 1; 
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    };
    useEffect(() => {
        const userString = sessionStorage.getItem('user');
        if (userString) {
            const user = JSON.parse(userString);
            setUserContext(user); 
        }
    }, [setUserContext]);

    if (!userContext) {
        return <Box sx={{ display: 'flex' }}>
        <CircularProgress />
      </Box>
    }
    return (
        <Container sx={{display:'flex', flexDirection: 'column', justifyContent:'center', alignItems:'center', maxWidth:'80vw',}}>
            <Typography  variant={isMobile ? "h4" : "h3"}  sx={{ mb:4, fontWeight:'bold'}}>Mi perfil</Typography>
            <Paper sx={{display:'flex', flexDirection:'column', p:5, }}>
                <Typography sx={{fontSize:20, mb:2}}>
                    <span style={{fontWeight:'bold', fontSize:20}}> Nombre completo: </span>
                    {`${userContext.name} ${userContext.lastName}`}
                </Typography>
                <Typography  sx={{fontSize:20, mb:2}}>
                    <span style={{fontWeight:'bold', fontSize:20}}>User Name: </span>
                    {userContext.user.userName}</Typography>
                <Typography  sx={{fontSize:20, mb:2}}>
                <span style={{fontWeight:'bold', fontSize:20}}>Documento de identidad n°: </span>                    
                    {userContext.num_dni} 
                </Typography>
                <Typography  sx={{fontSize:20, mb:2}}>
                <span style={{fontWeight:'bold', fontSize:20}}>Telefono: </span>  
                    {userContext.phone} 
                </Typography>
                <Typography  sx={{fontSize:20, mb:2}}>
                <span style={{fontWeight:'bold', fontSize:20}}>Fecha de nacimiento:  </span>                     
                    {formatDate(userContext.birth)} 
                </Typography>
                <Typography  sx={{fontSize:20, mb:2}}>
                <span style={{fontWeight:'bold', fontSize:20}}> Correo electrónico:  </span>   
                    {userContext.email} 
                </Typography>
            </Paper>
        </Container>
    )
}