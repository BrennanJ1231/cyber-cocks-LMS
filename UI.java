import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import javax.management.modelmbean.ModelMBean;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardUpLeftHandler;

//To do: implement object oriented programming
// Any time there is a choice use a method

public class UI {
    private static SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
    private CourseApplication courseApp = new CourseApplication();
    public Scanner keyboard = new Scanner(System.in);
    public static void main (String[] args) {
        UI ui = new UI();
        ui.run();
        //Keep the main clean
    }

    /**
     * Run is the main dialog of the UI. It calls other methods based on Users wishes
     */
    public void run() {
        try {
            System.out.println("Welcome to CyberCock's school of coding");
            System.out.println("=======================================================================");
            System.out.println("Please enter [1] to Register or enter [2] to Login");
            int choice = 0; 
            while (true) {
                choice = keyboard.nextInt(); 
                if (choice == 1 || choice == 2) {
                    break;
                }
            }
            keyboard.nextLine();
            //We need to make it check for errors when the user makes an account so it doesn't crash
            if (choice == 1) { // Register
                getRegister();
            }else if(choice == 2) {  
                getLogin();
            } else {
                System.out.println("Invalid input");
            }
            System.out.println("Welcome " +  courseApp.getUser().getFirstName() + "!");
            System.out.println("=======================================================================");
            if (courseApp.getUser().type.equals( "Admin")) {
                getAdminDialog(); // Admin
            } else if (courseApp.getUser().type.equals( "Author")) {
                getAuthorDialog(); // Author
            } else {
                getUserDialog(); // User
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //Methods below for the run method


    public void getRegister() throws ParseException {
        System.out.println("Welcome to the school of coding! It is time to register a new Account!");
        System.out.println("=======================================================================");
        // Copy this line for same length
        System.out.println("Please enter your First Name");
        String firstName = keyboard.nextLine();
        System.out.println("Please enter your Last Name");
        String lastName = keyboard.nextLine();
        System.out.println("Please enter your Email");
        String email = keyboard.nextLine();
        System.out.println("Please enter your Date of Birth in this notation (xx/xx/xxxx)");
        String date;
        while (true) {
            date = keyboard.nextLine();
            if(isValidDate(date) == true) {
                break;
            } else {
                System.out.println("The date provided is in the incorrect format please try again:");
            }
        }
        Date birthday = formatter.parse(date);
        System.out.println("Please enter your Desired Username");
        String username = keyboard.nextLine();
        System.out.println("Please enter your Password");
        String password = keyboard.nextLine();
        while (true) {
            System.out.println("Type [1] to create an author account, [2] to create a user account, and [3] to create an admin account");
            int choice =  keyboard.nextInt();
            if (choice == 1) {
                courseApp.createAuthorAccount("Author", firstName, lastName, email, birthday, username, password);
                break;
            } else if (choice == 2) {
                courseApp.createUserAccount("Registered User", firstName, lastName, email, birthday, username, password);
                break;
            } else if (choice == 3) {
                courseApp.createAdminAccount("Admin", firstName, lastName, email, birthday, username, password);
                break;
            }else {
                System.out.println("Invalid input");
            }
        }
        System.out.println("Logging you in.");
        courseApp.saveAll(); //Update the UserList after creating an account
        courseApp.login(username, password);
    }

    public void getLogin() {
        System.out.println("It is time to log in");
        System.out.println("Please enter your username");
        String username = keyboard.nextLine();
        System.out.println("Please enter your password");
        String password = keyboard.nextLine();
        courseApp.login(username, password); 
    }

    public void getAdminDialog() {
        System.out.println("You are an admin");
        while (true) {
            System.out.println("Please enter [1] to assign courses, enter [2] to findCourses, and enter [3] to take a course, enter [4] to log out.");
            int choice = keyboard.nextInt();
            if ( choice == 1 ) {
                System.out.println("Please enter the course details of the one you would like to assign");
            } else if ( choice == 2 ) {
                System.out.println("Please enter the course details so we can find your course");
            } else if ( choice == 3 ) {
                System.out.println("Please enter the course details of the one you would like to take");
                String courseChoice = keyboard.nextLine();
                courseApp.findCourse(courseChoice);
            } else if (choice == 4) {
                System.out.println("Logging you out");
                courseApp.logout();
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
    /*
     * Dialog for the author user type
     */
    public void getAuthorDialog() {
        System.out.println("You are an author");
        while (true) {
            System.out.println("Please enter [1] to create course, enter [2] to see your courses, enter [3] to edit one of your courses, enter [4] to logout");
            int choice = keyboard.nextInt();
            keyboard.nextLine();
            if (choice == 1 )  {
                // Create Courses
                makeCourse();
            } else if ( choice  == 2 ) {
                // List Courses
                ArrayList<Course> courses = currentCourses();
                for (int i = 0; i < courses.size();i++) {
                    System.out.println(courses.get(i).name);
                }
            } else if ( choice  == 3 ) { //The code for if the user would like to edit a course
                getEditCourseScreen();
            } else if ( choice == 4 ) {
                System.out.println("Logging you out");
                courseApp.logout();
                break;
            } else {
                System.out.println("Invalid Choice");
            }      
        }// End of while loop
    } // End of Author Dialog

    /*
     * Dialog for the Registered User usertype
     */
    public void getUserDialog() {
        System.out.println("You are a registered user");
        while (true) {  
            System.out.println("Please enter [1] to view your courses, enter [2] to search for one a courses, enter [3] to take a course, enter [4] to logout");
            int choice =  keyboard.nextInt();
            keyboard.nextLine();
            if(choice == 1) {
                showCourses();
            } else if ( choice == 2 ) { 
                System.out.println("You have selected to find courses");
                System.out.println("Please enter the name of the course to search for.");
                System.out.println();
                String name = keyboard.nextLine();
                courseApp.findCourse(name);
            } else if (choice == 3) { 
                System.out.println("You have decided to take a course. Please enter the name of the course you want to take");
                String courseChoice = keyboard.nextLine();
                courseApp.takeCourse(courseChoice);
            } else if (choice == 4) { 
                System.out.println("You have selected to logout. Good Bye");
                courseApp.logout();
                break;
            } else {
                System.out.println("Please enter a valid number");
            }
        }
    }
    public void showCourses() {

        System.out.println("Showing your courses");
                ArrayList<Course> myCourses = courseApp.getMyCourses();
                ArrayList<Progress> progress = (ArrayList<Progress>)courseApp.regUser.courseProgress;
                for(int i = 0; i < myCourses.size(); i++) {
                    System.out.println(i+1 + ": " + myCourses.get(i).name + "\t" + progress.get(i).courseProgress);
                }
                System.out.println("Type which course you would like to view, or enter 0 to go back");
                int choice = keyboard.nextInt();
                if(choice == 0) 
                    getUserDialog();
                showModules(myCourses.get(choice-1));
    }
    public void showModules(Course course) {
                System.out.println("Showing modules for " + course);
                courseApp.Currentcourse = course;
                System.out.println(courseApp.Currentcourse.name + "\n" + courseApp.Currentcourse.description);
                for(int i = 0; i < courseApp.Currentcourse.modules.size(); i++) {
                    System.out.println(i+1 + ": " + courseApp.Currentcourse.modules.get(i).title);
                }
                System.out.println("Enter the number of which module to take, or enter 0 to go back");
                int choice = keyboard.nextInt();
                if(choice == 0) {
                    showCourses();
                } 
                showMaterials(courseApp.Currentcourse.modules.get(choice - 1), course);
    }
    public void showMaterials(Module module, Course course) {
        System.out.println("Showing material for " + module.title);
        for(int i = 0; i < module.material.size(); i++) {
            System.out.println(i + 1 + ": " + module.material.get(i).name);
        }
        System.out.println("Enter the material to view, enter, " + (int)(module.material.size() + 2) +  " to view quizzes, or " + (int)(module.material.size()+3) + " to view comments or 0 to go back");
        int choice = keyboard.nextInt();
        keyboard.nextLine();
        if(choice == 0) {
            showModules(course);
        }
        if(choice == module.material.size() + 2) {
            viewQuizzes(module, course);
        }
        if(choice == module.material.size() + 3) {
            showComment(module);
        }
        System.out.println("Viewing " + module.material.get(choice-1).name);
        System.out.println(module.material.get(choice-1).content);
        System.out.println("Enter 0 to go back");
        choice = keyboard.nextInt();
        keyboard.nextLine();
        if(choice == 0) {
            showMaterials(module, course);
        }
    }
    public void viewQuizzes(Module module, Course course) {
        System.out.println("Showing quizzes");
        for(int i = 0; i < module.test.size(); i ++) {
            System.out.println(i+1 + ": " + module.test.get(i).name + " grade: " + module.test.get(i).grade);
        }
        System.out.println("Enter the quiz you would like to take or 0 to go back");
        int choice = keyboard.nextInt();
        keyboard.nextLine();
        if(choice == 0) {
            showMaterials(module, course);
        }
        takeQuiz(module.test.get(choice-1), module);
    }
    public void takeQuiz(Assignment test, Module module) {
        System.out.println("Taking quiz " + test.name);
        for(int i = 0; i < test.questions.size(); i ++ ) {
            System.out.println("Question: " + i+1 + " " + test.questions.get(i).question);
            for(int j = 0; j < test.questions.get(i).choices.size(); j++) {
                System.out.println(test.questions.get(i).choices.get(j));
            }
            System.out.println("Enter answer choice: ");
            String answer = keyboard.nextLine();
            if(answer.equals(test.questions.get(i).correctAnswer)) {
                test.questions.get(i).rightWrong = true;
            }
        }
        System.out.println("Grade: "+ test.calculateGrade());
        System.out.println("Enter 0 to go back");
        int choice = keyboard.nextInt();
        keyboard.nextLine();
        if(choice == 0) {
            viewQuizzes(module, courseApp.Currentcourse);
        }
    }
    public void showComment(Module module) {
        System.out.println("Viewing comments" + module.comments.get(0).content);
        if(module.comments == null) {
            System.out.println("No comments");
            showMaterials(module, courseApp.Currentcourse);
        }
        for(int i = 0; i < module.comments.size(); i++) {
            System.out.println(i+1 + ": " + "Author: " + courseApp.findUser(module.comments.get(i).author).username);
            System.out.println("Content: " + module.comments.get(i).content);
            System.out.println("Date: " + module.comments.get(i).date.toString());
            if(module.comments.get(i).comments != null) {
            for(int j = 0; j < module.comments.get(i).comments.size(); j ++) {
                System.out.println("\t" + j+1 + ": " + "Author: " + courseApp.findUser(module.comments.get(i).comments.get(j).author).username);
                System.out.println("\tContent: " + module.comments.get(i).comments.get(j).content);
                System.out.println("\tDate: " + module.comments.get(i).comments.get(j).date.toString());
            }
        }
        }
        System.out.println("Enter which comment you would like to reply to, or enter " + (int)(module.comments.size()+1) + " to create a new comment, or 0 to go back");
        int choice = keyboard.nextInt();
        keyboard.nextLine();
        if(choice == 0) {
            showMaterials(module, courseApp.Currentcourse);
        }
        if(choice == module.comments.size()+1) {
            newComment( module);
        }
        replyComment(module.comments.get(choice-1), module);
    }
    public void newComment(Module module) {
        System.out.println("Enter your comment: ");
        String content = keyboard.nextLine();
        module.comments.add(new Comment(courseApp.getUser().uuid, content, new Date()));
        System.out.println("Enter 0 to go back");
        int choice = keyboard.nextInt();
        if(choice == 0){
            courseApp.saveAll();
            showComment(module);
        }
    }
    public void replyComment(Comment comment, Module module) {
        System.out.println("Enter your comment: ");
        String content = keyboard.nextLine();
        comment.comments.add(new Comment(courseApp.getUser().uuid, content, new Date()));
        System.out.println("Enter 0 to go back");
        int choice = keyboard.nextInt();
        if(choice == 0){
            courseApp.saveAll();
            showComment(module);
        }
    }

    /**
     * returns an a
     * @return
     */
    public ArrayList<Course> currentCourses() {
        Author currentAuthor = (Author) courseApp.getUser();
                if (currentAuthor.listOfCourses.isEmpty()) {
                    return null;
                } else {
                    ArrayList<Course> courses = courseApp.getMyCourses();
                    return courses;
                }
    }

    public Course tweakCourse() {
        Author currentAuthor = (Author) courseApp.getUser();
        if (currentAuthor.listOfCourses.isEmpty()) {
            System.out.println("You currently have no classes");
        } else {
            ArrayList<Course> courses = courseApp.getMyCourses();
        for(int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).name);
        }
        System.out.println("Please enter the name of the course you would like to edit");
        String editCourse = keyboard.nextLine();
        System.out.println("Enter [1] to edit a module or [2] to edit instructive");
        Course tempCourse = courseApp.findCourse(editCourse);
        int choice = keyboard.nextInt();
        if(choice == 1) {
            System.out.println("Editing a module");
            editModule(tempCourse);
            // edit module method
        } else if( choice == 2) {
            System.out.println("Editing instructive material");
            editInstructive();
            // edit instructive material method
        } else {
            System.out.println("Invalid input");
        }
        }
    }

    /**
     * Dialog that pops up in the author dialog
     * @return
     */
    public Course makeCourse() {
        System.out.println("Time to create a Course");
        System.out.println("==========================================================================");
        System.out.println("Please enter the name of the Course");
        String name = keyboard.nextLine();
        System.out.println("Please enter a description of the Course");
        String description = keyboard.nextLine();
        System.out.println("Please enter the Language you are coding the course in");
        Language lang = Language.valueOf(keyboard.nextLine().toUpperCase());
        UUID uuid = UUID. randomUUID();
        Course course = new Course(name, description,lang, uuid, makeModule(), null);
        courseApp.addCourse(course);
        courseApp.saveAll();
        return course;
        
    }
    public Module editModule(Module mod) {
        // You want to take a Modue change the information, and save the module;
        System.out.println("==========================================================================");
        mod.setName;
        courseApp.saveAll();
        return mod;
    }


    public InstructiveMaterial editInstructive(Module mod) {
        //Enter the module name and edit the different
        ArrayList<InstructiveMaterial> material = new ArrayList<InstructiveMaterial>();
        System.out.println("Please enter the materials name");
        String materialName = keyboard.nextLine();
        System.out.println("Please enter the materials contents");
        String materialContent = keyboard.nextLine();
        System.out.println("Would you like to add more material? (Enter 'y' for yes, or 'n' for no)");
        material.add(new InstructiveMaterial(materialName, materialContent));
        return 
    }
    /**
     * Make Module Dialog
     * @param course the course you want to create a course in
     */
    public ArrayList<Module> makeModule() {
        ArrayList<Module> modules = new ArrayList<Module>();
        System.out.println("Time to create a Module");
        System.out.println("==========================================================================");
        System.out.println("Please enter the name of the Module");
        String name = keyboard.nextLine();
        System.out.println("Please enter a description");
        String description = keyboard.nextLine();
        System.out.println("It is time to enter the Instructive Material");
        System.out.println("==========================================================================");
        ArrayList<InstructiveMaterial> material = makeMaterial();
        modules.add(new Module(name, description, material, makeAssignment()));
        return modules;
    }

    public ArrayList<InstructiveMaterial> makeMaterial() {
        ArrayList<InstructiveMaterial> material = new ArrayList<InstructiveMaterial>();
        while (true) {
            System.out.println("Please enter the materials name");
            String materialName = keyboard.nextLine();
            System.out.println("Please enter the materials contents");
            String materialContent = keyboard.nextLine();
            System.out.println("Would you like to add more material? (Enter 'y' for yes, or 'n' for no)");
            material.add(new InstructiveMaterial(materialName, materialContent));
            String YoN = keyboard.nextLine();
            if(YoN.equalsIgnoreCase(YoN)) {
                break;
            }
        }
        return material;
    }

    public ArrayList<Assignment> makeAssignment() {
        System.out.println("It is time to make an assignment."); 
        System.out.println("==========================================================================");
        ArrayList<Assignment> assign =  new ArrayList<Assignment>();
        System.out.println("Please enter the name of the assignment");
        String name = keyboard.nextLine();
        assign.add(new Assignment(name, makeQuestion()));
        return assign;

    }

    public ArrayList<Question> makeQuestion() {
        ArrayList<Question> questions = new ArrayList<Question>();
        System.out.println("Please enter the name of the question");
        String name = keyboard.nextLine();
        System.out.println("Please enter the number of answers you want to provide");
        int numAnswers = keyboard.nextInt();
        keyboard.nextLine();
        ArrayList<String> answers = new ArrayList<String>();
        for (int i=1; i<numAnswers; i++) {
            System.out.println("Please enter answer "+ (i));
            String answerChoice = keyboard.nextLine();
            answers.add(answerChoice);
        }
        System.out.println("Please enter the correct answer choice");
        String answer = keyboard.nextLine();
        questions.add(new Question(name,answers,answer));
        return questions;
    
    }

    public static boolean isValidDate(String dateStr) {
        formatter.setLenient(false);
        try {
            Date date = formatter.parse(dateStr);
            // check if the parsed date is the same as the original date string
            if (!dateStr.equals(formatter.format(date))) {
                return false;
            }
            // check if the parsed date is a possible date
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false);
            cal.setTime(date);
            cal.get(Calendar.YEAR);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public void getCourseScreen() {

    }

    public void getModuleScreen() {

    }

    public void getMaterialScreen() {

    }

    public void getAssignmentScreen() {
        
    }
    
}
