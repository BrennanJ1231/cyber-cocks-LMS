import java.util.Scanner;

// UI class
public class UI {
    public static void main () { 
        System.out.println("Welcome to CyberCock's school of coding");
        System.out.println("Please enter [1] to Login or enter [2] to Register");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        if (choice == 1) {
            login();
        }else if(choice == 2) {
            RegisterUser();
        }
    }
}
public static void RegisterUser() {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Welcome to the school of coding! It is time to register a new Account!");
    System.out.println("=======================================================================");
    // Copy this line for same length
    System.out.println("Type [1] to create an author account, [2] to create a user account, and [3] to create an admin account");
    int choice =  keyboard.nextInt();
    String type ="";
    if (choice == 1) {
        type = "Author";
    } else if (choice ==2) {
        type = "User";
    } else if (choice == 3) {
        type = "Admin";
    }else {
        System.out.println("Invalid input");
    }
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
public static void login() {
    System.out.println("Welcome Back!");
            System.out.println("Please enter your username");
            String username = keyboard.next();
            System.out.println("Please enter your password");
            String password = keyboard.next();
}

//choose if user is going to be admin author and student

//find course

//