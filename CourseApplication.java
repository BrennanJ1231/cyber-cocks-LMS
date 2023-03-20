import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;


public class CourseApplication{
    private CourseList courselist;
    private UserList userList;
    private CurrentCourse course;
    private User user;

    /**
     * Constructor for the CourseApplication class. creates course list and user list
     */
    
    
     public CourseApplication() {
        this.courselist = new CourseList();
        this.userList = new UserList();
    }

    public ArrayList<Course>findCourses(String keyword) {
        return this.findCourses(keyword);
    }

    public ArrayList<User>findUser(String keyword) {
        return this.findUser(keyword);
    }

    public User createUserAccount(String type, String firstName, String lastName, String email, Calendar birthday2, String username, String password)  {
        User user = new User(type, firstName, lastName,  email, birthday2, username, password);
        this.userList.addUser(user);
        return user;
    }
    public User createAdminAccount(String type, String firstName, String lastName, String email, String birthday, String username, String password){
        User admin = new User(type, firstName, lastName, email, null, username, password);
        this.userList.addUser(admin);
        return admin;
    }
    public User createAuthorAccount(String type, String firstName, String lastName, String email, String birthday, String username, String password){
        User author = new User(type, firstName, lastName, email, null, username, password);
        this.userList.addUser(author);
        return author;
    }
    

    public Boolean login(String username, String password) {
        User user = this.userList.findUser(username);
        if (user != null &&  user.getPassword() == password) {
            this.user = user;
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<Course>getFavoriteCourses() {
        return this.getFavoriteCourses();
    }

    public ArrayList<Course>getMyCourses() {
        return this.getMyCourses();
    }

    public Course addCourse(String title) {

    }
    public Course editCourse(Course course, String title) {
        return 
    }
    public double reviewCourse (Course course) {
        return 0;
    }
    public String takeAssignment(Course course, String assignmentName){

    }
}