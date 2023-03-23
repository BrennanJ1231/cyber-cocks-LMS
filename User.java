import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
public class User {
    protected UUID uuid;
    protected String type;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
    protected Date birthday;
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
    public boolean getAge(){
      Instant instant = birthday.toInstant();
      ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
      LocalDate givenDate = zone.toLocalDate();
      Period period = Period.between(givenDate, LocalDate.now());
      return period.getYears() > 18;
    }
}
