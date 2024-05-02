import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import StartPage from "./components/StartPage";
import SignIn from "./components/signIn/SignIn";
import SignUp from "./components/signUp/SignUp";
import Home from './components/Home'
import ForgotPassword from "./components/signIn/ForgotPassword";
import EmailVerification from "./components/signIn/EmailVerification";
import NewPassword from "./components/signIn/NewPassword";
function App() {
  return (
      <Router>
        <Routes>

          <Route path="/" element={<StartPage />} />
            <Route path='/home' element={<Home />} />
          <Route path="/login" element={<SignIn />} />
            <Route path="/forgotpassword" element={<ForgotPassword />} />
            <Route path="/recoverpassword" element={<EmailVerification />} />
            <Route path='/updatepassword' element={<NewPassword />} />
          <Route path="/signup" element={<SignUp />} />





        </Routes>
      </Router>
  );
}

export default App;
