import java.util.ArrayList;
// We need to reference People.java
public class UserList {
    
    private static UserList UserList = null;
    private static ArrayList <User> userList = new ArrayList<User>();

    private UserList() {
        userList = DataLoader.loadUsers();
    }

    public static UserList getInstance() {
		if (UserList == null) {
			UserList = new UserList();
		}
		return UserList;
	}
    public void addUser(User user){
        userList.add(user);
    }
    public ArrayList<User> getAll(){
        return userList;
    }
    public void deleteUser(User user){
        userList.remove(user);
    }
    public void save(){
        DataWriter.saveUser(DataWriter.USERS_FILE_NAME);
    }
    public void editUser(User user){
        //Unsure what this needs to do
    }
    
}
