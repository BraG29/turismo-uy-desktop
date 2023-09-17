package turismouydesktop.gui.panels;

import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;

public interface ListDepartmentListener {
	
	public void onListDepartmentSelected(Long id);
	public void onListDepartmentSelectedDt(DtDepartment department);
}
