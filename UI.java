import java.io.File;
import java.io.FileWriter;
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
            }else if(choice == 2) {  // login
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
        }catch(Exception e) { // catch exception for try
            e.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //Methods below for the run method


    /**
     * Gets the dialog for the registering user.
     * ends up registering user
     */
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

    /**
     * returns the log in for the app
     */
    public void getLogin() {
        System.out.println("It is time to log in");
        System.out.println("Please enter your username");
        String username = keyboard.nextLine();
        System.out.println("Please enter your password");
        String password = keyboard.nextLine();
        courseApp.login(username, password); 
    }

    /**
     * shows the dialog that the admin recieves
     */
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
                if (courses != null) {
                    System.out.println();
                    for (int i = 0; i < courses.size();i++) {
                        System.out.println(courses.get(i).name);
                    }
                    System.out.println();
                } else {
                    System.out.println("You have no courses!");
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

    /**
     * Shows what courses the User is taking.
     */
    public void showCourses() {
        System.out.println("Showing your courses");
                ArrayList<Course> myCourses = courseApp.getMyCourses();
                ArrayList<Progress> progress = courseApp.regUser.courseProgress;
                for(int i = 0; i < myCourses.size(); i++) {
                    System.out.println(i+1 + ": " + myCourses.get(i).name + "\t" + progress.get(i).courseProgress);
                }
                System.out.println("Type which course you would like to view, or enter 0 to go back");
                int choice = keyboard.nextInt();
                keyboard.nextLine();
                if(choice == 0) 
                    getUserDialog();
                showModules(myCourses.get(choice-1));
    }
    /**
     * Shows modules for which course is inputted
     * @param course course that contains the modules
     */
    public void showModules(Course course) {
        System.out.println("Showing modules for " + course.name);
        courseApp.Currentcourse = course;
        System.out.println(courseApp.Currentcourse.name + "\n" + courseApp.Currentcourse.description);
        for(int i = 0; i < courseApp.Currentcourse.modules.size(); i++) {
            System.out.println(i+1 + ": " + courseApp.Currentcourse.modules.get(i).title);
        }
        int index = courseApp.getMyCourses().indexOf(course);
        if(courseApp.regUser.courseProgress.get(index).courseProgress == 100) {
            System.out.println("Your course is complete would you like to print certificate?");
            String answer = keyboard.nextLine();
            if(answer.equalsIgnoreCase("yes")) {
                printCertificate();
            }
        }
        System.out.println("Enter the number of which module to take, or enter 0 to go back");
        int choice = keyboard.nextInt();
        if(choice == 0) {
            showCourses();
        } 
        showMaterials(courseApp.Currentcourse.modules.get(choice - 1), course);
    }

    /**
     * printCertificate creates a new txt file and that displays the certification
     */
    public void printCertificate() {
        try {
            File newFile = new File("./certificate.txt");
            FileWriter writer = new FileWriter(newFile);
            writer.write("This is to certify that " + courseApp.getUser().firstName + " " +
             courseApp.getUser().lastName + " has completed the " + courseApp.Currentcourse.name + " course");
            writer.close();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * takes a module and course and shows all the material associated with the code
     */
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
        System.out.println("Enter 1 to print to text file");
        int print = keyboard.nextInt();
        keyboard.nextLine();
        if(print == 1) {
            printToTxt(module.material.get(choice-1));
        }
        System.out.println("Enter 0 to go back");
        choice = keyboard.nextInt();
        keyboard.nextLine();
        if(choice == 0) {
            showMaterials(module, course);
        }
    }
    /**
     * viewQuizes shows a list of possible quizzes and asks you to chose one
     * @param module module you want to test in
     * @param course what course the quiz is in
     */
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
    /**
     * creates a new txt file amd adds the name of the material into it
     * @param material array list Instructive material prints to the new file
     */
    public void printToTxt(InstructiveMaterial material) {
        try {
            File newFile = new File("./" + material.name + ".txt");
            FileWriter writer = new FileWriter(newFile);
            writer.write("Title: " + material.name + "\n" + material.content);
            writer.close();
            
        } catch(Exception e) { // catch exception
            e.printStackTrace();
        }
    }
    /**
     *  takeQuiz allows you to actually take a quiz
     * @param test takes assignment test as the input for which test user takes
     * @param module instance of module
     */
    public void takeQuiz(Assignment test, Module module) {
        System.out.println("Taking quiz " + test.name);
        for(int i = 0; i < test.questions.size(); i ++ ) {
            System.out.println("Question: " + (i+1) + " " + test.questions.get(i).question);
            for(int j = 0; j < test.questions.get(i).choices.size(); j++) {
                System.out.println(test.questions.get(i).choices.get(j));
            }
            System.out.println("Enter answer choice: ");
            String answer = keyboard.nextLine();
            if(answer.equalsIgnoreCase(test.questions.get(i).correctAnswer)) {
                test.questions.get(i).rightWrong = true;
            } else {
                test.questions.get(i).rightWrong = false;
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

    /**
     * showComment displays the dialog and allows the user view all the comments
     * @param module shows the module the comments are in
     */
    public void showComment(Module module) {
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
    /**
     * newComment allows the user to create a new comment
     * @param module the module that the user would like to comment on
     */
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
    /**
     * replyComment deals with the comment on comment issue. Call this to reply to a comment
     * @param comment Comment you are replying to
     * @param module module you are commenting in
     */
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
     * returns an array list of surrent courses
     * @return list of courses
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

    /**
     *  getEditCourseScreen is called when you want to edit a course
     */
    public void getEditCourseScreen() {
        Author currentAuthor = (Author) courseApp.getUser();
        if (currentAuthor.listOfCourses.isEmpty()) {
            System.out.println("You currently have no courses");
        } else {
            ArrayList<Course> courses = courseApp.getMyCourses();
            for(int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).name);
            }
            System.out.println("Please enter the name of the course you would like to edit");
            String editCourse = keyboard.nextLine();
            while (true) {
                System.out.println("Enter [1] to edit a module or enter [2] to add a module, or [0] to go back:");
                int choice = keyboard.nextInt();
                keyboard.nextLine();
                if (choice == 1) {
                    editModule(editCourse);
                    break;
                } else if (choice == 2) {
                    courseApp.findCourse(editCourse);
                    courseApp.findCourse(editCourse).modules.addAll(makeModule());
                    courseApp.saveAll();
                    break;
                } else if(choice == 0) {
                    getAuthorDialog();
                }else {
                    System.out.println("Invalid choice please try again!");
                }
            }
            
        }
    }

    /**
     * edit Module takes a string and finds a course by the keyword. It then allows the user to edit the module inside
     */
    public void editModule(String editCourse) {
        for(int i = 0; i < courseApp.courselist.findCourse(editCourse).modules.size(); i++) {
            System.out.println(courseApp.courselist.findCourse(editCourse).modules.get(i).title);
        }
        System.out.println("Please enter the name of the module you would like to edit");
        String editModule = keyboard.nextLine();
        System.out.println("Enter [1] to edit a assignment, [2] to edit instructive material, [3] to add an assignment, or [4] to add instructive material:");
        int choice = keyboard.nextInt();
        keyboard.nextLine();
        if(choice == 1) {
            editAssignment(courseApp.findCourse(editCourse),courseApp.findCourse(editCourse).findModule(editModule));
            // edit module method
        } else if( choice == 2) {
            editInstructive(courseApp.findCourse(editCourse),courseApp.findCourse(editCourse).findModule(editModule));
            // edit instructive material method
        } else if( choice == 3) {
            courseApp.findCourse(editCourse).findModule(editModule).test.addAll( makeAssignment());
            // edit instructive material method
        } else if( choice == 4) {
            courseApp.findCourse(editCourse).findModule(editModule).material.addAll( makeMaterial());
            // edit instructive material method
        } else if(choice == 0){
            getEditCourseScreen();
        }else {
            System.out.println("Invalid input");
        }
        courseApp.saveAll();
    }

    /**
     * editAssignment takes an Assignment and changes it as needed
     * @param course The course you would like to edit
     * @param mod the module you would like to edit
     */
    public void editAssignment(Course course, Module mod) {
        //Enter the module name and edit the different
        for (int i = 0; i < mod.test.size(); i++) {
            System.out.println(mod.test.get(i).name);
        }
        System.out.println("Enter the name of the assignment you would like to edit");
        String testName = keyboard.nextLine();
        while (true) {
            System.out.println("Enter [1] to edit the assignment name, enter [2] to edit questions, enter [3] to add questions, or enter [0] to go back");
            int choice = keyboard.nextInt();
            keyboard.nextLine();
            if (choice == 1) {
                System.out.println("Please enter the assignments new name");
                String newName = keyboard.nextLine();
                course.findModule(mod.title).findAssignment(testName).setName(newName);
            } else if (choice == 2) {
                editQuestion(course.findModule(mod.title).findAssignment(testName));
            } else if( choice == 3) {
                courseApp.Currentcourse.findModule(mod.title).findAssignment(testName).questions.addAll(makeQuestion());
            } else if(choice == 0) {
                editModule(course.name);
            }else {
                System.out.println("Invalid option please try again!");
            }
            courseApp.saveAll();
        }
    }

    /**
     * edit question returns the question you want to edit and allows the user to change and save it
     * @param test takes the assignment that the question belongs to
     */
    public void editQuestion(Assignment test) {
        //Enter the module name and edit the different
        for (int i = 0; i < test.questions.size(); i++) {
            System.out.println(test.questions.get(i).question);
        }
        System.out.println("Enter the name of the question you would like to edit");
        String questionName = keyboard.nextLine();
        Question question = null;
        for (int i = 0; i < test.questions.size(); i++) {
            if(test.questions.get(i).question.equalsIgnoreCase(questionName)) {
                question = test.questions.get(i);
            }
        }
        while (true) {
            System.out.println("Enter [1] to edit the question or enter [2] to edit question's answers");
            int choice = keyboard.nextInt();
            keyboard.nextLine();
            if (choice == 1) {
                System.out.println("Please enter the questions new question");
                String newName = keyboard.nextLine();
                question.setQuestion(newName);
                break;
            } else if (choice == 2) {
                for (int i = 0; i < question.choices.size(); i++) {
                    System.out.println(question.choices.get(i));
                }
                System.out.println("Please enter the answer choice you would like to edit");
                String oldChoice = keyboard.nextLine();
                System.out.println("Please enter the new answer choice you would like to provide");
                String newChoice = keyboard.nextLine();
                break;
            } else {
                System.out.println("Invalid option please try again!");
            }
            courseApp.saveAll();
        }
    }
    /**
     * Dialog that pops up in the author dialog
     * @return the course the author created
     */
    public Course makeCourse() {
        System.out.println("Time to create a Course");
        System.out.println("==========================================================================");
        System.out.println("Please enter the name of the Course");
        String name = keyboard.nextLine();
        System.out.println("Please enter a description of the Course");
        String description = keyboard.nextLine();
        while (true) {
            System.out.println("Language choices:\nJAVA\nC_PLUS_PLUS\nPYTHON\nC_SHARP\nPHP\nSWIFT\nGO\nJAVASCRIPT");
            System.out.println("Please enter the Language you are coding the course in");
            String language = keyboard.nextLine();
            if (language.equalsIgnoreCase("java") || language.equalsIgnoreCase("c_plus_plus") || language.equalsIgnoreCase("python") 
            || language.equalsIgnoreCase("c_sharp") || language.equalsIgnoreCase("php") || language.equalsIgnoreCase("swift") 
            || language.equalsIgnoreCase("go") || language.equalsIgnoreCase("javascript")) {
                break;
            } else {
                System.out.println("Invalid response try again!");
            }
        }
        Language lang = Language.valueOf(keyboard.nextLine().toUpperCase());
        UUID uuid = UUID. randomUUID();
        Course course = new Course(name, description,lang, uuid, makeModule(), null);
        courseApp.addCourse(course);
        courseApp.saveAll();
        return course;
        
    }



    /**
     * editInstructive allows the user to change the material of an existing module 
     * @param course existing course
     * @param mod module to check size
     */
    public void editInstructive(Course course, Module mod) {
        //Enter the module name and edit the different
        while(true) {
        for (int i = 0; i < mod.material.size(); i++) {
            System.out.println(mod.material.get(i).name);
        }
        
        System.out.println("Enter the name of the material you would like to edit");
        String materialName = keyboard.nextLine();
        
            System.out.println("Enter [1] to edit material name, [2] to edit material contents, or enter [0] to go back");
            int choice = keyboard.nextInt();
            keyboard.nextLine();
            if (choice == 1) {
                System.out.println("Please enter the materials new name");
                String newName = keyboard.nextLine();
                course.findModule(mod.title).findMaterial(materialName).setName(newName);
            } else if (choice == 2) {
                System.out.println("Please enter the materials new contents");
                String newContent = keyboard.nextLine();
                course.findModule(mod.title).findMaterial(materialName).setContent(newContent);
            } else if(choice == 0) {
                editModule(course.name);
            }else {
                System.out.println("Invalid option please try again!");
            }
            courseApp.saveAll();
        }
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

    /**
     * makeMaterial gets user input and assigns the users input to the instructive materials list
     */
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

    /**
     * makeAssignment creates assignments that will contain questions
     * @return a list of assignments
     */
    public ArrayList<Assignment> makeAssignment() {
        System.out.println("It is time to make an assignment."); 
        System.out.println("==========================================================================");
        ArrayList<Assignment> assign =  new ArrayList<Assignment>();
        System.out.println("Please enter the name of the assignment");
        String name = keyboard.nextLine();
        assign.add(new Assignment(name, makeQuestion()));
        return assign;

    }

    /**
     * makeQuestion creates a question that can be called upon in an assignment
     */
    public ArrayList<Question> makeQuestion() {
        ArrayList<Question> questions = new ArrayList<Question>();
        System.out.println("Please enter the question");
        String name = keyboard.nextLine();
        System.out.println("Please enter the number of answers you want to provide");
        int numAnswers = keyboard.nextInt();
        keyboard.nextLine();
        ArrayList<String> answers = new ArrayList<String>();
        for (int i=1; i<numAnswers+1; i++) {
            System.out.println("Please enter answer "+ (i));
            String answerChoice = keyboard.nextLine();
            answers.add(answerChoice);
        }
        System.out.println("Please enter the correct answer choice");
        String answer = keyboard.nextLine();
        questions.add(new Question(name,answers,answer));
        return questions;
    
    }

    /**
     * isValidDate uses formatter to check if the date the user input is valid
     */
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
    
}
