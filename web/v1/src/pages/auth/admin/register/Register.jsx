import React, { useEffect, useState } from 'react';

import { useFetch } from '../../../../hooks/useFetch';
import { ADMIN_CHECK, CREATE_ADMIN, SERVER_URL } from '../../../../config/url';
import FormWrapper from '../../login/components/FormWrapper';
import Form from './components/Form';
import AdminExists from './components/AdminExists';

const AdminRegister = () => {
  const [isSuperAdminExists, setIsSuperAdminExists] = useState(false);
  useEffect(() => {
    const fetchAdminCheck = async () => {
      const adminCheckRes = await useFetch(SERVER_URL + ADMIN_CHECK);
      if (adminCheckRes.success) {
        setIsSuperAdminExists(true);
      } else {
        setIsSuperAdminExists(false);
      }
    };
    fetchAdminCheck();
  }, []);
  const CurrentView = () => {
    return isSuperAdminExists ? (
      <AdminExists />
    ) : (
      <Form url={SERVER_URL + CREATE_ADMIN} />
    );
  };
  return (
    <div className="w-full h-[100vh] flex justify-center items-center">
      <FormWrapper>
        <CurrentView />
      </FormWrapper>
    </div>
  );
};

export default AdminRegister;
