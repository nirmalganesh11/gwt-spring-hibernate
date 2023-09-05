package proj.server.servicepack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import proj.server.daopack.SalaryDAO;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.shared.SalaryClass;

public class SalaryService implements ISalaryService{
	private SalaryDAO salarydao;
	private String Operation1 ="SALARY_ROLE";
	
	
	public SalaryService(SalaryDAO salarydao){
		this.salarydao = salarydao;
	}
	@Override
	public String saveSalaryRecord(SalaryClass salcal) {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	        List<CustomGrantedAuthority> authorities = (List<CustomGrantedAuthority>) authentication.getAuthorities();
	        for (CustomGrantedAuthority authority : authorities) {
	            String role = authority.getAuthority();
	            if(role.equals(Operation1)) {
	            	return salarydao.saveSalaryRecord(salcal);
	            }
	        }
	    }
		//throw new Exception("not allowed");
		return "RoleNotAllowed";
		
	}
	@Override
	public List<SalaryClass> getSalRecord(String username) {
//		List<SalaryClass> newclss= new ArrayList<>();
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication != null && authentication.isAuthenticated()) {
//	        List<CustomGrantedAuthority> authorities = (List<CustomGrantedAuthority>) authentication.getAuthorities();
//	        for (GrantedAuthority authority : authorities) {
//	            String role = authority.getAuthority();
//	            if(role.equals(Operation1)) {
//	            	return salarydao.getSalRecord(username);
//	            }
//	        }
//	    }
		return salarydao.getSalRecord(username);
		//return null;
		//return newclss;
	}
}
