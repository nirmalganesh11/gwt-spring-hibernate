package proj.server.daopack;

import java.util.List;

import proj.shared.DesigClass;

public interface IDesigDAO {

	String addDesig(DesigClass toBeAdded);

	List<String> getUsernames();

	DesigClass getDesignation(String designame);

}