import java.util.ArrayList;

public class RegisteredUser extends User {

    protected ArrayList <Course> currentCourse;
    protected ArrayList <Progress> courseProgress;

    public RegisteredUser(String username, String firstName, String lastName, String email ){
        this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
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
