import React, { useState } from "react";
import axios from "axios";

function PatientForm() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phone: "",
    address: "",
    postcode: "",
    appointmentReasoning: "",
    time: "",
    date: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8080/api/patients", formData);
      alert("Appointment booked for " + res.data.name);
      setFormData({
        name: "",
        email: "",
        phone: "",
        address: "",
        postcode: "",
        appointmentReasoning: "",
        time: "",
        date: ""
      });
    } catch (err) {
      alert("Failed to submit. Check console.");
      console.error(err);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={styles.form}>
      <h2>Book an Appointment</h2>
      <input
        name="name"
        placeholder="Full Name"
        value={formData.name}
        onChange={handleChange}
        required />
      <input
        name="email"
        type="email"
        placeholder="Email"
        value={formData.email}
        onChange={handleChange}
        required />
      <input
        name="phone"
        placeholder="Phone Number"
        value={formData.phone}
        onChange={handleChange}
        required />
      <input
        name="address"
        placeholder="Address"
        value={formData.address}
        onChange={handleChange}
        required />
      <input
        name="postcode" placeholder="Postcode" value={formData.postcode} onChange={handleChange} required />
      <textarea
        name="appointmentReasoning"
        placeholder="Reason for Appointment"
        value={formData.appointmentReasoning}
        onChange={handleChange}
        required />
      <input 
        name="date" 
        type="date" 
        value={formData.date}
        onChange={handleChange} 
        required />
      <input 
        name="time"
        type="time"
        step="900"  // 900 seconds = 15 minutes, allowing 15 minute intervals between each time
        value={formData.time}
        onChange={handleChange}
        required/>
      <button type="submit">Submit</button>
    </form>
  );
}

const styles = {
  form: {
    maxWidth: "500px",
    margin: "auto",
    padding: "1rem",
    display: "flex",
    flexDirection: "column",
    gap: "0.75rem",
    backgroundColor: "#f9f9f9",
    borderRadius: "8px",
    boxShadow: "0 0 10px rgba(0,0,0,0.1)"
  }
};

export default PatientForm;
