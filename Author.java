import java.util.ArrayList;
import java.util.Date;

public class Author extends User{
    protected int coursesCreated;
    protected ArrayList<Course> listOfCourses;

    public Author(String username, String name, String email) {
        super(username, name, name, name, email );
    }
    public void createCourse(Course course) {
        listOfCourses.add(course);
    }
}
