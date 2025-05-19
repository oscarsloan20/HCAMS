import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import ViewAppointments from './pages/ViewAppointments';
import Navbar from './components/Navbar';
import BookAppointment from "./pages/BookAppointment";
import Login from "./pages/UserLogin";
import Signup from "./pages/Signup";
import PatientDashboard from "./pages/PatientDashboard";
import DoctorDashboard from "./pages/DoctorDashboard";
import BookAppointment from "./pages/BookAppointment";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />        
        <Route path="/login" element={<LoginPage />} />
        <Route path="/book" element={ 
        <ProtectedRoute>
        <Route path="/appointments" element={<ViewAppointments />} />
        </ProtectedRoute>
        } />
        
        <Route path="/signup" element={<Signup />} />
        <Route path="/dashboard/patient" element={<PatientDashboard />} />
        <Route path="/dashboard/doctor" element={<DoctorDashboard />} />
      </Routes>
    </Router>
  );
}

export default App;
