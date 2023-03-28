import java.util.ArrayList;
import java.util.*;
import java.util.UUID;

/**
This class represents an Admin user who can assign courses to registered users. And extends the User class
*/
public class Admin extends User { 

protected ArrayList <RegisteredUser> Students;

/**
Constructs a new Admin object with the specified UUID, type, username, first name, last name, email, birthday, and password.
@param uuid the UUID of the Admin.
@param type the type of the Admin.
@param username the username of the Admin.
@param firstName the first name of the Admin.
@param lastName the last name of the Admin.
@param email the email address of the Admin.
@param birthday the date of birth of the Admin.
@param password the password of the Admin.
*/
public Admin(UUID uuid, String type, String username, String firstName, String lastName, String email, Date birthday, String password ){
    super(uuid, type, firstName, lastName, email, birthday, username, password);  
}

/**
Assigns a course to a registered user.
@param course the course to be assigned.
@param student the registered user to whom the course will be assigned.
*/
public void assignCourse(Course course, RegisteredUser student){
    student.registerForCourse(course);
}
}