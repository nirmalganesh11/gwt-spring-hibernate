package proj.server.servicepack;

import java.util.List;

import proj.shared.DesigClass;

public interface IDesigService {
	String addDesig(DesigClass toBeAdded);

	List<String> getUsernames();

	DesigClass getDesignation(String designame);
	
}
