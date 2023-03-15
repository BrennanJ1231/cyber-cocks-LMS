import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {
	public static final String FILE_NAME = "users.json";
	public static void saveRegisteredUser(String fileName) {
        UserList userList = UserList.getInstance();
		ArrayList<User> users = userList.getAll();
		JSONArray jsonUsers = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< users.size(); i++) {
			jsonUsers.add(getUserJSON(users.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(FILE_NAME)) {
 
            file.write(jsonUsers.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static JSONObject getUserJSON(User user) {
		JSONObject userDetails = new JSONObject();
        if(user instanceof RegisteredUser) {
            userDetails.put("UUID",user.userID);
            userDetails.put("type","Registered User");
            userDetails.put("userName",user.username);
            userDetails.put("firstName",user.firstName);
            userDetails.put("lastName",user.lastName);
            userDetails.put("password",user.password);
            userDetails.put("email",user.email);
            userDetails.put("birthday",user.birthday);
        }
        if(user instanceof Admin) {
            userDetails.put("UUID",user.userID);
            userDetails.put("type","Admin");
            userDetails.put("userName",user.username);
            userDetails.put("firstName",user.firstName);
            userDetails.put("lastName",user.lastName);
            userDetails.put("password",user.password);
            userDetails.put("email",user.email);
            userDetails.put("birthday",user.birthday);
        }
        if(user instanceof Author) {
            userDetails.put("UUID",user.userID);
            userDetails.put("type","Author");
            userDetails.put("userName",user.username);
            userDetails.put("firstName",user.firstName);
            userDetails.put("lastName",user.lastName);
            userDetails.put("password",user.password);
            userDetails.put("email",user.email);
            userDetails.put("birthday",user.birthday);
        }
        return userDetails;
	}
}
