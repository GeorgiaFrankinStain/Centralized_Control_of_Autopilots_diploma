import * as React from 'react';
import {Link, NavLink} from "react-router-dom";
import {checkAuthStatus} from "../user/login/Login";
import "./LogIn.css"




type IAuthenticatedProps = {
    onLogout: () => void;
}

const LogIn: React.FC<IAuthenticatedProps> = ({onLogout}) => {

    return (
        <div className={'header-menu'}>
            <nav >
                { checkAuthStatus() ? (
                    <ul>
                        <li>
                            <NavLink to="/profile">Profile</NavLink>
                            <a onClick={onLogout}>Logout</a>
                        </li>
                        <li>
                        </li>
                    </ul>
                ): (
                    <>
                        <div>
                            <NavLink to="/login">ВХОД</NavLink>
                            <span> / </span>
                            <NavLink to="/signup">РЕГИСТРАЦИЯ</NavLink>
                        </div>
                        <div>
                            <span className={"login-throw"}>вход через github, google</span>
                        </div>


                    </>
                )}
            </nav>
        </div>
    )
};

export { LogIn }