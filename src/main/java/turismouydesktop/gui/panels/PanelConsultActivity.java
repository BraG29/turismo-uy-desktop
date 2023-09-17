package turismouydesktop.gui.panels;

import javax.swing.JPanel;

import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class PanelConsultActivity extends JPanel implements ListDepartmentListener {
	private ListDepartment jListDepartment;
	private ShowActivityData showActivityVentana = null;
	private List<DtTouristicActivity> actividades = null;
	private JList listActivities = new JList();
	
	//atributos visuales
	private JScrollPane scrollPaneActivities = new JScrollPane();
	private final JLabel lblActividadAElegir = new JLabel("Actividad a elegir:");
	
	
	/*
	 * Create the panel.
	 */
	public PanelConsultActivity() {
		setLayout(null);
		jListDepartment = new ListDepartment(140,200);
		jListDepartment.setBounds(0, 42, 179, 200);
		add(jListDepartment);
		jListDepartment.setListener(this);
		
		JLabel lblSeleccioneDepartamento = new JLabel("Departamento a elegir:");
		lblSeleccioneDepartamento.setBounds(0, 26, 200, 15);
		add(lblSeleccioneDepartamento);

		scrollPaneActivities.setBounds(230, 42, 140, 200);
		add(scrollPaneActivities);
		scrollPaneActivities.setViewportView(listActivities);
		scrollPaneActivities.setVisible(false);
		
		lblActividadAElegir.setBounds(230, 26, 140, 15);
		add(lblActividadAElegir);
		lblActividadAElegir.setVisible(false);
		
		listActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				IController controller = ControllerFactory.getIController();
				showActivityVentana = new ShowActivityData(controller.getTouristicActivityData(actividades.get(listActivities.getSelectedIndex()).getId()));
				add(showActivityVentana);
				showActivityVentana.setBounds(100, 200, 140, 200);
				showActivityVentana.setVisible(true);
				
				reDimensionWindow(180);
			}
		});
	
		
	}

	@Override
	public void onListDepartmentSelected(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onListDepartmentSelectedDt(DtDepartment department) {
		
		actividades = department.getActivities();
		
		//creo los Array de String.
		String[] activitiesStringArray = new String[actividades.size()];
							
		//itero sobre el DT para darles valor a las array de String
		int i = 0;
		for(DtTouristicActivity acti : actividades) {
				activitiesStringArray[i] = acti.getName();
				i++;
		}
		loadList(activitiesStringArray);
		
		scrollPaneActivities.setVisible(true);
		listActivities.setVisible(true);
		lblActividadAElegir.setVisible(true);
	}
	
	private void loadList(String[] loadData) {
		
		listActivities.setModel(new AbstractListModel() {
			String[] values = loadData;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listActivities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listActivities.setFont(new Font("Dialog", Font.PLAIN, 12));
	}
	
	private void reDimensionWindow(int height) {
		
		Rectangle contentDimensions = this.getBounds();
		//x,y,widht,height
		contentDimensions.height += 500;
		contentDimensions.width += 500;
		
		this.setBounds(contentDimensions);
	}
}
