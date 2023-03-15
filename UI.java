import java.util.Scanner;

// Log in and register
public class UI {
    public static void main () { 
        System.out.println("Welcome to CyberCock's school of coding");
        System.out.println("Please enter [1] to Login or enter [2] to Register");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        if (choice == 1) {
            ReturningUser();
        }else if(choice == 2) {
            RegisterUser();
        }
    }
}
/**
 * RegisterUser contains the dialog for the User to register their acct
 */
public static void RegisterUser() {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Welcome to the school of coding! It is time to register a new Account!");
    System.out.println("=======================================================================");
    // Copy this line for same length
    boolean valid = true;
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
        valid = false;
    } 
    }   while(valid);
    System.out.println("Please enter your First Name");
    String firstName = keyboard.next();
    System.out.println("Please enter your Last Name");
    String lastName = keyboard.next();
    System.out.println("Please enter your Email");
    String email = keyboard.next();
    System.out.println("Please enter your Date of Birth in this notation (xx/xx/xxxx)");
    String birthday = keyboard.next();
    System.out.println("Please enter your Desired Username");
    String username = keyboard.next();
    System.out.println("Please enter your Password");
    String password = keyboard.next();
    createUserAccount(type,firstName,lastName,email, birthday, username, password);
}

/**
 * login contains the dialog and checks if the User is in the User list. If so should login
 */
public static void ReturningUser() {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Welcome Back!");
    System.out.println("Please enter your username");
    String username = keyboard.next();
    System.out.println("Please enter your password");
    String password = keyboard.next();
    login(username,password);
}


//find course

//