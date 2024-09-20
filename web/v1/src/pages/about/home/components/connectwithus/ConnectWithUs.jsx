import { Button } from '@mui/material';
import React from 'react';

const ConnectWithUs = () => {
  return (
    <div className="w-full bg-gray-50 p-20 flex justify-center items-center flex-col gap-4">
      <div className=" font-semibold  text-2xl">
        Stay in the loop – let's chat!
      </div>
      <div className="text-base font-light">
        Got a unique need or a one-of-a-kind feature request? We're here to
        help! Reach out to our expert team anytime for assistance
      </div>
      <Button
        sx={{
          backgroundColor: 'black',
          color: 'white',
          ':hover': {
            background: 'gray',
          },
          borderRadius: 10,
          padding: 1.5,
        }}
      >
        Connect
      </Button>
    </div>
  );
};

export default ConnectWithUs;
