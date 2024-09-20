import React from 'react';

const Feature = ({ feature }) => {
  const { icon, featureTitle, featureDesc } = feature;
  return (
    <div className="text-white w-1/3 p-8 flex flex-col gap-3">
      <div>{icon}</div>
      <div className="flex flex-col gap-2">
        <div className=" text-lg  font-medium">{featureTitle}</div>
        <div className="text-sm  font-thin">{featureDesc}</div>
      </div>
    </div>
  );
};

export default Feature;
