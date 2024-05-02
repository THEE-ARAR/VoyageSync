import React, {Component, createContext} from "react";
import axios from "axios";
import { initializeApp } from "firebase/app";
import {
    getAuth,
    signInWithEmailAndPassword,
    createUserWithEmailAndPassword,
    sendPasswordResetEmail,
    sendEmailVerification,
    updatePassword
} from "firebase/auth";

const fireBaseConfig ={
    apiKey: "AIzaSyDIrLz1X3I5PQVzf5L8-_UXoYcp60tEaI8",
    authDomain: "voyage-35077.firebaseapp.com",
    projectId: "voyage-35077",
    storageBucket: "voyage-35077.appspot.com",
    messagingSenderId: "27856019259",
    appId: "1:27856019259:web:c9986c63e0084e789d211e"

};
initializeApp(fireBaseConfig);
export const AuthContext = createContext();



export class AuthProvider extends Component {
    auth = getAuth();
    state = {
        currentUser: null,
        errors: [],
        refresh: null
    };



    setCurrentUser = (user) => {
        this.setState({ currentUser: user });
    };

    setErrors = (errorObject, append = false) => {
        this.setState(prevState => ({
            errors: append ? [...prevState.errors, errorObject] : [errorObject]
        }));
    };


    signIn = async (email, password) => {

        try {
            const userCredential = await signInWithEmailAndPassword(this.auth, email, password);
            this.setCurrentUser(userCredential.user);
        } catch (error) {
            console.error("Sign-in error:", error);
            this.setErrors(error.message);
        }

    };

    signOut = async () => {
        await axios.get("http://localhost:3000/logout").then( res => {
            this.state.setCurrentUser({});
            localStorage.removeItem("user");
            localStorage.removeItem("firebaseResponse");
            clearInterval(this.state.refresh);
        }).catch(err => console.log(err));

    };

    createUser = async (email, password, username) => {
        try {
            const userCredential = await this.auth.createUserWithEmailAndPassword( email, password);
            await sendEmailVerification(userCredential.user);
            this.setCurrentUser(userCredential.user);
            await this.database.ref('Users/' + userCredential.user.uid).set({
                email: email,
                password: password,
                username: username
            });
            return userCredential.user;
        } catch (error) {
            console.error("Error creating user", error.message);
            this.setErrors(error.message);
        }


    };


    passwordReset = async (email) => {
        try {
            await sendPasswordResetEmail(this.auth, email);
            console.log("Password reset email sent successfully.");
        } catch (error) {
            console.error("Error verification email verification", error.message);
        }
    };

    updatePassword = async (newPassword) => {
        if (!this.state.currentUser) {
            console.error("No user logged in");
            return;
        }
        try {
            await updatePassword(this.state.currentUser, newPassword);
            console.log("Password updated successfully.");
        } catch (error) {
            console.error("Error updating password:", error.message);
            this.setErrors(error.message);
        }

    };


    render() {
        const { children } = this.props;
        return (
            <AuthContext.Provider value={{
                ...this.state,
                setCurrentUser: this.setCurrentUser,
                signIn: this.signIn,
                signOut: this.signOut,
                createUser: this.createUser,
                passwordReset: this.passwordReset,
                updatePassword: this.updatePassword }}>
                {children}
            </AuthContext.Provider>
        );
    }

}