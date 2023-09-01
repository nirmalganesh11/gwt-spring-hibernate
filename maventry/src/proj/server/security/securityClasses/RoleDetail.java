package proj.server.security.securityClasses;

public class RoleDetail {
	
	private int roleDetailId;
	private Permission servletPermission;
	private String description;
	
	
	public Permission getServletPermission() {
		return servletPermission;
	}

	public int getRoleDetailId() {
		return roleDetailId;
	}

	public void setRoleDetailId(int roleDetailId) {
		this.roleDetailId = roleDetailId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setServletPermission(Permission servletPermission) {
		this.servletPermission = servletPermission;
	}
}
