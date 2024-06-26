import { Typography, Box, useMediaQuery, CircularProgress } from "@mui/material";
import LayoutLoged from "../components/layouts/dawer-sidebar/LayoutLoged";
import { DetailView } from "../components/detail/DetailView";
import { useParams } from 'react-router-dom';
import { useAccounts } from "../context/AccountContext";
import { useEffect } from "react";

export const AccountDetail = () => {
  
  const { id } = useParams();
  const { accounts, loadAccount, loading } = useAccounts();
  const isMobile = useMediaQuery('(max-width:600px)');
  //const account = accounts.length > 0 ? accounts[0] : null;
  const account = accounts.find(acc => acc.numCuenta.toString() === id);
  
  useEffect(() => {
    if (id) {
        loadAccount([id]); 
    }
}, [id, loadAccount]);

  return (
    <LayoutLoged>
      <Box sx={{display:'flex',flexDirection:'column', alignItems:'center', height:'100vh', maxWidth:'80vw'}}>
        <Typography  variant={isMobile ? "h4" : "h3"} sx={{mt:10, fontWeight:'bold', mb:7}}>Detalle de la cuenta</Typography>
        {!loading && account ? <DetailView cuenta={account}/>:<Box sx={{ display: 'flex' }}>
      <CircularProgress />
    </Box>}
      </Box>      
    </LayoutLoged>    
  );
};
