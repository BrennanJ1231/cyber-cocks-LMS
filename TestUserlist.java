import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// Brennan Johnson
public class TestUserlist {
    private UserList users = UserList.getInstance();
    private ArrayList<User> userList = users.getAll();
    @BeforeEach
    public void setup() {
        userList.clear();
        userList.add(new RegisteredUser(UUID.randomUUID(), "Registered User", "bJohnson", "Brennan", "Johnson", "1231", "email", new Date(), null));
        userList.add(new Author(UUID.randomUUID(), "Author", "rAycock", "Ryan", "Aycock", "1231", "email", new Date(), 0, null));
        userList.add(new Admin(UUID.randomUUID(), "Admin", "mLoeb", "Mason", "Loeb", "1231", new Date(), null));
        DataWriter.saveUser();
    }
    @AfterEach
    public void tearDown() {
        UserList.getInstance().getAll().clear();
        DataWriter.saveUser();
    }

    @Test
    public void testHaveValidFirstItem() {
        boolean hasBrennan = users.findUser("bJohnson").username.equalsIgnoreCase("bJohnson");
        assertTrue(hasBrennan);
    }
    @Test
    public void testHaveValidMiddleItem() {
        boolean hasRyan = users.findUser("rAycock").username.equalsIgnoreCase("rAycock");
        assertTrue(hasRyan);
    }
    @Test
    public void testHaveValidLastItem() {
        boolean hasMason = users.findUser("mLoeb").username.equalsIgnoreCase("mLoeb");
        assertTrue(hasMason);
    }
    @Test
    public void testHaveInvalidItem() {
        boolean hasInvalid = users.findUser("no one") == null;
        assertTrue(hasInvalid);
    }

    @Test

    public void testFindNull() {
        String name = null;
        boolean hasNull = users.findUser(name) == null;
        assertTrue(hasNull);

    }

    @Test 
    public void testDeleteUser() {
        users.deleteUser(userList.get(0));
        boolean hasUser = users.findUser("bJohnson") == null;
        assertTrue(hasUser);
    }
    @Test 
    public void testFindByUUID() {
        boolean hasUser = users.findUser(userList.get(1).uuid).username.equalsIgnoreCase("rAycock");
        assertTrue(hasUser);
    }
    @Test
    public void testFindInvalidUUID() {
        boolean hasUser = users.findUser(UUID.randomUUID()) == null;
        assertTrue(hasUser);
    }
    @Test
    public void testFindNullUUId() {
        UUID uuid = null;
        boolean hasUser = users.findUser(uuid) == null;
        assertTrue(hasUser);
    }
    @Test
    public void testAddNullUser() {
        users.addUser((new User(null, null, null, null, null, null, null, null));
        boolean hasNull = userList.get(userList.size()-1) == null;
        assertTrue(hasNull);
    }
    @Test
    public void addInvalidUserType() {
        users.addUser(new User(UUID.randomUUID(),"Type","Brennan", "Johnson", "brennan@email.com", new Date(), "b", "1231"));
        boolean hasUser = users.findUser("b") == null;
        assertTrue(hasUser);
    }
    @Test
    public void addDuplicate() {
        users.addUser(new RegisteredUser(UUID.randomUUID(), "Registered User", "bJohnson", "Brennan", "Johnson", "1231", "email", new Date(), null));
        boolean hasUser = userList.get(userList.size()-1) == null;
        assertTrue(hasUser);
    }


}
