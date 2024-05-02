import React, {useState} from 'react'
import DottedLine from "../../assets/dotted-line.png";
import Airplane from "../../assets/airplane.png";
import SmallCloud from "../../assets/smallCloud.png";
import BigCloud from "../../assets/bigCloud.png";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Link} from "react-router-dom";

export default function NewPassword() {
    const [ password, setPassword ] = useState("");
    const [ showPassword, setShowPassword ] = useState(false);


    return (
        <div>
            <div className="header-container">
                <img src={DottedLine} alt="DottedLine" className="dotted-line"/>
                <img src={Airplane} alt="Airplane" className="airplane"/>
                <img src={SmallCloud} alt="Small Cloud" className="small-cloud"/>
                <img src={BigCloud} alt="Big Cloud" className="big-cloud"/>
            </div>
            <h1>Password Reset</h1>
            <h3> Please create your new password.</h3>
            <h2>Your new password must be different
                <br/>from your old one.
            </h2>
            <div>
                <FontAwesomeIcon
                    icon={showPassword ? faEyeSlash : faEye}
                    size="2x"
                    onClick={() => setShowPassword(!showPassword)}
                    className='password-icon-reset'
                />
                <input
                    type={showPassword ? 'text' : 'password'}
                    placeholder='enter your new password'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <FontAwesomeIcon
                    icon={showPassword ? faEyeSlash : faEye}
                    size="2x"
                    onClick={() => setShowPassword(!showPassword)}
                    className='password-icon-reset'
                />
                <input
                    type={showPassword ? 'text' : 'password'}
                    placeholder='enter your new password'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>
            <div>
                <Link to='/login' className='contiue-button'>Continue</Link>
            </div>
        </div>
    )
}
