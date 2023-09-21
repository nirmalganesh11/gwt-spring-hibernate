package proj.server.servicepack;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.annotation.Secured;
import proj.server.Exceptions.AccessDeniedException;
import proj.server.daopack.DesigDAO;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.servicesInterfaces.DesigService;
import proj.server.sessionManagement.UserSession;
import proj.shared.DesigClass;
import proj.server.enums.*;

public class DesigServiceImpl implements DesigService {
	
	private DesigDAO desigdao;
	
	private UserSession userSession;
	
	
	public DesigServiceImpl(DesigDAO desigdao){
		this.desigdao = desigdao;
	}
	

	@Override
	public String addDesig(DesigClass toBeAdded) {
		
		if(checkPermission(MethodPermissions.WRITE_DESIGNATION.name())) {
			return "Dont have enough permissions";
		}
		
		
		return desigdao.addDesig(toBeAdded);
	}
	

	@Override
	public List<String> getUsernames(){
		
		if(checkPermission(MethodPermissions.READ_DESIGNATION.name())) {
			return null;
		}
			
		return desigdao.getUsernames();
	}
	

	@Override
	public DesigClass getDesignation(String designame){
		
		if(checkPermission(MethodPermissions.READ_DESIGNATION.name())) {
			return null;
		}
		
		
		return desigdao.getDesignation(designame);
	}
	
	
	public DesigDAO getDesigdao() {
		return desigdao;
	}


	public void setDesigdao(DesigDAO desigdao) {
		this.desigdao = desigdao;
	}


	public UserSession getUserSession() {
		return userSession;
	}


	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
	public Boolean checkPermission(String authority) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for(GrantedAuthority ga: auth.getAuthorities()) {
			if(ga.getAuthority().equals(authority)) {
				return false;
			}
			
		}
		return true;
	}


	@Override
	public List<DesigClass> listDesignations() {
		return desigdao.listDesigantions();
	}
	
	
}
