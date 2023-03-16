import java.util.ArrayList;
import java.util.Calendar;

public class Admin extends User { 

protected ArrayList <RegisteredUser> Students;

public Admin(String type, String username, String firstName, String lastName, String email, Calendar birthday, String password ){
    super(type, firstName, lastName, email, birthday, username, password);
    this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        
}

public void assignCourse(Course course, RegisteredUser student){
    student.registerForCourse(course);
}
}