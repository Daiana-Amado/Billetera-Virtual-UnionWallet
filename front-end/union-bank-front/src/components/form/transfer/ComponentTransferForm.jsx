/* eslint-disable no-unused-vars */
/* eslint-disable no-undef */
import { Container, TextField, Dialog, FormControl ,DialogActions, DialogContent, InputLabel,FormHelperText, DialogContentText, DialogTitle, Button, Select, MenuItem } from "@mui/material";
import { useForm, Controller} from "react-hook-form";
import { useState  } from "react";
import theme from "../../../theme/Theme";
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import { useAccounts } from "../../../context/AccountContext";
import { useUser } from "../../../context/UserContext";

export const ComponentTransferForm = () => {
    
    const formData = useForm({        
        originAccount: '',
        targetAccount: '',
        description: '',
        amount: ''
    });
    const {  control,   formState,  handleSubmit} = formData;
    const {errors, isValid} = formState;
    const [confirmationDialogOpen, setConfirmationDialogOpen] = useState(false);
    const [successDialogOpen, setSuccessDialogOpen] = useState(false);
    const [errorDialogOpen, setErrorDialogOpen] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const [formDataState, setFormDataState] = useState({});
    const {accounts} =useAccounts();
    const [transactionReference, setTransactionReference] = useState('');
    const apiUrl =import.meta.env.VITE_BACKEND_URL;
    const {userContext} = useUser();
    
    
    const handleOpenConfirmDialog = data => {
        setFormDataState(data);
        setConfirmationDialogOpen(true);
    };

    const handleTransfer = async () => {
        const payload = {
            nroCuentaOrigen: formDataState.originAccount,
            nroCuentaDestino: formDataState.targetAccount,
            monto: parseFloat(formDataState.amount),
            descripcion: formDataState.description
        };
        try {
            const response = await fetch(`${apiUrl}/api/v1/transferencia/transferir`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
            const responseData = await response.json();
            if (response.ok && responseData.status === "Transferencia Exitosa") {
                setTransactionReference(responseData.referencia); 
                //await sendTransferNotification(userContext.email, responseData.referencia);
                setSuccessDialogOpen(true);
            } else {
                throw new Error(responseData.error || 'Error al realizar la transferencia');
            }
        } catch (error) {
            console.error('Error al realizar la transferencia:', error);
            setErrorMessage(error.message);
            setErrorDialogOpen(true);
        }
        setConfirmationDialogOpen(false);
    };

    const handleAcceptSuccessDialog = () => {
        setSuccessDialogOpen(false);
        formData.reset(); 
    };
    
    const validateNumericInput = (event) => {
        const tecla = event.key;
        if (!/^\d$/.test(tecla) && tecla !== 'Backspace') {
            event.preventDefault();
        }
    };
    const validateDecimalNumericInput = (event) => {
        const tecla = event.key;
        if (!/^[\d.]$/.test(tecla) && tecla !== 'Backspace') {
            event.preventDefault();
        }
    };
    return (
        <Container
            maxWidth="md"
            sx={{
                alignItems: "center",
                minHeight: 600,
                overflowY: "auto",
                justifyItems: "center",
            }}
        >
        <form onSubmit={handleSubmit(handleOpenConfirmDialog)}>
            <Controller
                name="originAccount"
                control={control}
                defaultValue=""
                rules={{ required: "Este campo es requerido" }}
                render={({ field }) => (
                <FormControl variant="outlined" fullWidth sx={{ mb: 2, mt: 2 }}>
                    <InputLabel>Cuenta origen</InputLabel>
                    <Select
                        {...field}
                        label="Cuenta origen"
                        variant="outlined"
                        fullWidth
                        error={!!errors.originAccount}
                    >
                        <MenuItem value="" disabled>
                            Seleccionar cuenta origen
                        </MenuItem>
                            {accounts.map(account => (
                                <MenuItem key={account.numCuenta} value={account.numCuenta}>
                                    {account.numCuenta}
                                </MenuItem>
                            ))}
                    </Select>
                        {errors.originAccount && (
                            <FormHelperText error>{errors.originAccount.message}</FormHelperText>
                        )}
                    </FormControl>
                    )}
                />
            <Controller
                name="targetAccount"
                control={control}
                defaultValue=''
                rules={{ 
                    required: {value: true, message:"Este campo es requerido"}, 
                    minLength: {value:9, message: "El valor ingresado es muy corto"}, 
                    maxLength:{value:9, message:"El valor ingresado es muy largo"},
                }}
                render={({field}) =>(
                    <TextField
                        {...field}
                        label='Cuenta destino'
                        variant="outlined"                     
                        type="number"                        
                        value={field.value} 
                        fullWidth
                        sx={{mb:2, mt:2}}
                        onKeyDown={validateNumericInput}
                        error={!!errors.targetAccount}
                        helperText={ errors.targetAccount?.message}
                    />
                )}
            />
            <Controller
                name="description"
                control={control}
                defaultValue=''
                rules={{ 
                    required: {value: true, message:"Este campo es requerido"}, 
                    minLength: {value:4, message: "El valor ingresado es muy corto"}, 
                    maxLength:{value:30, message:"El valor ingresado es muy largo"},
                }}
                render={({field}) =>(
                    <TextField
                        {...field}
                        label='Descripción'
                        variant="outlined"
                        type="text"
                        value={field.value} 
                        fullWidth
                        sx={{mb:2, mt:2}}
                        error={!!errors.description}
                        helperText={ errors.description?.message}
                    />
                )}
            />
            <Controller
                name="amount"
                control={control}
                defaultValue=''
                rules={{ 
                    required: {value: true, message:"Este campo es requerido"}, 
                    minLength: {value:1, message: "El valor ingresado es muy corto"}, 
                    pattern: {
                        value: /^\d*\.?\d*$/, 
                        message: "Por favor, ingresa solo números enteros"
                    }
                }}
                render={({field}) =>(
                    <TextField
                        {...field}
                        label='Valor a transferir'
                        variant="outlined"
                        type="number"
                        value={field.value} 
                        fullWidth 
                        sx={{mb:2, mt:2}}
                        onKeyDown={validateDecimalNumericInput}
                        error={!!errors.amount}
                        helperText={ errors.amount?.message}
                    />
                )}
            />
            <Button
                type="submit"
                variant="contained" 
                fullWidth
                sx={{backgroundColor: theme.palette.secondary.main, color: theme.palette.primary.main, fontWeight:'bold', '&:hover': {
                    backgroundColor: theme.palette.secondary.light, 
                },}}
            >
                Confirmar
            </Button>
        </form>
        {/* Confirmación de transferencia */}
        <Dialog open={confirmationDialogOpen} onClose={() => setConfirmationDialogOpen(false)} sx={{textAlign:'center'}}>
            <DialogTitle sx={{ fontWeight: 'bold' }}>Confirmar Transferencia</DialogTitle>
            <DialogContent >
                <DialogContentText sx={{color:theme.palette.primary.dark}}><span style={{fontWeight:'bold'}}>La transferencia se hará de la cuenta origen n°:</span>  {formDataState.originAccount}</DialogContentText>
                <DialogContentText sx={{color:theme.palette.primary.dark}}><span  style={{fontWeight:'bold'}}>A la cuenta destino n°: </span> {formDataState.targetAccount}</DialogContentText>
                <DialogContentText sx={{color:theme.palette.primary.dark}}><span  style={{fontWeight:'bold'}}>Por concepto de: </span> {formDataState.description}</DialogContentText>
                
                <DialogContentText sx={{color:theme.palette.primary.dark}}><span  style={{fontWeight:'bold'}}>  por el valor de:  $ </span>{formDataState.amount}   </DialogContentText>                    
            </DialogContent>
            <DialogActions>
                <Button 
                    onClick={() => setConfirmationDialogOpen(false)}
                    sx={{backgroundColor: theme.palette.error.light, color: theme.palette.primary.dark, fontWeight:'bold' }}
                >
                    Cancelar
                </Button>
                <Button 
                    onClick={handleTransfer}
                    sx={{backgroundColor: theme.palette.secondary.main, color: theme.palette.primary.main, fontWeight:'bold' }}
                >
                    Enviar
                </Button>
            </DialogActions>
        </Dialog>
        {/* Éxito de transferencia */}
        <Dialog open={successDialogOpen} onClose={() => setSuccessDialogOpen(false)}>                
            <DialogContent sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center' , flexDirection:'column'}}>
            <DialogTitle sx={{ ml: 1, fontSize:40, fontWeight: 'bold', color:theme.palette.primary.main }}>¡Transacción exitosa! </DialogTitle>
                <DialogContentText > <CheckCircleOutlineIcon sx={{ fontSize: 48, color: 'green' }} /> </DialogContentText>               
                <DialogContentText sx={{ ml: 1, fontSize:20, color:theme.palette.primary.dark }}>El número de referencia de la transacción es: #{transactionReference}</DialogContentText>     
                <DialogContentText  sx={{ mt: 3 }} >Te hemos enviado un correo con los detalles de la transacción.</DialogContentText>                    
            </DialogContent>
            <DialogActions>
                <Button 
                    onClick={handleAcceptSuccessDialog}
                    sx={{backgroundColor: theme.palette.secondary.main, color: theme.palette.primary.main, fontWeight:'bold' }}
                >
                    Aceptar
                </Button>
            </DialogActions>
        </Dialog>
            {/* Error en la transferencia */}
        <Dialog open={errorDialogOpen} onClose={() => setErrorDialogOpen(false)}>
            <DialogTitle sx={{ fontWeight: 'bold' , color: theme.palette.error.main}}>Error en la Transferencia</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    {errorMessage}
                </DialogContentText>
            </DialogContent>                
            <DialogActions>
                <Button 
                    onClick={() => setErrorDialogOpen(false)}
                    sx={{backgroundColor: theme.palette.secondary.main, color: theme.palette.primary.main, fontWeight:'bold' }}
                >
                    Aceptar
                </Button>
            </DialogActions>
        </Dialog>
        </Container>
    );
};