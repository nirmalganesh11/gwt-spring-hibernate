package proj.server.security.securityClasses;

import org.springframework.security.core.GrantedAuthority;



public class CustomGrantedAuthority implements GrantedAuthority {


	private static final long serialVersionUID = 1L;
	
	private String role;

	public CustomGrantedAuthority(String role) {
		this.role = role;
	}
	
	public CustomGrantedAuthority() {
		
	}

	@Override
	public String getAuthority() {
		
		return role;
	}
	
	public boolean equals(Object obj) {
		
		if (obj instanceof CustomGrantedAuthority) {
			return role.equals(((CustomGrantedAuthority) obj).role);
		}
		if (this == obj) {
			return true;
		}
		
		return false;
	}
	

}
