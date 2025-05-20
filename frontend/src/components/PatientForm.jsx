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

  const generateTimeOptions = () => {
    const options = [];
    let hour = 9;
    let minute = 0;

    while (hour < 17 || (hour === 17 && minute === 0)) {
      const hh = String(hour).padStart(2, "0");
      const mm = String(minute).padStart(2, "0");
      options.push(
        <option key={`${hh}:${mm}`} value={`${hh}:${mm}`}>
          {`${hh}:${mm}`}
        </option>
      );
      minute += 15;
      if (minute === 60) {
        minute = 0;
        hour++;
      }
    }
    return options;
  };

  return (
    <div style={styles.wrapper}>
      <form onSubmit={handleSubmit} style={styles.form}>
        <h2 style={styles.heading}>Book an Appointment</h2>
        <input name="name" placeholder="Full Name" value={formData.name} onChange={handleChange} required style={styles.input} />
        <input name="email" type="email" placeholder="Email" value={formData.email} onChange={handleChange} required style={styles.input} />
        <input name="phone" placeholder="Phone Number" value={formData.phone} onChange={handleChange} required style={styles.input} />
        <input name="address" placeholder="Address" value={formData.address} onChange={handleChange} required style={styles.input} />
        <input name="postcode" placeholder="Postcode" value={formData.postcode} onChange={handleChange} required style={styles.input} />
        <select name="appointmentReasoning" value={formData.appointmentReasoning} onChange={handleChange} required style={styles.input}>
          <option value="">-- Select Reason --</option>
          <option value="Cardiology">Cardiology</option>
          <option value="Dermatology">Dermatology</option>
          <option value="Neurological">Neurological</option>
          <option value="Orthopedics">Orthopedics</option>
          <option value="Pathology">Pathology</option>
          <option value="Pediatric">Pediatric</option>
          <option value="Psychiatry">Psychiatry</option>
          <option value="Unsure">Unsure</option>
        </select>
        <input name="date" type="date" value={formData.date} onChange={handleChange} required style={styles.input} />
        <select name="time" value={formData.time} onChange={handleChange} required style={styles.input}>
          <option value="">-- Select Time --</option>
          {generateTimeOptions()}
        </select>
        <button type="submit" style={styles.button}>Submit</button>
      </form>
    </div>
  );
}

const styles = {
  wrapper: {
    padding: "1rem",
    display: "flex",
    justifyContent: "center",
  },
  form: {
    width: "100%",
    maxWidth: "500px",
    padding: "2rem",
    display: "flex",
    flexDirection: "column",
    gap: "1rem",
    backgroundColor: "#ffffff",
    borderRadius: "12px",
    boxShadow: "0 4px 20px rgba(0,0,0,0.1)",
  },
  heading: {
    textAlign: "center",
    marginBottom: "1rem",
    color: "#003366",
  },
  input: {
    padding: "0.75rem",
    border: "1px solid #ccc",
    borderRadius: "6px",
    fontSize: "1rem",
    width: "100%",
  },
  button: {
    padding: "0.75rem",
    backgroundColor: "#003366",
    color: "white",
    border: "none",
    borderRadius: "6px",
    fontSize: "1rem",
    cursor: "pointer",
    transition: "background-color 0.3s ease",
  },
  '@media (max-width: 600px)': {
    form: {
      padding: "1rem"
    },
    input: {
      fontSize: "0.9rem",
    },
    button: {
      fontSize: "0.9rem"
    }
  }
};

export default PatientForm;
