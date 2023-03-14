import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class Author extends User{
    protected int coursesCreated;
    protected ArrayList<Course> listOfCourses;

    public Author(String username, String name, String email) {
        super(username, name, name, name, email );
    }
    public Author(String username, String firstName, String lastName, String password, String email, Calendar birthday, int coursesCreated, ArrayList<Course> listOfCourses) {
        super(username, firstName, lastName, password, email, birthday);
        this.coursesCreated = coursesCreated;
        this.listOfCourses = listOfCourses;
    }
    public void createCourse(Course course) {
        listOfCourses.add(course);
    }
}
