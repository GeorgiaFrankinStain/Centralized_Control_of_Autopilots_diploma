import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { BrowserRouter } from "react-router-dom";

export const API_BASE_URL = 'http://localhost';
//export const API_BASE_URL = 'http://172.16.238.10';
//export const API_BASE_URL = 'http://alamutra.online'; //for site server
export const API_BASE_URL_BACKEND_DATABASE = API_BASE_URL + ':8081';
export const ACCESS_TOKEN = 'accessToken';

export const OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect';
//export const OAUTH2_REDIRECT_URI = 'http://compose-ccoa-frontend/oauth2/redirect';

export const GOOGLE_AUTH_URL = API_BASE_URL_BACKEND_DATABASE + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = API_BASE_URL_BACKEND_DATABASE + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL = API_BASE_URL_BACKEND_DATABASE + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;



ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
        <App />
      </BrowserRouter>
  </React.StrictMode>,
    document.getElementById('root')
);
