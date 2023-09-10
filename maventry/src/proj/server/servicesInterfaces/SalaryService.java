package proj.server.servicesInterfaces;

import java.util.List;

import proj.shared.SalaryClass;

public interface SalaryService {
	
	String saveSalaryRecord (SalaryClass salcal);
	List<SalaryClass> getSalRecord(String username);
}
