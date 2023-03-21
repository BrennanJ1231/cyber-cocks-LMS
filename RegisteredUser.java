import java.util.ArrayList;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
public class RegisteredUser extends User {

    protected ArrayList <Course> currentCourse;
    protected ArrayList <Progress> courseProgress;

    public RegisteredUser(UUID uuid, String type, String username, String firstName, String lastName, String password, String email, Date birthday, ArrayList<Course> currenCourses) {
        super(uuid, type, firstName, lastName, email, birthday, username, password);
        this.currentCourse = currenCourses;
    }

    public ArrayList<Course> courseEnrolled(){
       return currentCourse;
    }
    public void getCourse(){
       
    }
    public void registerForCourse(Course course){
        currentCourse.add(course);
    }
    public void unregisterForCourse(Course course){
        currentCourse.remove(course);
    }
    public void addRating(Course course, double rating){
        CourseList courses = CourseList.getInstance();
        ArrayList<Course> courseList = courses.getAll();
        for(int i = 0; i<courseList.size(); i++) {
            if(course.uuid.equals(courseList.get(i).uuid)) {

            }
        }
    }
    public boolean showComment(){
        Instant instant = birthday.toInstant();
      ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
      LocalDate givenDate = zone.toLocalDate();
      Period period = Period.between(givenDate, LocalDate.now());
      return period.getYears() >18;
    }

}
