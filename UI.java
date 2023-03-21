import java.util.Calendar;
import java.util.Scanner;

public class UI {
    public static void main (String[] args) {
        UI ui = new UI();
        ui.run();
        //Keep the main clean
    }

    /**
     * Run is the main dialog of the UI. It calls other methods based on Users wishes
     */
    public static void run() {
        System.out.println("Welcome to CyberCock's school of coding");
        System.out.println("Please enter [1] to Login or enter [2] to Register");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        String type;
        if (choice == 1) {
            ReturningUser();
            System.out.println("Welcome " + User.getName() + " to CyberCock's school of coding");
        }else if(choice == 2) {
            RegisterUser();
            System.out.println("Now that you registered, You have to log in");
            ReturningUser();
        }else {
            System.out.println("Invalid Choice");
        }
//Make a while loop so that as long as you are logged in you are able to continue
        //while () {
        // Login or register dialog
        System.out.println("Welcome " +  User.getFirstName() + "!");
        System.out.println("=======================================================================");
        if (type == "Admin") {
            adminDialog();
            System.out.println("")
        } else if (type == "Author") {
            authorDialog();
        } else {
            userDialog();
        }
        System.out.println("Thank you for using Our App");
         // Figure out the type and then display their options
    }
    
    
    /**
     * Register User is registering a User into the user list
     */
    public static void RegisterUser() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the school of coding! It is time to register a new Account!");
        System.out.println("=======================================================================");
        // Copy this line for same length
        String type ="";
        do {
        System.out.println("Type [1] to create an author account, [2] to create a user account, and [3] to create an admin account");
        System.out.println();
        int choice =  keyboard.nextInt();
        if (choice == 1) {
            type = "Author";
        } else if (choice ==2) {
            type = "User";
        } else if (choice == 3) {
            type = "Admin";
        }else {
            System.out.println("Invalid input");
        } 
        }   while(true);
        System.out.println("Please enter your First Name");
        String firstName = keyboard.nextLine();
        System.out.println("Please enter your Last Name");
        String lastName = keyboard.nextLine();
        System.out.println("Please enter your Email");
        String email = keyboard.nextLine();
        System.out.println("Please enter your Date of Birth in this notation (xx/xx/xxxx)");
        Calendar birthday = keyboard.next();
        System.out.println("Please enter your Desired Username");
        String username = keyboard.nextLine();
        System.out.println("Please enter your Password");
        String password = keyboard.nextLine();
        CourseApplication.createUserAccount(type,firstName,lastName,email, birthday, username, password);
        }

    /**
    * returning user contains the dialog and checks if the User is in the User list. If so should login
    */
    public static boolean getReturningUser() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome Back!");
        System.out.println("Please enter your username");
        String username = keyboard.nextLine();
        System.out.println("Please enter your password");
        String password = keyboard.nextLine();
        boolean login = CourseApplication.login(username,password);
        return login;
    }

    /*
     * Dialog for the admin User type 
     */
    public static void getAdminDialog() {
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
            CourseApplication.findCourses(courseChoice);
        }
    }
    /*
     * Dialog for the author user type
     */
    public static void getAuthorDialog() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("You are an author");
        System.out.println("Please enter [1] to create course, enter [2] to see your courses, enter [3] to edit one of your courses");
        System.out.println();
        int choice = keyboard.nextInt();
        if (choice == 1 )  {
            // Create Courses
            CourseApplication.makeCourse();
            CourseApplication.makeModule();
        } else if ( choice  == 2 ) {
            // Find Courses
            CourseApplication.getMyCourses();
            System.out.println();
        } else if ( choice  == 3 ) {
            CourseApplication.editCourse();
            // Edit Courses
        } else {
            System.out.println("Invalid Choice");
        }
    }
    /*
     * Dialog for the Registered User usertype
     */
    public static void getUserDialog() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("You are a registered user");
        System.out.println("Please enter [1] to view your courses, enter [2] to search for one a courses, enter [3] to take a course, enter [4] to logout");
        int choice =  keyboard.nextInt();
        switch(choice) {
            case 1:
                System.out.println("Showing your courses");
                CourseApplication.getMyCourses();
                break;
            case 2:
                System.out.println("You have selected to find courses");
                CourseApplication.findCourses();
                break;
            case 3:
                System.out.println("You have decided to take a course. Please enter the name of the course you want to take");
                String courseChoice = keyboard.nextLine();
                CourseApplication.takeCourse(courseChoice);
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
    public static void makeCourse() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("You have selected to create a Course");
        System.out.println("=======================================================================");
        System.out.println("Please enter the name of the Course");
        String name = keyboard.nextLine();
        System.out.println("Please enter a description of the Course");
        String description = keyboard.nextLine();
        System.out.println("Please enter the Language you are coding the course in");
        Language lang = keyboard.nextLine();
        System.out.println("Please enter the UUID for the course");
        String uuid = keyboard.nextLine();
        Course course = new Course(name, description, lang, null);
    }
    /**
     * Make Module Dialog
     * @param course the course you want to create a course in
     */
    public static void makeModule(Course course) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("You have selected to create a Module");
        System.out.println("=======================================================================");
        System.out.println("Please enter the name of the Module");
        String name = keyboard.nextLine();
        System.out.println("Please enter intsructive material");
        String description = keyboard.nextLine();
        CourseApplication.addModule(name,description);
    }

    public static void addQuestion(Module module) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the name of the question");
        String name = keyboard.nextLine();
        System.out.println("Please enter the description of the question");
        Assignment.addAssignment(name);
    }
}   

//find course

//