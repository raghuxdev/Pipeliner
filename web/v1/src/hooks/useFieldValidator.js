const useFieldValidator = (values, requestedValidationField) => {
  const errors = {};

  if (requestedValidationField.email) {
    if (!values.email) {
      errors.email = 'Required';
    } else if (
      !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(values.email)
    ) {
      errors.email = 'Invalid email address';
    }
  }
  if (requestedValidationField.password) {
    if (!values.password) {
      errors.password = 'Required';
    } else if (values.password.length < 8) {
      errors.password = 'Password Length Must Be Above 8';
    }
  }
  if (requestedValidationField.otp) {
    if (!values.otp) {
      errors.otp = 'Required';
    } else if (values.otp.length < 4) {
      errors.otp = 'Otp Length Must Be Above 4';
    }
  }
  if (requestedValidationField.repassword) {
    if (values.password != values.repassword) {
      errors.repassword = "Password Doesn't Match";
    }
  }
  if (requestedValidationField.organization) {
    if (!values.organization) {
      errors.organization = 'Organization Required!';
    } else if (values.organization.length <= 3) {
      errors.organization = 'Organization Name Length Must Be Above 3';
    }
  }

  return errors;
};

export default useFieldValidator;
