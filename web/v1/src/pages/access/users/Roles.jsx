import React from 'react';

import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
const Roles = ({ roles }) => {
  return (
    <Autocomplete
      sx={{ minWidth: 420 }}
      multiple
      id="tags-outlined"
      options={roles}
      getOptionLabel={(role) => role.key}
      defaultValue={roles}
      filterSelectedOptions
      renderInput={(params) => (
        <TextField {...params} label="Roles" placeholder="Favorites" />
      )}
    />
  );
};

export default Roles;
