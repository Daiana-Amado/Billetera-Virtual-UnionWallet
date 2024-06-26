import DrawerItem from './DrawerItem';
import HomeIcon from '@mui/icons-material/Home';
import OutboxIcon from '@mui/icons-material/Outbox';
import PersonOutlineIcon from '@mui/icons-material/PersonOutline';
import LogoutIcon from '@mui/icons-material/Logout';
import { Box, CssBaseline, Drawer, Toolbar, AppBar, IconButton, List, Avatar, ListItemIcon, ListItemText, ListItemButton } from '@mui/material';
import PropTypes from 'prop-types';
import MenuIcon from '@mui/icons-material/Menu';
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import theme from '../../../theme/Theme';
import useMediaQuery from '@mui/material/useMediaQuery';
import { useUser } from '../../../context/UserContext';


const drawerWidth = 240;

//to user  loged
function LayoutLoged(props) {

  const { children } = props;
  const [mobileOpen, setMobileOpen] = useState(false);
  const isMobile = useMediaQuery(theme.breakpoints.down('sm'));
  const { setUserContext } = useUser();
  const {userContext} = useUser();

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };
    const handleCloseSession= () => {      
        sessionStorage.removeItem('user');
        setUserContext(null);
        navigator
    }
  const drawer = (
    <Box>
      <Toolbar />
        <List>
        <DrawerItem text="Inicio" icon={<HomeIcon />} to="/home"  />
        <DrawerItem text="Transferir" icon={<OutboxIcon />} to="/transfer" />
        <DrawerItem text="Mi Perfil" icon={<PersonOutlineIcon />} to={`/myProfile`}/>
        <ListItemButton 
          sx={{
              '&:hover': {
                backgroundColor: theme.palette.secondary.main,
              },
            }} 
          onClick={handleCloseSession} 
          component={Link} 
          to="/login"
        >
          <ListItemIcon >
            <LogoutIcon />
          </ListItemIcon>
          <ListItemText primary="Cerrar Sesión" />
        </ListItemButton>
        </List>
    </Box>
  );
  const avatarLetter = userContext ? userContext.name.charAt(0).toUpperCase() : '';
  useEffect(() => {
    const userString = sessionStorage.getItem('user');
    if (userString) {
        const user = JSON.parse(userString);
        setUserContext(user); 
    }
}, [setUserContext]);

  return (
    <Box sx={{ display: 'flex', mb:10 }}>
      <CssBaseline />
      <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
        <Toolbar sx={{display:'flex', justifyContent:'space-between'}}>
          <IconButton aria-label="open drawer" edge="start" onClick={handleDrawerToggle} sx={{ mr: 2, display: { sm: 'none', color:'white' } }}>
          <MenuIcon />
          </IconButton>
          <Box sx={{display:'flex', alignItems:'center'}}>
            <Link to='/home' style={{ textDecoration: 'none' }}>          
              <img src="/images/SoloBlanco.svg" alt="Logo" style={{ height:55}} />
              <img src="/images/UnionWallet.svg" alt="Logo" style={{ height: 60}} />
            </Link>    
          </Box>     
          <Box sx={{display:'flex',}}>
            <Link to='/myProfile' style={{ textDecoration: 'none' }}>
              <Avatar  sx={{ml:2, backgroundColor: theme.palette.secondary.main, color:'black'}}  >  {avatarLetter}</Avatar> 
            </Link>
          </Box>       
        </Toolbar>
      </AppBar>
      <Drawer
        variant={isMobile ? 'temporary' : 'permanent'}
        open={mobileOpen}
        onClose={handleDrawerToggle}
        ModalProps={{ keepMounted: true }}
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          '& .MuiDrawer-paper': { width: drawerWidth },
          ...(isMobile && { '& .MuiDrawer-paper': { boxSizing: 'border-box' } })
        }}
      >
        {drawer}
      </Drawer>
      <Box component="main" sx={{ flexGrow: 1, p: 3, height:'100vh', width: { sm: `calc(100% - ${drawerWidth}px)` } }}>
        {children}        
      </Box>      
    </Box>   
  );
}
LayoutLoged.propTypes = {
    children: PropTypes.node
};

export default LayoutLoged;