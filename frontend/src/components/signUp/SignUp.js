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
    const [showPassword, setShowPassword] = React.useState(false);
    const [setError] = useState(null);
    const [loading, setLoading] = useState(true);

    const createUser = async () => {
        try {
            setLoading(true);
            const response = await fetch("/api/users/create", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ username, email, password }),
            });
            const data = await response.json();
            if (response.ok) {
                // User created successfully, handle success (e.g., redirect to login page)
                console.log("User created successfully:", data);
            } else {
                // Server returned error response, handle error
                setError(data.message || "Failed to create user");
            }
        } catch (error) {
            console.error("Error creating user:", error);
            // Handle network errors
            setError("Failed to create user. Please try again later.");
        } finally {
            setLoading(false);
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Validate input fields
        if (!username || !email || !password || !confirmPassword) {
            setError("All fields are required.");
            return;
        }
        if (password !== confirmPassword) {
            setError("Passwords do not match.");
            return;
        }
        // Reset error state
        setError(null);
        // Send user data to backend
        createUser();
    };

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