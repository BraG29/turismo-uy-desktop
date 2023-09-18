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
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicDeparture;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ConsultDeparture extends JFrame implements ListDepartmentListener{

	private JPanel contentPane;
	private ShowDepartureData departureData;
	
	private ListDepartment departmentsList; //panel
	private JList listDepartmentsActivities;
	private List<DtTouristicActivity> activities;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultDeparture frame = new ConsultDeparture();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		departureData = new ShowDepartureData();
		departureData.setBounds(268, 28, 300, 224);
		contentPane.add(departureData);
		
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
				
				Long activityId = activities.stream().filter(departure -> departure.getName().equals(selectedActivity))
						.findFirst()
						.get()
						.getId();
				
				IController ctrl = ControllerFactory.getIController();
				DtTouristicDeparture departure = ctrl.getTouristicDepartureData(activityId);
				departureData.loadData(departure);
				
			}
		});
		scrollPaneActDep.setViewportView(listDepartmentsActivities);
		
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
