import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataWriterTest {
	private UserList users = UserList.getInstance();
	private ArrayList<User> userList = users.getAll();
    private CourseList courses = CourseList.getInstance();
    private ArrayList<Course> courseList = courses.getAll();
    /**
     * Set up for each test clears user list and saves it
     */
	@BeforeEach
	public void setup() {
		UserList.getInstance().getAll().clear();
		DataWriter.saveUser();
        CourseList.getInstance().getAll().clear();
        DataWriter.saveCourse();
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
	public void testWritingZeroUsers() {
		userList = DataLoader.loadUsers();
		assertEquals(0, userList.size());
	}

    /**
     * tests the data writer to see if it writes one user when added to the userList
     */
	@Test
	public void testWritingOneUser() {
		userList.add(new User(UUID.randomUUID(),"Author", "John", "Wick","jwick@email.com", new Date(), "him", "cactus"));
		DataWriter.saveUser();
		assertEquals("John", DataLoader.loadUsers().get(0).getFirstName());
	}
	
    /**
     * Tests if the correct user is going to be in the position desired when there are more than one user
     */
	@Test
	public void testWritingFiveUsers() {
		userList.add(new User(UUID.randomUUID(),"Author", "John", "Wick","jwick@email.com", null, "him", "cactus"));
		userList.add(new User(UUID.randomUUID(),"Author", "Steve", "Oreck","stevie@email.com", new Date(), "SteveO", "chrisanthimum"));
		userList.add(new User(UUID.randomUUID(),"User", "Ryan", "Stanton","ryguy@email.com", new Date(), "him", "cactus"));
		userList.add(new User(UUID.randomUUID(),"Admin", "French", "Montana","Drizzy@email.com", new Date(), "him", "cactus"));
		userList.add(new User(UUID.randomUUID(),"Admin", "Frankie", "Blooms","bloomingonion@email.com", new Date(), "him", "cactus"));
		DataWriter.saveUser();
		assertEquals("Frankie", DataLoader.loadUsers().get(4).getFirstName());
	}
	

    /**
     * tests how the program handles blanks as the input
     */
	@Test
	public void testWritingEmptyUser() {
		userList.add(new User(UUID.randomUUID(), "", "", "", "",new Date(),"",""));
		DataWriter.saveUser();
		assertEquals("", DataLoader.loadUsers().get(0).getFirstName());
	}
	
    /**
     * Tests how the program handles a null in the params of User and see if it returns null when that param is called
     */
	@Test
	public void testWritingNullUser() {
		userList.add(new User(null,null,null,"", null,null,null,null));
		DataWriter.saveUser();
		assertEquals(null, DataLoader.loadUsers().get(0).getFirstName());
	}
	// Course Writing Tests
    //////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * tests the data loader to see if courseList is empty with nothing added
     */
    @Test
   	public void testWritingZeroCourses() {
        courseList = DataLoader.loadCourses();
		assertEquals(0, courseList.size());
    }

    /**
     * tests the data writer to see if it writes one Course when added to the Course List
     */
	@Test
	public void testWritingOneCourse() {
		courseList.add(new Course("Loops", "Creating loops with code", Language.JAVA, UUID.randomUUID(),null, null ));
		DataWriter.saveUser();
		assertEquals("John", DataLoader.loadUsers().get(0).getFirstName());
	}
	
    /**
     * Tests if the correct Course is going to be in the position desired when there are more than one Course
     */
	@Test
	public void testWritingFiveCourses() {
		courseList.add(new Course("Loops","Different Loops",Language.JAVASCRIPT, UUID.randomUUID(), null, null));
		courseList.add(new Course("Hello World", "", Language.C_PLUS_PLUS, UUID.randomUUID(), null, null));
		courseList.add(new Course("Inheritance", "", Language.C_SHARP, UUID.randomUUID(), null, null));
		courseList.add(new Course("C++ Basics", "", Language.C_PLUS_PLUS, UUID.randomUUID(), null, null));
		courseList.add(new Course("Classes", "", Language.PHP, UUID.randomUUID(), null, null));
		DataWriter.saveCourse();
		assertEquals("Hello World", DataLoader.loadCourses().get(2).getCourseName());
	}
	

    /**
     * tests how the program handles blanks as the input
     */
	@Test
	public void testWritingEmptyCourse() {
		courseList.add(new Course("","",null, UUID.randomUUID(), null, null));
		DataWriter.saveCourse();
		assertEquals("", DataLoader.loadCourses().get(0).getCourseName());
	}
	
    /**
     * Tests how the program handles a null in the params of Course and see if it returns null when that param is called
     */
	@Test
	public void testWritingNullCourse() {
		courseList.add(new Course(null,null,null, null, null, null));
		DataWriter.saveCourse();
		assertEquals(null, DataLoader.loadCourses().get(0).getCourseName());
	}

    // Comment Testing and Language Testing

    @Test
    public void testCourseLanguage() {
        courseList.add(new Course("Loops", "Creating loops with code", Language.JAVA, UUID.randomUUID(),null, null ));
        DataWriter.saveCourse();
        assertEquals("JAVA", DataLoader.loadCourses().get(0).getLanguage());
    }

    @Test
    public void testNullLanguage() {
        courseList.add(new Course("Hello World", "Creating your first code", null, UUID.randomUUID(),null, null ));
        DataWriter.saveCourse();
        assertEquals(null, DataLoader.loadCourses().get(0).getLanguage());
    }

    @Test
    public void testComments() {
		courseList.add(new Course(null, null, null, null, null,null));
        DataWriter.saveCourse();
        assertEquals(null, DataLoader.loadCourses().get(0).comments);
    }

    @Test
    public void testNullComments(){
        
    }

    @Test
    public void testCommentComments() {

    }

}
