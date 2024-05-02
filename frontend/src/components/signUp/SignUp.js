import React, {useState} from "react";
import DottedLine from "../../assets/dotted-line.png";
import Airplane from "../../assets/airplane.png";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEnvelope, faEye, faEyeSlash, faUser} from "@fortawesome/free-solid-svg-icons";
import {Link} from "react-router-dom";

export default function SignUp() {

    const [ username , setUsername ] = useState("");
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [confirmPassword, setConfirmPassword] = React.useState('');
    const [ showPassword, setShowPassword] = React.useState(true);


    return (
        <div className="signUp">
            <div className='signup-header'>
                <img src={DottedLine} alt="DottedLine" className="dotted-line"/>
                <img src={Airplane} alt="Airplane" className="airplane"/>
            </div>
            <h1>Sign Up</h1>
            <div className='signup-forms'>
                <FontAwesomeIcon icon={faUser} size='2x' className='user-icon'/>
                <input
                    type='text'
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    placeholder='username'
                />
                <FontAwesomeIcon icon={faEnvelope} size="2x" className='email-icon'/>
                <input
                    type='email'
                    placeholder='email'
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <FontAwesomeIcon
                    icon={showPassword ? faEyeSlash : faEye}
                    size='2x'
                    onClick={() => setShowPassword(!showPassword)}
                    className='password-icon'/>
                <input type={showPassword ? 'text' : 'password'}
                       placeholder='password'
                       value={password}
                       onChange={(e) => setPassword(e.target.value)}
                />
                <FontAwesomeIcon
                    icon={showPassword ? faEyeSlash : faEye}
                    size='2x'
                    onClick={() => setShowPassword(!showPassword)}
                    className='password-icon'/>
                <input type={showPassword ? 'text' : 'password'}
                       placeholder='confirm password'
                       value={confirmPassword}
                       onChange={(e) => setConfirmPassword(e.target.value)}
                />
            </div>
            <div>
                <button type='submit' className='signup-button'>Create Account</button>
            </div>
            <div>
                <p>By creating an account, you're agreeing to our
                    <br/> Terms & Conditions.
                </p>
            </div>
        </div>
    );
}