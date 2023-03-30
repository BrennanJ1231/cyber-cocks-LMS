import java.util.ArrayList;
import java.util.UUID;
// We need to reference People.java
public class UserList {
    
    private static UserList users = null;
    private static ArrayList <User> userList = new ArrayList<User>();

    public UserList() {
        userList = DataLoader.loadUsers();
    }


    public static UserList getInstance() {
		if (users == null) {
			users = new UserList();
		}
		return users;
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

    
    public User findUser(String username) {
        for (int i=0; i<userList.size();i++) {
            if (userList.get(i).username.equalsIgnoreCase(username)) {
                return userList.get(i);
            }
        }
        return null;
    }
    public User findUser(UUID uuid) {
        for (int i=0; i<userList.size();i++) {
            if (userList.get(i).uuid.equals(uuid)) {
                return userList.get(i);
            }
        }
        return null;
    }
}
