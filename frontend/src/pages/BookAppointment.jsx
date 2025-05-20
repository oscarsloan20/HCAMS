import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import PatientForm from "../components/PatientForm";

function BookAppointment() {
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      navigate("/login");
    }
  }, [navigate]);

  return (
    <div>
      <PatientForm />
    </div>
  );
}

export default BookAppointment;
