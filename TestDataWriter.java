import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

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
		userList = DataLoader.getAll();
		assertEquals(0, userList.size());
	}

	@Test
	void testWritingOneUser() {
		userList.add(new User(UUID.randomUUID(),"Author", "John", "Wick","jwick@email.com", newDate(), "him", "cactus"));
		DataWriter.saveUser();
		assertEquals("asmith", DataLoader.getAll().get(0).getUserName());
	}
	
	@Test
	void testWritingFiveUsers() {
		userList.add(new User("asmith", "Amy", "Smith", 19, "803-454-3344"));
		userList.add(new User("bsmith", "Amy", "Smith", 19, "803-454-3344"));
		userList.add(new User("csmith", "Amy", "Smith", 19, "803-454-3344"));
		userList.add(new User("dsmith", "Amy", "Smith", 19, "803-454-3344"));
		userList.add(new User("esmith", "Amy", "Smith", 19, "803-454-3344"));
		DataWriter.saveUsers();
		assertEquals("esmith", DataLoader.getUsers().get(4).getUserName());
	}
	
	@Test
	void testWritingEmptyUser() {
		userList.add(new User("", "", "", 0, ""));
		DataWriter.saveUsers();
		assertEquals("", DataLoader.getUsers().get(0).getUserName());
	}
	
	@Test
	void testWritingNullUser() {
		userList.add(new User(null, "", "", 0, ""));
		DataWriter.saveUsers();
		assertEquals(null, DataLoader.getUsers().get(0).getUserName());
	}
	
}
