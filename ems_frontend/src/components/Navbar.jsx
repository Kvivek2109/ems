import './Navbar.css';

function Navbar() {
  return (
    <div className='nav-bar'>
      <nav>
        <ul>
          <li><a href='/register'>Register</a></li>
          <li><a href='/list'>View Employee List</a></li>
        </ul>
      </nav>
    </div>
  );
}

export default Navbar;