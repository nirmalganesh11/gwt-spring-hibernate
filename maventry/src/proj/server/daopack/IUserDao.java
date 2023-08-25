package proj.server.daopack;

import proj.shared.User;

public interface IUserDao {

	String addUser(User toBeAdded);

	User findByUsername(String username);

	String validateUsername(String username);

}