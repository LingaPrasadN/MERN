import { useState, useEffect } from "react";
import api from "../axios";

export default function Invite() {
  const [newInvites, setNewInvites] = useState(false);
  const [email, setEmail] = useState("");
  const [invites, setInvites] = useState([]);
  const [receivedInvites, setReceivedInvites] = useState([]);

  useEffect(() => {
    api
      .get("/invites/sent/get")
      .then((response) => {
        console.log(response.data);
        setInvites(Array.isArray(response.data) ? response.data : []);
      })
      .catch((error) => {
        console.error("Error fetching invites:", error);
      });
  }, [newInvites]);

  useEffect(() => {
    const fetchReceivedInvites = () => {
      api
        .get("/invites/received")
        .then((res) => {
          setReceivedInvites(Array.isArray(res.data) ? res.data : []);
        })
        .catch((err) => {
          console.error("Error fetching received invites:", err);
        });
    };

    fetchReceivedInvites();

    const interval = setInterval(fetchReceivedInvites, 10000);

    return () => clearInterval(interval);
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/invites/send", {
        toEmail: email,
      });
      alert("Invite sent successfully!");
      setEmail("");
    } catch (error) {
      alert("Failed to send invite.");
    }
    setNewInvites((prev) => !prev);
  };

  const handleAccept = async (inviteId) => {
    try {
      await api.post(`/invites/accept/${inviteId}`);
      alert("Invite accepted successfully!");
    } catch (error) {
      alert("Failed to accept invite.");
    }
  };

  const handleReject = async (inviteId) => {
    try {
      await api.post(`/invites/reject/${inviteId}`);
      alert("Invite rejected successfully!");
    } catch (error) {
      alert("Failed to reject invite.");
    }
  };

  return (
    <>
      <div className="container">
        <div className="card">
          <h1>Received Invites</h1>
          {receivedInvites.length === 0 ? (
            <p>No invites received.</p>
          ) : (
            <ul>
              {receivedInvites.map((invite) => (
                <li key={invite.id}>
                  <p>Email: {invite.inviteeEmail}</p>
                  <p>Sent At: {invite.sentAt}</p>
                  <p>Status: {invite.status}</p>
                  {invite.status === "PENDING" && (
                    <>
                      <button
                        className="btn-primary"
                        onClick={() => handleAccept(invite.id)}
                      >
                        Accept
                      </button>
                      <br></br>
                      <br></br>
                      <button
                        className="btn-primary"
                        onClick={() => handleReject(invite.id)}
                      >
                        Reject
                      </button>
                    </>
                  )}
                </li>
              ))}
            </ul>
          )}
        </div>
      </div>

      <div className="container">
        <div className="card">
          <h1>Send Invite</h1>
          <form onSubmit={handleSubmit}>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Friend's Email"
            />
            <button type="submit" className="btn-primary">
              Send Invite
            </button>
          </form>
        </div>
      </div>

      <div className="container">
        <div className="card">
          <h1>Invites Sent</h1>
          {invites.length === 0 ? (
            <p>No invites sent.</p>
          ) : (
            <ul>
              {invites.map((invite) => (
                <li key={invite.id}>
                  Email: {invite.inviteeEmail} <br></br> SentAt: {invite.sentAt}{" "}
                  <br></br> Status: {invite.status}
                </li>
              ))}
            </ul>
          )}
        </div>
      </div>
    </>
  );
}
