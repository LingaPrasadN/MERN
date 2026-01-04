import { Link } from "react-router-dom";
import Login from "./Login";

function Home() {
  return (
    <div className="container">
      <div className="card">
        <h1>Welcome</h1>

        <Login />

        <br />

        <Link to="/register">Register</Link>
        <br />
        <br />
        <Link to="/forgot-password">Forgot Password</Link>
      </div>
    </div>
  );
}

export default Home;
