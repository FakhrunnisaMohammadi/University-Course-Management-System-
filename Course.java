import java.util.Objects;

public class Course implements Comparable<Course> {
    private String courseCode;
    private String courseName;
    private int credits;
    private int capacity;

    public Course(String courseCode, String courseName, int credits, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.capacity = capacity;
    }

    // getters and setters method for course
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // toString method
    @Override
    public String toString() {
        return "[" + courseCode + "] " + courseName + " (" + credits + " credits, cap: " + capacity + ")";
    }

    // overridw equals method here
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        Course c = (Course) o;
        return Objects.equals(courseCode, c.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    // For TreeSet sorting by courseCode
    @Override
    public int compareTo(Course o) {
        if (o == null) {
            return 1;
        }
        return this.courseCode.compareTo(o.courseCode);
    }
}
