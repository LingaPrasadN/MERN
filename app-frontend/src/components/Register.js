import React, { useState } from "react";
import api from "../axios";
import { Link, useNavigate } from "react-router-dom";

function Register() {
  const navigate = useNavigate();

  const [step, setStep] = useState("FORM"); // FORM | OTP
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
  });
  const [otp, setOtp] = useState("");
  const [message, setMessage] = useState("");

  // STEP 1: Send OTP
  const handleRegister = async (e) => {
    e.preventDefault();
    setMessage("");

    try {
      await api.post("/auth/register", {
        name: form.name,
        email: form.email,
        password: form.password,
      });

      setStep("OTP");
      setMessage("OTP sent to your email");
    } catch (err) {
      setMessage(err.response?.data || "Failed to send OTP");
    }
  };

  // STEP 2: Verify OTP & complete registration
  const handleVerifyOtp = async (e) => {
    e.preventDefault();
    setMessage("");

    try {
      await api.post("/auth/verify-register-otp", {
        name: form.name,
        email: form.email,
        password: form.password,
        otp: otp,
      });

      setMessage("Registration successful! Please login.");
      setTimeout(() => navigate("/"), 1500);
    } catch (err) {
      setMessage(err.response?.data || "Invalid OTP");
    }
  };

  return (
    <div className="container">
      <div className="form-container card">
        <h2>Register</h2>

        {step === "FORM" && (
          <form onSubmit={handleRegister}>
            <input
              type="text"
              placeholder="Name"
              value={form.name}
              onChange={(e) => setForm({ ...form, name: e.target.value })}
              required
            />

            <input
              type="email"
              placeholder="Email"
              value={form.email}
              onChange={(e) => setForm({ ...form, email: e.target.value })}
              required
            />

            <input
              type="password"
              placeholder="Password"
              value={form.password}
              onChange={(e) => setForm({ ...form, password: e.target.value })}
              required
            />

            <button type="submit" className="btn-primary">
              Register
            </button>
          </form>
        )}

        {step === "OTP" && (
          <form onSubmit={handleVerifyOtp}>
            <input
              type="text"
              placeholder="Enter OTP"
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
              required
            />

            <button type="submit" className="btn-primary">
              Verify OTP
            </button>
          </form>
        )}

        {message && <p>{message}</p>}

        <Link to="/">Already have an account? Login</Link>
      </div>
    </div>
  );
}

export default Register;
