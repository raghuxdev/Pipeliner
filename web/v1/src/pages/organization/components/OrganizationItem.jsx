import React from 'react';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import ImageIcon from '@mui/icons-material/Image';
import Avatar from '@mui/material/Avatar';
import CurrencyRupeeIcon from '@mui/icons-material/CurrencyRupee';
import { IconButton } from '@mui/material';
import Toggle from './Toggle';

const WalletBalance = ({ walletBalance }) => {
  return (
    <div className="font-semibold text-black flex gap-1 ">
      <div>
        <CurrencyRupeeIcon sx={{ width: 15 }} />
      </div>
      <div> {walletBalance}</div>
    </div>
  );
};
const OrganizationLabel = ({ organization }) => {
  return (
    <div className="font-light text-black flex">
      <div> {organization.displayName}</div>
    </div>
  );
};

const OrganizationItem = ({ organization }) => {
  return (
    <ListItem sx={{ width: '35%' }} className=" border rounded-lg ">
      <ListItemAvatar>
        <Avatar sx={{ background: 'black' }}>
          {organization.displayName[0].toUpperCase()}
        </Avatar>
      </ListItemAvatar>
      <ListItemText
        primary={<OrganizationLabel organization={organization} />}
        secondary={<WalletBalance walletBalance={organization.walletBalance} />}
      />
      <ListItemText
        primary={<div>Admin User</div>}
        secondary={<div>raghu.g@pipeliner.io</div>}
      />
      <ListItemText primary={<div>Users</div>} secondary={<div>100</div>} />
      <ListItemText primary={<div>Executions</div>} secondary={<div>20</div>} />

      <Toggle />
    </ListItem>
  );
};

export default OrganizationItem;
