import React from 'react';
import Feature from './Feature';
import DashboardIcon from '@mui/icons-material/Dashboard';

const Features = () => {
  const features = [
    {
      icon: <DashboardIcon />,
      featureTitle: 'Access Management',
      featureDesc:
        'Admin dashboard allows you to manage users using interactive solutions',
    },
    {
      icon: <DashboardIcon />,
      featureTitle: 'Role Based Access',
      featureDesc:
        'Role and Permission based access allows organizations to get full control over it.',
    },
    {
      icon: <DashboardIcon />,
      featureTitle: 'Access Management',
      featureDesc:
        'Admin dashboard allows you to manage users using interactive solutions',
    },
    {
      icon: <DashboardIcon />,
      featureTitle: 'Access Management',
      featureDesc:
        'Admin dashboard allows you to manage users using interactive solutions',
    },
    {
      icon: <DashboardIcon />,
      featureTitle: 'Access Management',
      featureDesc:
        'Admin dashboard allows you to manage users using interactive solutions',
    },
    {
      icon: <DashboardIcon />,
      featureTitle: 'Access Management',
      featureDesc:
        'Admin dashboard allows you to manage users using interactive solutions',
    },
  ];
  return (
    <div className="w-full bg-black flex justify-center items-center flex-col p-10">
      <div className="flex justify-center items-center flex-col  gap-2">
        <div className="text-white font-semibold text-xl ">
          Easy to Use and Safe
        </div>
        <div className="text-white text-lg  font-light">
          Flowpium provides you with a single consolidated platform to make
          Automation a breeze.
        </div>
      </div>
      <div className=" flex  justify-between flex-wrap">
        {features.map((feature) => {
          return <Feature feature={feature} />;
        })}
      </div>
    </div>
  );
};

export default Features;
