import React from 'react'
import {Link} from "react-router-dom";
import './signInStyles.css'
import DottedLine from '../../assets/dotted-line.png'
import Airplane from '../../assets/airplane.png'
import smallCloud from "../../assets/smallCloud.png";
import bigCloud from "../../assets/bigCloud.png";


export default function EmailVerification() {
    return (
        <div className='sign-in-container'>
            <div className="forget-password-header">
                <img src={DottedLine} alt="DottedLine" className='dotted-line'/>
                <img src={Airplane} alt="Airplane" className='airplane'/>
                <img src={smallCloud} alt="Small Cloud" className='small-cloud'/>
                <img src={smallCloud} alt="Small Cloud" className='small-cloud'/>
                <img src={bigCloud} alt="Big Cloud" className='big-cloud'/>
            </div>
            <h1 className='forgot-title'>Email Verification</h1>
            <h3> Please enter the 4-digit code that
                <br/>was sent to your email.
            </h3>
            <div className='code-input-field'>
                <input type='text' className='code-input'/>
                <input type='text' className='code-input'/>
                <input type='text' className='code-input'/>
                <input type='text' className='code-input'/>
            </div>
            <p className='code-resend-p'> Didn't receive a code?
                <span>
                    <Link to='/forgotpassword' className='create-account-btn'>Resend</Link>
                </span>
            </p>
            <div className="continue-btn">
                <Link to='/updatepassword' className='send-code-btn'>Reset Password</Link>
            </div>
        </div>
    )
}
