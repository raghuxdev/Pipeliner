import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
export const useCurrentUser = (url) => {
  const [user, setCurrentUser] = useState(undefined);
  const [error, setError] = useState(undefined);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    const fetchCurrentUser = async () => {
      setLoading(true);
      try {
        const res = await axios.get(url);
        console.log(res);
        if (res.status <= 204) {
          setCurrentUser(res.data);
        } else {
          setError(res.data?.error);
          navigate('/login');
        }
      } catch (err) {
        console.log(err);
        setError(err.response.data.error);
      } finally {
        setLoading(false);
      }
    };
    fetchCurrentUser();
  }, [url]);

  return { user, error, loading };
};
