import './App.css';
import GlobalRoutes from './Routes';
import { IntlProvider } from 'react-intl';
import englishMessages from './locales/en.json';

import UserContextProvider from './providers/user/UserContextProvider.jsx';

function App() {
  return (
    <>
      <UserContextProvider>
        <IntlProvider
          messages={englishMessages}
          locale={navigator.language}
          defaultLocale="en"
        >
          <GlobalRoutes />
        </IntlProvider>
      </UserContextProvider>
    </>
  );
}

export default App;
