import React, { useState } from 'react';
import logo from '../../assets/logo-notxt.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import { initializeApp } from 'firebase/app';
import { getAuth, signInWithEmailAndPassword } from 'firebase/auth';

const firebaseConfig = {
        apiKey: "AIzaSyDIrLz1X3I5PQVzf5L8-_UXoYcp60tEaI8",
        authDomain: "voyage-35077.firebaseapp.com",
        projectId: "voyage-35077",
        storageBucket: "voyage-35077.appspot.com",
        messagingSenderId: "27856019259",
        appId: "1:27856019259:web:c9986c63e0084e789d211e"
    };

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

export default function SignIn() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(true);

    const handleSignIn = () => {
        signInWithEmailAndPassword(auth, email, password)
            .then((userCredential) => {
                // Signed in successfully
                let user;
                user = userCredential.user;
                // Redirect
            })
            .catch((error) => {
                // Handle sign-in errors
                console.error('Sign-in error:', error.message);
            });
    };

    return (
        <div className="sign-in-container">
            <div className="sign-in-header">
                <img src={logo} alt="Logo" className="sologo" />
                <h1> Welcome back, to VoyageSync! </h1>
                <h2> Login to your account. </h2>
            </div>
            <div className="sign-in-form">
                <div className="email-div">
                    <FontAwesomeIcon icon={faUser} size="2x" className="email-icon" />
                    <input
                        type="email"
                        name="email"
                        placeholder="enter your email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="sign-in-input-field"
                    />
                </div>
                <div className="password-div">
                    <FontAwesomeIcon
                        icon={showPassword ? faEyeSlash : faEye}
                        size="2x"
                        onClick={() => setShowPassword(!showPassword)}
                        className="password-icon"
                    />
                    <input
                        type="password"
                        name="password"
                        placeholder="enter your password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="sign-in-input-field"
                    />
                </div>
                <div className="sign-in-btns">
                    <Link to="/forgotpassword" className="forgot-btn">
                        Forgot password?
                    </Link>
                    <button onClick={handleSignIn} className="sign-in-btn">
                        Sign In
                    </button>
                    <p className="create-account-p">
                        Don't have an account?
                        <span>
              <Link to="/signup" className="create-account-btn">
                Create an account.
              </Link>
            </span>
                    </p>
                </div>
            </div>
        </div>
    );
}
