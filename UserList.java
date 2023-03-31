import java.util.ArrayList;
import java.util.UUID;
/**
UserList represents a list of users in the system.
*/
public class UserList {
    
    private static UserList users = null;
    private static ArrayList <User> userList = new ArrayList<User>();

    /**
    Constructor for UserList class.
    Initializes the userList by loading users from data file using DataLoader.
    */
    public UserList() {
        userList = DataLoader.loadUsers();
    }

    /**
    Get the instance of UserList (Singleton Pattern).
    @return UserList object.
    */
    public static UserList getInstance() {
		if (users == null) {
			users = new UserList();
		}
		return users;
	}

    /**
    Adds a user to the userList.
    @param user User object to be added.
    */
    public void addUser(User user){
        userList.add(user);
    }

    /**
    Returns all the users in the userList.
    @return ArrayList of User objects.
    */
    public ArrayList<User> getAll(){
        return userList;
    }

    /**
    Deletes a user from the userList.
    @param user User object to be deleted.
    */
    public void deleteUser(User user){
        userList.remove(user);
    }

    /**
    Saves the userList to data file using DataWriter.
    */
    public void save(){
        DataWriter.saveUser(DataWriter.USERS_FILE_NAME);
    }

    /**
    Finds a user by username from the userList.
    @param username Username of the user to be found.
    @return User object if found, otherwise null.
    */
    public User findUser(String username) {
        for (int i=0; i<userList.size();i++) {
            if (userList.get(i).username.equalsIgnoreCase(username)) {
                return userList.get(i);
            }
        }
        return null;
    }

    /**
    Finds a user by UUID from the userList.
    @param uuid UUID of the user to be found.
    @return User object if found, otherwise null.
    */
    public User findUser(UUID uuid) {
        for (int i=0; i<userList.size();i++) {
            if (userList.get(i).uuid.equals(uuid)) {
                return userList.get(i);
            }
        }
        return null;
    }
}
