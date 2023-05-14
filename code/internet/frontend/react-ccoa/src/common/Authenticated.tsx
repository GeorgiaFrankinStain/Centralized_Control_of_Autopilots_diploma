import * as React from 'react';
import {checkAuthStatus} from "../user/login/Login";
import { Navigate } from 'react-router';


export type UserDataFromUserMeType = {
    name: string;
    email: string;
    imageUrl: string;
}


type IAuthenticatedProps = {
    children: JSX.Element;
}

const Authenticated: React.FC<IAuthenticatedProps> = ({ children}) => {

    console.log(checkAuthStatus());

    return checkAuthStatus() ? (
        <React.Fragment>{children}</React.Fragment>
    ) : (
        <Navigate to={{pathname: "/login"}} />
    )
};

export { Authenticated }