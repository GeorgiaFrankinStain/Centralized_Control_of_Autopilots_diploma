import React, {useState} from "react";
import './Login.css';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import { useForm } from 'react-hook-form';
import {Link, useNavigate} from "react-router-dom";
import { Navigate } from 'react-router';
import fbLogo from '../../img/fb-logo.png';
import googleLogo from '../../img/google-logo.png';
import githubLogo from '../../img/github-logo.png';
import {ACCESS_TOKEN, FACEBOOK_AUTH_URL, GITHUB_AUTH_URL, GOOGLE_AUTH_URL} from "../../index";
import {login} from "../../util/APIUtils";
import {toast} from "react-toastify";




export const checkAuthStatus = (): boolean => {
    return localStorage.getItem('tstz.authenticated') === 'true';
};



const Login: React.FC = () => {
    return (
        <div className="login-container">
            <div className="login-content">
                <h1 className="login-title">Login to SpringSocial</h1>
                <SocialLogin />
                <div className="or-separator">
                    <span className="or-text">OR</span>
                </div>
                <LoginForm />
                <span className="signup-link">New user? <Link to="/signup">Sign up!</Link></span>
            </div>
        </div>
    )
};

export default Login;





const SocialLogin: React.FC = () => {
    return (
        <div className="social-login">
            <a className="btn btn-block social-btn github" href={GITHUB_AUTH_URL}>
                <img src={githubLogo} alt="Github" /> Log in with Github</a>
        </div>
    );
};



export type RegistrationFormFields = {
    firstName: string;
};


type IBodyLoginRequst = {
    email: string;
    password: string;
};
type IBodyLoginResponse = {
    accessToken: string;
};



const LoginForm: React.FC = () => {


    const navigate = useNavigate();

    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');


    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<RegistrationFormFields>();

    const onChangeEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
        event.preventDefault();
        setEmail(event.target.value);
    };
    const onChangePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
        event.preventDefault();
        setPassword(event.target.value);
    };
    const onSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        console.log('submitting...');

        const loginRequest: IBodyLoginRequst = {
            email: email,
            password: password
        };

        login(loginRequest)
            .then((response: IBodyLoginResponse)=> {
                localStorage.setItem(ACCESS_TOKEN, response.accessToken);
                toast("You're successfully logged in!");
                // this.props.history.push("/");
                console.log("navigate to /");
                navigate("/");
            }).catch((error: any) => {
                toast((error && error.message) || 'Oops! Something went wrong. Please try again!');
            });
    };

    if (checkAuthStatus()) {
        localStorage.setItem('needUpdateForceStae', 'true');
        return <Navigate to={{pathname: "/profile"}}/>; //FIXME navigate to redirect
    } else {

        return (
            <form onSubmit={onSubmit}>
                <div className="form-item">
                    <input type="email" name="email"
                           className="form-control" placeholder="Email"
                           value={email} onChange={onChangeEmail} required/>
                </div>
                <div className="form-item">
                    <input type="password" name="password"
                           className="form-control" placeholder="Password"
                           value={password} onChange={onChangePassword } required/>
                </div>
                <div className="form-item">
                    <button type="submit" className="btn btn-block btn-primary">Login</button>
                </div>
            </form>
        )
    }
};