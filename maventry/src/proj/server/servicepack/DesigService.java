package proj.server.servicepack;

import java.util.List;

import proj.server.daopack.IDesigDAO;
import proj.shared.DesigClass;

public class DesigService implements IDesigService {
	private IDesigDAO desigdao;
	public DesigService(IDesigDAO desigdao){
		this.desigdao = desigdao;
	}
	@Override
	public String addDesig(DesigClass toBeAdded) {
		return desigdao.addDesig(toBeAdded);
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
