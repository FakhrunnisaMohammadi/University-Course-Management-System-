public class StudentCourseKey implements Comparable<StudentCourseKey> {
    private String studentId;
    private String courseCode;

    public StudentCourseKey(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public int compareTo(StudentCourseKey code) {
        int cmpare = this.studentId.compareTo(code.studentId);
        if (cmpare != 0) {
            return cmpare;
        }
        return this.courseCode.compareTo(code.courseCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StudentCourseKey)) {
            return false;
        }
        StudentCourseKey k = (StudentCourseKey) obj;
        return studentId.equals(k.studentId) && courseCode.equals(k.courseCode);
    }

    @Override
    public int hashCode() {
        return studentId.hashCode() * 31 + courseCode.hashCode();
    }

    @Override
    public String toString() {
        return studentId + "-" + courseCode;
    }
}
