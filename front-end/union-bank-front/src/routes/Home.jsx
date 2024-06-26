import { Typography, Box, useMediaQuery } from "@mui/material";
import LayoutLoged from "../components/layouts/dawer-sidebar/LayoutLoged";
import DataGridDemo from "../components/transactions/TableTransactions";
import BankAccount from "../components/home/BankAccount";

export const Home = () => {
  const isMobile = useMediaQuery('(max-width:600px)');
  return (
    <LayoutLoged>
      <Box sx={{display:'flex', alignContent:'center', flexDirection:'column', alignItems:'center',}}>
        <Typography variant={!isMobile ? "h3" : "h4"} sx={{mt:10,mb:5, fontWeight:'bold'}}> Inicio  </Typography>     
        <BankAccount/>
        <DataGridDemo/>
      </Box>        
    </LayoutLoged>      
  )
}
