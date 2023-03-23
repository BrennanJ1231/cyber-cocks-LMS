import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;

public class CourseApplication{
    private CourseList courselist;
    private UserList userList;
    Course Currentcourse;
    private User user;
    private RegisteredUser regUser;
    private Admin admin;
    private Author author;

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
        User user = this.userList.findUser(username);  
        if (user != null &&  user.getPassword().equals(password)) {
            this.user = user;
            if(this.user.type.equalsIgnoreCase("Registered User")) {
                regUser = (RegisteredUser) user;
            }
            if(this.user.type.equalsIgnoreCase("Author")) {
                author = (Author) user;
            }
            if(this.user.type.equalsIgnoreCase("Admin")) {
                admin = (Admin) user;
            }
            return true;
        } else {
            return false;
        }
    }
    
    public ArrayList<Course>getFavoriteCourses() {
        return this.getFavoriteCourses();
    }

    public ArrayList<Course>getMyCourses() {
        return regUser.currentCourse;
    }

    public Course addCourse(String title) {
        Course newCourse = new Course(title, title, null, null, null, null);
        courselist.addCourse(newCourse);
        return newCourse;
    }
    public Course editCourse(Course course, String title) {
        course.name = title;
        return course;
    }

    public Module addModule(String name, String description) {
        Module newModule = new Module(name, description, null);
        return newModule;
    }
    public double reviewCourse (Course course, double rating) {
        course.rating = rating;
        return rating;
    }
    public String takeAssignment(Course course, String assignmentName){
        return assignmentName;
    }
    public User getUser() {
        return this.user;
    }
    public Course takeCourse(String name) {
        return courselist.findCourse(name);
    }
}