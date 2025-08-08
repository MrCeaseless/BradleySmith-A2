// Abstract class for any person in the system.
// Stores common details like ID, name, and phone number.
// Cannot be created directly — must be subclassed by Employee or Visitor.
public abstract class Person {

    private int id;                // Unique identifier
    private String fullName;       // Person's full name
    private String phoneNumber;    // Contact number

    // Paramaterised Constructor — uses setters to apply validation rules
    public Person(int id, String fullName, String phoneNumber) {
        setId(id);
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for full name
    public String getFullName() {
        return fullName;
    }

    // Getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Set ID — must be greater than 0
    public final void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        this.id = id;
    }

    // Set full name — cannot be blank and must be 80 characters or fewer
    public final void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be null or blank");
        }
        if (fullName.length() > 80) {
            throw new IllegalArgumentException("Full name must be 80 characters or fewer");
        }
        this.fullName = fullName.trim();
    }

    // Set phone number — must match allowed format and be 8–20 characters long
    public final void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("^[0-9 +()-]{8,20}$")) {
            throw new IllegalArgumentException("Phone number format is invalid");
        }
        this.phoneNumber = phoneNumber;
    }
}
