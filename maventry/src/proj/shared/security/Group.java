package proj.shared.security;

import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Group implements IsSerializable{
	
	private int groupId;
	private String groupName;
	private String groupDesc;
	private int countOfUsers;
	private List<UserAccount> users;
	private Set<Role> roles;
	
	public Group() {
		
	}
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public int getCountOfUsers() {
		return countOfUsers;
	}
	public void setCountOfUsers(int countOfUsers) {
		this.countOfUsers = countOfUsers;
	}
	public List<UserAccount> getUsers() {
		return users;
	}
	public void setUsers(List<UserAccount> users) {
		this.users = users;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

}
