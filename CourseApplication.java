import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;

public class CourseApplication{
    private CourseList courselist;
    private UserList userList;
    protected Course Currentcourse;
    private User user;
    private RegisteredUser regUser;
    private Admin admin;
    private Author author;

    /**
     * Constructor for the CourseApplication class. creates course list and user list
     */
     public CourseApplication() {
        this.courselist = CourseList.getInstance();
        this.userList = UserList.getInstance();
    }

    public Course findCourse(String name) {
        Currentcourse = courselist.findCourse(name);
        return courselist.findCourse(name);
    }

    public ArrayList<User>findUser(String keyword) {
        return this.findUser(keyword);
    }
    public User findUser(UUID uuid) {
        return this.findUser(uuid);
    }

    public User createUserAccount(String type, String firstName, String lastName, String email, java.util.Date birthday, String username, String password)  {
        User registeredUser = new RegisteredUser(UUID.randomUUID(), type, username, firstName, lastName, password, email, birthday, null );
        userList.addUser(registeredUser);
        return registeredUser;
    }
    public User createAdminAccount(String type, String firstName, String lastName, String email, java.util.Date birthday, String username, String password){
        User admin = new Admin(UUID.randomUUID(), type, username, firstName, lastName, email, birthday, password);
        userList.addUser(admin);
        return admin;
    }    
    public User createAuthorAccount(String type, String firstName, String lastName, String email, java.util.Date birthday, String username, String password){
        User author = new Author(UUID.randomUUID(), type, username, firstName, lastName, password, email, birthday, 0 , null );
        userList.addUser(author);
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

    public void logout() {
        saveAll();
        System.out.println("Successfully saved data, logging out");
        System.exit(0);
    }
    
    public ArrayList<Course>getFavoriteCourses() {
        return this.getFavoriteCourses();
    }

    public ArrayList<Course>getMyCourses() {
        if(user.type.equalsIgnoreCase("author"))
            return author.listOfCourses;
        if(user.type.equalsIgnoreCase("registered user"))
            return regUser.currentCourse;
        return null;    
        //Change this based on whether the user is author or Registered User
    }

    public Course addCourse(Course newCourse) {
        courselist.addCourse(newCourse);
        author.createCourse(newCourse);
        return newCourse;
    }
    public Course editCourse(Course course, String title) {
        course.name = title;
        return course;
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

    public void saveAll() {
        userList.save();
        courselist.save();
    }


}