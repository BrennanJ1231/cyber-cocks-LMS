import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
public class DataLoader {
    private static ArrayList<Course> courseList = new ArrayList<Course>();
    private static ArrayList<User> userList = new ArrayList<User>();
    public void LoadUsers() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./json/users.json"));
            JSONArray users = (JSONArray)obj;
            Iterator iterator = users.iterator();
            int i = 0;
            while(iterator.hasNext()) {
                JSONObject user = (JSONObject)users.get(i);
                String uuid = (String)user.get("UUID");
                String type = (String)user.get("type");
                String username = (String)user.get("username");
                String firstName = (String)user.get("firstName");
                String lastname = (String) user.get("lastName");
                String password = (String)user.get("password");
                String email = (String) user.get("email");
                String birthday = (String)user.get("birthday");
                
            }
        }
    }
    public void loadCourses() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./json/courses.json"));
            JSONArray courses = (JSONArray)obj;
            JSONObject course = (JSONObject)courses.get(0);
            String uuid = (String)course.get("UUID");
            String name = (String)course.get("name");
            Language lang = Language.valueOf((String)course.get("language"));
            String description = (String)course.get("description");
            JSONArray modules = (JSONArray)course.get("modules");
            Iterator iterator = modules.iterator();
            Course newCourse = new Course(name, null, description, lang);
            while(iterator.hasNext()) {
                JSONObject module = (JSONObject)modules.get(0);
                newCourse.addModule(getModules(module));
                iterator.next();
            }
            JSONArray comments = (JSONArray)course.get("comments");
            iterator = comments.iterator();
            while(iterator.hasNext()) {
                JSONObject comment = (JSONObject) comments.get(0);
                getComments(comment);
                iterator.next();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Module getModules(JSONObject module) {
        String title = (String)module.get("title");
        String description = (String)module.get("description");
        JSONArray materials = (JSONArray)module.get("material");
        Iterator iterator = materials.iterator();
        while(iterator.hasNext()) {
            JSONObject material = (JSONObject) materials.get(0);
            getMaterial(material);
            iterator.next();
        }
        JSONArray assignments = (JSONArray)module.get("assignment");
        iterator = assignments.iterator();
        while(iterator.hasNext()) {
            JSONObject assignment = (JSONObject) assignments.get(0);
            getAssignments(assignment);
            iterator.next();
        }
        return 
    }
    public Material getMaterial(JSONObject material) {
        String name = (String)material.get("name");
        String content = (String)material.get("content");
        return new Material
    }

    public Assignment getAssignments(JSONObject assignment) {
        String name = (String)assignment.get("name");
        String type = (String)assignment.get("type");
        JSONArray questions = (JSONArray) assignment.get("questions");
        Iterator iterator = questions.iterator();
        while(iterator.hasNext()) {
            JSONObject question = (JSONObject)questions.get(0);
            getQuestions(question);
            iterator.next();
        }
    }

    public static Question getQuestions(JSONObject question) {
        String name = (String) question.get("name");
        ArrayList<String> choices = (ArrayList<String>)question.get("choices");
        String correctAnswer = (String)question.get("correctAnswer");
    }
    public static Comment getComments(JSONObject comment) {
        String author = (String) comment.get("author");
        String content = (String)comment.get("content");
        Date date = (Date) comment.get("date");
        JSONArray comments = (JSONArray)comment.get("comments");
        Iterator iterator = comments.iterator();
        while(iterator.hasNext()) {
            JSONObject comment2 = (JSONObject) comments.get(0);
            getComments(comment2);
            iterator.next();
        }
    }
}