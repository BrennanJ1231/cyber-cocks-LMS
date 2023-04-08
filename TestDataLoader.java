import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDataLoader {
	private UserList users = UserList.getInstance();
	private ArrayList<User> userList = users.getAll();
	
	@BeforeEach
	public void setup() {
		userList.clear();
		userList.add(new User(UUID.randomUUID(),"Author", "Walter", "White", "heisenberg@gmail.com", new Date(), "Waltuh", "1231"));
        userList.add(new User(UUID.randomUUID(),"RegisteredUser", "Jesse", "Pinkman", "YeahScience@gmail.com", new Date(), "YeahBitch", "1231"));
        userList.add(new User(UUID.randomUUID(),"Admin", "Mike", "Ermantrout", "PimentoCheese@gmail.com", new Date(), "Mike", "1231"));
		DataWriter.saveUser();
	}
	
	@AfterEach
	public void tearDown() {
		users.getInstance().getAll().clear();
		DataWriter.saveUser();
	}
	
	
	@Test
	public void testGetUsersSize() {
		userList = DataLoader.loadUsers();
		assertEquals(3, userList.size());
	}

	@Test
	public void testGetUsersSizeZero() {
		users.getInstance().getAll().clear();
		DataWriter.saveUser();
		assertEquals(0, userList.size());
	}
	
	@Test
	public void testGetUserFirstUserName() {
		userList = DataLoader.loadUsers();
		assertEquals("Walter", userList.get(0).getFirstName());
	}

    @Test
	public void testGetUserPassword() {
		userList = DataLoader.loadUsers();
		assertEquals("1231", userList.get(0).getPassword());
	}
    
    @Test
	public void testGetUserType() {
		userList = DataLoader.loadUsers();
		assertEquals("Author", userList.get(0).getType());
	}

    @Test
    public void testUserAge() {
        userList = DataLoader.loadUsers();
		assertEquals(true, userList.get(0).getAge());
    }
}