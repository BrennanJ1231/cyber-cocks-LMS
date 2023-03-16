import java.util.ArrayList;
import java.util.Calendar;
public class Author extends User{
    protected int coursesCreated;
    protected ArrayList<Course> listOfCourses;

    public Author(String type, String username, String firstName, String lastName, String password, String email, Calendar birthday, int coursesCreated, ArrayList<Course> listOfCourses) {
        super(type,firstName, lastName, email, birthday,username,password);
        this.coursesCreated = coursesCreated;
        this.listOfCourses = listOfCourses;
    }
    public void createCourse(Course course) {
        listOfCourses.add(course);
        this.coursesCreated++;
    }
}
