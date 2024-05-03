import React, { useState, useEffect } from 'react';
import './Profile.css';
import { Link } from 'react-router-dom';
import { getAuth } from 'firebase/auth';

function Profile() {
    const [userData, setUserData] = useState(null);

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const auth = getAuth();
                const user = auth.currentUser;

                if (user) {
                    setUserData({
                        userId: user.uid,
                        email: user.email,
                        username: user.displayName || 'John Doe',
                    });
                } else {
                    console.error('No user is authenticated');
                    // Handle not authenticated user case
                }
            } catch (error) {
                console.error('Error fetching user data:', error);
            }
        };

        fetchUserData();
    }, []);

    return (
        <div>
            <header>
                <h1>Welcome to VoyageSync</h1>
            </header>
            <nav>
                <ul>
                    <li><Link to="/home">Home</Link></li>
                    <li><Link to="/explore">Explore</Link></li>
                    <li><Link to="/messages">Messages</Link></li>
                    <li><Link to="/location">Location</Link></li>
                    <li><Link to="/profile">Profile</Link></li>
                </ul>
            </nav>
            <div className="content">
                <p className="big-text">Your Profile</p>
                {userData && (
                    <div className="bubble">
                        <p>Name: {userData.username}</p>
                        <p>Email: {userData.email}</p>
                    </div>
                )}
            </div>
        </div>
    );
}

export default Profile;
