import { useEffect, useState } from "react";
import NavBar from "./NavBar";
import api from "../axios";

export default function Friends() {
  const [friends, setFriends] = useState([]);

  useEffect(() => {
    api
      .get("/friends/get")
      .then((response) => {
        Array.isArray(response.data)
          ? setFriends(response.data)
          : setFriends([]);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("Error fetching friends:", error);
      });
  }, []);

  return (
    <>
      <NavBar />
      <div className="container">
        <div className="card">
          <h1>Friends List</h1>
          {friends.length === 0 ? (
            <p>Add friends by sending invites.</p>
          ) : (
            friends.map((friendEle) => {
              return (
                <ul key={friendEle.id}>
                  <li>{friendEle.name}</li>
                  <li>{friendEle.email}</li>
                </ul>
              );
            })
          )}
        </div>
      </div>
    </>
  );
}
