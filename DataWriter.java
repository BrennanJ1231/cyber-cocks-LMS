import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {
	
	
	public static void saveUser() {
        User user = user.getInstance();
		ArrayList<User> friends = user.getPeople();
		JSONArray jsonUsers = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< friends.size(); i++) {
			jsonUsers.add(getUserJSON(friends.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(PEOPLE_FILE_NAME)) {
 
            file.write(jsonFriends.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static JSONObject getUserJSON(User user, Course course) {
		JSONObject userDetails = new JSONObject();
		userDetails.put("UUID",user.userID);
        userDetails.put("type","Registered User");
        userDetails.put("userName",user.username);
        userDetails.put("firstName",user.firstName);
        userDetails.put("lastName",user.lastName);
        userDetails.put("password",user.password);
        userDetails.put("email",user.email);
        userDetails.put("birthday",user.birthday);
        userDetails.put("currentCourse",course.name);
        userDetails.put("courseProgress",course.completion);
        return userDetails;
	}
}
/*
public class DataWriter {
    
    private String studentsFilename;
    private String coursesFilename;
    private String enrollmentsFilename;

    public DataWriter(String studentsFilename, String coursesFilename ) {
        this.studentsFilename = studentsFilename;
        this.coursesFilename = coursesFilename;
    }

    public void writeDataRegisteredUser(User user, Course course) {
    
    public static Object searchJson(Map<String, Object> jsonMap, String key, Object value) {
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            String k = entry.getKey();
            Object v = entry.getValue();
            if (key.equals(k) && value.equals(v)) {
                return jsonMap;
            }
            if (v instanceof Map) {
                Object result = searchJson((Map<String, Object>) v, key, value);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public void writeDataRegisteredUser(User user, Course course) {
        try {
            FileWriter studentsWriter = new FileWriter(studentsFilename);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = mapper.readValue(studentsFilename, Map.class);
            // search for object with key "UUID" and value UUID
            Object result = searchJson(jsonMap, "UUID", user.userID);
            if (result == null) {
                ((Map<String, Object>) result).put("UUID",user.userID);
                ((Map<String, Object>) result).put("type","Registered User");
                ((Map<String, Object>) result).put("userName",user.username);
                ((Map<String, Object>) result).put("firstName",user.firstName);
                ((Map<String, Object>) result).put("lastName",user.lastName);
                ((Map<String, Object>) result).put("password",user.password);
                ((Map<String, Object>) result).put("email",user.email);
                ((Map<String, Object>) result).put("birthday",user.birthday);
                ((Map<String, Object>) result).put("currentCourse",course.name);
                ((Map<String, Object>) result).put("courseProgress",course.completion);
            }
            studentsWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeDataAuthor(User user, Course course) {
        try {
            FileWriter studentsWriter = new FileWriter(studentsFilename);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = mapper.readValue(studentsFilename, Map.class);
            // search for object with key "UUID" and value UUID
            Object result = searchJson(jsonMap, "UUID", user.userID);
            if (result == null) {
                ((Map<String, Object>) result).put("UUID",user.userID);
                ((Map<String, Object>) result).put("type","Author");
                ((Map<String, Object>) result).put("userName",user.username);
                ((Map<String, Object>) result).put("firstName",user.firstName);
                ((Map<String, Object>) result).put("lastName",user.lastName);
                ((Map<String, Object>) result).put("password",user.password);
                ((Map<String, Object>) result).put("email",user.email);
                ((Map<String, Object>) result).put("birthday",user.birthday);
                ((Map<String, Object>) result).put("currentCourse",course.name);
                ((Map<String, Object>) result).put("courseProgress",course.completion);
            }
            studentsWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeDataAdmin(User user, Course course) {
        try {
            FileWriter studentsWriter = new FileWriter(studentsFilename);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = mapper.readValue(studentsFilename, Map.class);
            // search for object with key "UUID" and value UUID
            Object result = searchJson(jsonMap, "UUID", user.userID);
            if (result == null) {
                ((Map<String, Object>) result).put("UUID",user.userID);
                ((Map<String, Object>) result).put("type","Admin");
                ((Map<String, Object>) result).put("userName",user.username);
                ((Map<String, Object>) result).put("firstName",user.firstName);
                ((Map<String, Object>) result).put("lastName",user.lastName);
                ((Map<String, Object>) result).put("password",user.password);
                ((Map<String, Object>) result).put("email",user.email);
                ((Map<String, Object>) result).put("birthday",user.birthday);
                ((Map<String, Object>) result).put("students",course.name);
            }
            studentsWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
*/