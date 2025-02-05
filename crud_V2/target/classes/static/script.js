const apiUrl = "http://localhost:8080/employees";
const employeeForm = document.getElementById("employeeForm");
const employeeTable = document.getElementById("employeeTable").getElementsByTagName("tbody")[0];

// Fetch all employees and populate the table
function fetchEmployees() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            employeeTable.innerHTML = ""; // Clear the table
            data.forEach(employee => {
                const row = employeeTable.insertRow();
                row.innerHTML = `
                    <td>${employee.id}</td>
                    <td>${employee.name}</td>
                    <td>${employee.department}</td>
                    <td class="actions">
                        <button class="edit" onclick="editEmployee(${employee.id})">Edit</button>
                        <button class="delete" onclick="deleteEmployee(${employee.id})">Delete</button>
                    </td>
                `;
            });
        })
        .catch(error => console.error("Error fetching employees:", error));
}

// Add or Update Employee
employeeForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const id = document.getElementById("employeeId").value;
    const name = document.getElementById("name").value;
    const department = document.getElementById("department").value;

    const employee = { name, department };

    if (id) {
        // Update existing employee
        fetch(`${apiUrl}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(employee)
        })
            .then(() => {
                fetchEmployees();
                employeeForm.reset();
                document.getElementById("employeeId").value = "";
            })
            .catch(error => console.error("Error updating employee:", error));
    } else {
        // Add new employee
        fetch(apiUrl, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(employee)
        })
            .then(() => {
                fetchEmployees();
                employeeForm.reset();
            })
            .catch(error => console.error("Error adding employee:", error));
    }
});

// Edit Employee
function editEmployee(id) {
    fetch(`${apiUrl}/${id}`)
        .then(response => response.json())
        .then(employee => {
            document.getElementById("employeeId").value = employee.id;
            document.getElementById("name").value = employee.name;
            document.getElementById("department").value = employee.department;
        })
        .catch(error => console.error("Error fetching employee:", error));
}

// Delete Employee
function deleteEmployee(id) {
    fetch(`${apiUrl}/${id}`, { method: "DELETE" })
        .then(() => fetchEmployees())
        .catch(error => console.error("Error deleting employee:", error));
}

// Load employees when the page loads
fetchEmployees();