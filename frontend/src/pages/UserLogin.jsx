import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post("http://localhost:8080/users/login", {
        email,
        password,
      });

      const { role, userId } = res.data;
      localStorage.setItem("userId", userId);
      localStorage.setItem("role", role);

      if (role === "PATIENT") {
        navigate("/dashboard/patient");
      } else if (role === "DOCTOR") {
        navigate("/dashboard/doctor");
      }
    } catch (error) {
      alert("Login failed: " + error.response.data);
    }
  };

  return (
    <form onSubmit={handleLogin}>
      <h2><center>Login</center></h2>
      <center><input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
      /></center>
      <div></div>
      <center><input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        required
      /></center>
      <div></div>
      <center><button type="submit">Login</button></center>
    </form>
  );
};

export default Login;
