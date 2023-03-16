import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {
    public static final String USERS_FILE_NAME = "users.json";
	public static final String COURSES_FILE_NAME = "courses.json";
	public static void saveUser(String fileName) {
        UserList userList = UserList.getInstance();
		ArrayList<User> users = userList.getAll();
		JSONArray jsonUsers = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< users.size(); i++) {
            if(users.get(i).getType().equalsIgnoreCase("RegisteredUser")) {
                RegisteredUser regUser = new RegisteredUser(users.get(i).username, users.get(i).firstName, users.get(i).lastName, users.get(i).password, users.get(i).email, users.get(i).birthday, users.get(i).currentCourses);
                //users.get(i);
                jsonUsers.add(getRegisteredUserJSON(regUser));
            }
            if(users.get(i).getType().equalsIgnoreCase("Admin")) {
                Admin adminUser = new Admin(users.get(i).username, users.get(i).firstName, users.get(i).lastName, users.get(i).password, users.get(i).email, users.get(i).birthday, users.get(i).students);
                jsonUsers.add(getAdminUserJSON(adminUser));
            }
            if(users.get(i).getType().equalsIgnoreCase("Author")) {
                Author authorUser = new Author(users.get(i).username, users.get(i).firstName, users.get(i).lastName, users.get(i).password, users.get(i).email, users.get(i).birthday, users.get(i).currentCourses);
                jsonUsers.add(getAuthorUserJSON(authorUser));
            }
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(USERS_FILE_NAME)) {
 
            file.write(jsonUsers.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static JSONObject getRegisteredUserJSON(RegisteredUser user) {
		JSONObject userDetails = new JSONObject();
            userDetails.put("UUID",user.userID);
            userDetails.put("type","Registered User");
            userDetails.put("userName",user.username);
            userDetails.put("firstName",user.firstName);
            userDetails.put("lastName",user.lastName);
            userDetails.put("password",user.password);
            userDetails.put("email",user.email);
            userDetails.put("birthday",user.birthday);
            userDetails.put("currentCourse", user.currentCourse);
            userDetails.put("courseProgress",user.courseProgress);
        return userDetails;
	}
    public static JSONObject getAdminUserJSON(Admin user) {
		JSONObject userDetails = new JSONObject();
            userDetails.put("UUID",user.userID);
            userDetails.put("type","Admin");
            userDetails.put("userName",user.username);
            userDetails.put("firstName",user.firstName);
            userDetails.put("lastName",user.lastName);
            userDetails.put("password",user.password);
            userDetails.put("email",user.email);
            userDetails.put("birthday",user.birthday);
            userDetails.put("students", user.students);
        return userDetails;
	}
    public static JSONObject getAuthorUserJSON(Author user) {
		JSONObject userDetails = new JSONObject();
        userDetails.put("UUID",user.userID);
        userDetails.put("type","Author");
        userDetails.put("userName",user.username);
        userDetails.put("firstName",user.firstName);
        userDetails.put("lastName",user.lastName);
        userDetails.put("password",user.password);
        userDetails.put("email",user.email);
        userDetails.put("birthday",user.birthday);
        userDetails.put("createdCourses", user.coursesCreated);
        userDetails.put("coursesCreated", user.listOfCourses);
        return userDetails;
	}

    public static void saveCourse(String fileName) {
        CourseList courseList = CourseList.getInstance();
		ArrayList<Course> courses = courseList.getAll();
		JSONArray jsonCourses = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< courses.size(); i++) {
            jsonCourses.add(getCourseJSON(courses.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            file.write(jsonCourses.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    public static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
		courseDetails.put("UUID", course.uuid);
        courseDetails.put("name", course.name);
        courseDetails.put("language", course.language);
        courseDetails.put("description", course.description);
        for(int i = 0; i < course.modules.size(); i++) {
            courseDetails.put("modules", getModuleJSON(course.modules.get(i)));
        }
        courseDetails.put("comments", course.modules);
        courseDetails.put("rating", course.modules);
        return courseDetails;
    }
    public static JSONObject getModuleJSON(Module module) {
        JSONObject moduleDetails = new JSONObject();
		moduleDetails.put("title", module.title);
        moduleDetails.put("description", module.description);
        for(int i = 0; i < module.material.size(); i++) {
            moduleDetails.put("material", getMaterialJSON(module.material.get(i)));
        }
        for(int i = 0; i < module.test.size(); i++) {
            moduleDetails.put("quizzes", getAssignmentJSON(module.test.get(i)));
        }
        return moduleDetails;
    }
    public static JSONObject getMaterialJSON(InstructiveMaterial material) {
        JSONObject materialDetails = new JSONObject();
		materialDetails.put("name", material.name);
        materialDetails.put("content", material.content);
        return materialDetails;
    }
    public static JSONObject getAssignmentJSON(Assignment test) {
        JSONObject assignmentDetails = new JSONObject();
		assignmentDetails.put("name", test.name);
        assignmentDetails.put("type", test.type);
        Question newQuestion;
        for(int i = 0; i < test.questions.size(); i++) {
            assignmentDetails.put("questions", getQuestionJSON(test.questions.get(i)));
        }
        return assignmentDetails;
    }
    public static JSONObject getQuestionJSON(Question question) {
        JSONObject questionDetails = new JSONObject();
		questionDetails.put("name", question.question);
        questionDetails.put("choices", question.choices);
        questionDetails.put("correctAnswer", question.correctAnswer);
        return questionDetails;
    }
    public static JSONObject getCommentsJSON(Comment comment) {
        JSONObject commentDetails = new JSONObject();
		commentDetails.put("author", comment.author);
        commentDetails.put("content", comment.content);
        commentDetails.put("datePosted", comment.date);
        commentDetails.put("comments", comment.comments);
        return commentDetails;
    }
    public static void main(String[] args) {
        DataLoader.loadCourses();
        //DataLoader.LoadUsers();
        //DataWriter.saveUser(USERS_FILE_NAME);
        DataWriter.saveCourse(COURSES_FILE_NAME);
    }
}
