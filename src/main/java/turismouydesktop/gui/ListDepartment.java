package turismouydesktop.gui;

import java.awt.Font;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtProvider;

public class ListDepartment extends JPanel {
	
	private ListDepartmentListener listener;
	private JList listDepartment = new JList();
	private JScrollPane scrollPaneDepartment = new JScrollPane();
	/**
	 * Create the panel.
	 */
	public ListDepartment() {
		setLayout(null);
		
		//llamo al controlador y le pido DT de departamento
		IController controller = ControllerFactory.getIController();
		List<DtDepartment> dtDepartments = controller.getListDepartment(true);
		
		//creo los Array de String.
		String[] departmentStringArray = new String[dtDepartments.size()];
				
		//itero sobre el DT para darles valor a las array de String
		int i = 0;
		for(DtDepartment depa : dtDepartments) {
				departmentStringArray[i] = depa.getName();
				i++;
		}
		
		scrollPaneDepartment.setBounds(0, 0, 179, 98);
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
	
	public void setListener(ListDepartmentListener listener) {
		this.listener = listener;
	}
}
