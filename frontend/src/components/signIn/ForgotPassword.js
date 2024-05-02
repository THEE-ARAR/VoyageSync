import React, { useState } from "react";
import {Link} from "react-router-dom";
import DottedLine from '../../assets/dotted-line.png';
import Airplane from '../../assets/airplane.png';
import smallCloud from '../../assets/smallCloud.png';
import bigCloud from '../../assets/bigCloud.png';
import {faEnvelope} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";



export default function ForgotPassword() {

    const [email, setEmail] = useState("");

    return(
        <div className='sign-in-container'>
            <div className="forget-password-header">
                <img src={DottedLine} alt="DottedLine" className='dotted-line'/>
                <img src={Airplane} alt="Airplane" className='airplane'/>
                <img src={smallCloud} alt="Small Cloud" className='small-cloud'/>
                <img src={smallCloud} alt="Small Cloud" className='small-cloud'/>
                <img src={bigCloud} alt="Big Cloud" className='big-cloud'/>
            </div>
            <h1 className='forgot-title'>Forgot your password?</h1>
            <h2>Enter the email associated
                <br/>with your account.
            </h2>
            <div>
                <FontAwesomeIcon icon={faEnvelope} size="2x" />
                <input
                    type='email'
                    value={email}
                    placeholder='enter your email'
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>
            <div>
                <Link to='/recoverpassword'>Send Code.</Link>
            </div>
        </div>
    )
}