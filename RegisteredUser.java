import java.util.ArrayList;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
/**
This class represents a registered user of the system that can enroll in courses.
*/
public class RegisteredUser extends User {

 
    protected ArrayList <Course> currentCourse; //The list of courses the user is currently enrolled in.
    protected ArrayList <Progress> courseProgress; //The list of progress made by the user for each course

    /**
    Constructs a new RegisteredUser with the given properties.
    @param uuid The unique identifier for this user.
    @param type The type of user (e.g. "registered").
    @param username The user's username.
    @param firstName The user's first name.
    @param lastName The user's last name.
    @param password The user's password.
    @param email The user's email address.
    @param birthday The user's date of birth.
    @param currentCourses The list of courses the user is currently enrolled in.
    */
    public RegisteredUser(UUID uuid, String type, String username, String firstName, String lastName, String password, String email, Date birthday, ArrayList<Course> currentCourses) {
        super(uuid, type, firstName, lastName, email, birthday, username, password);
        this.currentCourse = currentCourses;
    }

    /**
    Returns the list of courses the user is currently enrolled in.
    @return The list of courses the user is currently enrolled in.
    */
    public ArrayList<Course> courseEnrolled() {
        return currentCourse;
    }
    
    /**
    Registers the user for the given course.
    @param course The course to register for.
    */
    public void registerForCourse(Course course) {
        currentCourse.add(course);
    }

    /**
    Unregisters the user from the given course.
    @param course The course to unregister from.
    */
    public void unregisterForCourse(Course course) {
        currentCourse.remove(course);
    }

    /**
    Adds a rating for the given course.
    @param course The course to add the rating for.
    @param rating The rating to add.
    */
    public void addRating(Course course, double rating){
        CourseList courses = CourseList.getInstance();
        ArrayList<Course> courseList = courses.getAll();
        for(int i = 0; i<courseList.size(); i++) {
            if(course.uuid.equals(courseList.get(i).uuid)) {

            }
        }
    }

    /**
    Returns true if the user is older than 18 years old and false otherwise.
    @return True if the user is older than 18 years old, false otherwise.
    */
    public boolean showComment() {
        Instant instant = birthday.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        Period period = Period.between(givenDate, LocalDate.now());
        return period.getYears() > 18;
    }

}
