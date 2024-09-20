import React from 'react';
import FormWrapper from '../login/components/FormWrapper';
import Form from './components/Form';
import {
  CREATE_SUB_ADMIN_AND_ORGANIZATION,
  SERVER_URL,
} from '../../../config/url';

const Register = () => {
  return (
    <div className="w-full h-[100vh] flex justify-center items-center">
      <FormWrapper>
        <Form url={SERVER_URL + CREATE_SUB_ADMIN_AND_ORGANIZATION} />
      </FormWrapper>
    </div>
  );
};

export default Register;
