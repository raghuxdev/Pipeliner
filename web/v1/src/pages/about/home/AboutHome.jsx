import React from 'react';
import HomeNavbar from '../../../components/navbars/HomeNavbar';
import Hero from './components/hero/Hero';
import Features from './components/features/Features';
import FAQs from './components/Faqs/FAQs';
import ConnectWithUs from './components/connectwithus/ConnectWithUs';

const AboutHome = () => {
  return (
    <div>
      <HomeNavbar />
      <Hero />
      <Features />
      <FAQs />
      <ConnectWithUs />
    </div>
  );
};

export default AboutHome;
