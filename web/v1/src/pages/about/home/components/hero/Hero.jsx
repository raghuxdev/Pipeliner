import React from 'react';
import Button from '@mui/material/Button';
import Points from './points/Points';
import useFormatMessage from '../../../../../hooks/useFormatMessage';
import Step from './steps/Step';
import { data } from '../../../../../data/requiredData.jsx';
import { styles } from '../../../../../config/mui';
const Hero = () => {
  const formatMsg = useFormatMessage();
  return (
    <div className=" p-4 lg:p-14">
      <div className="  flex justify-center items-center flex-col gap-7 lg:gap-10">
        <div className=" w-full mt-4 text-2xl text-center  font-normal text-black lg:text-5xl">
          {formatMsg('heroSection.title')}
        </div>
        <div className="w-full text-sm lg:w-[60rem] text-center  font-extralight lg:text-lg text-black">
          {formatMsg('heroSection.desc')}
        </div>
        <div>
          <Button sx={styles.getStartedButton}>
            {formatMsg('heroSection.getStartedLabel')}
          </Button>
        </div>
        <div className=" flex flex-col lg:flex-row justify-center items-center gap-4 lg:gap-8">
          {data.points.map((point) => {
            return <Points point={point} />;
          })}
        </div>
        <div className=" text-base lg:text-xl flex gap-1 font-extralight text-gray-500">
          {formatMsg('heroSection.stepsDesc')}
        </div>
        <div className="text-xl gap-5  flex-col lg:flex-row flex w-full justify-evenly">
          {data.steps.map((step) => {
            return <Step step={step} />;
          })}
        </div>
      </div>
    </div>
  );
};

export default Hero;
