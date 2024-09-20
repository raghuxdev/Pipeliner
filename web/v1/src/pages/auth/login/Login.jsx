import React from 'react';
import HomeNavbar from '../../../components/navbars/HomeNavbar';
import FormWrapper from './components/FormWrapper';
import Form from './components/Form';

const Login = () => {
  return (
    <div className="w-full h-[100vh] flex justify-center items-center">
      <FormWrapper>
        <Form />
      </FormWrapper>
    </div>
  );
};

export default Login;
