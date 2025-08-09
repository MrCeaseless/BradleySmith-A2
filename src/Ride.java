import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

// Represents a ride in the park.
// Holds a waiting queue and a ride history, tracks capacity and cycles.
// Implements the behaviour defined by RideInterface.
public class Ride implements RideInterface {

    private String name;                // Ride name (e.g., "Thunder Run")
    private Employee operator;          // Employee who operates the ride; can be null (closed)
    private int maxRider;               // Seats available per cycle; must be >= 1
    private int numOfCycles;            // How many times this ride has run

    private final Queue<Visitor> waitingQueue;     // People waiting to ride (FIFO)
    private final LinkedList<Visitor> rideHistory; // People who have ridden

    // Constructor — validates inputs and sets up collections
    public Ride(String name, Employee operator, int maxRider) {
        setName(name);
        setOperator(operator);     // null allowed; runOneCycle must block if null
        setMaxRider(maxRider);     // must be >= 1
        this.numOfCycles = 0;      // starts at zero
        this.waitingQueue = new ArrayDeque<>();
        this.rideHistory = new LinkedList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public Employee getOperator() {
        return operator;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    // Setters with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ride name cannot be null or blank");
        }
        this.name = name.trim();
    }

    // Operator can be null to indicate the ride is closed/unassigned.
    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public void setMaxRider(int maxRider) {
        if (maxRider < 1) {
            throw new IllegalArgumentException("maxRider must be at least 1");
        }
        this.maxRider = maxRider;
    }

    // Setter is optional; usually only incremented inside runOneCycle()
    public void setNumOfCycles(int numOfCycles) {
        if (numOfCycles < 0) {
            throw new IllegalArgumentException("numOfCycles cannot be negative");
        }
        this.numOfCycles = numOfCycles;
    }

    // ===== Interface implementations =====
    // Leave these unimplemented for now; Parts 3–7 will fill them in.
    // Each method explains what you’ll implement next.

    // Add visitor to the back of the waiting queue.
    // Should validate non-null, avoid duplicates if you want stricter rules, and print result.
    @Override
    public boolean addVisitorToQueue(Visitor v) {
        if (v == null) {
            System.out.println("[Queue] Cannot add: because vistor is null.");
            return false;
        }

        // Implement (FIFO)
        boolean added = waitingQueue.offer(v);
        if (added) {
            System.out.println("[Queue] Added to queue: " + v.getFullName() + "(ticket " +v.getTicketId() + "). Size now: " + waitingQueue.size());
        } else {
            System.out.println("[Queue] Failed to add visitor to the queue.");
        }
        return added;
        }
    

    // Remove visitor from the front of the waiting queue (or report empty).
    @Override
    public Visitor removeVisitorFromQueue() {
         // If queue is empty, report and return null
    if (waitingQueue.isEmpty()) {
        System.out.println("[Queue] No visitors to remove: the queue is empty.");
        return null;
    }
    // Dequeue from the front (FIFO)
    Visitor v = waitingQueue.poll();
    System.out.println("[Queue] Removed from queue: " + v.getFullName()
            + " (ticket " + v.getTicketId() + "). Remaining: " + waitingQueue.size());
    return v;
}

    // Print all visitors currently waiting (front to back). Keep output readable.
    @Override
    public void printQueue() {
          if (waitingQueue.isEmpty()) {
        System.out.println("[Queue] The queue is empty.");
        return;
    }
    System.out.println("[Queue] Current waiting line (front → back):");
    int pos = 1;
    // For readability, a simple for-each is fine here
    for (Visitor v : waitingQueue) {
        System.out.println("  " + (pos++) + ". " + v.getFullName()
                + " (ticket " + v.getTicketId() + ")");
    }
}

    // Add a visitor to ride history (append at end). Print success/failure.
    @Override
    public boolean addVisitorToHistory(Visitor v) {
        throw new UnsupportedOperationException("addVisitorToHistory not implemented yet");
    }

    // Return true if visitor is present in ride history; print the check result.
    @Override
    public boolean checkVisitorFromHistory(Visitor v) {
        throw new UnsupportedOperationException("checkVisitorFromHistory not implemented yet");
    }

    // Return the number of visitors recorded in ride history.
    @Override
    public int numberOfVisitors() {
        throw new UnsupportedOperationException("numberOfVisitors not implemented yet");
    }

    // Print all visitors from history using an Iterator (marker expects Iterator usage).
    @Override
    public void printRideHistory() {
        // Example of the Iterator you’ll use later:
        // Iterator<Visitor> it = rideHistory.iterator();
        // while (it.hasNext()) { Visitor v = it.next(); ... }
        throw new UnsupportedOperationException("printRideHistory not implemented yet");
    }

    // Run a single ride cycle:
    // - Block if operator is null (ride closed) and print a clear message
    // - If queue is empty, print a message and return
    // - Dequeue up to maxRider visitors, append to history, report each
    // - Increment numOfCycles and print the new cycle count
    @Override
    public void runOneCycle() {
        throw new UnsupportedOperationException("runOneCycle not implemented yet");
    }
}
