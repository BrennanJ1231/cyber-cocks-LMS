import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest {
	private UserList users = UserList.getInstance();
	private ArrayList<User> userList = users.getAll();
    private CourseList courses = CourseList.getInstance();
    private ArrayList<Course> courseList = courses.getAll();
	
	@BeforeEach
	public void setup() {
		UserList.getInstance().getAll().clear();
		DataWriter.saveUser();
        CourseList.getInstance().getAll().clear();
        DataWriter.saveCourse();
	}
	
	@AfterEach
	public void tearDown() {
		UserList.getInstance().getAll().clear();
		DataWriter.saveUser();
	}
	
	
	@Test
	void testWritingZeroUsers() {
		userList = DataLoader.loadUsers();
		assertEquals(0, userList.size());
	}

	@Test
	void testWritingOneUser() {
		userList.add(new User(UUID.randomUUID(),"Author", "John", "Wick","jwick@email.com", new Date(), "him", "cactus"));
		DataWriter.saveUser();
		assertEquals("John", DataLoader.loadUsers().get(0).getFirstName());
	}
	
	@Test
	void testWritingFiveUsers() {
		userList.add(new User(UUID.randomUUID(),"Author", "John", "Wick","jwick@email.com", new Date("11/04/2001"), "him", "cactus"));
		userList.add(new User(UUID.randomUUID(),"Author", "Steve", "Oreck","stevie@email.com", new Date(), "SteveO", "chrisanthimum"));
		userList.add(new User(UUID.randomUUID(),"User", "Ryan", "Stanton","ryguy@email.com", new Date(), "him", "cactus"));
		userList.add(new User(UUID.randomUUID(),"Admin", "French", "Montana","Drizzy@email.com", new Date(), "him", "cactus"));
		userList.add(new User(UUID.randomUUID(),"Admin", "Frankie", "Blooms","bloomingonion@email.com", new Date(), "him", "cactus"));
		DataWriter.saveUser();
		assertEquals("Frankie", DataLoader.loadUsers().get(4).getFirstName());
	}
	
	@Test
	void testWritingEmptyUser() {
		userList.add(new User(UUID.randomUUID(), "", "", "", "",new Date(),"",""));
		DataWriter.saveUser();
		assertEquals("", DataLoader.loadUsers().get(0).getFirstName());
	}
	
	@Test
	void testWritingNullUser() {
		userList.add(new User("","",null,"", "",new Date(),"",""));
		DataWriter.saveUser();
		assertEquals(null, DataLoader.loadUsers().get(0).getFirstName());
	}
	
}
