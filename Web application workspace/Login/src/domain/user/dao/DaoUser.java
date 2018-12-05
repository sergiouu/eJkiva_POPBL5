package domain.user.dao;
import java.util.ArrayList;

import domain.user.model.User;

public interface DaoUser {
	public User loadUser(String username,String password);
	public User loadUser(int userId);
	public ArrayList<User> loadUsers();
	public boolean insertUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(int userId);
}
