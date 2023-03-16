import java.util.Scanner;

// Log in and register
public class UI {
    public static void main () {
        run();
    }

/**
 * RegisterUser contains the dialog for the User to register their acct
 */
    public static void run() {
        System.out.println("Welcome to CyberCock's school of coding");
        System.out.println("Please enter [1] to Login or enter [2] to Register");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        String type;
        if (choice == 1) {
            ReturningUser();
        }else if(choice == 2) {
            RegisterUser();
        } else {
            System.out.println("Invalid Choice");
            break;
        }
        // Login or register dialog

        System.out.println("Welcome " /*Have this be the users first name*/  "!");
        System.out.println("=======================================================================");
        if (type == "Admin") {
            adminDialog();
        } else if (type == "Author") {
            authorDialog();
        } else {
            userDialog();
        }
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
        String birthday = keyboard.nextLine();
        System.out.println("Please enter your Desired Username");
        String username = keyboard.nextLine();
        System.out.println("Please enter your Password");
        String password = keyboard.nextLine();
        CourseApplication.createUserAccount(type,firstName,lastName,email, birthday, username, password);
}

    /**
    * returning user contains the dialog and checks if the User is in the User list. If so should login
    */
    public static boolean ReturningUser() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome Back!");
        System.out.println("Please enter your username");
        String username = keyboard.nextLine();
        System.out.println("Please enter your password");
        String password = keyboard.nextLine();
        boolean login = CourseApplication.login(username,password);
        if(true) {

        }
    }

    /*
     * Dialog for the admin User type 
     */
    public static void adminDialog() {
        Scanner keyboard = new Scanner(System.in);
        if(User.getType() == "Admin") {
        System.out.println("You are an admin");
        System.out.println("Please enter [1] to assign courses, enter [2] to findCourses, and enter [3] to take a course");
        int choice = keyboard.nextInt();
        if ( choice == 1 ) {
            System.out.println("Please enter the course details of the one you would like to assign");
        } else if( choice == 2 ) {
            System.out.println("Please enter the course details so we can find your course");
        } else if ( choice == 3 ) {
            System.out.println("Please enter the course details of the one you would like to take");
        findCourses()
        } 
    }

    public makeCourse() {
        Scanner keyboard = new Scanner(System.in);
    }
}   
}
//find course

//