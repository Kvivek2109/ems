import './App.css';
import Navbar from './components/Navbar';
import Register from './components/Register';
import List from './components/List';
import Details from './components/Details';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <div>
        <h1 className='App-header'>Employee Management System</h1>
        <Navbar />
      </div>
      <div className='container'>
        <Router>
          <div className='container-box'>
            <Routes>
              <Route path="/" element={<Register/>} />
              <Route path="/register" element={<Register />} />
              <Route path="/list" element={<List/>} />
              <Route path="/details/:employeeId" element={<Details/>} /> 
            </Routes>
          </div>
        </Router>
      </div>  
    </div>
  );
}

export default App;
