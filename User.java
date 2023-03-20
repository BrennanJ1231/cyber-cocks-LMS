
import java.util.*;
public class User {
    protected UUID uuid;
    protected String type;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
<<<<<<< HEAD
    protected Calendar birthday;
    public User(String type, String firstName, String lastName, String email, Calendar birthday2, String username, String password) {
=======
    protected Date birthday;
    public User(UUID uuid, String type, String firstName, String lastName, String email, Date birthday, String username, String password) {
>>>>>>> a7bd7d95ece0884e4660a2f0c7571ffde8f2bac9
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.type = type;
        this.uuid = uuid;
    }
    public UUID getUserID() {
        return this.uuid;
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
