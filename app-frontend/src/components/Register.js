import React, { useState } from "react";
import api from "../axios";
import { Link } from "react-router-dom";

function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    try {
      api.post("/auth/register", {
        username,
        password,
      });
      setMessage("Registration successful!");
    } catch (error) {
      setMessage("Registration failed. Please try again.");
    }
  };

  return (
    <div className="container">
      <div className="form-container card">
        <h2>Register</h2>

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button type="submit" className="btn-primary">
            Register
          </button>

          {message && (
            <p>
              {message} <br />
              <Link to="/">Login</Link>
            </p>
          )}
        </form>
      </div>
    </div>
  );
}

export default Register;
