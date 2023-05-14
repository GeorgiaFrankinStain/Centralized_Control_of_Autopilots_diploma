import * as React from 'react';
import {Link, NavLink} from "react-router-dom";
import {checkAuthStatus} from "../user/login/Login";




type IAuthenticatedProps = {
    onLogout: () => void;
}

const AppHeader: React.FC<IAuthenticatedProps> = ({onLogout}) => {

    return (
        <div className={'header-menu'}>
                    <Link to="/" >Spring Social</Link>
                    <nav >
                        { checkAuthStatus() ? (
                            <ul>
                                <li>
                                    <NavLink to="/profile">Profile</NavLink>
                                </li>
                                <li>
                                    <a onClick={onLogout}>Logout</a>
                                </li>
                            </ul>
                        ): (
                            <ul>
                                <li>
                                    <NavLink to="/login">Login</NavLink>
                                </li>
                                <li>
                                    <NavLink to="/signup">Signup</NavLink>
                                </li>
                            </ul>
                        )}
                    </nav>
        </div>
    )
};

export { AppHeader }