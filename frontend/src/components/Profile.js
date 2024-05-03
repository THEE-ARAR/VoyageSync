import React, { useState, useEffect } from 'react';
import './Profile.css';
import { Link } from 'react-router-dom';

function Profile() {
    const [userData, setUserData] = useState({
        username: "John Doe",
        email: "johndoe@example.com",
        bio: "#CatchingFlightsNotFeelingsandThatsOnSpirit;)",
        interests: ["Travel", "Photography", "Hiking"]
    });

    useEffect(() => {
        // Mock fetchUserData function
        const fetchUserData = async () => {
            // Simulate fetching data from backend (since backend is not working)
            // In a real scenario, this would be replaced with an API call
            try {
                // Simulate delay
                await new Promise(resolve => setTimeout(resolve, 1000));

                // Mock user data
                const mockUserData = {
                    username: "Jane Smith",
                    email: "janesmith@example.com",
                    bio: "#CatchingFlightsNotFeelingsandThatsOnSpirit;).",
                    interests: ["Travel", "Photography", "Hiking", "Cooking", "Nature"]
                };

                // Set mock user data
                setUserData(mockUserData);
            } catch (error) {
                console.error('Error fetching user data:', error);
            }
        };

        // Call fetchUserData
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
                    <div className="profile-info">
                        <img src="https://via.placeholder.com/150" alt="Random Placeholder" className="avatar" />
                        <div className="bubble">
                            <p>Name: {userData.username}</p>
                            <p>Email: {userData.email}</p>
                            <p>Bio: {userData.bio}</p>
                            <p>Interests: {userData.interests.join(", ")}</p>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}

export default Profile;
