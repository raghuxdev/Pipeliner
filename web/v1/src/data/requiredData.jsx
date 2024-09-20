import { RiCalendarScheduleFill } from 'react-icons/ri';
import { TiFlowSwitch } from 'react-icons/ti';
import { LiaRobotSolid } from 'react-icons/lia';
import { FaUserAlt } from 'react-icons/fa';
import { MdWorkHistory } from 'react-icons/md';
import { RiEmotionHappyFill } from 'react-icons/ri';
import { MdVerified } from 'react-icons/md';
import DashboardIcon from '@mui/icons-material/Dashboard';

//sidebar items
import { SiStreamrunners } from 'react-icons/si';
import { FaUniversalAccess } from 'react-icons/fa6';
import { FaWallet } from 'react-icons/fa6';
import { GoOrganization } from 'react-icons/go';

export const data = {
  pages: ['Pricing', 'Docs', 'Blog'],
  points: [
    {
      icon: <TiFlowSwitch size={25} />,
      boldText: 'Simplify ',
      text: 'Workflows Effortlessly',
    },
    {
      icon: <RiCalendarScheduleFill size={25} />,
      boldText: 'Scheduling ',
      text: 'Made Easy',
    },
    {
      icon: <LiaRobotSolid size={25} />,
      boldText: 'Automate',
      text: ' Scheduling Tasks',
    },
  ],
  steps: [
    {
      icon: <FaUserAlt size={20} />,
      stepTitle: 'Step 1',
      stepDesc: 'Register Account For Free',
    },
    {
      icon: <MdWorkHistory size={25} />,
      stepTitle: 'Step 2',
      stepDesc: 'Schedule Automation Jobs',
    },
    {
      icon: <RiEmotionHappyFill size={25} />,
      stepTitle: 'Step 3',
      stepDesc: 'Begin Reducing Work',
    },
  ],
  features: [
    {
      icon: <DashboardIcon />,
      featureTitle: 'Access Management',
      featureDesc:
        'Admin dashboard allows you to manage users using interactive solutions',
    },
    {
      icon: <MdVerified />,
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
  ],
  dashboardRestrictedItems: [
    {
      name: 'Pipelines',
      requiredSubject: 'Jobs',
      icon: <SiStreamrunners />,
      route: '/pipelines',
    },
    {
      name: 'Organizations',
      requiredSubject: 'Organizations',
      icon: <GoOrganization />,
      route: '/organizations',
    },
    {
      name: 'Access Management',
      requiredSubject: 'Roles',
      icon: <FaUniversalAccess />,
      route: '/access',
    },
    {
      name: 'Wallet',
      requiredSubject: 'Jobs',
      icon: <FaWallet />,
      route: '/wallet',
    },
  ],
};
