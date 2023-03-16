import java.util.ArrayList;
// We need to reference People.java
public class UserList {
    
    private static UserList UserList = null;
    private static ArrayList <User> userList = new ArrayList<User>();

    private UserList() {
        userList = DataLoader.LoadUsers();
    }

    public static UserList getInstance() {
		if (UserList == null) {
			UserList = new UserList();
		}
		return UserList;
	}
    public void addUser(User user){

    }
    public ArrayList<User> getAll(){
        return userList;
    }
    public void deleteUser(User user){

    }
    public void save(){

    }
    public void editUser(User user){

    }
    public void findUser (User user) {
        
    }
}
