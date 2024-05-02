import React from 'react'
import {Link} from "react-router-dom";
import DottedLine from '../../assets/dotted-line.png'
import Airplane from '../../assets/airplane.png'
import SmallCloud from '../../assets/smallCloud.png'
import BigCloud from '../../assets/bigCloud.png'


export default function EmailVerification() {
    return (
        <div>
            <div className="header-container">
                <img src={DottedLine} alt="DottedLine" className="dotted-line" />
                <img src={Airplane} alt="Airplane" className="airplane" />
                <img src={SmallCloud} alt="Small Cloud" className="small-cloud" />
                <img src={BigCloud} alt="Big Cloud" className="big-cloud" />
            </div>
            <h1>Email Verification</h1>
            <h3> Please enter the 4-digit code that was
                <br/>sent to your email.
            </h3>
            <div className='code-input-field'>
                <input type='number' className='code-input'/>
                <input type='number' className='code-input'/>
                <input type='number' className='code-input'/>
                <input type='number' className='code-input'/>
            </div>
            <p> Didn't receive a code?
                <span>
                    <Link to='/forgotpassword' className='create-account-btn'>Resend.</Link>
                </span>
            </p>
            <div className="continue-btn">
                <Link to='/updatepassword' className='contiue-button'>Reset Password</Link>
            </div>
        </div>
    )
}
