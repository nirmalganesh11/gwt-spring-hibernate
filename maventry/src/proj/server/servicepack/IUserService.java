package proj.server.servicepack;

import proj.shared.User;

public interface IUserService {
	String addUser(User toBeAdded);

	User findByUsername(String username);

	String validateUsername(String username);
}
