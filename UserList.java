import java.util.ArrayList;
// We need to reference People.java
public class UserList {
    
    private static UserList UserList = null;
    private static ArrayList <RegisteredUser> RegisteredUserList = new ArrayList<>();

    private UserList() {
        userList = DataLoader.LoadUsers();
    }

    public static UserList getInstance() {
		if (RegisteredUserList == null) {
			RegisteredUserList = new UserList();
		}
		return UserList;
	}
    public void addUser(User user){

    }
    public ArrayList<RegisteredUser> getRegisteredUsers(){
        return RegisteredUserList;
    }
    public void deleteUser(User user){

    }
    public void save(){

    }
    public void editUser(User user){

    }
}
