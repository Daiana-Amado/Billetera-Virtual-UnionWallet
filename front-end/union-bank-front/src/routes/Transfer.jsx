import { Typography, Box, useMediaQuery } from "@mui/material";
import LayoutLoged from "../components/layouts/dawer-sidebar/LayoutLoged";
import { ComponentTransferForm } from "../components/form/transfer/ComponentTransferForm";

export const Transfer = () => {

  const isMobile = useMediaQuery('(max-width:600px)');

  return (
    <LayoutLoged>
      <Box sx={{display:'flex', justifyContent:'center', alignItems:'center', flexDirection:'column'}}>
        <Typography variant={!isMobile ? "h3" : "h4"} sx={{ mt:10, fontWeight: 'bold', mb: 5 }}>Transferir a otra cuenta: </Typography>     
        <ComponentTransferForm/>
      </Box>
      
    </LayoutLoged>      
  )
}