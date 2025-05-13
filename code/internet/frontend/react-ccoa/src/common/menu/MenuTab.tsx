import {IUserData} from "../../user/profile/profile";
import React from "react";
import {LogIn} from "../LogIn";
import {NavLink, useLocation, useRoutes} from "react-router-dom";
import {toast} from "react-toastify";
import classNames from "classnames";


interface IMenuTabProps {
    index: number;
    toLink: string;
    title: string;
}


export function MenuTab({index, toLink, title}: IMenuTabProps) {
    const router = useLocation();
    const isActiveTab = router.pathname === toLink;
    const zIndex = isActiveTab ? 30 : index + 5;


    return (
        <>
            <NavLink to={toLink}>
                <div className={classNames({"con": true, "non-first-con": 0 !== index})}
                     id={`${ isActiveTab ? "activeTabMenu" : ""}`}
                     style={{
                        zIndex: zIndex
                     }}
                >
                    <div className={"con-before"}></div>
                    <div className={"con-moment"} style={{zIndex: zIndex + 1}}><span>{title}</span></div>
                    <div className={"con-after"}></div>
                </div>
            </NavLink>
        </>

    );

}