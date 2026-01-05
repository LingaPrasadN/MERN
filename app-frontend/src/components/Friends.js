import Invite from "./Invite";
import NavBar from "./NavBar";

export default function Friends() {
  return (
    <>
      <NavBar />
      <div className="container">
        <Invite />
        <div className="card">
          <h1>Friends List</h1>
        </div>
      </div>
    </>
  );
}
