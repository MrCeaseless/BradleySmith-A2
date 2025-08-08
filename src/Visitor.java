// Represents a visitor to the park.
// Extends Person and adds visitor-specific details like ticket ID, age, and height.
public class Visitor extends Person {

    private String ticketId;  // Unique ticket identifier
    private int age;          // Visitor's age
    private int heightCm;     // Visitor's height in centimeters

    // Constructor — calls Person constructor and sets visitor-specific fields
    public Visitor(int id, String fullName, String phoneNumber,
                   String ticketId, int age, int heightCm) {
        super(id, fullName, phoneNumber);
        setTicketId(ticketId);
        setAge(age);
        setHeightCm(heightCm);
    }

    // Getter for ticket ID
    public String getTicketId() {
        return ticketId;
    }

    // Set ticket ID — cannot be blank
    public void setTicketId(String ticketId) {
        if (ticketId == null || ticketId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ticket ID cannot be null or blank");
        }
        this.ticketId = ticketId.trim();
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Set age — must be 0 or greater
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be zero or greater");
        }
        this.age = age;
    }

    // Getter for height
    public int getHeightCm() {
        return heightCm;
    }

    // Set height — must be 0 or greater
    public void setHeightCm(int heightCm) {
        if (heightCm < 0) {
            throw new IllegalArgumentException("Height must be zero or greater");
        }
        this.heightCm = heightCm;
    }
}
