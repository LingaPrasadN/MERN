import React, { useState } from "react";
import api from "../axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const [Username, setUsername] = useState("");
  const [Password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post("/auth/login", {
        username: Username,
        password: Password,
      });
      const token = response.data;
      localStorage.setItem("token", token);
      setMessage("Login successful!");
      setTimeout(() => {
        navigate("/dashboard");
      }, 500);
    } catch (error) {
      setMessage("Login failed. Please try again.");
    }
  };

  return (
    <div className="form-container card">
      <h2>Login</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Username"
          value={Username}
          onChange={(e) => setUsername(e.target.value)}
        />

        <input
          type="password"
          placeholder="Password"
          value={Password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button type="submit" className="btn-primary">
          Login
        </button>

        {message && <p>{message}</p>}
      </form>
    </div>
  );
}

export default Login;
