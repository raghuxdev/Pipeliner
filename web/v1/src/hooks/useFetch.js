import axios from 'axios';

export const useFetch = async (url) => {
  console.log(url);
  try {
    const res = await axios.get(url, {
      headers: {
        'Content-Type': 'application/json',
      },
      withCredentials: true,
    });
    return res.data;
  } catch (err) {
    console.log(err);
    return err;
  }
};
