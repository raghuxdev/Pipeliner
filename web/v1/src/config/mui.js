import { styled, useTheme } from '@mui/material/styles';
export const styles = {
  input: {
    '& .MuiOutlinedInput-root': {
      '& fieldset': {
        borderColor: 'black',
      },
      '&:hover fieldset': {
        borderColor: 'black',
      },
      '&.Mui-focused fieldset': {
        borderColor: 'black',
      },
    },
    '& .MuiInputLabel-root': {
      color: 'black',
    },
    '& .MuiInputLabel-root.Mui-focused': {
      color: 'black',
    },
  },
  buttonPrimary: {
    backgroundColor: 'black',
    color: 'white',
    ':hover': {
      background: 'gray',
    },

    padding: 1.7,
  },
  buttonSecondary: {
    backgroundColor: 'white',
    color: 'black',
    padding: 1.7,
  },
  getStartedButton: {
    backgroundColor: 'black',
    color: 'white',
    ':hover': {
      background: 'gray',
    },
    borderRadius: 10,
    padding: 1.5,
  },
  errorText: { color: 'darkred' },
  //TODO: look after this
  // openedMixin: (theme) => ({
  //   width: drawerWidth,
  //   transition: theme.transitions.create('width', {
  //     easing: theme.transitions.easing.sharp,
  //     duration: theme.transitions.duration.enteringScreen,
  //   }),
  //   overflowX: 'hidden',
  // }),
  // closedMixin: (theme) => ({
  //   transition: theme.transitions.create('width', {
  //     easing: theme.transitions.easing.sharp,
  //     duration: theme.transitions.duration.leavingScreen,
  //   }),
  //   overflowX: 'hidden',
  //   width: `calc(${theme.spacing(7)} + 1px)`,
  //   [theme.breakpoints.up('sm')]: {
  //     width: `calc(${theme.spacing(8)} + 1px)`,
  //   },
  // }),
  // DrawerHeader: styled('div')(({ theme }) => ({
  //   display: 'flex',
  //   alignItems: 'center',
  //   justifyContent: 'flex-end',
  //   padding: theme.spacing(0, 1),
  //   // necessary for content to be below app bar
  //   ...theme.mixins.toolbar,
  // })),
  // AppBar: styled(MuiAppBar, {
  //   shouldForwardProp: (prop) => prop !== 'open',
  // })(({ theme, open }) => ({
  //   zIndex: theme.zIndex.drawer + 1,
  //   transition: theme.transitions.create(['width', 'margin'], {
  //     easing: theme.transitions.easing.sharp,
  //     duration: theme.transitions.duration.leavingScreen,
  //   }),
  //   ...(open && {
  //     marginLeft: drawerWidth,
  //     width: `calc(100% - ${drawerWidth}px)`,
  //     transition: theme.transitions.create(['width', 'margin'], {
  //       easing: theme.transitions.easing.sharp,
  //       duration: theme.transitions.duration.enteringScreen,
  //     }),
  //   }),
  // })),
  // Drawer: styled(MuiDrawer, {
  //   shouldForwardProp: (prop) => prop !== 'open',
  // })(({ theme, open }) => ({
  //   width: drawerWidth,
  //   flexShrink: 0,
  //   whiteSpace: 'nowrap',
  //   boxSizing: 'border-box',
  //   ...(open && {
  //     ...this.openedMixin(theme),
  //     '& .MuiDrawer-paper': this.openedMixin(theme),
  //   }),
  //   ...(!open && {
  //     ...closedMixin(theme),
  //     '& .MuiDrawer-paper': this.closedMixin(theme),
  //   }),
  // })),
};
