import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button, FormHelperText } from '@mui/material';
import React from 'react';
import { styles } from '../../../../config/mui';

import useFormatMessage from '../../../../hooks/useFormatMessage';

const UserDetails = ({ formik, error, loading }) => {
  const formatMsg = useFormatMessage();

  return (
    <form
      onSubmit={formik.handleSubmit}
      className="flex flex-col gap-5 p-4 w-[23rem]"
    >
      <div>
        <TextField
          required
          name={formatMsg('form.emailFieldName')}
          value={formik.values.email}
          label={formatMsg('form.emailFieldLabel')}
          onChange={formik.handleChange}
          className="w-full"
          sx={styles.input}
        />

        {formik.errors.email ? (
          <FormHelperText sx={styles.errorText}>
            {formik.errors.email}
          </FormHelperText>
        ) : null}
      </div>
      <div>
        <TextField
          required
          name={formatMsg('form.passwordFieldName')}
          value={formik.values.password}
          sx={styles.input}
          className="w-full"
          onChange={formik.handleChange}
          label={formatMsg('form.passwordFieldLabel')}
        />
        {formik.errors.password ? (
          <FormHelperText sx={styles.errorText}>
            {formik.errors.password}
          </FormHelperText>
        ) : null}
      </div>
      {error && (
        <FormHelperText
          className="flex justify-center items-center"
          sx={styles.errorText}
        >
          {error}
        </FormHelperText>
      )}

      <Button type="submit" sx={styles.buttonPrimary}>
        {loading ? 'Please Wait...' : 'Next'}
      </Button>
      <Button sx={styles.buttonSecondary}>Create Account</Button>
    </form>
  );
};

export default UserDetails;
