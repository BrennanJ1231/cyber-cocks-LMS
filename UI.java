import java.util.Scanner;

// UI class
public class UI {
    public static void main () { 
        System.out.println("Welcome to CyberCock's school of coding");
        System.out.println("Please enter [1] to Login or enter [2] to Register");
        Scanner keyboard = new Scanner(System.in);
        int choice = keyboard.nextInt();
        if (choice == 1) {
            System.out.println("Welcome Back!");
        }else if(choice == 2) {
            System.out.println("Welcome to the school of coding!");
        }
    }
}

//choose if user is going to be admin author and student

//find course

//