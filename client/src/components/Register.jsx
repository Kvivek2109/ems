import React, { useState } from 'react';
import { createEmployee } from '../controller/EmployeeController';

function Register() {

  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: ''
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    createEmployee(formData);
    setFormData({ firstName: '', lastName: '', email: '' });
  };

  return (
    <>
      <h2>Create New Employee</h2>
      <form className='form-container' onSubmit={handleSubmit}>
        <div className='form-input'>
          <label htmlFor="firstName">First Name:</label>
          <input
            type="text"
            id="firstName"
            name="firstName"
            value={formData.firstName}
            onChange={handleChange}
          />
        </div>
        <div className='form-input'>
          <label htmlFor="lastName">Last Name:</label>
          <input
            type="text"
            id="lastName"
            name="lastName"
            value={formData.lastName}
            onChange={handleChange}
          />
        </div>
        <div className='form-input'>
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
        </div>
        <button className='submit-button' type="submit">Register</button>
      </form>
    </>
  );
}

export default Register;