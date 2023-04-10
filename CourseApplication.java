import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;

/**
 * CourseApplication is a class that represents a course management application.
 * It allows users to create and manage courses, create and manage user accounts,
 * log in and log out, and perform other related tasks.
 */
public class CourseApplication{
    CourseList courselist;
    private UserList userList;
    protected Course Currentcourse;
    private User user;
    protected RegisteredUser regUser;
    private Admin admin;
    private Author author;

    /**
     * Constructor for the CourseApplication class. creates course list and user list
     */
     public CourseApplication() {
        this.courselist = CourseList.getInstance();
        this.userList = UserList.getInstance();
    }

     /**
     * Finds a course with a given name in the course list.
     *
     * @param name The name of the course to find.
     * @return The course with the given name.
     */
    public Course findCourse(String name) {
        Currentcourse = courselist.findCourse(name);
        return courselist.findCourse(name);
    }

     /**
     * Finds users with a given keyword in the user list.
     *
     * @param keyword The keyword to search for.
     * @return An ArrayList of users that match the keyword.
     */
    public ArrayList<User>findUser(String keyword) {
        return this.findUser(keyword);
    }

    /**
     * Finds a user with a given UUID in the user list.
     *
     * @param uuid The UUID of the user to find.
     * @return The user with the given UUID.
     */
    public User findUser(UUID uuid) {
        return userList.findUser(uuid);
    }

     /**
     * Creates a new registered user account in the user list.
     *
     * @param type The type of the user account.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email address of the user.
     * @param birthday The birthday of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The newly created registered user.
     */
    public User createUserAccount(String type, String firstName, String lastName, String email, java.util.Date birthday, String username, String password)  {
        User registeredUser = new RegisteredUser(UUID.randomUUID(), type, username, firstName, lastName, password, email, birthday, null );
        userList.addUser(registeredUser);
        return registeredUser;
    }

    /**
     * Creates a new admin user account in the user list.
     *
     * @param type The type of the user account.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email address of the user.
     * @param birthday The birthday of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The newly created admin user.
     */
    public User createAdminAccount(String type, String firstName, String lastName, String email, java.util.Date birthday, String username, String password){
        User admin = new Admin(UUID.randomUUID(), type, username, firstName, lastName, email, birthday, password);
        userList.addUser(admin);
        return admin;
    }    

       /**
     * Creates an author account with the given information.
     *
     * @param type the type of the user account
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param email the email of the user
     * @param birthday the birthday of the user
     * @param username the username of the user
     * @param password the password of the user
     * @return the created author account
     */
    public User createAuthorAccount(String type, String firstName, String lastName, String email, java.util.Date birthday, String username, String password){
        User author = new Author(UUID.randomUUID(), type, username, firstName, lastName, password, email, birthday, 0 , null );
        userList.addUser(author);
        return author;
    }
    
 /**
     * Logs the user into the application.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return true if the user is successfully logged in, false otherwise
     */
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

    /**
     * Logs the user out of the application and saves all data.
     */
    public void logout() {
        saveAll();
        System.out.println("Successfully saved data, logging out");
        System.exit(0);
    }
    
    /**
     * Gets the favorite courses of the user.
     *
     * @return the list of favorite courses of the user
     */
    public ArrayList<Course>getFavoriteCourses() {
        return this.getFavoriteCourses();
    }

    /**
     * Gets the courses of the user.
     *
     * @return the list of courses of the user
     */
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
    //public Course assignCourse(String name, Course course) {
        // return courselist.assignCourse(name, course);
    //}

    public void saveAll() {
        userList.save();
        courselist.save();
    }


}