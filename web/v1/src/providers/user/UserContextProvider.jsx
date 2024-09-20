import React, { createContext, useEffect, useReducer } from 'react';
import { useFetch } from '../../hooks/useFetch';
import { CURRENT_USER, LOGIN_ROUTE, SERVER_URL } from '../../config/url';
const initialUserContext = null;
export const UserContext = createContext(initialUserContext);
const useReducerFunction = (currentUserState, action) => {
  switch (action.type) {
    case 'UPDATE_USER_CONTEXT':
      return { ...action.payload };
  }
};

const UserContextProvider = ({ children }) => {
  const [currentUserState, dispatch] = useReducer(
    useReducerFunction,
    initialUserContext
  );
  useEffect(() => {
    const fetchCurrentUser = async () => {
      if (!currentUserState) {
        try {
          const data = await useFetch(SERVER_URL + CURRENT_USER);
          if (data.user.id) {
            dispatch({ type: 'UPDATE_USER_CONTEXT', payload: data });
          } else {
            // window.location.pathname = LOGIN_ROUTE;
          }
        } catch (error) {
          // window.location.pathname = LOGIN_ROUTE;
        }
      }
    };
    fetchCurrentUser();
  }, []);

  return (
    <UserContext.Provider value={{ currentUserState, dispatch }}>
      {children}
    </UserContext.Provider>
  );
};

export default UserContextProvider;
