import React, { Component } from 'react';
import { Navigate } from 'react-router';
import {ACCESS_TOKEN} from "../../index";
import {useNavigate, useSearchParams} from 'react-router-dom';


interface IOAuth2RedirectHandlerProps {
    setErrorAuthenticated: React.Dispatch<React.SetStateAction<string | null>>;
    forceUpdateState: () => void;
}

//(newDataForOrder: IDataForOrderWithoutId) => void

const OAuth2RedirectHandler: React.FC<IOAuth2RedirectHandlerProps> = ({setErrorAuthenticated, forceUpdateState}) => {
    // function getUrlParameter(name) {
    //     name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    //     var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    //
    //
    //
    //     // var results = regex.exec(this.props.location.search);
    //     return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    // };

    const navigate = useNavigate();


    const [searchParams] = useSearchParams();

    const token = searchParams.get('token');
    const error = searchParams.get('error');

    // const token = getUrlParameter('token');
    // const error = getUrlParameter('error');

    console.log("OAuth2RedirectHandler tocken " + token);


    forceUpdateState();
    if(token) {
        localStorage.setItem(ACCESS_TOKEN, token);
        console.log("try navigate to profile");
        navigate("/profile");
        return <Navigate to={{pathname: "/profile"}}/>;
    } else {
        setErrorAuthenticated(error);
        navigate("/login");
        return <Navigate to={{pathname: "/login"}}/>;
    }
};

export default OAuth2RedirectHandler;