package proj.shared.security;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

import proj.server.security.securityClasses.CustomGrantedAuthority;




public class UserAccount implements UserDetails,Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private Collection<CustomGrantedAuthority> authorities;
	private List<Role> roles = new ArrayList<Role>();
	
	public UserAccount() {}
	
	@Override
	public Collection<CustomGrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Collection<CustomGrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	public List<Role> getRoles(){
		return roles;
	}
	public	void setRoles(List<Role> object){
		this.roles = object;
	}
	
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
