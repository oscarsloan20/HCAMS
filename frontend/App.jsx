import React, { useState } from "react";
import axios from "axios";

function PatientForm() {
  const [patient, setPatient] = useState({
    name: "",
    age: "",
    gender: "",
    symptoms: ""
  });

  const handleChange = (e) => {
    setPatient({ ...patient, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axios.post("http://localhost:8080/api/patients", patient)
      .then(response => {
        alert("Patient scheduled successfully!");
        console.log(response.data);
      })
      .catch(error => {
        console.error("Error saving patient:", error);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Schedule Appointment</h2>
      <input name="name" placeholder="Name" onChange={handleChange} required />
      <input name="age" type="number" placeholder="Age" onChange={handleChange} required />
      <select name="gender" onChange={handleChange} required>
        <option value="">Select gender</option>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
      </select>
      <textarea name="symptoms" placeholder="Describe symptoms" onChange={handleChange} required></textarea>
      <button type="submit">Submit</button>
    </form>
  );
}

export default PatientForm;
