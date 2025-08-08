// Represents an employee working at the park.
// Extends Person and adds employee-specific details like role and employee ID.
public class Employee extends Person {

    private String employeeId;  // Unique staff identifier
    private String role;        // Job role (e.g., "Ride Operator")
    private boolean active;     // Employment status

    // Constructor — calls Person constructor and sets employee-specific fields
    public Employee(int id, String fullName, String phoneNumber,
                    String employeeId, String role, boolean active) {
        super(id, fullName, phoneNumber);
        setEmployeeId(employeeId);
        setRole(role);
        setActive(active);
    }

    // Getter for employee ID
    public String getEmployeeId() {
        return employeeId;
    }

    // Set employee ID — cannot be blank
    public final void setEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or blank");
        }
        this.employeeId = employeeId.trim();
    }

    // Getter for role
    public String getRole() {
        return role;
    }

    // Set role — cannot be blank
    public final void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or blank");
        }
        this.role = role.trim();
    }

    // Getter for active status
    public boolean isActive() {
        return active;
    }

    // Set active status
    public final void setActive(boolean active) {
        this.active = active;
    }
}
