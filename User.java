import java.util.*;
public class User {
    protected String userID;
    protected String type;
    protected String username;
    protected String type;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
    protected Calendar birthday;
    public User(String type, String firstName, String lastName, String email, String birthday2, String username, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
<<<<<<< HEAD
        this.birthday = birthday2;
=======
        this.birthday = birthday;
        this.type = type;
>>>>>>> de9801900693586e77db23e23a0d963794ff85f7
    }
    public String getUserID() {
        return this.userID;
    }
    public String getPassword() {
        return this.password;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getType() {
        return this.type;
    }
}
