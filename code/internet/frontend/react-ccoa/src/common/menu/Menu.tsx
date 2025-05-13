import React, {useState} from "react";
import {toast} from "react-toastify";
import {IUserData} from "../../user/profile/profile";
import {LogIn} from "../LogIn";
import "./Menu.css";
import {ACCESS_TOKEN, API_BASE_URL_BACKEND_DATABASE} from "../../index";
import {MenuTab} from "./MenuTab";


interface IMenuProps {
    userDataRef: IUserData;
    userDataSet: React.Dispatch<React.SetStateAction<IUserData>>
    forceUpdateState: () => void;
}


export function Menu({userDataRef, userDataSet, forceUpdateState}: IMenuProps) {


    const dataFetch = async () => {


        if(!localStorage.getItem(ACCESS_TOKEN)) {
            console.log("No access token set.");
            return;
        }

        let options = {
            url: API_BASE_URL_BACKEND_DATABASE + "/user/me",
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





        const data: IUserData = await (
            await fetch(
                API_BASE_URL_BACKEND_DATABASE + "/user/me", options
            )
        ).json();


        // isAuthenticatedRefDataForGlobalAuthDataInstall.current = true;
        window.localStorage.setItem('tstz.authenticated', 'true');


        const isProfileNeedUpdate: boolean = userDataRef.name !== data.name;
        if (isProfileNeedUpdate) {
            userDataSet(data);
            forceUpdateState();
        }

    };

    dataFetch();


    const handleLogout = () => {
        localStorage.removeItem(ACCESS_TOKEN);
        userDataSet({
            name: "",
            email: "",
            imageUrl: ""
        });
        window.localStorage.setItem('tstz.authenticated', 'false');
        forceUpdateState();
        toast("You're safely logged out!");
    };


    const menuTabs = [
        {
            "toLink": "/",
            "title": "The GAME"
        },
        {
            "toLink": "/tutorial",
            "title": "ТУТОРИАЛ"
        },
        {
            "toLink": "/documentation",
            "title": "ДОКУМЕНТАЦИЯ"
        }
    ]

    return (
        <>
            <LogIn onLogout={handleLogout} />
            <div className={"background-menu"}></div>
            <div className={'menu'}>
                <div style={{display: "inline", width: "fit-content"}}>
                    {menuTabs.map((tab, index) => {
                        return <MenuTab index={index} toLink={tab.toLink} title={tab.title}></MenuTab>
                    })}
                </div>


                <div className={'menu-line last-menu-item'}></div>
            </div>



            <div className={'menu-line first-menu-item'}></div>
        </>

    );

}
