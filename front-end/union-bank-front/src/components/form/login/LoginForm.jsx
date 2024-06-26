import {  useForm } from 'react-hook-form';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import VisibilityIcon from '@mui/icons-material/Visibility';
import { Container, Typography, TextField, Button, Link, Grid, Box, Paper, Dialog, DialogTitle, DialogContent, DialogActions, IconButton, InputAdornment } from '@mui/material';
import {  useNavigate } from 'react-router-dom';
import {  useState } from 'react';
import theme from '../../../theme/Theme';
import ErrorIcon from '@mui/icons-material/Error';
import { useUser } from "../../../context/UserContext";

const LoginForm = () => {
    
    const { setUserContext } = useUser();
    const { register, handleSubmit, formState: { errors } } = useForm();
    const navigate = useNavigate();
    const [showErrorDialog, setShowErrorDialog] = useState(false);
    const [showPassword, setShowPassword] = useState(false);
    const apiUrl =import.meta.env.VITE_BACKEND_URL;

    
    const onSubmit = async (data) => {         
        try {
            const response = await fetch(`${apiUrl}/api/v1/user/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    email: data.email,
                    password: data.password
                })
            });
            const responseData = await response.json();

            if (response.ok) {
                setUserContext(responseData);
                sessionStorage.setItem('user', JSON.stringify(responseData))
                navigate('/home');
            } else {
                throw new Error(responseData.message || 'Error al iniciar sesión');
            }
        }catch (error) {
            console.error('Error al inciar sesión: ', error)
            setShowErrorDialog(true)
        }  
    };

    const handleCloseErrorDialog = () => {
        setShowErrorDialog(false);
    };
    const handleTogglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };
    // const validatePassword = (event) => {
    //     const tecla = event.key;
    //     if (tecla !== 'Backspace' && (tecla === ' ' )) {
    //         event.preventDefault();
    //     }
    // };
    
    const validateInput= (event) => {
        const tecla = event.key;
        if (tecla !== 'Backspace' && (tecla === ' ' ) ) {
            event.preventDefault(); 
        }
    };

    return (
        <Container maxWidth="md" sx={{display:'flex', alignItems:"center", minHeight:600, overflowY: 'auto', mt:5}}>
            <Grid container spacing={2} alignItems="center">
            <Grid item xs={12} md={6}>
                <Box sx={{ display: 'flex', justifyContent: 'center' }}>
                <Paper elevation={3} sx={{ p: 2, maxWidth: '80%', mx: 'auto' }}>
                    <Typography variant="h4" align="center" gutterBottom>
                    Iniciar Sesión
                    </Typography>
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                            <TextField
                                {...register('email', {
                                required: 'Email es requerido',
                                pattern: {
                                    value: /^\S+@\S+\.\S{2,}$/i,
                                    message: 'Email inválido'
                                },
                                })}
                                label="Email"
                                fullWidth
                                onKeyDown={validateInput}
                                error={Boolean(errors.email)}
                                helperText={errors.email && errors.email.message}
                            />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    {...register('password', {
                                        required: 'Contraseña es requerida',                                   
                                        minLength:{value:6, message:'la contraseña es muy corta, deben ser 6 caracteres'},
                                        maxLength:{value:6,  message:'la contraseña es muy larga, deben ser 6 caracteres'}
                                    })}
                                    type={showPassword ? 'text' : 'password'}
                                    label="Contraseña"
                                    fullWidth
                                    onKeyDown={validateInput}
                                    error={Boolean(errors.password)}
                                    helperText={errors.password && errors.password.message}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton onClick={handleTogglePasswordVisibility}>
                                                    {showPassword ? <VisibilityOffIcon /> : <VisibilityIcon />}
                                                </IconButton>
                                            </InputAdornment>
                                        )
                                    }}
                                />
                            </Grid>
                            <Grid item xs={12}>
                            <Button type="submit" variant="contained" sx={{backgroundColor: theme.palette.secondary.main, color: theme.palette.primary.main, fontWeight:'bold', '&:hover': {
                                    backgroundColor: theme.palette.secondary.light, 
                                },}} fullWidth>
                                <Typography color='black'>
                                    Iniciar Sesión
                                </Typography>                            
                            </Button>
                            </Grid>
                            <Grid item xs={12}>
                            <Typography align="center" color='primary'>
                                <Link href="#" variant="body2">
                                ¿Olvidaste tu contraseña?
                                </Link>
                            </Typography>
                            </Grid>
                            <Grid item xs={12}>
                            <Typography align="center"  color='black'>
                                ¿No tienes una cuenta?{' '}
                                <Link href="/register" variant="body2">
                                Regístrate
                                </Link>
                            </Typography>
                            </Grid>
                        </Grid>

                    </form>
                </Paper>
                </Box>
            </Grid>
                <Grid item xs={12} md={6} className='hide-img' >
                    <Box sx={{ display: 'flex', justifyContent: 'center' }}>
                        <img src='/images/luces.jpg' alt="Login" style={{width: '80%', maxWidth: 400, height: 'auto', maxHeight: '80vh' }} />
                    </Box>
                </Grid>
            </Grid>
             {/* Ventana emergente de error */}
            <Dialog open={showErrorDialog} onClose={handleCloseErrorDialog} sx={{display:'flex', flexDirection:'column', justifyContent:'center'}}>
                <DialogTitle sx={{fontWeight:'bold', color:theme.palette.error.main}}><ErrorIcon sx={{color: theme.palette.error.main, alignItems:'center', mr:2}}/>Error de inicio de sesión </DialogTitle>
                <DialogContent >                    
                    <Typography >
                        Los datos ingresados son incorrectos. Por favor, revisalos de nuevo e inténtalo nuevamente.
                    </Typography>
                </DialogContent>
                <DialogActions>
                <Button 
                    onClick={handleCloseErrorDialog} 
                    sx={{backgroundColor: theme.palette.secondary.main, color: theme.palette.primary.main, fontWeight:'bold', '&:hover': {
                    backgroundColor: theme.palette.secondary.light, 
                },}}
                >
                    Aceptar
                </Button>
                </DialogActions>
            </Dialog>
        </Container>
    );
};

export default LoginForm;