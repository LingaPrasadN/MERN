import NavBar from "./NavBar";

function Profile() {
  return (
    <>
      <NavBar />
      <div className="container">
        <div className="card profile">
          <img src="https://i.pravatar.cc/150" alt="profile" />
          <div>
            <h1>Profile</h1>
            <p>User details go here</p>
          </div>
        </div>
      </div>
    </>
  );
}

export default Profile;
