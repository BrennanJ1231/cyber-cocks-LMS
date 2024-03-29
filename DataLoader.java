import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
The DataLoader class loads user and course data from JSON files.
*/
public class DataLoader {
    private static ArrayList<Course> courseList;
    private static ArrayList<User> userList;
    private static SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");

    /**
    The loadUsers method loads user data from a JSON file and returns a list of User objects.
    @return An ArrayList of User objects.
    */
    public static ArrayList<User> loadUsers() {
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
                Date birthday = formatter.parse(user.get("birthday").toString());
                if(type.equalsIgnoreCase("author")) {
                    int coursesCreated = Integer.parseInt(user.get("createdCourses").toString());
                    ArrayList<Course> createdCourses = new ArrayList<Course>();
                    JSONArray courseArray = (JSONArray)user.get("coursesCreated");
                    if(courseArray != null) {
                        Iterator iterator2 = courseArray.iterator();
                        int j = 0;
                        while(iterator2.hasNext()) {
                            JSONObject cUUID = (JSONObject)courseArray.get(j);
                            UUID courseUUID = UUID.fromString(cUUID.get("UUID").toString());
                            for(int k = 0; k< courseList.size();k++) {
                                if(courseList.get(k).uuid.equals(courseUUID)) {
                                    createdCourses.add(courseList.get(k));
                                }
                            }
                            j++;
                            iterator2.next();
                        }
                    }
                    userList.add(new Author(uuid,"Author", username, firstName, lastname, password, email, birthday, coursesCreated, createdCourses));

                }
                if(type.equalsIgnoreCase("registered user")) {
                    ArrayList<Course> currentCourses = new ArrayList<Course>();
                    ArrayList<Progress> courseP = new ArrayList<Progress>();
                    JSONArray courseArray = (JSONArray)user.get("currentCourses");
                    if(courseArray != null){
                    Iterator iterator2 = courseArray.iterator();
                    int j = 0;
                    while(iterator2.hasNext()) {
                        JSONObject cUUID = (JSONObject)courseArray.get(j);
                        UUID courseUUID = UUID.fromString(cUUID.get("UUID").toString());
                        for(int k = 0; k < courseList.size();k++) {
                            if(courseList.get(k).uuid.equals(courseUUID)) {
                                currentCourses.add(courseList.get(k));
                            }
                        }
                        j++;
                        iterator2.next();
                    }
                    JSONArray courseProgress = (JSONArray)user.get("courseProgress");
                    if(courseProgress != null) {
                    Iterator iterator3 = courseProgress.iterator();
                    j = 0;
                    while(iterator3.hasNext()) {
                        JSONObject progress = (JSONObject)courseProgress.get(j);
                        Double prog = Double.parseDouble(progress.get("courseProgress").toString());
                        Progress pro = new Progress();
                        pro.courseProgress = prog;
                        courseP.add(pro);
                        iterator3.next();
                        j++;
                    }
                }
                }
                    RegisteredUser RegUser = new RegisteredUser(uuid, "Registered user", username, firstName, lastname, password, email, birthday, currentCourses);
                    RegUser.courseProgress = courseP;
                    userList.add(RegUser);
                }
                if(type.equalsIgnoreCase("admin")) {
                    userList.add(new Admin(uuid,"Admin", username, firstName, lastname, password, birthday, email));
                }
                i++;
                iterator.next();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
    The loadCourse method loads course data from a JSON file and returns a list of Course objects.
    @return An ArrayList of Course objects.
    */
    public static ArrayList<Course> loadCourses() {
        courseList = new ArrayList<Course>();
        try {
            FileReader reader = new FileReader("./json/courses.json");
            JSONParser parser = new JSONParser();
            JSONArray courses = (JSONArray)parser.parse(reader);
            Iterator iterator2 = courses.iterator();
            int i = 0;
            while(iterator2.hasNext()) {
                ArrayList<Module> moduleList = new ArrayList<Module>();
                ArrayList<Comment> commentList = new ArrayList<Comment>();
                JSONObject course = (JSONObject)courses.get(i);
                UUID uuid = UUID.fromString(course.get("UUID").toString());
                String name = (String)course.get("name");
                Language lang = Language.valueOf((String)course.get("language"));
                String description = (String)course.get("description");
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
                if(comments != null) {
                    iterator = comments.iterator();
                    while(iterator.hasNext()) {
                        JSONObject comment = (JSONObject) comments.get(k);
                        commentList.add(getComments(comment));
                        k++;
                        iterator.next();
                    }
                 }
                courseList.add(new Course(name, description, lang, uuid, moduleList, commentList));
                i++;
                iterator2.next();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public static Module getModules(JSONObject module) {
        ArrayList<InstructiveMaterial> materialList = new ArrayList<InstructiveMaterial>();
        ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
        ArrayList<Comment> commentList = new ArrayList<Comment>();
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
        JSONArray assignments = (JSONArray)module.get("assignment");
        iterator = assignments.iterator();
        i = 0;
        while(iterator.hasNext()) {
            JSONObject assignment = (JSONObject) assignments.get(i);
            assignmentList.add(getAssignments(assignment));
            i++;
            iterator.next();
        }
        i = 0;
        JSONArray comments = (JSONArray)module.get("comments");
        if(comments != null) {
            iterator = comments.iterator();
            while(iterator.hasNext()) {
                JSONObject comment = (JSONObject) comments.get(i);
                commentList.add(getComments(comment));
                i++;
                iterator.next();
            }
         }
       Module modules = new Module(title, description, materialList, assignmentList);
       modules.comments = commentList;
        return modules;
      
    }
    public static InstructiveMaterial getMaterial(JSONObject material) {
        String name = (String)material.get("name");
        String content = (String)material.get("content");
        return new InstructiveMaterial(name, content);
    }

    public static Assignment getAssignments(JSONObject assignment) {
        ArrayList<Question> questionList = new ArrayList<Question>();
        String name = (String)assignment.get("name");
        JSONArray questions = (JSONArray) assignment.get("questions");
        Iterator iterator = questions.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            JSONObject question = (JSONObject)questions.get(i);
            questionList.add(getQuestions(question));
            i++;
            iterator.next();
        }
        return new Assignment(name, questionList);
    }

    public static Question getQuestions(JSONObject question) {
        String name = (String) question.get("name");
        ArrayList<String> choices = (ArrayList<String>)question.get("choices");
        String correctAnswer = (String)question.get("correctAnswer");
        return new Question(name, choices, correctAnswer);
    }
    public static Comment getComments(JSONObject comment) {
        try {
            ArrayList<Comment> commentList = new ArrayList<Comment>();
            UUID author = UUID.fromString((String) comment.get("author"));
            String content = (String)comment.get("content");
            Date date = formatter.parse(comment.get("datePosted").toString());
            Comment newComment = new Comment(author, content, date);
            JSONArray comments = (JSONArray)comment.get("comments");
            if(comments != null) {
            Iterator iterator = comments.iterator();
            int i = 0;
            while(iterator.hasNext()) {
                JSONObject comment2 = (JSONObject) comments.get(i);
                commentList.add(getComments(comment2));
                i++;
                iterator.next();
            }
            newComment.addComments(commentList);
        }
            return newComment;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<RegisteredUser> getAdminStudentList(User admin) {
        ArrayList<RegisteredUser> studentList = new ArrayList<RegisteredUser>();
        UUID uuid = admin.uuid;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./json/users.json"));
            JSONArray users = (JSONArray)obj;
            Iterator iterator = users.iterator();
            int i = 0;
            while(iterator.hasNext()) {
                JSONObject user = (JSONObject)users.get(i);
                if(UUID.fromString(user.get("UUID").toString()).equals(uuid)) {
                    JSONArray students = (JSONArray)user.get("students");
                    Iterator iterator2 = students.iterator();
                    int j = 0;
                    while(iterator2.hasNext()) {
                        JSONObject sUUID = (JSONObject)students.get(j);
                        UUID studentUUID = UUID.fromString(sUUID.get("UUID").toString());
                        for(int k = 0; k< userList.size();k++) {
                            if(userList.get(k).uuid.equals(studentUUID)) {
                                studentList.add((RegisteredUser) (userList.get(k)));
                            }
                        }
                        j++;
                        iterator2.next();
                    }
                }
                i++;
                iterator.next();
            }
            return studentList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
   