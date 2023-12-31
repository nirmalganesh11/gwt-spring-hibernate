package proj.server.servicesInterfaces;

import java.util.List;

import proj.server.Exceptions.AccessDeniedException;
import proj.shared.DesigClass;

public interface DesigService {
	
	String addDesig(DesigClass toBeAdded);

	List<String> getUsernames();

	DesigClass getDesignation(String designame);
	
	List<DesigClass> listDesignations();
	
}
