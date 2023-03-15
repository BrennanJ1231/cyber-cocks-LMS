import java.util.*;
public class User {
    protected String userID;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
    protected Calendar birthday;
    public User(String username, String firstName, String lastName, String password, String email, Calendar birthday) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }
    public String getUserID() {
        return this.userID;
    }
}
