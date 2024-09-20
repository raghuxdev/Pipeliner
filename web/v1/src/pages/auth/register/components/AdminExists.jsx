import { Button } from '@mui/material';
import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { styles } from '../../../../../config/mui';
import { LOGIN_ROUTE } from '../../../../../config/url';

const AdminExists = () => {
  return (
    <div className="flex justify-center items-center flex-col gap-3">
      <div className="text-xl font-light">Admin User Already Exists!</div>
      <Link className="font-semibold  " to={LOGIN_ROUTE}>
        Login
      </Link>
    </div>
  );
};

export default AdminExists;
