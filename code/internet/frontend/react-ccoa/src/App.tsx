import React, {useState} from 'react';
import './App.css';
import {toast, ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import { Routes, Route } from 'react-router-dom';
import Home from "./pages/Home";
import Documentation from "./pages/Documentation";
import Profile, {IUserData} from "./user/profile/profile";
import Login, {checkAuthStatus} from "./user/login/Login";
import SignUp from "./user/signup/Signup";
import OAuth2RedirectHandler from "./user/oauth2/OAuth2RedirectHandler";
import {ACCESS_TOKEN, API_BASE_URL_BACKEND_DATABASE} from "./index";
import {LogIn} from "./common/LogIn";
import {Bottom} from "./common/Bottom";
import {Menu} from "./common/menu/Menu";
import {Authenticated} from "./common/Authenticated";



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




    const [userData, userDataSet] = useState<IUserData>({
        name: "",
        email: "",
        imageUrl: ""
    });








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
        {/*<button onClick={notify}>Notify!</button>*/}
        <div>
            <Menu userDataRef={userData} userDataSet={userDataSet} forceUpdateState={forceUpdateState} />
            <div className={'general-container'} style={{paddingTop: "90px"}}>
                <Routes>key={signalUpdate}
                    <Route path={'/'} element={<Home />} />
                    <Route path={'/documentation'} element={<Documentation />} />
                    <Route path={'/login'} element={<Login />} />
                    <Route path={'/signup'} element={<SignUp authenticated={isAuthenticatedRefDataForGlobalAuthDataInstall.current} />} />
                    <Route path={'/oauth2/redirect'} element={
                        <OAuth2RedirectHandler setErrorAuthenticated={setErrorAuthenticated} forceUpdateState={forceUpdateState}/>} />
                    <Route path={'/profile'} element={
                        <Authenticated key={signalUpdate} children={
                            <Profile name={userData.name} imageUrl={userData.imageUrl} key={signalUpdate}/>}
                        />}
                    />


                </Routes>
            </div>

            <Bottom />
        </div>


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
