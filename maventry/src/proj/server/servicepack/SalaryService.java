package proj.server.servicepack;

import java.util.List;

import proj.server.daopack.SalaryDAO;
import proj.shared.SalaryClass;

public class SalaryService implements ISalaryService{
	private SalaryDAO salarydao;
	public SalaryService(SalaryDAO salarydao){
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
