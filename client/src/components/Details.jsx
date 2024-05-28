import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getEmployeeById, updateEmployeeById, deleteEmployeeById } from '../controller/EmployeeController';

function Details() {

  const { employeeId } = useParams();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: ''
  });

  useEffect(() => {
    getEmployeeById(employeeId)
    .then((response) => {
      setFormData(response);
    })
    .catch(error => {
      console.error(error);
    })
  }, [employeeId]);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleUpdate = async (event) => {
    event.preventDefault();
    await updateEmployeeById(employeeId, formData);
    navigate(`/list`);
  };

  const handleDelete = async (employeeId) => {
    await deleteEmployeeById(employeeId);
    navigate(`/list`);
  };

  return (
    <div className='update-container'>
      <h2>Update Employee Details</h2>
      <form className='form-container' onSubmit={handleUpdate}>
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
        <button className='submit-button' type="submit">Update</button>
      </form>
      <button className='submit-button' onClick={() => handleDelete(employeeId)}>Delete</button>
    </div>
  );
}

export default Details;