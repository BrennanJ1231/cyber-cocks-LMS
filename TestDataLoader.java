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
	private CourseList courses = CourseList.getInstance();
	private ArrayList<Course> courseList = courses.getAll();
	private ArrayList<Comment> comments;
	private ArrayList<Module> modules;
	
	@BeforeEach
	public void setup() {
		userList.clear();
		courseList.clear();
		User author = new Author(UUID.randomUUID(), "Author", "Waltuh", "Walter", "White", "1231", "heisenberg@gmail.com", new Date(), 0, null);
		User registeredUser = new RegisteredUser(UUID.randomUUID(), "RegisteredUser", "jPinkman", "Jesse", "Pinkman","1231", "YeahScience@gmail.com", new Date(), null );
		User admin = new Admin(UUID.randomUUID(), "admin", "mikeE", "Mike", "Ermantrout", "PimentoCheese@gmail.com", new Date(), "1231");
		userList.add(author);
        userList.add(registeredUser);
        userList.add(admin);
		DataWriter.saveUser();

		ArrayList<Comment> commentsJava = new ArrayList<>();
		Comment comment1 = new Comment(registeredUser.uuid, "What a great Course!", new Date());
		commentsJava.add(comment1);
		comments = commentsJava;

		ArrayList<InstructiveMaterial> material = new ArrayList<>();
		InstructiveMaterial instructiveMaterial = new InstructiveMaterial("Print a Line", "System.out.println();");
		material.add(instructiveMaterial);

		ArrayList<Question> questions = new ArrayList<>();
		ArrayList<String> choices = new ArrayList<>();
		choices.add("System.out.println();");
		choices.add("This is the wrong answer");
		Question q1 = new Question("What is the command to print a line?", choices, "System.out.println();");
		questions.add(q1);

		ArrayList<Assignment> assignments = new ArrayList<>();
		Assignment assignment = new Assignment("Quiz 1", questions);
		assignments.add(assignment);

		ArrayList<Module> modulesJava = new ArrayList<>();
		Module module1 = new Module("How to print to console", "Instructions on how to print to console", material, assignments);
		modulesJava.add(module1);
		modules = modulesJava;

		Course java = new Course("Java 1", "This is an introduction to java", Language.JAVA, author.uuid, modulesJava, commentsJava);
		courseList.add(java);

		DataWriter.saveCourse();
	}
	
	@AfterEach
	public void tearDown() {
		users.getInstance().getAll().clear();
		courses.getInstance().getAll().clear();
		DataWriter.saveUser();
		DataWriter.saveCourse();
	}
	
	@Test
	public void testGetUsersSize() {
		userList = users.getInstance().getAll();
		assertEquals(3, userList.size());
	}

	@Test
	public void testGetUsersSizeZero() {
		users.getInstance().getAll().clear();
		DataWriter.saveUser();
		assertEquals(0, userList.size());
	}
	
	@Test
	public void testGetUserFirstName() {
		userList = users.getInstance().getAll();
		assertEquals("Walter", userList.get(0).getFirstName());
	}

    @Test
	public void testGetUserPassword() {
		userList = users.getInstance().getAll();
		assertEquals("1231", userList.get(0).getPassword());
	}
    
    @Test
	public void testGetUserType() {
		userList = users.getInstance().getAll();
		assertEquals("Author", userList.get(0).getType());
	}

	@Test
	public void testGetCourseName() {
		courseList = courses.getInstance().getAll();
		assertEquals("Java 1", courseList.get(0).name);
	}

	@Test
	public void testGetLanguage() {
		courseList = courses.getInstance().getAll();
		assertEquals(Language.JAVA, courseList.get(0).getLanguage());
	}

	@Test
	public void testGetCourseAuthor() {
		courseList = courses.getInstance().getAll();
		assertEquals("Waltuh", courseList.get(0).author);
	}

	@Test
	public void testGetCourseComments() {
		courseList = courses.getInstance().getAll();
		assertEquals(comments, courseList.get(0).comments);
	}

	@Test
	public void testGetCourseModules() {
		courseList = courses.getInstance().getAll();
		assertEquals(modules, courseList.get(0).modules);
	}
}