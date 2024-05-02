import React from 'react';
import './HomePage.css';

function HomePage() {
    // Function to handle click events on navigation links
    const handleClick = (event, id) => {
        event.preventDefault();
        const section = document.querySelector(id);
        if (section) {
            section.scrollIntoView({ behavior: 'smooth' });
        }
    };

    return (
        <div>
            <header>
                <h1>Welcome to VoyageSync</h1>
            </header>
            <nav>
                <ul>
                    <li><a href="#home" aria-label="Home" onClick={(e) => handleClick(e, '#home')}>Home</a></li>
                    <li><a href="#explore" aria-label="Explore" onClick={(e) => handleClick(e, '#explore')}>Explore</a></li>
                    <li><a href="#messages" aria-label="In-App Messaging" onClick={(e) => handleClick(e, '#messages')}>Messages</a></li>
                    <li><a href="#location" aria-label="Location Sharing" onClick={(e) => handleClick(e, '#location')}>Location</a></li>
                    <li><a href="#profile" aria-label="Profile" onClick={(e) => handleClick(e, '#profile')}>Profile</a></li>
                </ul>
            </nav>
            <div className="content">
                <p>VoyageSync is your one-stop travel companion. Whether you're exploring new destinations or keeping in touch with friends during your adventures, we've got you covered.</p>
                <p>From in-app messaging to real-time location sharing, discover all the tools you need to make your travels seamless and exciting. Stay tuned as we continue to add more features that help connect and enhance your travel experiences.</p>
            </div>
        </div>
    );
}


export default HomePage;
