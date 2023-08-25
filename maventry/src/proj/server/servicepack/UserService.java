package proj.server.servicepack;

import proj.server.daopack.IUserDao;
import proj.shared.User;

public class UserService implements IUserService{
	public IUserDao userdao;
	public UserService(IUserDao authclass){
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
