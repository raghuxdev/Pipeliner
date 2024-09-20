const ADMIN = '/admin';
const AUTH = '/auth';
const LOGIN = '/login';
const SIGNUP = '/signup';
const PROFILE = '/profile';
const ACCOUNT = '/account';
const APPEARANCE = '/appearance';
const NOTIFICATIONS = '/notifications';
const DISPLAY = '/display';
const API = '/api';
export const SETTINGS = '/settings';
export const ADMINSIGNUP = ADMIN + AUTH + SIGNUP;
export const ADMINLOGIN = ADMIN + AUTH + LOGIN;
export const PROFILESETTINGS = SETTINGS + PROFILE;
export const APPEARANCESETTINGS = SETTINGS + APPEARANCE;
export const ACCOUNTSETTINGS = SETTINGS + ACCOUNT;
export const NOTIFICATIONSSETTINGS = SETTINGS + NOTIFICATIONS;
export const DISPLAYSETTINGS = SETTINGS + DISPLAY;
export const ADMINSIGNUPAPI = API + ADMINSIGNUP;

export const SERVER_URL = 'http://localhost:8080/api';

export const LOGIN_ROUTE = '/login';
export const DASHBOARD_ROUTE = '/dashboard';
export const ADMIN_REGISTER_ROUTE = '/admin/register';
export const ORGANIZATIONS_ROUTE = '/organizations';
export const REGISTER = '/register';
export const ACCESS_ROUTE = '/access';

export const LOGIN_ENDPOINT = '/users/login';
export const ADMIN_CHECK = '/admin/auth/SuperAdmin/existence';
export const CURRENT_USER = '/users/me';
export const ORGANIZATIONS = '/organizations';
export const CREATE_ADMIN = '/admin/auth/signup';
export const CREATE_SUB_ADMIN_AND_ORGANIZATION = '/users/signup';
export const ORGANIZATION_EXIST_CHECK =
  '/admin/auth/organization/{name}/existence';

export const EMAIL_EXIST_CHECK = '/users/{email}/existence';
export const FETCH_USER = '/users';
