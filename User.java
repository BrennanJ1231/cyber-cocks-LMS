import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
/**
The User class represents a user in the system with various attributes such as
UUID, type, username, first name, last name, password, email, and birthday.
*/
public class User {
    protected UUID uuid;
    protected String type;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
    protected Date birthday;


/**
 * Constructs a new user with the given parameters.
 * @param uuid
 * @param type
 * @param firstName
 * @param lastName
 * @param email
 * @param birthday
 * @param username
 * @param password
 */
    public User(UUID uuid, String type, String firstName, String lastName, String email, Date birthday, String username, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.type = type;
        this.uuid = uuid;
    }

    /*
     * Gets the uuid of the user
     * @return a UUID
     */
    public UUID getUserID() {
        return this.uuid;
    }

    /**
    Returns the password of the user.
    @return a String of a password .
    */
    public String getPassword() {
        return this.password;
    }

    /*
     * Gets the first name of the user
     * @returns a String of the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /*
     * gets the type of user
     * returns aString of the type
     */
    public String getType() {
        return this.type;
    }

    /*
     * gets the age of the user
     * returns a boolean of the age return true if the user's age is over 18 years old, false otherwise.
     */
    public boolean getAge(){
      Instant instant = birthday.toInstant();
      ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
      LocalDate givenDate = zone.toLocalDate();
      Period period = Period.between(givenDate, LocalDate.now());
      return period.getYears() > 18;
    }
}
