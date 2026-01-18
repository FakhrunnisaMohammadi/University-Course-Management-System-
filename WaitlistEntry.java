public class WaitlistEntry implements Comparable<WaitlistEntry> {
    private Student student;
    private int priority; // lower number = higher priority
    private long timestamp;

    public WaitlistEntry(Student student, int priority, long timestamp) {
        this.student = student;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    public Student getStudent() {
        return student;
    }

    public int getPriority() {
        return priority;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(WaitlistEntry o) {
        if (this.priority != o.priority) {
            return Integer.compare(this.priority, o.priority);
        }
        return Long.compare(this.timestamp, o.timestamp);
    }

    @Override
    public String toString() {
        return student.getName() + " (Priority: " + priority + ")";
    }
}
