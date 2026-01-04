import { Link } from "react-router-dom";

function NavBar() {
  return (
    <div className="navbar">
      <h2>MERN App</h2>

      <div className="nav-links">
        <Link to="/dashboard">Home</Link>
        <Link to="/profile">Profile</Link>
        <Link to="/friends">Friends</Link>
        <Link to="/logout">Logout</Link>
      </div>
    </div>
  );
}

export default NavBar;
