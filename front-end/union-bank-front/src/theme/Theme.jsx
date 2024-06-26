import { createTheme } from '@mui/material/styles';
import { red } from '@mui/material/colors';

const theme = createTheme({
  palette: {
    primary: {
      main: '#1A237E', // dark blue - navbar, footer y sidebar-menú
      dark: '#000000' // black to texts
    },
    secondary: {
      main: '#4ED7FC', //actions - light blue
      light: '#2196F3' // azul medio
    },
    error: {
      main: red.A700,
    },
  },
  breakpoints: {
    values: {
      xs: 0,
      sm: 600,
      md: 960,
      lg: 1280,
      xl: 1920,
    },
  },
  overrides: {
    MuiCssBaseline: {
      '@global': {
        body: {
          background: 'linear-gradient(to right, #1A237E, #4ED7FC)' // degradado de azul oscuro a azul claro
        },
      },
    },
  },
});

export default theme;