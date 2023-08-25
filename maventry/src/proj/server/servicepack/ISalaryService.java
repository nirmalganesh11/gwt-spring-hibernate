package proj.server.servicepack;

import java.util.List;

import proj.shared.SalaryClass;

public interface ISalaryService {
	String saveSalaryRecord (SalaryClass salcal);
	List<SalaryClass> getSalRecord(String username);
}
