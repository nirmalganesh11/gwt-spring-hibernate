package proj.shared.security;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Role implements IsSerializable{
	
	private int role_id;
	
	private String roleName;
	
	private String roleDescription;
	
	private int userCount;
	
	private Set<Permission> rolePermissions;
	
	private Set<FullUser> usersAssigned;
	
	private String[] stringArrOfPermissionNames;
	
	private String [] assignedGroups;
	
	
	
	public Role() {}
	
	public Role(String roleName,Set<Permission> arr) {
		this.roleName = roleName;
		setRolePermissions(arr);
	}
	
	public Role(String roleName) {
		this.roleName = roleName;
	}
	
	
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public String[] getAssignedGroups() {
		return assignedGroups;
	}

	public void setAssignedGroups(String[] assignedGroups) {
		this.assignedGroups = assignedGroups;
	}



	public String[] getStringArrOfPermissionNames() {
		return stringArrOfPermissionNames;
	}

	public void setStringArrOfPermissionNames(String[] stringArrOfPermissionNamesPassed) {
		
//		if(rolePermissions == null) {
//			rolePermissions = new HashSet<Permission>();
//			for(String str: stringArrOfPermissionNamesPassed) {
//				rolePermissions.add(new Permission(str));
//			}
//		}
//		
		this.stringArrOfPermissionNames = stringArrOfPermissionNamesPassed;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public Set<Permission> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(Set<Permission> passedRolePermissions) {
		
//		if(stringArrOfPermissionNames == null) {
//			stringArrOfPermissionNames = new String [passedRolePermissions.size()];
//			int count =0;
//			for(Permission perm: passedRolePermissions) {
//				stringArrOfPermissionNames[count++] = perm.getPermissionName();	
//			}
//			
//		}
		this.rolePermissions = passedRolePermissions;
		
	}
	
}
