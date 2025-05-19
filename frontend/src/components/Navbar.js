import { Link } from 'react-router-dom';

function Navbar() {
  return (
    <nav>
      <Link to="/">Home</Link> | 
      <Link to="/book"> Book Appointment</Link> | 
      <Link to="/login">Login</Link>
      {/*<Link to="/appointments"> View Appointments</Link> */} 
    </nav>
  );
}

export default Navbar;
