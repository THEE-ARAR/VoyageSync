import React from "react";
import './styles.css'
import logo from '../assets/logo-notxt.png'
import {Link} from "react-router-dom";

export default function StartPage() {
    return (
        <div className="start-page-main">
            <div className='header'>
                <img src={logo} alt='logo' className='logo'/>
                <h1 className='title'> VOYAGESYNC</h1>
                <h2 className='sub-title'>Thee ARAR</h2>
            </div>
            <div className='greetings'>
                <h2> Welcome,  </h2>
                <h3> Thee place to sync your travels!</h3>
                <div className='btns-container'>
                    <Link to='/signup' className='btn'>Create Account</Link>
                    <Link to='/login' className='btn'>Sign In</Link>
                </div>
            </div>
        </div>
    );
}