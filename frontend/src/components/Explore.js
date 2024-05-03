import React, { useEffect } from 'react';
import './Explore.css';
import { Link } from "react-router-dom";

function Explore() {
    useEffect(() => {
        const handleCardClick = () => {
            alert('More detailed information can be shown soon!');
        };

        document.querySelectorAll('.card').forEach(card => {
            card.addEventListener('click', handleCardClick);
        });
        return () => {
            document.querySelectorAll('.card').forEach(card => {
                card.removeEventListener('click', handleCardClick);
            });
        };
    }, []);

    return (
        <div>
            <header>
                <h1>Explore Amazing Trips</h1>
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
            <div className="back-button">
                <Link to="/home">Back</Link>
            </div>
            <div className="container">
                <section className="grid">
                    <div className="card">
                        <img src="https://i.pinimg.com/564x/eb/8a/01/eb8a0172bb64a0e80bf5aaad82fe9534.jpg"
                             alt="Beautiful Beaches"/>
                        <div className="card-content">
                            <h2>Tropical Beach Paradise</h2>
                            <p>Explore white sandy beaches and crystal clear waters.</p>
                        </div>
                    </div>
                    <div className="card">
                        <img
                            src="https://images-sp.summitpost.org/tr:e-sharpen,e-contrast-1,fit-max,q-60,w-500/593025.JPG"
                            alt="Mountain Adventure"/>
                        <div className="card-content">
                            <h2>Mountain Adventure</h2>
                            <p>Climb breathtaking peaks and enjoy stunning vistas.</p>
                        </div>
                    </div>
                    <div className="card">
                        <img
                            src="https://www.frommers.com/system/media_items/attachments/000/863/401/s980/TXTags_River_Dining.jpg?1541830283"
                            alt="City Tours"/>
                        <div className="card-content">
                            <h2>City Tours</h2>
                            <p>Discover the rich history and vibrant culture of major cities.</p>
                        </div>
                    </div>
                    {/* Additional cards below */}
                    <div className="card">
                        <img src="https://cdn.adventure-life.com/11/36/65/Safari/1300x820.webp"
                             alt="Safari Adventure"/>
                        <div className="card-content">
                            <h2>Safari Adventure</h2>
                            <p>Experience the thrill of wildlife up close in their natural habitat.</p>
                        </div>
                    </div>
                    <div className="card">
                        <img src="https://communication272spring2014.files.wordpress.com/2014/01/float.jpg?w=640"
                             alt="Cultural Festivals"/>
                        <div className="card-content">
                            <h2>Cultural Festivals</h2>
                            <p>Participate in colorful and vibrant festivals around the world.</p>
                        </div>
                    </div>
                    <div className="card">
                        <img
                            src="https://t4.ftcdn.net/jpg/07/25/48/11/360_F_725481153_P2DNpA5oruHOyZnJtqHetc9BQ1wSavjQ.jpg"
                            alt="Underwater Exploration"/>
                        <div className="card-content">
                            <h2>Underwater Exploration</h2>
                            <p>Dive into the depths to explore coral reefs and marine life.</p>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    );
}

export default Explore;
