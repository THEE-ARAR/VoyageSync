import React from 'react';
import logo from '../../assets/logo-notxt.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import {Link} from "react-router-dom";
import './signInStyles.css'

export default function SignIn() {

    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [ showPassword, setShowPassword] = React.useState(true);



    return (
        <div className="sign-in-container">
            <div className="sign-in-header">
                <img src={logo} alt="Logo" className='sologo'/>
                <h1> Welcome back, to VoyageSync! </h1>
                <h2> Login to your account. </h2>
            </div>
            <div className='sign-in-form'>
                <div className='email-div'>
                    <FontAwesomeIcon icon={faUser} size="2x" className='email-icon' />
                    <input
                        type='email'
                        name='email'
                        placeholder='enter your email'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className='sign-in-input-field'/>
                </div>
                <div className='password-div'>
                    <FontAwesomeIcon
                        icon={showPassword ? faEyeSlash : faEye}
                        size='2x'
                        onClick={() => setShowPassword(!showPassword)}
                        className='password-icon' />
                    <input
                        type='password'
                        name='password'
                        placeholder='enter your password'
                        value={password}
                        onChange={(e) => {setPassword(e.target.value)}}
                        className='sign-in-input-field'/>

                </div>
                <div className='sign-in-btns'>
                    <Link to='/forgotpassword' className='forgot-btn'>Forgot password?</Link>
                    <Link to='/home' className='sign-in-btn'>Sign In</Link>
                    <p className='create-account-p'>Don't have an account?
                        <span><Link to='/signup' className='create-account-btn'>Create an account.</Link></span>
                    </p>
                </div>
            </div>
        </div>
    )
}