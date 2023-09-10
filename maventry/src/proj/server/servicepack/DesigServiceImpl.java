package proj.server.servicepack;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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
		
		Boolean authorised = false;
		if(userSession != null) {
			for(String permission: userSession.getRightsAssigned()) {
				if(MethodPermissions.WRITE_DESIGNATION.name().equals(permission)) {
					authorised = true;
				}
			}
			
		}
		if(!authorised)
			return "Not Enough Permissions";
		
		return desigdao.addDesig(toBeAdded);
	}
	

	@Override
	public List<String> getUsernames() throws AccessDeniedException {
			
		return desigdao.getUsernames();
	}
	
	@Override
	public DesigClass getDesignation(String designame) throws AccessDeniedException {
		
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
}
