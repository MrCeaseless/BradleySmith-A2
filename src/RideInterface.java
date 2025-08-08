// Contract for ride behaviour used in the assignment.
// Queue methods (Part 3), history methods (Part 4A), run cycle (Part 5).
public interface RideInterface {

    // Queue (waiting line)
    boolean addVisitorToQueue(Visitor v);   // add to back of the queue; print success/failure
    Visitor removeVisitorFromQueue();       // remove from front; print success/failure
    void printQueue();                      // print all visitors currently waiting

    // Ride history (who actually rode)
    boolean addVisitorToHistory(Visitor v); // add to end of history; print success/failure
    boolean checkVisitorFromHistory(Visitor v); // true if visitor is in history; print result
    int numberOfVisitors();                 // count of visitors in history
    void printRideHistory();                // print all visitors using an Iterator

    // Run one ride cycle (moves up to maxRider from queue to history)
    void runOneCycle();                     // prints messages for each step; increments cycles
}
