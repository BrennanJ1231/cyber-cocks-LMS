import java.util.ArrayList;
import java.util.*;
import java.util.UUID;
/**
 * The Author class extends the User class and represents an author who has created courses.
 */
public class Author extends User{
    protected int coursesCreated;
    protected ArrayList<Course> listOfCourses;

      /**
     * Creates a new instance of the Author class with the given parameters.
     *
     * @param uuid The UUID of the author.
     * @param type The type of user (e.g. "Author", "Student").
     * @param username The username of the author.
     * @param firstName The first name of the author.
     * @param lastName The last name of the author.
     * @param password The password of the author.
     * @param email The email of the author.
     * @param birthday The birthday of the author.
     * @param coursesCreated The number of courses created by the author.
     * @param listOfCourses The list of courses created by the author.
     */
    public Author(UUID uuid, String type, String username, String firstName, String lastName, String password, String email, Date birthday, int coursesCreated, ArrayList<Course> listOfCourses) {
        super(uuid, type,firstName, lastName, email, birthday,username,password);
        this.coursesCreated = coursesCreated;
        this.listOfCourses = listOfCourses;
    }

     /**
     * Creates a new course and adds it to the author's list of courses.
     *
     * @param course The course to create and add to the author's list of courses.
     */
    public void createCourse(Course course) {
        if(listOfCourses != null){
            listOfCourses.add(course);
        }
        else  {
            listOfCourses = new ArrayList<Course>();
            listOfCourses.add(course);
        }
        this.coursesCreated++;
    }
}
