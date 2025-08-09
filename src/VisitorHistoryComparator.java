import java.util.Comparator;

// Compare by full name (A->Z, case-insensitive), then ticketId to break ties.
public class VisitorHistoryComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        int byName = v1.getFullName().compareToIgnoreCase(v2.getFullName());
        if (byName != 0) return byName;
        return v1.getTicketId().compareToIgnoreCase(v2.getTicketId());
    }
}
