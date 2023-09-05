package proj.server.servicepack;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import proj.server.daopack.IDesigDAO;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.shared.DesigClass;

public class DesigService implements IDesigService {
	
	private IDesigDAO desigdao;
	public DesigService(IDesigDAO desigdao){
		this.desigdao = desigdao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String addDesig(DesigClass toBeAdded) {
		
		String Operation1 ="DESIG_ROLE";
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	        List<CustomGrantedAuthority> authorities = (List<CustomGrantedAuthority>) authentication.getAuthorities();
	        for (GrantedAuthority authority : authorities) {
	            String role = authority.getAuthority();
	            if(role.equals(Operation1)) {
	            	return desigdao.addDesig(toBeAdded);
	            	//break;
	            }
	        }
	    }
		return "RoleNotAllowed";
	}
	@Override
	public List<String> getUsernames() {
		return desigdao.getUsernames();
	}
	@Override
	public DesigClass getDesignation(String designame) {
		return desigdao.getDesignation(designame);
	}
	
}
