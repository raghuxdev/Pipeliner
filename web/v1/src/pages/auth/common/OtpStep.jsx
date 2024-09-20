import TextField from '@mui/material/TextField';
import { Button, FormHelperText } from '@mui/material';
import React from 'react';
import { styles } from '../../../config/mui';
import useFormatMessage from '../../../hooks/useFormatMessage';
const OtpStep = ({ formik }) => {
  const formatMsg = useFormatMessage();
  return (
    <form
      onSubmit={formik.handleSubmit}
      className="flex flex-col gap-5 p-4 w-[23rem]"
    >
      <TextField
        required
        name={formatMsg('form.otpFieldName')}
        value={formik.values.otp}
        label={formatMsg('form.otpFieldLabelName')}
        onChange={formik.handleChange}
        sx={styles.input}
      />
      {formik.errors.otp ? (
        <FormHelperText sx={styles.errorText}>
          {formik.errors.otp}
        </FormHelperText>
      ) : null}
      <Button sx={styles.buttonPrimary}>Submit</Button>
      <Button sx={styles.buttonSecondary}>Back</Button>
    </form>
  );
};

export default OtpStep;
