import React, {useState} from "react";
import './Signup.css';
import {GITHUB_AUTH_URL} from "../../index";
import {Link, useNavigate} from "react-router-dom";
import githubLogo from '../../img/github-logo.png';
import { Navigate } from 'react-router';

import {IDataForOrderWithoutId} from "../../DragList";
import {toast} from "react-toastify";
import {signup} from "../../util/APIUtils";




interface ISignUpProps{
    authenticated: boolean
}


const SignUp: React.FC<ISignUpProps> = ({authenticated}) => {


    console.log("sign up authenticated" + authenticated);


    if(authenticated) {
        return <Navigate
            to={{pathname: "/"}}/>;
    }

    return (
        <div className="signup-container">
            <div className="signup-content">
                <h1 className="signup-title">Signup with SpringSocial</h1>
                <SocialSignup />
                <div className="or-separator">
                    <span className="or-text">OR</span>
                </div>
                <SignupForm />
                <span className="login-link">Already have an account? <Link to="/login">Login!</Link></span>
            </div>
        </div>
    );
};


export default SignUp;




const SignupForm: React.FC = () => {
    return (
        <div className="social-signup">
            <a className="btn btn-block social-btn github" href={GITHUB_AUTH_URL}>
                <img src={githubLogo} alt="Github" /> Sign up with Github</a>
        </div>
    );
};


type IBodyRegistryRequst = {
    name: string;
    email: string;
    password: string;
};

const SocialSignup: React.FC = () => {


    const navigate = useNavigate();

    const [name, setName] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');



    const onChangeName = (event: React.ChangeEvent<HTMLInputElement>) => {
        event.preventDefault();
        setName(event.target.value);
    };
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

        const signUpRequest: IBodyRegistryRequst = {
            name: name,
            email: email,
            password: password
        };

        signup(signUpRequest)
            .then((response: any) => {
                toast("You're successfully registered. Please login to continue!");
                // this.props.history.push("/login");
                navigate("/");
            }).catch((error: any) => {
            toast((error && error.message) || 'Oops! Something went wrong. Please try again!');
        });
    };


    return (
        <form onSubmit={onSubmit}>
            <div className="form-item">
                <input type="text" name="name"
                       className="form-control" placeholder="Name"
                       value={name} onChange={onChangeName} required/>
            </div>
            <div className="form-item">
                <input type="email" name="email"
                       className="form-control" placeholder="Email"
                       value={email} onChange={onChangeEmail} required/>
            </div>
            <div className="form-item">
                <input type="password" name="password"
                       className="form-control" placeholder="Password"
                       value={password} onChange={onChangePassword} required/>
            </div>
            <div className="form-item">
                <button type="submit" className="btn btn-block btn-primary" >Sign Up</button>
            </div>
        </form>

    );
};