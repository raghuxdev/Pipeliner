import axios from 'axios';
import { useState } from 'react';

export const usePost = () => {
  const [data, setData] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const postData = async (url, body) => {
    setLoading(true);
    try {
      const response = await axios.post(url, body, {
        headers: {
          'Content-Type': 'Application/json',
        },
        withCredentials: true,
      });
      if (response.status <= 204) {
        setData(response.data);
      } else {
        setError(response.data?.error);
      }
    } catch (err) {
      setError(err.response.data.error);
    } finally {
      setLoading(false);
    }
  };

  return { data, error, loading, postData };
};
