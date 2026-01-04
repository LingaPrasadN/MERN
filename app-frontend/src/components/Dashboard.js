import NavBar from "./NavBar";
import api from "../axios";
import React, { useEffect, useState } from "react";

function Dashboard() {
  const [data, setData] = useState("");

  useEffect(() => {
    api
      .get("/dashboard")
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
    <>
      <NavBar />
      <div className="container">
        <div className="card">
          <h1>Dashboard</h1>
          <p>{data}</p>
        </div>
      </div>
    </>
  );
}

export default Dashboard;
