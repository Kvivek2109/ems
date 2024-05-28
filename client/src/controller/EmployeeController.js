export const createEmployee = async (employee) => {
  try {
    const response = await fetch('http://localhost:8081/api/employee', {
      method: 'Post',
      headers: { 'Content-Type': 'application/json'},
      body: JSON.stringify(employee) 
    });
    if (!response.ok) {
      alert('Server error: ', response.status);
      throw new Error('Server error: ', response.status);
    }
    alert('Employee created successfully!');
    const data = await response.json();
    return data;
  } catch (error) {
    console.log('Error saving data: ', error)
  }
}

export const employeeList = async () => {
  try {
    const response = await fetch('http://192.168.61.1:8081/api/employees');
    if (!response.ok) {
      throw new Error('Failed to fetch data');
    }
    return await response.json();
  } catch (error) {
    console.error('Error fetching data:', error);
  }
};

export const getEmployeeById = async (id) => {
  try {
    const response = await fetch('http://localhost:8081/api/'+id);
    if (!response.ok) {
      throw new Error("Failed to fetch data");
    }
    return await response.json();
  } catch (error) {
    console.error('Error fetching data: ', error)
  }
}

export const updateEmployeeById = async (id, employee) => {
  try {
    const response = await fetch('http://localhost:8081/api/'+id, {
    method: 'Put',
    headers: {'Content-type': 'application/json'},
    body: JSON.stringify(employee)
    });
    if(!response.ok) {
      alert('Server error: ', response.status);
      throw new Error('Server error: ', response.status);
    }
    alert('Employee updated successfully!');
    return await response.json();
  } catch (error) {
    console.error('Error updating data: ', error);
  }
}

export const deleteEmployeeById = async (id) => {
  try {
    const response = await fetch(`http://localhost:8081/api/${id}`, {
      method: 'Delete'
    });
    if(!response.ok) {
      alert('Server error: ', response.status);
      throw new Error(`Server error: ${response.status}`)
    }
    const result = response.text();
    alert( result);
    return result;
  } catch (error) {
    console.error('Error deleting data: ', error);
  }
}