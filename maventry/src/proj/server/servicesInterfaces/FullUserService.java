package proj.server.servicesInterfaces;

import java.util.List;

import proj.shared.security.FullUser;



public interface FullUserService {

	public String addFullUser(FullUser toBeAdded);
	public String deleteFullUser(FullUser role);
	public String updateFullUser(FullUser role);
	public List<FullUser> getAllFullUsers();
}
