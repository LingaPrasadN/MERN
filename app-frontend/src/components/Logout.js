import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Logout() {
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    localStorage.removeItem("token");

    setMessage("Logout successful! Redirecting...");

    const timer = setTimeout(() => {
      navigate("/", { replace: true });
    }, 2000);

    return () => clearTimeout(timer);
  }, [navigate]);

  return (
    <div>
      <h2>{message}</h2>
    </div>
  );
}
