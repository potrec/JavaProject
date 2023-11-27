import axios from "axios";
import { getToken } from "./getToken";

export const http = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    "Authorization": "Bearer " + getToken(),
  },
  withCredentials: true
  });