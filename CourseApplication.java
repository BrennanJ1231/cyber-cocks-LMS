import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;

public class CourseApplication{
    private CourseList courselist;
    private UserList userList;
    private Course Currentcourse;
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

    public User createUserAccount(String type, String firstName, String lastName, String email, java.util.Date birthday, String username, String password)  {
        User registeredUser = new RegisteredUser(null, type, username, firstName, lastName, password, email, birthday, null );
        this.userList.addUser(registeredUser);
        return registeredUser;
    }
    public User createAdminAccount(String type, String firstName, String lastName, String email, java.util.Date birthday, String username, String password){
        User admin = new Admin(null, type, username, firstName, lastName, email, birthday, password);
        this.userList.addUser(admin);
        return admin;
    }

    
    public User createAuthorAccount(String type, String firstName, String lastName, String email, java.util.Date birthday, String username, String password){
        User author = new Author(null, type, username, firstName, lastName, password, email, birthday, 0 , null );
        this.userList.addUser(author);
        return author;
    }
    

    public Boolean login(String username, String password) {
        User user = this.userList.findUser(username);  //Are you thinking about adding a "findUser" method?
        if (user != null &&  user.getPassword() == password) {
            this.user = user;
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Course>getMyCourses() {
        return this.getMyCourses();
    }

    public Course addCourse(String title) {
        Course newCourse = new Course(title, title, null, null);
        courselist.addCourse(newCourse);
        return newCourse;
    }
    public Course editCourse(Course course, String title) {
        course.name = title;
        return course;
    }

    public Module addModule(Course course) {
        Module newModule = new Module(course, null, null, null);
        return newModule;
    }
    public double reviewCourse (Course course, double rating) {
        course.rating = rating;
        return rating;
    }
    public String takeAssignment(Course course, String assignmentName){
        //Where are the assignments to be stored fo them to be accessed to be taken?
    }
}