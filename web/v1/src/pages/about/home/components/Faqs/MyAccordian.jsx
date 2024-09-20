import React from 'react';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { Accordion } from '@mui/material';
const MyAccordian = ({ faq }) => {
  const { question, answer } = faq;
  return (
    <Accordion
      sx={{
        boxShadow: 'none',
      }}
      className="p-2 w-full "
    >
      <AccordionSummary
        expandIcon={<ExpandMoreIcon />}
        aria-controls="panel1-content"
        id="panel1-header"
        className="font-semibold text-lg "
      >
        {question}
      </AccordionSummary>
      <AccordionDetails className="text-base font-thin">
        {answer}
      </AccordionDetails>
    </Accordion>
  );
};

export default MyAccordian;
