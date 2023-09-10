package proj.server.security.securityClasses;

import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

import proj.shared.security.Role;
import proj.shared.security.*;

public class RoleDecorator implements IsSerializable{
	
	private int roleId;
	private String roleName;
	private String[] rolePermissions;
	
	public RoleDecorator(Role role) {
		this.roleName = role.getRoleName();
		this.rolePermissions = extractStringArray(role.getRolePermissions());
		
		
	}
	public RoleDecorator() {
		
	}
	
	private String[] extractStringArray (Set<Permission> set){
		String [] extracted = new String [set.size()];
		int count=0;
		for(Permission perm: set) {
			extracted[count++] = perm.getPermissionName();
		}
		return extracted;	
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
	public String[] getRolePermissions() {
		return rolePermissions;
	}
	public void setRolePermissions(String[] rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
	
}
