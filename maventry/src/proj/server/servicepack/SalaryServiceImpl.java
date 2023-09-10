package proj.server.servicepack;


import java.util.List;

import org.springframework.security.core.Authentication;
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
		

		return salarydao.saveSalaryRecord(salcal);
	}
	@Override
	public List<SalaryClass> getSalRecord(String username) {

		return salarydao.getSalRecord(username);

	}
}
