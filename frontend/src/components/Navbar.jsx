import { Link, useNavigate } from 'react-router-dom';

function Navbar() {
  const isAuthenticated = !!localStorage.getItem('token');
  const navigate = useNavigate();

  const handleSignOut = () => {
    localStorage.removeItem('token');
    navigate('/login');
  };

  return (
    <nav style={styles.nav}>
      <div style={styles.linkGroup}>
        <Link to="/" style={styles.link}>Home</Link>
        <Link to="/book" style={styles.link}>Book Appointment</Link>
      </div>
      
      <div>
        {isAuthenticated ? (
          <button onClick={handleSignOut} style={styles.button}>Sign Out</button>
        ) : (
          <>
            <Link to="/login" style={styles.link}>Login </Link>
            
            <Link to="/signup" style={styles.link}>Sign Up</Link>
          </>
        )}
      </div>
    </nav>
  );
}

const styles = {
  nav: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    backgroundColor: '#003366',
    padding: '1rem 2rem',
    color: 'white',
  },
  linkGroup: {
    display: 'flex',
    gap: '1rem',
  },
  link: {
    color: 'white',
    textDecoration: 'none',
    fontSize: '1rem',
  },
  button: {
    backgroundColor: '#aa0000',
    color: 'white',
    border: 'none',
    padding: '0.5rem 1rem',
    borderRadius: '5px',
    cursor: 'pointer',
    fontSize: '1rem',
  }
};

export default Navbar;
