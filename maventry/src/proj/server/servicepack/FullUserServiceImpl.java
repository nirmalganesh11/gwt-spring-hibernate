package proj.server.servicepack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import proj.server.daopack.FullUserDAO;
import proj.server.daopack.RoleDAO;
import proj.server.servicesInterfaces.FullUserService;
import proj.server.servicesInterfaces.UserService;
import proj.shared.User;
import proj.shared.security.FullUser;

public class FullUserServiceImpl implements FullUserService{
	
	private FullUserDAO fulluserdao;
	
	private UserService userServ;
	
	
	//private ApplicationContext context;
	
	public FullUserServiceImpl(FullUserDAO fulluserDao) {
		this.fulluserdao = fulluserDao;
	}
	
	@Override
	public String addFullUser(FullUser toBeAdded) {
		System.out.println("came here when button is pressed"+ toBeAdded.getFirstName());
		
		User newUser = new User(toBeAdded.getUserId(),toBeAdded.getPassword(),toBeAdded.getRolesList());
		userServ.addUser(newUser);
		
		return fulluserdao.addFullUser(toBeAdded);

	}
	
	@Override
	public List<FullUser> getAllFullUsers() {
		return fulluserdao.getAllFullUsers();
	}
	

	@Override
	public String deleteFullUser(FullUser user) {
		return fulluserdao.deleteFullUser(user);
	}

	@Override
	public String updateFullUser(FullUser role) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	public FullUserDAO getFulluserdao() {
		return fulluserdao;
	}

	public void setFulluserdao(FullUserDAO fulluserdao) {
		this.fulluserdao = fulluserdao;
	}

	public UserService getUserServ() {
		return userServ;
	}

	public void setUserServ(UserService userServ) {
		this.userServ = userServ;
	}
	
}
