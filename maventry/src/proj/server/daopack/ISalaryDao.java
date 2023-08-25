package proj.server.daopack;

import java.util.List;

import proj.shared.SalaryClass;

public interface ISalaryDao {
	public String saveSalaryRecord (SalaryClass salcal);
	public List<SalaryClass> getSalRecord(String username);
}
