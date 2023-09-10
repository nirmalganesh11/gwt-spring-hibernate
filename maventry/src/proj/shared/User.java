package proj.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

import proj.shared.security.Role;

public class User implements IsSerializable {
	
	private int id;
	private String username;
	private String password;
	private String[] rolesArray;
	
	public User() {}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User(String username,String password,List<Role> rolesList) {
		this.username = username;
		this.password = password;
		this.rolesArray = convertRolesArray(rolesList);
		
	}
	public String [] convertRolesArray(List<Role> rolesList) {
		String [] rolenames = new String [rolesList.size()];
		int count =0;
		for(Role r: rolesList) {
			rolenames[count++] = r.getRoleName();
		}
		return rolenames;
		
	}
	public String[] getRolesArray() {
		return rolesArray;
	}
	public void setRolesArray(String[] rolesArray) {
		this.rolesArray = rolesArray;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password +"]";
	}
	
	
	
	
}
