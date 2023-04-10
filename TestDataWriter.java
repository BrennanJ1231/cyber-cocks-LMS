// Copyright Mason Loeb
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Mason Loeb
class TestDataWriter{
	private UserList users = UserList.getInstance();
	private ArrayList<User> userList = users.getAll();
    private CourseList courses = CourseList.getInstance();
    private ArrayList<Course> courseList = courses.getAll();
	private ArrayList<Module> module = new ArrayList<>(); 
    /**
     * Set up for each test clears user list and saves it
     */
	@BeforeEach
	public void setup() {
		UserList.getInstance().getAll().clear();
        CourseList.getInstance().getAll().clear();
        DataWriter.saveCourse();
		User author = new Author(UUID.randomUUID(), "Author", "barelylegal", "Saul", "Goodman", "honesty", "bettercallsaul@gmail.com", new Date(), 0, null);
		User registeredUser = new RegisteredUser(UUID.randomUUID(), "RegisteredUser", "jPinkman", "Jesse", "Pinkman","1231", "YeahScience@gmail.com", new Date(), null );
		User admin = new Admin(UUID.randomUUID(), "admin", "mikeE", "Mike", "Ermantrout", "PimentoCheese@gmail.com", new Date(), "1231");
		userList.add(author);
        userList.add(registeredUser);
        userList.add(admin);
		DataWriter.saveUser();
	}
	
    /**
     * Clears the UserList and Course List. Same as setup but occurs after the test to tear it down
     */
	@AfterEach
	public void tearDown() {
		UserList.getInstance().getAll().clear();
		DataWriter.saveUser();
        CourseList.getInstance().getAll().clear();
        DataWriter.saveCourse();
	}
	

    // User Writing Tests
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
	
    /**
     * checks the size of the UserList when no User is added
     */
	@Test
	void testWritingZeroUsers() {
		userList = DataLoader.loadUsers();
		assertEquals(0, userList.size());
	}

    /**
     * tests the data writer to see if it writes one user when added to the userList
     */
	@Test
	void testWritingOneUser() {
		User regUser = new RegisteredUser(UUID.randomUUID(), "RegisteredUser", "Keanue", "John", "Wick","stella", "luvmydog@gmail.com", new Date(), null );
		userList.add(regUser);
		DataWriter.saveUser();
		DataLoader.loadUsers();
		assertEquals("John", userList.get(3).getFirstName());
	}
	
    /**
     * Tests if the correct user is going to be in the position desired when there are more than one user
     */
	@Test
	void testWritingFourMoreUsers() {
		userList.add(new RegisteredUser(UUID.randomUUID(), "RegisteredUser", "Keanue", "John", "Wick","stella", "luvmydog@gmail.com", new Date(), null));
		userList.add(new Author(UUID.randomUUID(),"Author","bigSteve", "Steve", "Oreck","choop", "stevie@email.com", new Date(), 0,null));
		userList.add(new RegisteredUser(UUID.randomUUID(),"RegisteredUser", "bigman", "Ryan", "Stanton", "basball" ,"ryguy@email.com", new Date(), null));
		userList.add(new Admin(UUID.randomUUID(),"Admin", "DrakesUncle","French", "Montana","Drizzy@email.com", new Date(),"cactus"));
		DataWriter.saveUser();
		DataLoader.loadUsers();
		assertEquals("French", userList.get(6).getFirstName());
	}
	

    /**
     * tests how the program handles blanks as the input
     */
	@Test
	void testEmptyType() {
		userList.add(new RegisteredUser(UUID.randomUUID(), "", "", "", "","", "", new Date(), null));
		DataWriter.saveUser();
		DataLoader.loadUsers();
		assertEquals("RegisteredUser", userList.get(3).getType());
	}

	/**
     * tests how the program handles null as the input for the type
     */
	 @Test
	void testNullType() {
		userList.add(new RegisteredUser(UUID.randomUUID(), null, "", "", "","", "", new Date(), null));
		DataWriter.saveUser();
		DataLoader.loadUsers();
		assertEquals("RegisteredUser", userList.get(3).getType());
	}
	
	/**
	 * 
	 */
	@Test
	void testblankAge(){
		userList.add(new Admin(UUID.randomUUID(),"Admin", "DrakesUncle","French", "Montana","Drizzy@email.com", new Date(),"cactus"));
		DataWriter.saveUser();
		DataLoader.loadUsers();
		assertEquals(false, userList.get(3).getAge());
	}

    /**
     * Tests how the program handles a null in the params of User and see if it returns null when that param is called
     */
	@Test
	void testWritingNullName() {
		userList.add(new Author(UUID.randomUUID(),"Author",null,null,null,null, null, new Date(),0,null));
		DataWriter.saveUser();
		DataLoader.loadUsers();
		assertEquals(null, userList.get(3).getFirstName());
	}
	// Course Writing Tests
    //////////////////////////////////////////////////////////////////////////////////////////////////
     /**
     * tests the data loader to see if courseList is empty with nothing added
     */
    @Test
    void testWritingZeroCourses() {
        courseList = DataLoader.loadCourses();
		assertEquals(0, courseList.size());
    }

    /**
     * tests the data writer to see if it writes one Course when added to the Course List
     */
	@Test
	void testWritingOneCourse() {
		courseList.add(new Course("Loops", "Creating loops with code", Language.JAVA, UUID.randomUUID(),module, null ));
		DataWriter.saveCourse();
		DataLoader.loadCourses();
		assertEquals("Loops", courseList.get(0).getCourseName());
	}
	
    /**
     * Tests if the correct Course is going to be in the position desired when there are more than one Course
     */
	@Test
	void testWritingFiveCourses() {
		courseList.add(new Course("Loops","Different Loops",Language.JAVASCRIPT, UUID.randomUUID(), module, null));
		courseList.add(new Course("Hello World", "", Language.C_PLUS_PLUS, UUID.randomUUID(), module, null));
		courseList.add(new Course("Inheritance", "", Language.C_SHARP, UUID.randomUUID(), module, null));
		courseList.add(new Course("C++ Basics", "", Language.C_PLUS_PLUS, UUID.randomUUID(), module, null));
		courseList.add(new Course("Classes", "", Language.PHP, UUID.randomUUID(), module, null));
		DataWriter.saveCourse();
		DataLoader.loadCourses();
		assertEquals("Hello World", courseList.get(1).getCourseName());
	}
	

    /**
     * tests how the program handles blanks as the input
     */
	@Test
	void testWritingNullLanguage() {
		courseList.add(new Course("","",null, UUID.randomUUID(), module, null));
		DataWriter.saveCourse();
		DataLoader.loadCourses();
		assertEquals(null,courseList.get(0).getLanguage());
	}
	
    /**
     * Tests how the program handles a null in the params of Course and see if it returns null when that param is called
     */
	@Test
	void testWritingNullCourse() {
		courseList.add(new Course(null,null,null, null, module, null));
		DataWriter.saveCourse();
		DataLoader.loadCourses();
		assertEquals(null, courseList.get(0).getCourseName());
	}

    // Comment Testing and Language Testing

    @Test
    void testCourseLanguage() {
        courseList.add(new Course("Loops", "Creating loops with code", Language.JAVA, UUID.randomUUID(),module, null ));
        DataWriter.saveCourse();
		DataLoader.loadCourses();
        assertEquals("JAVA", courseList.get(0).getLanguage());
    }


}
