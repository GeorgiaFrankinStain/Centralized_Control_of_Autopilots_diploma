import React, {ReactElement} from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {globalConfig, globalConfigUrl} from "./config";
import { z } from 'zod';
import { BrowserRouter } from "react-router-dom";
import axios from "axios";

//export const API_BASE_URL = 'http://172.16.238.10';
//export const API_BASE_URL = 'http://alamutra.online'; //for site server
//export let API_BASE_URL = 'temporary_replacement'; //for site server
export let API_BASE_URL_BACKEND_DATABASE = "temporary_replacement";
export let API_BACKEND_CALCULATOR = "temporary_replacement";
export const ACCESS_TOKEN = 'accessToken';

export let OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect';
//export const OAUTH2_REDIRECT_URI = 'http://compose-ccoa-frontend/oauth2/redirect';

export const GOOGLE_AUTH_URL = API_BASE_URL_BACKEND_DATABASE + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = API_BASE_URL_BACKEND_DATABASE + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL = API_BASE_URL_BACKEND_DATABASE + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;





// index.tsx:
axios.get(globalConfigUrl)
    .then((response) => {
        globalConfig.config = response.data;
        // API_BASE_URL = globalConfig.config.REACT_APP_BASE_URL;
        API_BASE_URL_BACKEND_DATABASE = globalConfig.config.REACT_APP_BACKEND_DATABASE_URL;
        API_BACKEND_CALCULATOR = globalConfig.config.REACT_APP_BACKEND_CALCULATOR_URL;
        console.log("after change variable");
        console.log(API_BACKEND_CALCULATOR);
        return <App />;
    })
    .catch(e => {
        return <p style={{color: "red", textAlign: "center"}}>Error while fetching global config</p>;
    })
    .then((reactElement: ReactElement) => {
        ReactDOM.render(
            <React.StrictMode>
                <BrowserRouter>
                    <App />
                </BrowserRouter>
            </React.StrictMode>,
            document.getElementById("root")
        );
    });

//
// ReactDOM.render(
//   <React.StrictMode>
//       <BrowserRouter>
//         <App />
//       </BrowserRouter>
//   </React.StrictMode>,
//     document.getElementById('root')
// );
