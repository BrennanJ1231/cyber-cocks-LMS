import java.util.ArrayList;

public class UserList {
    
    private ArrayList <User> User;
    private static UserList userList;

    private UserList() {}

    public static UserList getInstance() {
		if (userList == null) {
			System.out.println("Creating a chocolate boiler");
			userList = new UserList();
		}
		return userList;
	}
    public void addUser(User user){

    }
    public void getAll(Users){

    }
    public void deleteUser(User user){

    }
    public void save(){

    }
    public void editUser(User user){

    }
}
