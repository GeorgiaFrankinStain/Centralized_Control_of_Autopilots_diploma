import React, {useState} from 'react';
import './App.css';
import {toast, ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import { Routes, Route } from 'react-router-dom';
import Home from "./home/Home";
import Profile, {IUserData} from "./user/profile/profile";
import Login, {checkAuthStatus} from "./user/login/Login";
import SignUp from "./user/signup/Signup";
import OAuth2RedirectHandler from "./user/oauth2/OAuth2RedirectHandler";
import {Authenticated, UserDataFromUserMeType} from "./common/Authenticated";
import {ACCESS_TOKEN, API_BASE_URL} from "./index";
import {AppHeader} from "./common/AppHeader";


function App() {


  const [errorAuthenticated, setErrorAuthenticated] = useState<string | null>('');
    const isAuthenticatedRefDataForGlobalAuthDataInstall = React.useRef(false);


/*    if (isAuthenticatedRefDataForGlobalAuthDataInstall) {
        window.localStorage.setItem('tstz.authenticated', 'true');
    } else {
        window.localStorage.setItem('tstz.authenticated', 'false');
    }*/


    const [signalUpdate, forceUpdate] = useState<number>(0);


    function getRandomInt(max: number) {
        return Math.floor(Math.random() * max);
    }

    console.log(getRandomInt(3))

    const forceUpdateState = (): void => {
        forceUpdate(getRandomInt(9999));
    };


    if (localStorage.getItem('needUpdateForceStae') === 'true') {
        localStorage.setItem('needUpdateForceStae', 'false');
        forceUpdate(signalUpdate + 1);
        console.log("update on ")
    }




    const userDataRef = React.useRef<UserDataFromUserMeType>({
        name: "",
        email: "",
        imageUrl: ""
    });



    const dataFetch = async () => {


        if(!localStorage.getItem(ACCESS_TOKEN)) {
            console.log("No access token set.");
            return;
        }

        let options = {
            url: API_BASE_URL + "/user/me",
            method: 'GET'
        };


        const headers = new Headers({
            'Content-Type': 'application/json',
        });

        if(localStorage.getItem(ACCESS_TOKEN)) {
            headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
        }

        const defaults = {headers: headers};
        options = Object.assign({}, defaults, options);





        const data: UserDataFromUserMeType = await (
            await fetch(
                API_BASE_URL + "/user/me", options
            )
        ).json();


        // isAuthenticatedRefDataForGlobalAuthDataInstall.current = true;
        window.localStorage.setItem('tstz.authenticated', 'true');


        const isProfileNeedUpdate: boolean = userDataRef.current.name !== data.name;
        if (isProfileNeedUpdate) {
            userDataRef.current = data;
            forceUpdate(signalUpdate + 1);
        }

    };

    dataFetch();







    const handleLogout = () => {
        localStorage.removeItem(ACCESS_TOKEN);
        userDataRef.current = {
            name: "",
            email: "",
            imageUrl: ""
        };
        window.localStorage.setItem('tstz.authenticated', 'false');
        forceUpdate(signalUpdate + 1);
        toast("You're safely logged out!");
    };


/*
    if (checkAuthStatus() === false && userDataRef.current.name !== '') {
        window.localStorage.setItem('tstz.authenticated', 'true');
    }*/

    const notify = () => {
        toast("Wow so easy!");
        toast("2222!");
    };

  return (
    <div className="App">
        <div>
            <AppHeader onLogout={handleLogout} />
        </div>
        <Routes>key={signalUpdate}
            <Route path={'/'} element={<Home />} />
            <Route path={'/login'} element={<Login />} />
            <Route path={'/signup'} element={<SignUp authenticated={isAuthenticatedRefDataForGlobalAuthDataInstall.current} />} />
            <Route path={'/oauth2/redirect'} element={
                <OAuth2RedirectHandler setErrorAuthenticated={setErrorAuthenticated} forceUpdateState={forceUpdateState}/>} />
            <Route path={'/profile'} element={
                <Authenticated key={signalUpdate} children={
                           <Profile name={userDataRef.current.name} imageUrl={userDataRef.current.imageUrl} key={signalUpdate}/>}
                />}
            />


        </Routes>

        <button onClick={notify}>Notify!</button>
        <ToastContainer
            position="bottom-right"
            autoClose={7000}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
            theme="light"
        />
    </div>
  );
}

export default App;
