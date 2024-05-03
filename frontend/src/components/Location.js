import React from 'react';
import './Location.css';
import {Link} from "react-router-dom"; //


function Location() {
    return (
        <div>
            <header>
                <h1>Location Sharing</h1>
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

            <div className="container">
                <section>
                    <h2>Stay Connected, Stay Safe</h2>
                    <p>Sharing your location with loved ones has never been easier or more secure! Our location sharing feature allows you to share your real-time location with selected contacts, giving them peace of mind and keeping you connected wherever you go.</p>
                </section>
                <section>
                    <h2>How It Works</h2>
                    <ol>
                        <li>Open the app and navigate to the location sharing section.</li>
                        <li>Select the contacts you want to share your location with.</li>
                        <li>Set the duration for how long you want to share your location.</li>
                        <li>That's it! Your selected contacts will now receive real-time updates of your location.</li>
                    </ol>
                </section>
                <section>
                    <h2>Privacy and Security</h2>
                    <p>We take your privacy and security seriously. Your location data is encrypted and stored securely on our servers. Only the contacts you choose will have access to your shared location, and you can stop sharing at any time with just a few taps.</p>
                </section>
                <section>
                    <h2>Additional Features</h2>
                    <ul>
                        <li>View your location history and manage shared locations.</li>
                        <li>Receive notifications when your contacts arrive at or leave a location.</li>
                        <li>Customize sharing preferences and set expiration times.</li>
                    </ul>
                </section>
                <div className="card">
                    <img src="https://static01.nyt.com/images/2021/02/23/science/24tb-newmap-video-image/24tb-newmap-video-image-superJumbo.jpg" alt="Global Map"/>
                    <div className="card-content">
                        <h2>Global Map</h2>
                        <p>View your location and the locations of your friends on a comprehensive global map, updated in real-time for accuracy and precision.</p>
                    </div>
                </div>
                <div className="card">
                    <img src="https://static.standard.co.uk/s3fs-public/thumbnails/image/2017/07/06/10/snapchatmap.jpg?width=1200&auto=webp&quality=75" alt="Friends Location"/>
                    <div className="card-content">
                        <h2>Friends Location</h2>
                        <p>See where your friends are in real-time, helping you stay connected and making meet-ups easier than ever before.</p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Location;
