import React from 'react';
import MyAccordian from './MyAccordian';

const Accordions = () => {
  const accordians = [
    {
      question: 'What is Flowpium?',
      answer:
        'ezto auth is a secure authentication and authorization platform that prioritizes security while maintaining a user-friendly setup process. It effectively safeguards applications from potential threats such as compromised credentials and devices.Our access security solution is designed to ensure the safety of all users, devices, and applications, enabling you to concentrate on your core activities with confidence in your security infrastructure.',
    },
    {
      question: 'Does Flowpium support on premise?',
      answer:
        'Cloud-based deployment offers greater flexibility compared to an on-premise design. However, on-premise deployment remains an option, and your choice depends on your specific circumstances and requirements.',
    },
    {
      question: 'How can I request for new feature?',
      answer:
        'ezto auth enables users to directly request additional features through the contact sales page        .',
    },
    {
      question: 'Can I list my solution in Flowpium auth Marketplace?',
      answer:
        'We created the Marketplace to link our clients with service providers and builders that frequently deploy integrations. Your organisation will be promoted to our clients that are wanting to do more with their authentication pipeline by listing your integration with ezto auth.        ',
    },
  ];
  return (
    <div className="flex flex-col gap-4 justify-center items-center ">
      {accordians.map((FAQ) => {
        return <MyAccordian faq={FAQ} />;
      })}
    </div>
  );
};

export default Accordions;
