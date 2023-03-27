import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;


public class UI {
    private static SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
    private CourseApplication courseApp = new CourseApplication();
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
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            keyboard.nextLine();
            //We need to make it check for errors when the user makes an account so it doesn't crash
            if (choice == 1) { // Register
                //while (true) {
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
                    Date birthday = formatter.parse(keyboard.nextLine());
                    System.out.println("Please enter your Desired Username");
                    String username = keyboard.nextLine();
                    System.out.println("Please enter your Password");
                    String password = keyboard.nextLine();
                    System.out.println("Type [1] to create an author account, [2] to create a user account, and [3] to create an admin account");
                    System.out.println();
                    choice =  keyboard.nextInt();
                    if (choice == 1) {
                    courseApp.createAuthorAccount("Author", firstName, lastName, email, birthday, username, password);
                    } else if (choice ==2) {
                    courseApp.createUserAccount("Registered User", firstName, lastName, email, birthday, username, password);
                    } else if (choice == 3) {
                    courseApp.createAdminAccount("Admin", firstName, lastName, email, birthday, username, password);
                    }else {
                    System.out.println("Invalid input");
                //}
                System.out.println("Logging you in.");
                courseApp.login(username, password);
                }
                // Make it so that you have to log in after you create an account


                System.out.println("Welcome " +  courseApp.getUser().getFirstName() + "!");
                System.out.println("=======================================================================");
                if (courseApp.getUser().type.equals( "Admin")) {
                    getAdminDialog();
                    System.out.println("");
                } else if (courseApp.getUser().type.equals("Author")) {
                    getAuthorDialog();
                } else {
                    getUserDialog();
                }
                System.out.println("Incorrect value displayed");
                // Checks for exception
            } else if(choice == 2) {  
                System.out.println("You have decided to log in");
            }//Log in
            System.out.println("Welcome Back!");
            System.out.println("Please enter your username");
            String username = keyboard.nextLine();
            System.out.println("Please enter your password");
            String password = keyboard.nextLine();
            courseApp.login(username, password);
    }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getAdminDialog() {
        if (courseApp.getUser().getAge()!= true) {
            System.out.println("You are too young to be an admin sorry");
        } else {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("You are an admin");
            System.out.println("Please enter [1] to assign courses, enter [2] to findCourses, and enter [3] to take a course");
            int choice = keyboard.nextInt();
            if ( choice == 1 ) {
                System.out.println("Please enter the course details of the one you would like to assign");
            } else if( choice == 2 ) {
                System.out.println("Please enter the course details so we can find your course");
            } else if ( choice == 3 ) {
                System.out.println("Please enter the course details of the one you would like to take");
                String courseChoice = keyboard.nextLine();
                courseApp.findCourses(courseChoice);
            }
        }
    }
    /*
     * Dialog for the author user type
     */
    public void getAuthorDialog() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("You are an author");
        System.out.println("Please enter [1] to create course, enter [2] to see your courses, enter [3] to edit one of your courses");
        System.out.println();
        int choice = keyboard.nextInt();
        if (choice == 1 )  {
            // Create Courses
            Course newCourse = makeCourse();
            Module newModule = makeModule(newCourse);
            makeQuestion(newModule);
        } else if ( choice  == 2 ) {
            // List Courses
            ArrayList<Course> courses = courseApp.getMyCourses();
            for(int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).name);
            }
            System.out.println();
        } else if ( choice  == 3 ) {
            ArrayList<Course> courses = courseApp.getMyCourses();
            for(int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).name);
            }
            System.out.println("Please enter the name of the course you would like to edit");
            //editCourse();
            // Edit Courses
        } else {
            System.out.println("Invalid Choice");
        }
    }
    /*
     * Dialog for the Registered User usertype
     */
    public void getUserDialog() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("You are a registered user");
        System.out.println("Please enter [1] to view your courses, enter [2] to search for one a courses, enter [3] to take a course, enter [4] to logout");
        int choice =  keyboard.nextInt();
        switch(choice) {
            case 1:
                System.out.println("Showing your courses");
                ArrayList<Course> myCourses = courseApp.getMyCourses();
                for(int i = 0; i < myCourses.size(); i++) {
                    System.out.println(i+1 + ": " + myCourses.get(i).name);
                }
                break;
            case 2:
                System.out.println("You have selected to find courses");
                System.out.println("Please enter the name of the course to search for.");
                System.out.println();
                String name = keyboard.nextLine();
                courseApp.findCourses(name);
                break;
            case 3:
                System.out.println("You have decided to take a course. Please enter the name of the course you want to take");
                String courseChoice = keyboard.nextLine();
                courseApp.takeCourse(courseChoice);
                break;
            case 4: 
                System.out.println("You have selected to logout. Good Bye");
            default:
                System.out.println("Please enter a valid number");

        }
    }

    /**
     * Dialog that pops up in the author dialog
     * @return
     */
    public Course makeCourse() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Time to create a Course");
        System.out.println("==========================================================================");
        System.out.println("Please enter the name of the Course");
        String name = keyboard.nextLine();
        System.out.println("Please enter a description of the Course");
        String description = keyboard.nextLine();
        System.out.println("Please enter the Language you are coding the course in");
        Language lang = Language.valueOf(keyboard.nextLine().toUpperCase());
        UUID uuid = UUID. randomUUID();
        Course course = new Course(name, description,lang, uuid, null, null);
        return course;
        
    }
    /**
     * Make Module Dialog
     * @param course the course you want to create a course in
     */
    public Module makeModule(Course course) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Time to create a Module");
        System.out.println("==========================================================================");
        System.out.println("Please enter the name of the Module");
        String name = keyboard.nextLine();
        System.out.println("Please enter a description");
        String description = keyboard.nextLine();
        System.out.println("It is time to enter the Instructive Material");
        ArrayList<InstructiveMaterial> material = new ArrayList<InstructiveMaterial>();
        while (true) {
            System.out.println("Please enter the materials name");
            String materialName = keyboard.nextLine();
            System.out.println("Please enter the materials contents");
            String materialContent = keyboard.nextLine();
            System.out.println("Would you like to add more material? (Enter 'y' for yes, or 'n' for no)");
            String YoN = keyboard.nextLine();
            if(YoN.equalsIgnoreCase(YoN)) {
                break;
            }
        }
        courseApp.addModule(name,description);
        Module newMod = new Module(name, description, material);
        return newMod;
    }

    public Question makeQuestion(Module module) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the name of the question");
        String name = keyboard.nextLine();
        System.out.println("Please enter the number of answers you want to provide");
        int numAnswers = keyboard.nextInt();
        ArrayList<String> answers = new ArrayList<String>();
        for (int i=0; i<numAnswers; i++) {
            System.out.println("Please enter answer"+(i+1));
            String answerChoice = keyboard.nextLine();
            answers.add(answerChoice);
        }
        System.out.println("Please enter the correct answer choice");
        String answer = keyboard.nextLine();
        Question question = new Question(name,answers,answer);
        return question;
        
    }
}