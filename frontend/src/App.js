import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import 'firebase/auth';
import 'firebase/firestore';
//import firebase from './Database';

import StartPage from "./components/StartPage";
import SignIn from "./components/signIn/SignIn";
import SignUp from "./components/signUp/SignUp";
import HomePage from "./components/HomePage";
import Messages from "./components/Messages";
import ForgotPassword from "./components/signIn/ForgotPassword";
import EmailVerification from "./components/signIn/EmailVerification";
import NewPassword from "./components/signIn/NewPassword";
import Profile from "./components/Profile";
import Explore from "./components/Explore";



function App() {
  return (
      <Router>
        <Routes>

          <Route path="/" element={<StartPage />} />
            <Route path='/home' element={<HomePage />} />
          <Route path="/login" element={<SignIn />} />
            <Route path="/forgotpassword" element={<ForgotPassword />} />
            <Route path="/recoverpassword" element={<EmailVerification />} />
            <Route path='/updatepassword' element={<NewPassword />} />
          <Route path="/signup" element={<SignUp />} />
            <Route path="/messages" element={<Messages />} />
            <Route path='/profile' element={<Profile />} />
            <Route path='/explore' element={<Explore />} />

        </Routes>
      </Router>
  );
}

export default App;
