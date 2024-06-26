import { Box, Toolbar } from "@mui/material";

const Navbar = () => {
  return (
    <Toolbar
      sx={{
        display: "flex",
        justifyContent: "flex-start",
        alignItems: "center",
      }}
    >
      <Box sx={{display:'flex', alignItems:'center'}}>    
        <img src="/images/SoloBlanco.svg" alt="Logo" style={{ height:50}} />
        <img src="/images/UnionWallet.svg" alt="Logo" style={{ height: 55}} />
      </Box> 
    </Toolbar>
  );
};

export default Navbar;
