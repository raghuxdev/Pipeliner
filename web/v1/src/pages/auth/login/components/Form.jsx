import React, { useEffect, useState } from 'react';

import UserDetails from '../steps/UserDetails';
import OtpStep from '../../common/OtpStep';
import useFieldValidator from '../../../../hooks/useFieldValidator';
import { useFormik } from 'formik';
import useFormatMessage from '../../../../hooks/useFormatMessage';
import { usePost } from '../../../../hooks/usePost';
import { useNavigate } from 'react-router-dom';
import {
  DASHBOARD_ROUTE,
  LOGIN_ENDPOINT,
  SERVER_URL,
} from '../../../../config/url';
const Form = () => {
  const formatMsg = useFormatMessage();
  const [step, setStep] = useState(1);
  const { data, postData, error, loading } = usePost();
  const navigate = useNavigate();
  useEffect(() => {
    console.log(data);
    if (data) {
      //TODO: Handle Otp verfication using Email
      navigate(DASHBOARD_ROUTE);
    }
  }, [data]);
  const requiredValidationFieldsForOne = {
    [formatMsg('form.emailFieldName')]: true,
    [formatMsg('form.passwordFieldName')]: true,
  };
  const requiredValidationFieldsForTwo = {
    [formatMsg('form.emailFieldName')]: true,
    [formatMsg('form.otpFieldName')]: true,
  };
  const formik = useFormik({
    initialValues: {
      email: '',
      password: '',
      otp: '',
    },
    validate: (values) => {
      switch (step) {
        case 1:
          return useFieldValidator(values, requiredValidationFieldsForOne);
        case 2:
          return useFieldValidator(values, requiredValidationFieldsForTwo);
      }
    },
    onSubmit: async (values) => {
      if (step == 1) {
        await handleUserPasswordSubmit(values);
      }
    },
  });
  const handleUserPasswordSubmit = async (values) => {
    await postData(SERVER_URL + LOGIN_ENDPOINT, values);
  };

  const CurrentStep = () => {
    return step == 1 ? (
      <UserDetails formik={formik} error={error} />
    ) : (
      <OtpStep formik={formik} />
    );
  };

  return (
    <div className="border p-8  rounded-sm">
      <div className="font-semibold text-center flex flex-col gap-2  ">
        <div className="text-2xl">Sign up</div>
        <div className=" font-light">Do more than business!</div>
      </div>
      {CurrentStep()}
    </div>
  );
};

export default Form;
