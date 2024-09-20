import React, { useEffect, useState } from 'react';

import TextField from '@mui/material/TextField';
import { Button, FormHelperText } from '@mui/material';

import { useFormik } from 'formik';

import { useNavigate } from 'react-router-dom';
import { styles } from '../../../../config/mui';
import useFormatMessage from '../../../../hooks/useFormatMessage';
import { usePost } from '../../../../hooks/usePost';
import useFieldValidator from '../../../../hooks/useFieldValidator';
import CircularProgress from '@mui/material/CircularProgress';
import {
  EMAIL_EXIST_CHECK,
  LOGIN_ROUTE,
  ORGANIZATION_EXIST_CHECK,
  SERVER_URL,
} from '../../../../config/url';
import { useFetch } from '../../../../hooks/useFetch';
const Form = ({ url }) => {
  const formatMsg = useFormatMessage();
  const { data, error, loading, postData } = usePost();
  const [isOrganizationAlreadyExists, setIsOrganizationAlreadyExists] =
    useState(false);
  const email = formatMsg('form.emailFieldName');
  const organization = formatMsg('form.organizationFieldName');
  const password = formatMsg('form.passwordFieldName');
  const repassword = formatMsg('form.reenterPasswordFieldName');
  const navigate = useNavigate();
  const [isEmailAlreadyExists, setIsEmailAlreadyExists] = useState(false);

  const requiredValidation = {
    [email]: true,
    [password]: true,
    [organization]: true,
    [repassword]: true,
  };

  const formik = useFormik({
    initialValues: {
      [email]: '',
      [password]: '',
      [organization]: '',
    },
    validate: (values) => useFieldValidator(values, requiredValidation),
    onSubmit: async (values) => {
      if (!isOrganizationAlreadyExists) {
        await postData(url, values);
      }
    },
  });

  useEffect(() => {
    console.log(data);
    if (data.success) {
      navigate(LOGIN_ROUTE);
    } else {
    }
  }, [data, error]);

  const handleExistenceValidation = async (e) => {
    switch (e.target.name) {
      case 'organization':
        const { organization } = formik.values;
        if (organization) {
          const res = await useFetch(
            SERVER_URL +
              ORGANIZATION_EXIST_CHECK.replace('{name}', organization)
          );

          if (res.success) {
            setIsOrganizationAlreadyExists(true);
          }
        }
      case 'email':
        const { email } = formik.values;
        if (email) {
          const res = await useFetch(
            SERVER_URL + EMAIL_EXIST_CHECK.replace('{email}', email)
          );
          if (res.success) {
            setIsEmailAlreadyExists(true);
          }
        }
    }
  };
  return (
    <form
      onSubmit={formik.handleSubmit}
      className="flex flex-col gap-5 p-4 w-[23rem]"
    >
      <div className="font-semibold text-center flex flex-col gap-2  ">
        <div className="text-2xl">Sign up</div>
        <div className=" font-light">Do more than business!</div>
      </div>

      <div>
        <TextField
          required
          className="w-full"
          sx={styles.input}
          onBlur={handleExistenceValidation}
          onChange={(values) => {
            setIsOrganizationAlreadyExists(false);
            formik.handleChange(values);
          }}
          name={organization}
          label={formatMsg('form.organizationFieldLabel')}
        />
        {formik.errors[organization] || isOrganizationAlreadyExists ? (
          <FormHelperText sx={styles.errorText}>
            {formik.errors[organization] || 'Organization Already Exists!'}
          </FormHelperText>
        ) : null}
      </div>
      <div>
        <TextField
          className="w-full"
          required
          sx={styles.input}
          value={formik.values[email]}
          onChange={formik.handleChange}
          name={email}
          onBlur={handleExistenceValidation}
          label={formatMsg('form.emailFieldLabel')}
        />
        {formik.errors[email] || isEmailAlreadyExists ? (
          <FormHelperText sx={styles.errorText}>
            {formik.errors[email] || 'Email is associated with Another account'}
          </FormHelperText>
        ) : null}
      </div>
      <div>
        <TextField
          required
          className="w-full"
          value={formik.values[password]}
          sx={styles.input}
          onChange={formik.handleChange}
          label={formatMsg('form.passwordFieldLabel')}
          name={password}
          type={password}
        />
        {formik.errors[password] ? (
          <FormHelperText sx={styles.errorText}>
            {formik.errors[password]}
          </FormHelperText>
        ) : null}
      </div>

      <div>
        <TextField
          required
          className="w-full"
          sx={styles.input}
          onChange={formik.handleChange}
          label={formatMsg('form.reenterPasswordFieldLabel')}
          name={repassword}
          type={password}
          value={formik.values[repassword]}
        />
        {formik.errors[repassword] ? (
          <FormHelperText sx={styles.errorText}>
            {formik.errors[repassword]}
          </FormHelperText>
        ) : null}
      </div>
      {error ? (
        <FormHelperText
          className="flex justify-center items-center"
          sx={styles.errorText}
        >
          {error}
        </FormHelperText>
      ) : null}
      <Button
        defaultValue={true}
        disabled={isEmailAlreadyExists || isOrganizationAlreadyExists}
        type="submit"
        className={`h-[3.5rem]`}
        sx={styles.buttonPrimary}
      >
        {loading ? <CircularProgress size={20} /> : 'Create'}
      </Button>
    </form>
  );
};

export default Form;
