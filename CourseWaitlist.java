import java.util.PriorityQueue;

public class CourseWaitlist {
    PriorityQueue<WaitlistEntry> courseWaitlist = new PriorityQueue<>();

    public void addToWaitlist(Student s, int priority) {
        WaitlistEntry entry = new WaitlistEntry(s, priority, System.currentTimeMillis());
        courseWaitlist.offer(entry);
        // System.out.println("Added to waitlist: " + s.getStudentId() + " with priority
        // " + priority);
    }

    public WaitlistEntry processNextStudent() {
        return courseWaitlist.poll();
    }

    public WaitlistEntry peekNextStudent() {
        return courseWaitlist.peek();
    }

    public int getWaitlistSize() {
        return courseWaitlist.size();
    }

    public void displayWaitlist() {
        // Note: iterating PriorityQueue doesn't guarantee priority order in iteration
        int pos = 1;
        System.out.println("=== Waiting List ===");
        for (WaitlistEntry e : courseWaitlist) {
            System.out.println("Position " + pos + ": " + e);
            pos++;
        }
    }
}
