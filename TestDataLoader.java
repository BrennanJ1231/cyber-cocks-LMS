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
		User author = new Author(UUID.randomUUID(), "Author", "Waltuh", "Walter", "White", "1231", "heisenberg@gmail.com", new Date(), 0, null);
		User registeredUser = new RegisteredUser(UUID.randomUUID(), "RegisteredUser", "jPinkman", "Jesse", "Pinkman","1231", "YeahScience@gmail.com", new Date(), null );
		User admin = new Admin(UUID.randomUUID(), "admin", "mikeE", "Mike", "Ermantrout", "PimentoCheese@gmail.com", new Date(), "1231");
		userList.add(author);
        userList.add(registeredUser);
        userList.add(admin);
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