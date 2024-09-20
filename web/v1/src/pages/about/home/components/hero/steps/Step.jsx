import React from 'react';

const Step = ({ step }) => {
  const { icon, stepTitle, stepDesc } = step;
  return (
    <div className="flex w-[31rem]  lg:w-1/4 p-2 lg:p-4  h-[6rem]  hover:cursor-pointer items-center gap-2 bg-gray-50  rounded-md">
      <div className="p-2">{icon}</div>
      <div>
        <div className="text-sm text-gray-400">{stepTitle}</div>
        <div className="text-base text-wrap  text-black">{stepDesc}</div>
      </div>
    </div>
  );
};

export default Step;
