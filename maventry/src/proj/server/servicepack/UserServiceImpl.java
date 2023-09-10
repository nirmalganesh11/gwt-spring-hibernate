package proj.server.servicepack;


import proj.server.daopack.UserDAO;
import proj.server.servicesInterfaces.UserService;
import proj.shared.User;

public class UserServiceImpl implements UserService{
	
	public UserDAO userdao;
	
	public UserServiceImpl(UserDAO authclass){
		this.userdao = authclass;
	}
	@Override
	public String addUser(User toBeAdded) {
		return userdao.addUser(toBeAdded);
	}
	@Override
	public User findByUsername(String username) {
		return userdao.findByUsername(username);
	}
	@Override
	public String validateUsername(String username) {
		return userdao.validateUsername(username);
	}
}
