import React from 'react';
//import firebase from './app';
import { Link } from 'react-router-dom';


function Messages() {
    return (
        <div className='messages-div'>
            <header className='messages-header'>
                <h1>Messages</h1>
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
            <body>
            <Link to="/home">
                <button className="back-button">Back</button>
            </Link>
            </body>
            <main id="message-container">
                {/* Messages will be dynamically inserted here */}
                <form id="message-form">
                    <input
                        type="text"
                        id="message-input"
                        placeholder="Type your message here..."
                    />
                    <button type="submit">Send</button>
                </form>

            </main>
            <footer>
            </footer>
        </div>
    );
}

export default Messages;
