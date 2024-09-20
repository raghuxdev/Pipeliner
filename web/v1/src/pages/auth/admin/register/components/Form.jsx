import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button, FormHelperText } from '@mui/material';
import { styles } from '../../../../../config/mui';
import { useFormik } from 'formik';
import useFormatMessage from '../../../../../hooks/useFormatMessage';
import useFieldValidator from '../../../../../hooks/useFieldValidator';
import { usePost } from '../../../../../hooks/usePost';
import {
  CREATE_ADMIN,
  LOGIN_ROUTE,
  ORGANIZATION_EXIST_CHECK,
  SERVER_URL,
} from '../../../../../config/url';
import { useNavigate } from 'react-router-dom';
import { useFetch } from '../../../../../hooks/useFetch';
const Form = () => {
  const formatMsg = useFormatMessage();
  const { data, error, loading, postData } = usePost();
  const [isOrganizationAlreadyExists, setIsOrganizationAlreadyExists] =
    useState(false);
  const email = formatMsg('form.emailFieldName');
  const organization = formatMsg('form.organizationFieldName');
  const password = formatMsg('form.passwordFieldName');
  const repassword = formatMsg('form.reenterPasswordFieldName');
  const navigate = useNavigate();

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
        await postData(SERVER_URL + CREATE_ADMIN, values);
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

  const handleOrganizationValidation = async () => {
    const { organization } = formik.values;
    const res = await useFetch(
      SERVER_URL + ORGANIZATION_EXIST_CHECK.replace('{name}', organization)
    );

    if (res.success) {
      setIsOrganizationAlreadyExists(true);
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
          onBlur={handleOrganizationValidation}
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
          label={formatMsg('form.emailFieldLabel')}
        />
        {formik.errors[email] ? (
          <FormHelperText sx={styles.errorText}>
            {formik.errors[email]}
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
      <Button type="submit" sx={styles.buttonPrimary}>
        Create
      </Button>
      {/* <Button sx={styles.buttonSecondary}>Already have workspace</Button> */}
    </form>
  );
};

export default Form;
