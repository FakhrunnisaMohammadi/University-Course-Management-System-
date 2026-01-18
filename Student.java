import java.util.Objects;

public class Student {
    private String studentId;
    private String name;
    private String email;
    private int year;

    public Student(String studentId, String name, String email, int year) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.year = year;
    }

    // getters and setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name + ", Year: " + year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return Objects.equals(studentId, s.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}
