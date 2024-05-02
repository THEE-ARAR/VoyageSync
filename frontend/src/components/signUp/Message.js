import React, { useState } from "react";

function Messages() {
    const [messages, setMessages] = useState([]);
    const [inputValue, setInputValue] = useState("");

    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if (inputValue.trim() !== "") {
            setMessages([...messages, inputValue.trim()]);
            setInputValue("");
        }
    };

    return (
        <>
            <header>
                <h1>Messages</h1>
            </header>
            <main id="message-container">
                {messages.map((message, index) => (
                    <div key={index} className="message">
                        {message}
                    </div>
                ))}
            </main>
            <footer>
                <button className="back-button" onClick={() => window.history.back()}>
                    Back
                </button>
                <form id="message-form" onSubmit={handleSubmit}>
                    <input
                        type="text"
                        id="message-input"
                        placeholder="Type your message here..."
                        value={inputValue}
                        onChange={handleInputChange}
                    />
                    <button type="submit">Send</button>
                </form>
            </footer>
        </>
    );
}

export default Messages;
