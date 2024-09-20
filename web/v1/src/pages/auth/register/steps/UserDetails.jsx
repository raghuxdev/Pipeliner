import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button, FormHelperText } from '@mui/material';
import React from 'react';
import { styles } from '../../../../config/mui';

import useFormatMessage from '../../../../hooks/useFormatMessage';

const UserDetails = ({ formik }) => {
  const formatMsg = useFormatMessage();

  return (
    <form
      onSubmit={formik.handleSubmit}
      className="flex flex-col gap-5 p-4 w-[23rem]"
    >
      <TextField
        required
        name={formatMsg('form.emailFieldName')}
        value={formik.values.email}
        label={formatMsg('form.emailFieldLabel')}
        onChange={formik.handleChange}
        sx={styles.input}
      />

      {formik.errors.email ? (
        <FormHelperText sx={styles.errorText}>
          {formik.errors.email}
        </FormHelperText>
      ) : null}
      <TextField
        required
        name={formatMsg('form.passwordFieldName')}
        value={formik.values.password}
        sx={styles.input}
        onChange={formik.handleChange}
        label={formatMsg('form.passwordFieldLabel')}
      />
      <TextField
        required
        name={formatMsg('form.re-enterPasswordFieldName')}
        value={formik.values.password}
        sx={styles.input}
        onChange={formik.handleChange}
        label={formatMsg('form.passwordFieldLabel')}
      />
      {formik.errors.password ? (
        <FormHelperText sx={styles.errorText}>
          {formik.errors.password}
        </FormHelperText>
      ) : null}
      <Button type="submit" sx={styles.buttonPrimary}>
        Next
      </Button>
      <Button sx={styles.buttonSecondary}>Create Account</Button>
    </form>
  );
};

export default UserDetails;
