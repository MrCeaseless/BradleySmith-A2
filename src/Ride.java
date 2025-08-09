import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
    public final void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ride name cannot be null or blank");
        }
        this.name = name.trim();
    }

    // Operator can be null to indicate the ride is closed/unassigned.
    public final void setOperator(Employee operator) {
        this.operator = operator;
    }

    public final void setMaxRider(int maxRider) {
        if (maxRider < 1) {
            throw new IllegalArgumentException("maxRider must be at least 1");
        }
        this.maxRider = maxRider;
    }

   
    public final void setNumOfCycles(int numOfCycles) {
        if (numOfCycles < 0) {
            throw new IllegalArgumentException("numOfCycles cannot be negative");
        }
        this.numOfCycles = numOfCycles;
    }

    // ===== Interface implementations =====
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
            System.out.println("[Queue] Added to queue: " + v.getFullName() + " (ticket " +v.getTicketId() + "). Size now: " + waitingQueue.size());
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
    System.out.println("[Queue] Current waiting line (front -> back):");
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
       if (v == null) {
        System.out.println("[History] Cannot add: visitor is null.");
        return false;
    }
    boolean added = rideHistory.add(v); // append at end
    if (added) {
        System.out.println("[History] Added: " + v.getFullName() + " (ticket " + v.getTicketId() + ")");
    } else {
        System.out.println("[History] Failed to add visitor.");
    }
    return added;
    }

    // Return true if visitor is present in ride history; print the check result.
    @Override
    public boolean checkVisitorFromHistory(Visitor v) {
          if (v == null) {
        System.out.println("[History] Check failed: visitor is null.");
        return false;
    }
    boolean present = rideHistory.contains(v); // uses Visitor.equals()
    System.out.println("[History] " + v.getFullName() + " (ticket " + v.getTicketId() + ") "
            + (present ? "is present." : "is NOT present."));
    return present;
}
    

    // Return the number of visitors recorded in ride history.
    @Override
    public int numberOfVisitors() {
         int count = rideHistory.size();
    System.out.println("[History] Total visitors recorded: " + count);
    return count;
    }

    // Print all visitors from history using an Iterator (marker expects Iterator usage).
    @Override
    public void printRideHistory() {
      if (rideHistory.isEmpty()) {
        System.out.println("[History] No visitors recorded yet.");
        return;
    }
    System.out.println("[History] All riders (first -> last):");

    // Iterator  required by the brief
    int i = 1;
    Iterator<Visitor> it = rideHistory.iterator();
    while (it.hasNext()) {
        Visitor v = it.next();
        System.out.println("  " + (i++) + ". " + v.getFullName()
                + " (ticket " + v.getTicketId() + ")");
    }

    }

    // Sort the ride history using any Comparator<Visitor>
public void sortHistory(Comparator<Visitor> comparator) {
    if (rideHistory.isEmpty()) {
        System.out.println("[Sort] Ride history is empty. Nothing to sort.");
        return;
    }
    Collections.sort(rideHistory, comparator); // LinkedList implements List
    System.out.println("[Sort] Ride history sorted.");
}

    // Run a single ride cycle:
    // - Block if operator is null (ride closed) and print a clear message
    // - If queue is empty, print a message and return
    // - Dequeue up to maxRider visitors, append to history, report each
    // - Increment numOfCycles and print the new cycle count
    @Override
    public void runOneCycle() {
         // 1) Safety checks
    if (operator == null || !operator.isActive()) {
        System.out.println("[Cycle] Cannot run: ride has no active operator assigned.");
        return;
    }
    if (waitingQueue.isEmpty()) {
        System.out.println("[Cycle] Cannot run: queue is empty.");
        return;
    }

    // 2) Work out how many seats to fill this cycle
    int seats = Math.min(maxRider, waitingQueue.size());
    System.out.println("[Cycle] Starting cycle #" + (numOfCycles + 1) +
            " on " + name + " with up to " + maxRider + " seats.");
    System.out.println("[Cycle] Filling " + seats + " seat(s).");

    // 3) Dequeue up to maxRider visitors and add each to history
    for (int i = 0; i < seats; i++) {
        Visitor v = waitingQueue.poll();               // from front of queue
        System.out.println("[Cycle] Riding: " + v.getFullName() +
                " (ticket " + v.getTicketId() + ")");
        rideHistory.add(v);                            // append to history
    }

    // 4) Increment cycle counter and report status
    numOfCycles++;
    System.out.println("[Cycle] Completed. Total cycles so far: " + numOfCycles);
    System.out.println("[Queue] Remaining in queue: " + waitingQueue.size());
}

