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
		userList.add(new User(UUID.randomUUID(),"Author", "Walter", "White", "heisenberg@gmail.com", new Date("11\09\1958"), "Waltuh", "1231"));
        userList.add(new User(UUID.randomUUID(),"RegisteredUser", "Jesse", "Pinkman", "YeahScience@gmail.com", new Date("11\03\1978"), "YeahBitch", "1231"));
        userList.add(new User(UUID.randomUUID(),"Admin", "Mike", "Ermantrout", "PimentoCheese@gmail.com", new Date("01\05\1949"), "Mike", "1231"));
		DataWriter.saveUser();
	}
	
	@AfterEach
	public void tearDown() {
		users.getInstance().getAll().clear();
		DataWriter.saveUser();
	}
	
	
	@Test
	void testGetUsersSize() {
		userList = DataLoader.loadUsers();
		assertEquals(3, userList.size());
	}

	@Test
	void testGetUsersSizeZero() {
		users.getInstance().getAll().clear();
		DataWriter.saveUser();
		assertEquals(0, userList.size());
	}
	
	@Test
	void testGetUserFirstUserName() {
		userList = DataLoader.loadUsers();
		assertEquals("Walter", userList.get(0).getFirstName());
	}

    @Test
	void testGetUserPassword() {
		userList = DataLoader.loadUsers();
		assertEquals("1231", userList.get(0).getPassword());
	}
    
    @Test
	void testGetUserType() {
		userList = DataLoader.loadUsers();
		assertEquals("Author", userList.get(0).getType());
	}

    @Test
    void testUserAge() {
        userList = DataLoader.loadUsers();
		assertEquals(true, userList.get(0).getAge());
    }
}