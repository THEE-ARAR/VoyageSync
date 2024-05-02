import React from 'react'

export default function Messages() {
    return (
        <div className='messages-div'>
            <header className='messgaes-header'>
                <h1>Messages</h1>
            </header>
            <main id="message-container">
                <!-- Messages will be dynamically inserted here -->
            </main>
            <footer>
                <form id="message-form">
                    <input type="text" id="message-input" placeholder="Type your message here..."/>
                    <button type="submit">Send</button>
                </form>
            </footer>
        </div>
    )
}
