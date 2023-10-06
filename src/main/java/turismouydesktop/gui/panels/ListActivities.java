package turismouydesktop.gui.panels;

import java.awt.Font;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;

public class ListActivities extends JPanel {
	private ListActivityListener listener;
	/**
	 * Create the panel.
	 */
	public ListActivities() {
		setLayout(null);
		//llamo al controlador y le pido DT de departamento
		IController controller = ControllerFactory.getIController();
		//getListActivityState thingy thanga
		//List<DtTouristicActivity> dtDepartments = controller.getList
		
		//creo los Array de String.
		String[] activityStringArray = new String[dtDepartments.size()];
				
		//itero sobre el DT para darles valor a las array de String
		int i = 0;
		for(DtDepartment depa : dtDepartments) {
				departmentStringArray[i] = depa.getName();
				i++;
		}
		
		scrollPaneDepartment.setBounds(0, 0, width, height);
		add(scrollPaneDepartment);
		scrollPaneDepartment.setViewportView(listDepartment);
		
		listDepartment.setModel(new AbstractListModel() {
			String[] values = departmentStringArray;
			
			public int getSize() {
				return values.length;
			}
			
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listDepartment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDepartment.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		listDepartment.addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				String departmentName = (String) listDepartment.getSelectedValue();
				if (listener != null && departmentName != null) {
					
					Long id = dtDepartments
							.stream()
							.filter(department -> department.getName().equalsIgnoreCase(departmentName))
							.findFirst()
							.get()
							.getId();
					
					DtDepartment dtDepartamento = dtDepartments.get(listDepartment.getSelectedIndex());
					listener.onListDepartmentSelected(id);
					listener.onListDepartmentSelectedDt(dtDepartamento);
					
				}
			}
		});
	}
	
	public void setListener(ListActivityListener listener) {
		this.listener = listener;
	}
}
