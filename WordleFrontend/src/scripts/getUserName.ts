import axios from 'axios';
import type { AxiosResponse, AxiosError } from 'axios';
import { getToken } from './getToken';
import { navigate } from './navigate';

export async function getUserName() {
  let userName = '';
  const http = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    "Authorization": "Bearer " + getToken(),
  },
  withCredentials: true
  });
  const response = await http.get('/api/v1/auth/user/me')
    .then((response: AxiosResponse) => {
      // User is authenticated
      userName = response.data.name;
    })
    .catch((reason: AxiosError) => {
      if(reason.response!.status === 404)
      {
        navigate('/login');
      }
      if(reason.response!.status === 401)
      {
        navigate('/login');
      }
    });
    return userName;
}