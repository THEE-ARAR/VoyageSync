import React, {useState} from "react";
import DottedLine from "../../assets/dotted-line.png";
import Airplane from "../../assets/airplane.png";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEnvelope, faEye, faEyeSlash, faUser} from "@fortawesome/free-solid-svg-icons";
import '../signIn/signInStyles.css'
import {Link} from "react-router-dom";
export default function SignUp() {

    const [ username , setUsername ] = useState("");
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [confirmPassword, setConfirmPassword] = React.useState('');
    const [ showPassword, setShowPassword] = React.useState(true);


    return (
        <div className='sign-in-container'>
            <div className="forget-password-header">
                <img src={DottedLine} alt="DottedLine" className='dotted-line'/>
                <img src={Airplane} alt="Airplane" className='airplane'/>
            </div>
            <h1 className='signup-header'>Sign Up</h1>
            <div className='signup-forms' style={{transform: 'translateY(-490px'}}>
                <div className='username-div'>
                    <FontAwesomeIcon icon={faUser} size='2x' className='user-icon'/>
                    <input
                        type='text'
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder='username'
                        className='sign-in-input-field'
                    />
                </div>
                <div className='email-div'>
                    <FontAwesomeIcon icon={faEnvelope} size="2x" className='email-icon'/>
                    <input
                        type='email'
                        placeholder='email'
                        value={email}
                        className='sign-in-input-field'
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                <div className='password-div'>
                    <FontAwesomeIcon
                        icon={showPassword ? faEyeSlash : faEye}
                        size='2x'
                        onClick={() => setShowPassword(!showPassword)}
                        className='password-icon'/>
                    <input type={showPassword ? 'text' : 'password'}
                           placeholder='password'
                           value={password}
                           className='sign-in-input-field'
                           onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div className='password-div'>
                    <FontAwesomeIcon
                        icon={showPassword ? faEyeSlash : faEye}
                        size='2x'
                        className='password-icon'/>
                    <input type={showPassword ? 'text' : 'password'}
                           placeholder='confirm password'
                           value={confirmPassword}
                           onClick={() => setShowPassword(!showPassword)} className='sign-in-input-field'

                           onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                </div>
            </div>
            <div className='sign-in-btns' style={{transform: 'translateY(-460px)'}}>
                <Link to='/home' className='sign-in-btn'>Create Account</Link>
                <p className='create-account-p'> By creating an account, you're agreeing to our
                    <span><br/><Link to='/' className='create-account-btn'>Terms & Conditions</Link></span>
                </p>
            </div>
        </div>
    );
}