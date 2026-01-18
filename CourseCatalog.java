import java.util.Iterator;
import java.util.LinkedList;

public class CourseCatalog {
    LinkedList<Course> courseCatalog = new LinkedList<>();

    // add course at the beginning of the list
    public void addCourseAtBeginning(Course c) {
        if (c == null) {
            return;
        }
        courseCatalog.addFirst(c);
    }

    // add course at the end of the list
    public void addCourseAtEnd(Course c) {
        if (c == null) {
            return;
        }
        courseCatalog.addLast(c);
    }

    // remove course from list
    public boolean removeCourse(String courseCode) {
        Iterator<Course> it = courseCatalog.iterator();
        while (it.hasNext()) {
            Course c = it.next();
            if (c.getCourseCode().equals(courseCode)) {
                it.remove();
                System.out.println("Removed course " + courseCode);
                return true;
            }
        }
        System.out.println("Course not found: " + courseCode);
        return false;
    }

    // display course forward
    public void displayCoursesForward() {
        System.out.println("___ Course Catalog (forward) ___");
        for (Course c : courseCatalog) {
            System.out.println(c);
        }
    }

    // display course backward
    public void displayCoursesBackward() {
        System.out.println("___ Course Catalog (backward) ___");
        Iterator<Course> it = courseCatalog.descendingIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    // search course using course code
    public Course searchCourse(String courseCode) {
        for (Course c : courseCatalog) {
            if (c.getCourseCode().equals(courseCode)) {
                return c;
            }
        }
        return null;
    }

    // get course method for main class
    public Course getCourse(String courseCode) {
        for (Course c : courseCatalog) { // or whatever your internal list is called
            if (c.getCourseCode().equals(courseCode)) {
                return c;
            }
        }
        return null;
    }

    public void getAllCourses() {
        if (courseCatalog.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("___ All Courses ___");
        for (Course c : courseCatalog) {
            System.out.println(c);
        }
    }

    /*
     * public List<Course> getAllCourses() {
     * 
     * return new ArrayList<>(courseCatalog);
     * 
     * }
     * 
     */
}
