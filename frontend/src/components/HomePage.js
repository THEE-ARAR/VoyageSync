import React from 'react';
import './HomePage.css';
import { Link } from 'react-router-dom';

function HomePage() {
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
                <p className="big-text">Bon Voyage!</p>
                <p className="bubble">Sync is your one-stop travel companion. Whether you're exploring new destinations
                    or keeping in touch with friends during your adventures, we've got you covered!</p>
                <p className="bubble">We allow YOU to tailor your journey based on your preference and interest for an unforgettable experience. Great for solo, group, and first time travelers.</p>
                <p className="bubble">From in-app messaging to real-time location sharing, discover all the tools you need to make your travels seamless and exciting. Stay tuned as we continue to add more features that help connect and enhance your travel experiences.</p>
            </div>
        </div>
    );
}


export default HomePage;
