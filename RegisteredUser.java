import java.util.ArrayList;
import java.util.*;
public class RegisteredUser extends User {

    protected ArrayList <Course> currentCourse;
    protected ArrayList <Progress> courseProgress;

    public RegisteredUser(String username, String firstName, String lastName, String password, String email, Calendar birthday ){
        super(username, firstName, lastName, password, email, birthday);
    }
    public RegisteredUser(String username, String firstName, String lastName, String password, String email, Calendar birthday, ArrayList<Course> currenCourses) {
        super(username, firstName, lastName, password, email, birthday);
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
