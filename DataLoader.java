import java.io.*;
import java.util.*;

import javax.security.auth.Subject;

import org.json.simple.*;
import org.json.simple.parser.*;
public class DataLoader {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./json/courses.json"));
            JSONArray courses = (JSONArray)obj;
            JSONObject course = (JSONObject)courses.get(0);
            String uuid = (String)course.get("UUID");
            String name = (String)course.get("name");
            String lang = (String)course.get("language");
            String description = (String)course.get("description");
            JSONArray modules = (JSONArray)course.get("modules");
            Iterator iterator = modules.iterator();
            while(iterator.hasNext()) {
                JSONObject module = (JSONObject)modules.get(0);
                getModules(module);
                iterator.next();
            }
            JSONArray comments = (JSONArray)course.get("comments");
            iterator = comments.iterator();
            while(iterator.hasNext()) {
                JSONObject comment = (JSONObject) comments.get(0);
                getComments(comment);
                iterator.next();
            }
            Double rating = Double.parseDouble(course.get("rating").toString());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void getModules(JSONObject module) {
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
    }
    public static void getMaterial(JSONObject material) {
        String name = (String)material.get("name");
        String content = (String)material.get("content");
    }

    public static void getAssignments(JSONObject assignment) {
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

    public static void getQuestions(JSONObject question) {
        String name = (String) question.get("name");
        ArrayList<String> choices = (ArrayList<String>)question.get("choices");
        String correctAnswer = (String)question.get("correctAnswer");
    }
    public static void getComments(JSONObject comment) {
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