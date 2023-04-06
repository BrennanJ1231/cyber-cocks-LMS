import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class TestUserlist {
    private UserList users = UserList.getInstance();
    private ArrayList<User> userList = users.getAll();
    @BeforeEach
    public void setup() {
        userList.clear();
        userList.add(new RegisteredUser(UUID.randomUUID(), "Registered User", "bJohnson", "Brennan", "Johnson", "1231", "email", new Date(), null));
        userList.add(new Author(UUID.randomUUID(), "Author", "rAycock", "Ryan", "Aycock", "1231", "email", new Date(), 0, null));
        userList.add(new Admin(UUID.randomUUID(), "Admin", "mLoeb", "Mason", "Loeb", "1231", new Date(), null));
        DataWriter.saveUser("./json/users.json");
    }
    @AfterEach
    public void tearDown() {
        UserList.getInstance().getAll().clear();
        DataWriter.saveUser("./json/users.json");
    }

    @Test
    void testHaveValidFirstItem() {
        boolean hasBrennan = users.findUser("bJohnson").username.equalsIgnoreCase("bJohnson");
        assertTrue(hasBrennan);
    }

    void testHaveValidMiddleItem() {
        boolean hasRyan = users.findUser("rAycock").username.equalsIgnoreCase("rAycock");
        assertTrue(hasRyan);
    }

    void testHaveValidLastItem() {
        boolean hasMason = users.findUser("mLoeb").username.equalsIgnoreCase("mLoeb");
        assertTrue(hasMason);
    }

}
