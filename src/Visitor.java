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
    public final void setTicketId(String ticketId) {
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
    public final void setAge(int age) {
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
    public final void setHeightCm(int heightCm) {
        if (heightCm < 0) {
            throw new IllegalArgumentException("Height must be zero or greater");
        }
        this.heightCm = heightCm;
    }

    @Override
public boolean equals(Object o) {
    if (this == o) return true; // If both references point to the same object in memory, then they are equal 
    if (!(o instanceof Visitor)) return false; // If the other object is Not a visitor, then they cannot be equal
    Visitor other = (Visitor) o;
  
    return this.ticketId != null && this.ticketId.equals(other.ticketId);   // ticketId uniquely identifies a visitor in this system, 
                          // Here ^ is also where we decide that two visitors with the same ticketId are equal
}

@Override
public int hashCode() { 
    return ticketId == null ? 0 : ticketId.hashCode(); // Here we check to see if the hashcode/ticketId is consistent with equals. 
    //ie. If two visitors are equal they MUST return the same hashCode, should the ticketId be null it will return a value of 0 as a default.
}

}
