package proj.shared.security;

import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Permission implements IsSerializable {
	
	private int permission_id;
	private String permissionName;
	private String permissionType;
	

	public Permission() {}
	
	public Permission(String name) {
		this.permissionName = name;
	}
	
	public Permission(String permName, String permType) {
		this.permissionName = permName;
		this.permissionType = permType;
		
		
	}
	
	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	
	public int getPermission_id() {
		return permission_id;
	}
	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
}
