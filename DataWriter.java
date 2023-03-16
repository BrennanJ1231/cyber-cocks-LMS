import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {
	public static final String FILE_NAME = "users.json";
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
        try (FileWriter file = new FileWriter(FILE_NAME)) {
 
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
}
