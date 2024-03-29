import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * This class contains methods to write data to JSON files.
 */
public class DataWriter {
    public static final String USERS_FILE_NAME = "./json/users.json";
	public static final String COURSES_FILE_NAME = "./json/courses.json";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("mm/dd/yyyy");

/**
 * writes all the user data to the JSON file
 * @param fileName
 */
	public static void saveUser() {
        UserList userList = UserList.getInstance();
		ArrayList<User> users = userList.getAll();
		JSONArray jsonUsers = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< users.size(); i++) {
            if(users.get(i).getType().equalsIgnoreCase("Registered User")) {
                jsonUsers.add(getRegisteredUserJSON((RegisteredUser) users.get(i)));
            }
            if(users.get(i).getType().equalsIgnoreCase("Admin")) {
                jsonUsers.add(getAdminUserJSON((Admin) users.get(i)));
            }
            if(users.get(i).getType().equalsIgnoreCase("Author")) {
                jsonUsers.add(getAuthorUserJSON((Author) users.get(i)));
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
	
    /**
     * Creates a JSON object for register user
     * @param user
     * @return JSONObject
     */
	public static JSONObject getRegisteredUserJSON(RegisteredUser user) {
		JSONObject userDetails = new JSONObject();
            userDetails.put("UUID",user.uuid.toString());
            userDetails.put("type","Registered User");
            userDetails.put("username",user.username);
            userDetails.put("firstName",user.firstName);
            userDetails.put("lastName",user.lastName);
            userDetails.put("password",user.password);
            userDetails.put("email",user.email);
            userDetails.put("birthday",FORMATTER.format(user.birthday));
            if(user.currentCourse != null) {
                JSONArray courses = new JSONArray();
                for(int i = 0; i < user.currentCourse.size();  i++) {
                    courses.add(addCourses(user.currentCourse.get(i)));
                }
                userDetails.put("currentCourses", courses);
            }
            if(user.courseProgress != null) {
                JSONArray progress = new JSONArray();
                for(int i = 0; i < user.courseProgress.size();  i++) {
                    progress.add(addProgress(user.courseProgress.get(i)));
                }
                userDetails.put("courseProgress", progress);

            }
        return userDetails;
	}
    public static JSONObject addProgress(Progress progress) {
        JSONObject progressDetails = new JSONObject();
        progressDetails.put("courseProgress", progress.courseProgress);
        return progressDetails;
    }

    /**
     * Creates a JSON object for admin
     * @param user
     * @return JSONObject
     */
    public static JSONObject getAdminUserJSON(Admin user) {
		JSONObject userDetails = new JSONObject();
            userDetails.put("UUID",user.uuid.toString());
            userDetails.put("type","Admin");
            userDetails.put("username",user.username);
            userDetails.put("firstName",user.firstName);
            userDetails.put("lastName",user.lastName);
            userDetails.put("password",user.password);
            userDetails.put("email",user.email);
            userDetails.put("birthday",FORMATTER.format(user.birthday));
            userDetails.put("students", user.Students);
        return userDetails;
	}

    /**
     * Creates a JSON object for auther
     * @param user
     * @return JSONObject
     */
    public static JSONObject getAuthorUserJSON(Author user) {
		JSONObject userDetails = new JSONObject();
        userDetails.put("UUID",user.uuid.toString());
        userDetails.put("type","Author");
        userDetails.put("username",user.username);
        userDetails.put("firstName",user.firstName);
        userDetails.put("lastName",user.lastName);
        userDetails.put("password",user.password);
        userDetails.put("email",user.email);
        userDetails.put("birthday",FORMATTER.format(user.birthday));
        userDetails.put("createdCourses", user.coursesCreated);
        if(user.listOfCourses != null) {
            JSONArray courses = new JSONArray();
            for(int i = 0; i < user.listOfCourses.size();  i++) {
                courses.add(addCourses(user.listOfCourses.get(i)));
            }
            userDetails.put("coursesCreated", courses);
        }
        return userDetails;
	}
    public static JSONObject addCourses(Course course) {
        JSONObject courses = new JSONObject();
        courses.put("UUID", course.uuid.toString());
        return courses;
    }


    public static void saveCourse() {
        CourseList courseList = CourseList.getInstance();
		ArrayList<Course> courses = courseList.getAll();
		JSONArray jsonCourses = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< courses.size(); i++) {
            jsonCourses.add(getCourseJSON(courses.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(COURSES_FILE_NAME)) {
            file.write(jsonCourses.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    public static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
		courseDetails.put("UUID", course.uuid.toString());
        courseDetails.put("name", course.name);
        courseDetails.put("language", course.language.toString());
        courseDetails.put("description", course.description);
        JSONArray jsonModules = new JSONArray();
        for(int i = 0; i < course.modules.size(); i++) {
            jsonModules.add(getModuleJSON(course.modules.get(i)));
        }
        courseDetails.put("modules", jsonModules);
        if(course.comments != null) {
            JSONArray comments = new JSONArray();
            for(int i = 0; i < course.comments.size();  i++) {
                comments.add(getCommentsJSON(course.comments.get(i)));
            }
            courseDetails.put("comments", comments);
        }
        courseDetails.put("rating", course.rating);
        return courseDetails;
    }
    public static JSONObject getModuleJSON(Module module) {
        JSONObject moduleDetails = new JSONObject();
		moduleDetails.put("title", module.title);
        moduleDetails.put("description", module.description);
        JSONArray jsonMaterial = new JSONArray();
        for(int i = 0; i < module.material.size(); i++) {
            jsonMaterial.add(getMaterialJSON(module.material.get(i)));
        }
        moduleDetails.put("material", jsonMaterial);
        JSONArray jsonTest = new JSONArray();
        for(int i = 0; i < module.test.size(); i++) {
            jsonTest.add(getAssignmentJSON(module.test.get(i)));
        }
        moduleDetails.put("assignment", jsonTest);
        if(module.comments != null) {
            JSONArray comments = new JSONArray();
            for(int i = 0; i < module.comments.size();  i++) {
                comments.add(getCommentsJSON(module.comments.get(i)));
            }
            moduleDetails.put("comments", comments);
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
        JSONArray jsonTest = new JSONArray();
        for(int i = 0; i < test.questions.size(); i++) {
            jsonTest.add(getQuestionJSON(test.questions.get(i)));
        }
        assignmentDetails.put("questions", jsonTest);
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
		commentDetails.put("author", comment.author.toString());
        commentDetails.put("content", comment.content);
        commentDetails.put("datePosted", FORMATTER.format(comment.date));
        if(comment.comments != null) {
            JSONArray comments = new JSONArray();
            for(int i = 0; i < comment.comments.size();  i++) {
                comments.add(getCommentsJSON(comment.comments.get(i)));
            }
            commentDetails.put("comments", comments);
        }
        return commentDetails;
    }
}
