import React, { useEffect, useState } from 'react';
import { useFetch } from '../../hooks/useFetch';
import { ORGANIZATIONS, SERVER_URL } from '../../config/url';
import Typography from '@mui/material/Typography';
import { List, Stack } from '@mui/material';
import DashboardLayout from '../../layout/DashboardLayout';
import OrganizationItem from './components/OrganizationItem';
import OrganizationTable from './components/OrganizationTable';

const Organizations = () => {
  const [organizations, setOrganizations] = useState([]);
  useEffect(() => {
    const fetch = async () => {
      const response = await useFetch(SERVER_URL + ORGANIZATIONS);
      if (response.success) {
        setOrganizations(response.data);
      }
    };
    fetch();
  }, []);

  return (
    <DashboardLayout>
      {/* <List className="flex gap-3 flex-wrap " sx={{ width: '100%' }}>
        {organizations.map((organization) => {
          return <OrganizationItem organization={organization} />;
        })}
      </List> */}
      <OrganizationTable
        organizations={organizations}
        setOrganizations={setOrganizations}
      />
    </DashboardLayout>
  );
};

export default Organizations;
