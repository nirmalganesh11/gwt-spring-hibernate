package proj.server.servicesInterfaces;

import proj.shared.User;

public interface UserService {
	
	String addUser(User toBeAdded);

	User findByUsername(String username);

	String validateUsername(String username);
	
}
