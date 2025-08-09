public class AssignmentTwo {

    public static void partThree() {
        // Set up a ride and an operator
        Employee op = new Employee(1, "Sam Operator", "0412 152 245", "EMP-001", "Ride Operator", true);
        Ride ride = new Ride("Thunder Run", op, 4);

        // Create some visitors
        Visitor v1 = new Visitor(101, "Ava Smith", "0412 111 111", "T-A1", 22, 165);
        Visitor v2 = new Visitor(102, "Ben Jones", "0412 222 222", "T-B2", 19, 172);
        Visitor v3 = new Visitor(103, "Clio Kim", "0412 333 333", "T-C3", 25, 168);
        Visitor v4 = new Visitor(104, "Drew Lee",  "0412 444 444", "T-D4", 18, 180);
        Visitor v5 = new Visitor(105, "Eli Tran",  "0412 555 555", "T-E5", 20, 170);

        // Add to queue 5 visitors
        ride.addVisitorToQueue(v1);
        ride.addVisitorToQueue(v2);
        ride.addVisitorToQueue(v3);
        ride.addVisitorToQueue(v4);
        ride.addVisitorToQueue(v5);

        // Remove one (front leaves the queue)
        ride.removeVisitorFromQueue();

        // Print the current queue (should show 4 left, in FIFO order)
        ride.printQueue();
    }

    public static void main(String[] args) {
        partThree();
    }
}