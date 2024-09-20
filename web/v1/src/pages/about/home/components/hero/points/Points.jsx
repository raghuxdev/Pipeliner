import React from 'react';

const Points = ({ point }) => {
  const { icon, boldText, text } = point;

  return (
    <div className="flex gap-1 w-[16rem] lg:w-auto ">
      {icon}
      <div className="font-semibold">{boldText}</div>
      <div>{text}</div>
    </div>
  );
};

export default Points;
