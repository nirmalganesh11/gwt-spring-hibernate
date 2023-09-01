package proj.server.security.securityClasses;

import java.util.Set;

public class Role {
	
	private int roleId;
	private String roleName;
	private Set<RoleDetail> roleDetails;
	
	public Role(String rolename) {
		this.roleName = rolename;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<RoleDetail> getRoleDetails() {
		return roleDetails;
	}
	public void setRoleDetails(Set<RoleDetail> roleDetails) {
		this.roleDetails = roleDetails;
	}
	
	
	
}
