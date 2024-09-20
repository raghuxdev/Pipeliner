import { BrowserRouter, Routes, Route } from 'react-router-dom';

import {
  ADMIN_REGISTER_ROUTE,
  DASHBOARD_ROUTE,
  LOGIN_ROUTE,
  ORGANIZATIONS_ROUTE,
  REGISTER,
  ACCESS_ROUTE,
} from './config/url';
import AboutHome from './pages/about/home/AboutHome';
import Login from './pages/auth/login/Login';

import AdminRegister from './pages/auth/admin/register/Register';
import Organizations from './pages/organization/Organizations';
import Dashboard from './pages/dashboard/Dashboard';
import Register from './pages/auth/register/Register';
import AccessManagement from './pages/access/AccessManagement';

const GlobalRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<AboutHome />} path="/" />
        <Route element={<Login />} path={LOGIN_ROUTE} />

        <Route element={<Dashboard />} path={DASHBOARD_ROUTE} />
        <Route element={<AccessManagement />} path={ACCESS_ROUTE} />
        <Route element={<AdminRegister />} path={ADMIN_REGISTER_ROUTE} />
        <Route element={<Register />} path={REGISTER} />
        <Route element={<Organizations />} path={ORGANIZATIONS_ROUTE} />
      </Routes>
    </BrowserRouter>
  );
};

export default GlobalRoutes;