public void exportRideHistory(Path file) {
    if (rideHistory.isEmpty()) {
        System.out.println("[File] No history to export.");
        return;
    }
    try (BufferedWriter bw = Files.newBufferedWriter(file)) {
        // header (optional, but helpful)
        bw.write("ticketId,fullName,phoneNumber,age,heightCm");
        bw.newLine();

        for (Visitor v : rideHistory) {
            String line = String.join(",",
                    v.getTicketId(),
                    escapeCsv(v.getFullName()),
                    escapeCsv(v.getPhoneNumber()),
                    String.valueOf(v.getAge()),
                    String.valueOf(v.getHeightCm())
            );
            bw.write(line);
            bw.newLine();
        }
        System.out.println("[File] Exported " + rideHistory.size() + " record(s) to " + file.toAbsolutePath());
    } catch (IOException e) {
        System.out.println("[File] Export failed: " + e.getMessage());
    }
}

// minimal CSV escaping (quote if contains comma or quote)
private String escapeCsv(String s) {
    if (s == null) return "";
    if (s.contains(",") || s.contains("\"")) {
        return "\"" + s.replace("\"", "\"\"") + "\"";
    }
    return s;
}

public void importRideHistory(Path file) {
    if (!Files.exists(file)) {
        System.out.println("[File] Import failed: file not found -> " + file.toAbsolutePath());
        return;
    }
    int loaded = 0, skipped = 0;
    try (BufferedReader br = Files.newBufferedReader(file)) {
        String line;
        boolean isFirst = true;
        while ((line = br.readLine()) != null) {
            if (isFirst) { // skip header
                isFirst = false;
                // If the first line is not a header, remove this block or detect by prefix
                if (line.toLowerCase().startsWith("ticketid")) continue;
            }
            String[] cols = parseCsvLine(line);
            if (cols.length != 5) { // ticketId, fullName, phone, age, heightCm
                skipped++;
                continue;
            }
            try {
                String ticketId = unquote(cols[0]).trim();
                String fullName = unquote(cols[1]).trim();
                String phone    = unquote(cols[2]).trim();
                int age         = Integer.parseInt(unquote(cols[3]).trim());
                int heightCm    = Integer.parseInt(unquote(cols[4]).trim());

                // Reconstruct Visitor with a dummy unique numeric id (not used in equals())
                Visitor v = new Visitor(100000 + rideHistory.size() + loaded + 1,
                        fullName, phone, ticketId, age, heightCm);
                rideHistory.add(v);
                loaded++;
            } catch (NumberFormatException ex) {
                // skip over malformeds row or validation errors
                skipped++;
            }
        }
        System.out.println("[File] Imported " + loaded + " record(s), skipped " + skipped + ". From: " + file.toAbsolutePath());
    } catch (IOException e) {
        System.out.println("[File] Import failed: " + e.getMessage());
    }
}

// very small CSV parser for quoted fields with commas
private String[] parseCsvLine(String line) {
    java.util.List<String> out = new java.util.ArrayList<>();
    StringBuilder cur = new StringBuilder();
    boolean inQuotes = false;
    for (int i = 0; i < line.length(); i++) {
        char ch = line.charAt(i);
        if (inQuotes) {
            if (ch == '"') {
                if (i + 1 < line.length() && line.charAt(i + 1) == '"') { // escaped quote
                    cur.append('"'); i++;
                } else {
                    inQuotes = false;
                }
            } else {
                cur.append(ch);
            }
        } else {
            switch (ch) {
            case '"' -> inQuotes = true;
            case ',' -> {
                out.add(cur.toString());
                cur.setLength(0);
                }
            default -> cur.append(ch);
            }
        }
    }
    out.add(cur.toString());
    return out.toArray(String[]::new);
}

private String unquote(String s) {
    if (s == null) return "";
    s = s.trim();
    if (s.startsWith("\"") && s.endsWith("\"") && s.length() >= 2) {
        return s.substring(1, s.length() - 1).replace("\"\"", "\"");
    }
    return s;
}
}
