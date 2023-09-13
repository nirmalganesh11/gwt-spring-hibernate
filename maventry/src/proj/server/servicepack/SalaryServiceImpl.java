package proj.server.servicepack;


import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import proj.server.daopack.SalaryDAO;
import proj.server.enums.MethodPermissions;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.servicesInterfaces.SalaryService;
import proj.shared.SalaryClass;

public class SalaryServiceImpl implements SalaryService{
	
	private SalaryDAO salarydao;
	
	
	
	public SalaryServiceImpl(SalaryDAO salarydao){
		this.salarydao = salarydao;
	}
	
	@Override
	public String saveSalaryRecord(SalaryClass salcal) {
		
		if(checkPermission(MethodPermissions.WRITE_SALARY.name())) {
			return "Dont have enough permissions";
		}

		return salarydao.saveSalaryRecord(salcal);
	}
	@Override
	public List<SalaryClass> getSalRecord(String username) {
		
		if(checkPermission(MethodPermissions.READ_SALARY.name())) {
			return null;
		}

		return salarydao.getSalRecord(username);

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
}
