import java.util.ArrayList;
import java.util.*;
public class RegisteredUser extends User {

    protected ArrayList <Course> currentCourse;
    protected ArrayList <Progress> courseProgress;

    public RegisteredUser(UUID uuid, String type, String username, String firstName, String lastName, String password, String email, Calendar birthday, ArrayList<Course> currenCourses) {
        super(uuid, type, firstName, lastName, email, birthday, username, password);
        this.currentCourse = currenCourses;
    }

    public void addFavorite(){

    }
    public void courseEnrolled(){

    }
    public void getCourse(){
        for (int i =0 ; i < currentCourse.size();i++){
            System.out.println(currentCourse.get(i));
        }
    }
    public void registerForCourse(Course course){
        currentCourse.add(course);
    }
    public void unregisterForCourse(Course course){
        currentCourse.remove(course);
    }
    public void addRating(){

    }
    public boolean showComment(int age){
        //@TODO add return type
    }

}
