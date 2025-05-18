import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();

  const handleLogin = async () => {
    // send login details to backend
    const res = await axios.post("/api/login", { email, password });
    const { token, role } = res.data;

    localStorage.setItem("token", token);
    localStorage.setItem("role", role);

    // redirect based on role
    if (role === "PATIENT") {
      navigate("/dashboard/patient");
    } else if (role === "DOCTOR") {
      navigate("/dashboard/doctor");
    }
  };

  return (
    // login form here
  );
};
