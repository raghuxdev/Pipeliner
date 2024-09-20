import React from 'react';
import Accordions from './Accordions';

const FAQs = () => {
  return (
    <div className="w-full p-10 flex justify-center items-center flex-col">
      <div className="flex flex-col gap-3 p-6">
        <div className="flex text-lg gap-1 justify-center items-center">
          <div className=" text-black ">Frequently Asked</div>
          <div className="text-gray-400">Questions</div>
        </div>
        <div className="text-2xl font-bold">Want to Ask Something from Us?</div>
      </div>
      <Accordions />
    </div>
  );
};

export default FAQs;
