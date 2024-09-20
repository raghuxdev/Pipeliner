import { useIntl } from 'react-intl';

const useFormatMessage = () => {
  const { formatMessage } = useIntl();
  return (id) => formatMessage({ id });
};

export default useFormatMessage;
