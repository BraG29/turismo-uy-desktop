package turismouydesktop.gui.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.ListDepartment;
import turismouydesktop.gui.panels.ListDepartmentListener;
import turismouydesktop.gui.panels.ShowDepartureData;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicDeparture;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JComboBox;

public class ConsultDeparture extends JFrame implements ListDepartmentListener{

	private JPanel contentPane;
	private ShowDepartureData departureDataPanel;
	
	private ListDepartment departmentsList; //panel
	private JList listDepartmentsActivities;
	
	private List<DtTouristicActivity> activities;
	private List<DtTouristicDeparture> departures;
	
	private JComboBox comboBoxDepartures;
	
	private String[] emptySet = {"-"};
	
	

	/**
	 * Create the frame.
	 */
	public ConsultDeparture() {
		setTitle("Consulta Salida Turistica");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 626, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		departureDataPanel = new ShowDepartureData();
		departureDataPanel.setBounds(264, 69, 300, 224);
		contentPane.add(departureDataPanel);
		
		departmentsList = new ListDepartment();
		departmentsList.setBounds(12, 45, 182, 113);
		contentPane.add(departmentsList);
		departmentsList.setListener(this);
		
		
		JLabel lblDepartamentos = new JLabel("Departamentos:");
		lblDepartamentos.setBounds(12, 12, 117, 15);
		contentPane.add(lblDepartamentos);
		
		JLabel lblActividadesDeDepartamento = new JLabel("Actividades de departamento:");
		lblActividadesDeDepartamento.setBounds(12, 170, 221, 15);
		contentPane.add(lblActividadesDeDepartamento);
		
		JScrollPane scrollPaneActDep = new JScrollPane();
		scrollPaneActDep.setBounds(12, 197, 208, 99);
		contentPane.add(scrollPaneActDep);
		
		listDepartmentsActivities = new JList();
		listDepartmentsActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
							
				String selectedActivity = (String) listDepartmentsActivities.getSelectedValue();
				
				Long activityId = activities
						.stream()
						.filter(activity -> activity.getName().equals(selectedActivity))
						.findFirst()
						.get()
						.getId();
				
				IController ctrl = ControllerFactory.getIController();
				departures = ctrl.getListTouristicDeparture(activityId);
				loadDepartures();
				
			}
		});
		scrollPaneActDep.setViewportView(listDepartmentsActivities);
		
		comboBoxDepartures = new JComboBox();
		comboBoxDepartures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedDeparture = (String) comboBoxDepartures.getSelectedItem();
				
				if(comboBoxDepartures.getSelectedIndex() != 0) {
					Long departureId = departures
							.stream()
							.filter(deparutre -> deparutre.getName().equals(selectedDeparture))
							.findFirst()
							.get()
							.getId();
					
					IController controller = ControllerFactory.getIController();
					
					DtTouristicDeparture departureData = controller.getTouristicDepartureData(departureId);
					
					departureDataPanel.loadData(departureData);
				}
			}
		});
		comboBoxDepartures.setBounds(343, 23, 215, 24);
		comboBoxDepartures.setModel(new DefaultComboBoxModel(emptySet));
		contentPane.add(comboBoxDepartures);
		
	}
	
	public void loadDepartures() {
		
		String[] departuresString = departures.stream()
                .map(DtTouristicDeparture::getName)
                .collect(Collectors.toList())
                .toArray(new String[0]);
		
		Integer totalLength = emptySet.length + departuresString.length;
		
		String[] concatenetedArray = new String[totalLength];
		System.arraycopy(emptySet, 0, concatenetedArray, 0, emptySet.length);
		System.arraycopy(departuresString, 0, concatenetedArray, emptySet.length, departuresString.length);
		
		comboBoxDepartures.setModel(new DefaultComboBoxModel(concatenetedArray));
	}

	@Override
	public void onListDepartmentSelected(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onListDepartmentSelectedDt(DtDepartment department) {
		
		activities = department.getActivities();

		if(!department.getActivities().isEmpty()) {
			
			//obtengo nombre de las actividades del departamento.
			String [] departmentsActivities = department
					.getActivities()
					.stream()
					.map(DtTouristicActivity::getName)
					.collect(Collectors.toList())
					.toArray(new String[0]);
			
			
			listDepartmentsActivities.setModel(new AbstractListModel() {
				String[] values = departmentsActivities;

				public int getSize() {
					return values.length;
				}

				public Object getElementAt(int index) {
					return values[index];
				}
			});
		}
		
	}
}
