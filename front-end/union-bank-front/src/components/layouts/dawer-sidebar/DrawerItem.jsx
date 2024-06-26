import PropTypes from 'prop-types';
import { ListItemButton, ListItemIcon, ListItemText } from '@mui/material';
import { Link } from 'react-router-dom';
import theme from '../../../theme/Theme';

function DrawerItem(props) {
  const { text, icon, to} = props;

  return (
    <ListItemButton
      component={Link}
      to={to}
      sx={{
        '&:hover': {
          backgroundColor: theme.palette.secondary.main,
        },
      }}
    >
      <ListItemIcon>{icon}</ListItemIcon>
      <ListItemText primary={text} />
    </ListItemButton>
  );
}

DrawerItem.propTypes = {
  text: PropTypes.string.isRequired,
  icon: PropTypes.node.isRequired,
  to: PropTypes.string.isRequired,
  selected:PropTypes.bool,
  onClick:PropTypes.func
};

export default DrawerItem;
