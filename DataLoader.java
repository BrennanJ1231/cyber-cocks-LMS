import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
public class DataLoader {
    private  ArrayList<Course> courseList;
    private  ArrayList<User> userList;
    public ArrayList<User> LoadUsers() {
        userList = new ArrayList<User>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./json/users.json"));
            JSONArray users = (JSONArray)obj;
            Iterator iterator = users.iterator();
            int i = 0;
            while(iterator.hasNext()) {
                JSONObject user = (JSONObject)users.get(i);
                UUID uuid = UUID.fromString((String)user.get("UUID"));
                String type = (String)user.get("type");
                String username = (String)user.get("username");
                String firstName = (String)user.get("firstName");
                String lastname = (String) user.get("lastName");
                String password = (String)user.get("password");
                String email = (String) user.get("email");
                String birthday = (String)user.get("birthday");
                if(type.equalsIgnoreCase("author")) {
                    int coursesCreated = Integer.parseInt(user.get("coursesCreated").toString());
                    ArrayList<Course> createdCourses = new ArrayList<Course>();
                    JSONArray courseArray = (JSONArray)user.get("createdCourses");
                    Iterator iterator2 = courseArray.iterator();
                    int j = 0;
                    while(iterator2.hasNext()) {
                        UUID courseUUID = UUID.fromString(courseArray.get(j).toString());
                        for(int k = 0; k< courseList.size();k++) {
                            if(courseList.get(k).uuid == courseUUID) {
                                createdCourses.add(courseList.get(k));
                            }
                        }
                        j++;
                        iterator2.next();
                    }

                }
                if(type.equalsIgnoreCase("registered user")) {
                    ArrayList<Course> currentCourses = new ArrayList<Course>();
                    JSONArray courseArray = (JSONArray)user.get("currentCourse");
                    Iterator iterator2 = courseArray.iterator();
                    int j = 0;
                    while(iterator2.hasNext()) {
                        UUID courseUUID = UUID.fromString(courseArray.get(j).toString());
                        for(int k = 0; k< courseList.size();k++) {
                            if(courseList.get(k).uuid == courseUUID) {
                                currentCourses.add(courseList.get(k));
                            }
                        }
                        j++;
                        iterator2.next();
                    }
                }
            }
        }
    }
    public ArrayList<Course> loadCourses() {
        courseList = new ArrayList<Course>();
        ArrayList<Module> moduleList = new ArrayList<Module>();
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./json/courses.json"));
            JSONArray courses = (JSONArray)obj;
            JSONObject course = (JSONObject)courses.get(0);
            UUID uuid = UUID.fromString(course.get("UUID").toString());
            String name = (String)course.get("name");
            Language lang = Language.valueOf((String)course.get("language"));
            String description = (String)course.get("description");
            Course newCourse = new Course(name, null, description, lang)
            JSONArray modules = (JSONArray)course.get("modules");
            Iterator iterator = modules.iterator();
            int k = 0;
            while(iterator.hasNext()) {
                JSONObject module = (JSONObject)modules.get(k);
                moduleList.add(getModules(module));
                k++;
                iterator.next();
            }
            k = 0;
            JSONArray comments = (JSONArray)course.get("comments");
            iterator = comments.iterator();
            while(iterator.hasNext()) {
                JSONObject comment = (JSONObject) comments.get(k);
                commentList.add(getComments(comment));
                k++;
                iterator.next();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Module getModules(JSONObject module) {
        ArrayList<InstructiveMaterial> materialList = new ArrayList<InstructiveMaterial>();
        ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
        String title = (String)module.get("title");
        String description = (String)module.get("description");
        JSONArray materials = (JSONArray)module.get("material");
        Iterator iterator = materials.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            JSONObject material = (JSONObject) materials.get(i);
            materialList.add(getMaterial(material));
            i++;
            iterator.next();
        }
        Module newModule = new Module(title, description, materialList);
        JSONArray assignments = (JSONArray)module.get("assignment");
        iterator = assignments.iterator();
        i = 0;
        while(iterator.hasNext()) {
            JSONObject assignment = (JSONObject) assignments.get(0);
            assignmentList.add(getAssignments(assignment));
            i++;
            iterator.next();
        }
        newModule.addAssignments(assignmentList);
        return newModule;
      
    }
    public InstructiveMaterial getMaterial(JSONObject material) {
        String name = (String)material.get("name");
        String content = (String)material.get("content");
        return new InstructiveMaterial(name, content);
    }

    public Assignment getAssignments(JSONObject assignment) {
        ArrayList<Question> questionList = new ArrayList<Question>();
        String name = (String)assignment.get("name");
        Type type = Type.valueOf((String)assignment.get("type"));
        Assignment newAssignment = new Assignment(name, type);
        JSONArray questions = (JSONArray) assignment.get("questions");
        Iterator iterator = questions.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            JSONObject question = (JSONObject)questions.get(i);
            questionList.add(getQuestions(question));
            i++;
            iterator.next();
        }
        newAssignment.addQuestions(questionList);
        return newAssignment;
    }

    public static Question getQuestions(JSONObject question) {
        String name = (String) question.get("name");
        ArrayList<String> choices = (ArrayList<String>)question.get("choices");
        String correctAnswer = (String)question.get("correctAnswer");
        return new Question(name, choices, correctAnswer);
    }
    public static Comment getComments(JSONObject comment) {
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        UUID author = UUID.fromString((String) comment.get("author"));
        String content = (String)comment.get("content");
        Date date = new Date((Long)comment.get("date"));
        Comment newComment = new Comment(author, content, date);
        JSONArray comments = (JSONArray)comment.get("comments");
        Iterator iterator = comments.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            JSONObject comment2 = (JSONObject) comments.get(i);
            commentList.add(getComments(comment2));
            i++;
            iterator.next();
        }
        newComment.addComments(commentList);
        return newComment;
    }
   