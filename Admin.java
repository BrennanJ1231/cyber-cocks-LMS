import java.util.ArrayList;
import java.util.*;
import java.util.UUID;

public class Admin extends User { 

protected ArrayList <RegisteredUser> Students;

public Admin(UUID uuid, String type, String username, String firstName, String lastName, String email, Date birthday, String password ){
    super(uuid, type, firstName, lastName, email, birthday, username, password);
    this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        
}

public void assignCourse(Course course, RegisteredUser student){
    student.registerForCourse(course);
}
}