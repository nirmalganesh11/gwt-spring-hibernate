package proj.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

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
