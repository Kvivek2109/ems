import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import './List.css';
import { employeeList } from '../controller/EmployeeController';

function List() {

  const navigate = useNavigate();

  const [employees, setEmployees] = useState([]);
  const location = useLocation();

  useEffect(() => {
    employeeList()
    .then((response) => {
      setEmployees(response);
    })
    .catch(error => {
      console.error(error);
    })
  }, [location.pathname]);

  const handleViewDetails = (id) => {
    navigate(`/details/${id}`);
  }

  return (
    <>
      <h2>Details of All Employees</h2>
      <div className='employee-list'>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>View Details</th>
            </tr>
          </thead>
          <tbody>
          {employees?.length > 0 ? (
            employees.map(employee => (
              <tr key={employee.employeeId}>
                <td>{employee.employeeId}</td>
                <td>{employee.firstName}</td>
                <td>{employee.lastName}</td>
                <td>{employee.email}</td>
                <td><button className='button' onClick={() => handleViewDetails(employee.employeeId)}>View Details</button></td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No employees found.</td>
            </tr>
          )}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default List;