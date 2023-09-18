package turismouydesktop.gui.frames;

import java.awt.Color;
import java.awt.EventQueue;
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

public class AddActivityToBundle extends JFrame implements ListTouristicBundleListener, ListDepartmentListener  {

	private JPanel contentPane;

	
	private ListTouristicBundle touristicBundleList;
	private ListDepartment departmentsList;
	
	
	private JLabel lblTouristicBundles;
	private JLabel lblDepartments;
	private JLabel lblBundleActivities;
	private JLabel lblDepartmentActivities;

	
	private JScrollPane scrollPaneDeptActivities;
	private JList departmentActivities;
	private JList bundleActivities; //jlist 
	private List<DtTouristicActivity> activities; //lista de las actividades
	
	private Long actBundId;
	private Long actDeptId;
	
	
	private PopUpWindow window;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddActivityToBundle frame = new AddActivityToBundle();
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
	public AddActivityToBundle() {
		
		IController controller = ControllerFactory.getIController();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		touristicBundleList = new ListTouristicBundle();
		touristicBundleList.setBounds(12, 42, 183, 92);
		touristicBundleList.setListener(this);
		contentPane.add(touristicBundleList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 185, 183, 99);
		contentPane.add(scrollPane);
		
		bundleActivities = new JList();
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
		
		
		departmentsList = new ListDepartment();
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
		
		departmentActivities = new JList();
		departmentActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//obtengo id
				//DtTouristicActivity activity = (DtTouristicActivity) departmentActivities.getSelectedValue();				
				//actDeptId = activity.getId();
				
				
				
			}
		});
		scrollPaneDeptActivities.setViewportView(departmentActivities);

		
		JButton btnAdd = new JButton("Añadir");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//metodo para añadir actividad a paquete.
					
					controller.addTouristicActivityToBundle(actBundId, actDeptId);
					window = new PopUpWindow("Éxito", "La actividad fue añadida correctamente.", Color.RED);
					window.setVisible(true);
					
				}catch(Exception e1) {
					
					window = new PopUpWindow("ERROR", "No se ha podido agregar la actividad", Color.RED);
					window.setVisible(true);
				}
			}
		});
		btnAdd.setBounds(170, 319, 117, 25);
		contentPane.add(btnAdd);
		
		
	}

	@Override
	public void onListTouristicBundle(Long id) {
		IController ctrl = ControllerFactory.getIController();
		DtTouristicBundle touristicBundle = ctrl.getTouristicBundleData(id);
		
		List<DtTouristicActivity> activities = touristicBundle.getActivities();
		//mandar nombres de la actividad a la lista.
		
		String[] activitiesName = activities.stream()
                .map(DtTouristicActivity::getName)
                .collect(Collectors.toList())
                .toArray(new String[0]);
			
		bundleActivities.setModel(new AbstractListModel() {
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

	@Override
	public void onListDepartmentSelectedDt(DtDepartment department) {
		
		
	}

	
	
}
