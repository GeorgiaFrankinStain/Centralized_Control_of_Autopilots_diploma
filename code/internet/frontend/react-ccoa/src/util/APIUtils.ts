import {ACCESS_TOKEN, API_BASE_URL_BACKEND_DATABASE} from "../index";
import {reject} from "q";

const request = (options: any): any => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    });
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
    .then(response => 
        response.json().then(json => {
            if(!response.ok) {
                return reject(json);
            }
            return json;
        })
    );
};

export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return reject("No access token set.");
    }

    return request({
        url: API_BASE_URL_BACKEND_DATABASE + "/user/me",
        method: 'GET'
    });
}

export function login(loginRequest: any) {
    return request({
        url: API_BASE_URL_BACKEND_DATABASE + "/auth/login",
        method: 'POST',
        body: JSON.stringify(loginRequest)
    });
}

export function signup(signupRequest: any) {
    return request({
        url: API_BASE_URL_BACKEND_DATABASE + "/auth/signup",
        method: 'POST',
        body: JSON.stringify(signupRequest)
    });
}