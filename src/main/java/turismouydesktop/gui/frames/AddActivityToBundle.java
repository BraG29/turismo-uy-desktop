package turismouydesktop.gui.frames;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.ListDepartment;
import turismouydesktop.gui.panels.ListDepartmentListener;
import turismouydesktop.gui.panels.ListTouristicBundle;
import turismouydesktop.gui.panels.ListTouristicBundleListener;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

	public class AddActivityToBundle extends JFrame implements ListTouristicBundleListener, ListDepartmentListener  {
	private static final long serialVersionUID = -4850562367236178831L;

	private JPanel contentPane;

	private ListTouristicBundle touristicBundleList;
	private ListDepartment departmentsList;
	
	private JLabel lblTouristicBundles;
	private JLabel lblDepartments;
	private JLabel lblBundleActivities;
	private JLabel lblDepartmentActivities;

	private JScrollPane scrollPaneDeptActivities;
	@SuppressWarnings("rawtypes")
	private JList departmentActivities;
	@SuppressWarnings("rawtypes")
	private JList bundleActivities;
	
	private List<DtTouristicActivity> activitiesDepartment; //lista de las actividades del Departamento seleccionado
	private List<DtTouristicActivity> activitiesBundle; //lista de las actividades del Paquete seleccionado
	
	private Long bundleId;
	private Long activityId;
	
	private PopUpWindow msgWindow;


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public AddActivityToBundle() {
		setTitle("Agregar Paquete Turistico");
		
		//llamo al controlador
		IController controller = ControllerFactory.getIController();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//asigno la lista de paquetes
		touristicBundleList = new ListTouristicBundle(true);
		touristicBundleList.setBounds(12, 42, 183, 92);
		touristicBundleList.setListener(this);
		contentPane.add(touristicBundleList);
		
		//añado la lista a un ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 185, 183, 99);
		contentPane.add(scrollPane);
		
		
		bundleActivities = new JList();
		bundleActivities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bundleActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//DtTouristicActivity activity = (DtTouristicActivity) bundleActivities.getSelectedValue();				
				//actBundId = activity.getId();
			}
		});
		scrollPane.setViewportView(bundleActivities);
		
				
		
		lblTouristicBundles = new JLabel("Lista de Paquetes:");
		lblTouristicBundles.setBounds(12, 15, 141, 15);
		contentPane.add(lblTouristicBundles);
		
		lblBundleActivities = new JLabel("Actividades del paquete:");
		lblBundleActivities.setBounds(12, 158, 183, 15);
		contentPane.add(lblBundleActivities);
		
		
		
		
		/*********Department*************/
		departmentsList = new ListDepartment(223, 92);
		departmentsList.setBounds(242, 42, 223, 92);
		
		departmentsList.setListener(this);
		contentPane.add(departmentsList);
		
		
		lblDepartments = new JLabel("Departamentos:");
		lblDepartments.setBounds(253, 15, 123, 15);
		contentPane.add(lblDepartments);
		
		
		lblDepartmentActivities = new JLabel("Actividades del departamento:");
		lblDepartmentActivities.setBounds(242, 158, 223, 15);
		contentPane.add(lblDepartmentActivities);
		
		scrollPaneDeptActivities = new JScrollPane();
		scrollPaneDeptActivities.setBounds(242, 185, 223, 99);
		contentPane.add(scrollPaneDeptActivities);
		
		//creo la lista de Actividades de Departamento
		departmentActivities = new JList();
		departmentActivities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		departmentActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String selectedActivity = (String) departmentActivities.getSelectedValue();
				if(selectedActivity != null) {
					activityId = activitiesDepartment
							.stream()
							.filter(activity -> activity.getName().equals(selectedActivity))
							.findFirst()
							.get()
							.getId();
				}
			}
		});
		scrollPaneDeptActivities.setViewportView(departmentActivities);

		
		JButton btnAdd = new JButton("Añadir");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//porqué diferente?
					if(!checkRepeatedActivity()) {
						throw new IllegalArgumentException("Esa Actividad ya esta en el Paquete");
					}
					
					controller.addTouristicActivityToBundle(bundleId, activityId);
					loadActivities(bundleId);
					msgWindow = new PopUpWindow(
							"Exito", 
							"La Actividad se agrego correctamente", 
							Color.RED);
					
					msgWindow.setVisible(true);
					
				} catch (Exception exception) {
					msgWindow = new PopUpWindow(
							"Error", 
							exception.getMessage(), 
							Color.RED);
					
					msgWindow.setVisible(true);
				}
			}
		});
		
		btnAdd.setBounds(170, 319, 117, 25);
		contentPane.add(btnAdd);
		
		
	}
	
	public Boolean checkRepeatedActivity() {
		for(DtTouristicActivity activity: activitiesBundle) {
			if(activity.getId() == activityId) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void onListTouristicBundle(Long id) {// get the Bundle ID on selection
		
		bundleId = id;
		loadActivities(id);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadActivities(Long id) {
		
		IController ctrl = ControllerFactory.getIController();
		DtTouristicBundle touristicBundle = ctrl.getTouristicBundleData(id);
		
		activitiesBundle = touristicBundle.getActivities();
		
		//mandar nombres de la actividad a la lista.
		
		
		String[] activitiesName = activitiesBundle
				.stream()
                .map(DtTouristicActivity::getName)
                .collect(Collectors.toList())
                .toArray(new String[0]);
			
		bundleActivities.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1167394817266709513L;
			String[] values = activitiesName;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}

	@Override
	public void onListDepartmentSelected(Long id) {
	
	}
	 

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onListDepartmentSelectedDt(DtDepartment department) {
		
		activitiesDepartment = department.getActivities();
		
		String[] activitiesName = activitiesDepartment
				.stream()
                .map(DtTouristicActivity::getName)
                .collect(Collectors.toList())
                .toArray(new String[0]);
		
		departmentActivities.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 7443499441968990204L;
			String[] values = activitiesName;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}

	@Override
	public void reLoadListBundle(DtTouristicBundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onListTouristicBundleDt(DtTouristicBundle dtBundle) {
		// TODO Auto-generated method stub
		
	}

	
	
}
