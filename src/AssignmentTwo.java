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
        System.out.println("=======Part 3========");
        partThree();
        System.out.println("=======Part 4a========");
        partFourA();
         System.out.println("=======Part 4b========");
         partFourB();
         System.out.println("=======Part 5========");
         partFive();
         System.out.println("=======Part 6========");
         partSix();
         System.out.println("=======Part 7========");
         partSeven();
    }

    public static void partFourA() {
    Employee op = new Employee(2, "Nina Operator", "0412 000 100", "EMP-002", "Ride Operator", true);
    Ride ride = new Ride("Falcon Loop", op, 4);

    Visitor a = new Visitor(201, "Ada Park", "0412 101 101", "TK-A1", 21, 163);
    Visitor b = new Visitor(202, "James Ray", "0412 202 202", "TK-B2", 18, 175);
    Visitor c = new Visitor(203, "Karl Jacobs", "0412 303 303", "TK-C3", 23, 168);
    Visitor d = new Visitor(204, "Brian Smith",  "0412 404 404", "TK-D4", 20, 171);
    Visitor e = new Visitor(205, "Ava Wilson",  "0412 505 505", "TK-E5", 19, 166);

    // Simulate that these people already rode
    ride.addVisitorToHistory(a);
    ride.addVisitorToHistory(b);
    ride.addVisitorToHistory(c);
    ride.addVisitorToHistory(d);
    ride.addVisitorToHistory(e);

    ride.checkVisitorFromHistory(c);   // should be true
    ride.checkVisitorFromHistory(new Visitor(999, "George Michel", "0412 000 000", "TK-Z9", 30, 180)); // false

    ride.numberOfVisitors();
    ride.printRideHistory();
}

public static void partFourB() {
    Employee op = new Employee(3, "Mika Operator", "0412 000 200", "EMP-003", "Ride Operator", true);
    Ride ride = new Ride("Comet Dash", op, 4);

    // Intentionally out of order + mixed case to show sorting
    ride.addVisitorToHistory(new Visitor(301, "Zane Cruz", "0412 111 000", "TK-11", 20, 170));
    ride.addVisitorToHistory(new Visitor(302, "Alex Moon", "0412 222 000", "TK-02", 19, 168));
    ride.addVisitorToHistory(new Visitor(303, "Alex Moon", "0412 333 000", "TK-01", 22, 175)); // Using the same name to initate a tie which is then broken late due to the ticketId being different
    ride.addVisitorToHistory(new Visitor(304, "Bella Sun", "0412 444 000", "TK-33", 25, 165));
    ride.addVisitorToHistory(new Visitor(305, "Cara Lin",  "0412 555 000", "TK-22", 23, 168));

    System.out.println("Before sort:");
    ride.printRideHistory();

    VisitorHistoryComparator cmp = new VisitorHistoryComparator();
    ride.sortHistory(cmp);

    System.out.println("After sort (name, then ticket):");
    ride.printRideHistory();
}

public static void partFive() {
    Employee op = new Employee(10, "Riley Operator", "0412 999 000",
            "EMP-010", "Ride Operator", true);
    Ride ride = new Ride("Vortex", op, 4);   // capacity 4 per cycle

    // Add 10 visitors to the queue
    for (int i = 1; i <= 10; i++) {
        ride.addVisitorToQueue(new Visitor(500 + i, "Visitor " + i, "0412 000 00" + i,
                "Q-" + i, 20 + (i % 5), 160 + i));
    }

    // Show queue before running
    System.out.println("=======Before cycle=======");
    ride.printQueue();

    // Run one cycle
    System.out.println("=======Run one cycle=======");
    ride.runOneCycle();

    // Show queue and history after
    System.out.println("=======After cycle=======");
    ride.printQueue();
    ride.numberOfVisitors();
    ride.printRideHistory();
}

public static void partSix() {
    Employee op = new Employee(20, "Opal Operator", "0412 700 000", "EMP-020", "Ride Operator", true);
    Ride ride = new Ride("Cyclone", op, 4);

    ride.addVisitorToHistory(new Visitor(801, "Liam Shaw", "0412 111 111", "H-01", 22, 171));
    ride.addVisitorToHistory(new Visitor(802, "Mia Wong",  "0412 222 222", "H-02", 20, 165));
    ride.addVisitorToHistory(new Visitor(803, "Noah O'Neil", "0412 333 333", "H-03", 24, 178)); // shows quote handling
    ride.addVisitorToHistory(new Visitor(804, "Zara, Kay", "0412 444 444", "H-04", 19, 160));   // shows comma handling

    java.nio.file.Path out = java.nio.file.Paths.get("ride_history.csv");
    ride.exportRideHistory(out);
}

public static void partSeven() {
    Employee op = new Employee(21, "Ivy Operator", "0412 800 000", "EMP-021", "Ride Operator", true);
    Ride ride = new Ride("Tempest", op, 4);

    java.nio.file.Path in = java.nio.file.Paths.get("ride_history.csv");
    ride.importRideHistory(in);

    ride.numberOfVisitors();
    ride.printRideHistory();
}

}