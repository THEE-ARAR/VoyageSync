import React, {useState} from 'react'
import DottedLine from "../../assets/dotted-line.png";
import Airplane from "../../assets/airplane.png";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Link} from "react-router-dom";
import smallCloud from "../../assets/smallCloud.png";
import bigCloud from "../../assets/bigCloud.png";

export default function NewPassword() {
    const [ password, setPassword ] = useState("");
    const [ showPassword, setShowPassword ] = useState(false);
    const [ passwordChange, setPasswordChange ] = useState("");

    return (
        <div className='sign-in-container'>
            <div className="forget-password-header">
                <img src={DottedLine} alt="DottedLine" className='dotted-line'/>
                <img src={Airplane} alt="Airplane" className='airplane'/>
                <img src={smallCloud} alt="Small Cloud" className='small-cloud'/>
                <img src={smallCloud} alt="Small Cloud" className='small-cloud'/>
                <img src={bigCloud} alt="Big Cloud" className='big-cloud'/>
            </div>
            <h1 className='forgot-title'>Password Reset</h1>
            <h3> Your new password must be
                <br/>different from your old one.
            </h3>
            <div className='sign-in-form'>
                <div className='new-password-form'>
                    <FontAwesomeIcon
                        icon={showPassword ? faEyeSlash : faEye}
                        size="2x"
                        onClick={() => setShowPassword(!showPassword)}
                        className='password-icon'
                    />
                    <input
                        type={showPassword ? 'text' : 'password'}
                        placeholder='enter your new password'
                        value={password}
                        className='sign-in-input-field'
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div className='new-password-form'>
                    <FontAwesomeIcon
                        icon={showPassword ? faEyeSlash : faEye}
                        size="2x"
                        onClick={() => setShowPassword(!showPassword)}
                        className='password-icon'
                    />
                    <input
                        type={showPassword ? 'text' : 'password'}
                        placeholder='enter your new password'
                        value={passwordChange}
                        className='sign-in-input-field'
                        onChange={(e) => setPasswordChange(e.target.value)}
                    />
                </div>
            </div>
            <div className='continue-to-home'>
                <Link to='/home' className='sign-in-btn'>Continue</Link>
            </div>
        </div>
    );
}
