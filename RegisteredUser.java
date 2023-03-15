import java.util.ArrayList;
import java.util.*;
public class RegisteredUser extends User {

    protected ArrayList <Course> currentCourse;
    protected ArrayList <Progress> courseProgress;

    public RegisteredUser(String type, String username, String firstName, String lastName, String password, String email, Calendar birthday, ArrayList<Course> currenCourses) {
        super(type, firstName, lastName, email, birthday, username, password);
        this.currentCourse = currenCourses;
    }

    public void addFavorite(){

    }
    public void courseEnrolled(){

    }
    public void getCourse(){

    }
    public void registerForCourse(){

    }
    public void unregisterForCourse(){

    }
    public void addRating(){

    }
    public boolean showComment(int age){
        //@TODO add return type
    }

}
