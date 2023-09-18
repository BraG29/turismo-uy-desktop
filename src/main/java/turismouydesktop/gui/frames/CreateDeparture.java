package turismouydesktop.gui.frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.ListDepartment;
import turismouydesktop.gui.panels.ListDepartmentListener;
import turismouydesktop.gui.panels.InsertDepartureData;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtTourist;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicDeparture;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class CreateDeparture extends JFrame implements ListDepartmentListener {

	private JPanel contentPane;
	private InsertDepartureData departureData;
	private ListDepartment departmentsList;
	private JLabel lblDepartmentActivities;
	private JList listDepartmentActivities;
	private PopUpWindow window;
	private DtTouristicActivity activity;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateDeparture frame = new CreateDeparture();
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
	public CreateDeparture() {
		
		IController ctrl= ControllerFactory.getIController();

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPaneActivities = new JScrollPane();
		scrollPaneActivities.setBounds(12, 44, 182, 88);
		contentPane.add(scrollPaneActivities);
		
		departmentsList = new ListDepartment();
		scrollPaneActivities.setViewportView(departmentsList);
		departmentsList.setListener(this);
		
		lblDepartmentActivities = new JLabel("Actividades del departamento:");
		lblDepartmentActivities.setBounds(12, 144, 231, 15);
		contentPane.add(lblDepartmentActivities);

		departureData = new InsertDepartureData();
		departureData.setBounds(246, 12, 439, 253);
		contentPane.add(departureData);
		
		
		JLabel lblDepartamentos = new JLabel("Departamentos:");
		lblDepartamentos.setBounds(12, 12, 164, 15);
		contentPane.add(lblDepartamentos);
		
		
		JScrollPane scrollPaneDeptActivities = new JScrollPane();
		scrollPaneDeptActivities.setBounds(12, 171, 182, 119);
		contentPane.add(scrollPaneDeptActivities);	
		listDepartmentActivities = new JList();
		listDepartmentActivities.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//actividad a la que le creo la salida
				activity = (DtTouristicActivity) listDepartmentActivities.getSelectedValue();								
				
			}
		});
		scrollPaneDeptActivities.setViewportView(listDepartmentActivities);
		
		
		
		
		JButton btnCreate = new JButton("Crear");
		btnCreate.setBounds(420, 277, 117, 25);
		contentPane.add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String name = departureData.getName();
					String place = departureData.getPlace();
					Integer maxTourists = departureData.getMaxTourists();
					LocalDate creationDate = departureData.getCreationDate();
					
					LocalDate day = departureData.getMeetingDate();
					LocalTime hour = departureData.getSpinnerSchedule();
					
					//armar local datetime de reunion.
					LocalDateTime meetingSchedule = day.atTime(hour);
					
					DtTouristicDeparture departure = new DtTouristicDeparture(null, name, maxTourists, creationDate, meetingSchedule, place, activity, null);
					
					ctrl.registerTouristicDeparture(departure);
					
					window = new PopUpWindow("Éxito", "La salida fue dada de alta con éxito.", Color.GREEN);
					window.setVisible(true);
					
				}catch (Exception e1) {
					window = new PopUpWindow("Campo/s vacío/s", "Por favor rellene los campos vacíos", Color.RED);
					window.setVisible(true);
				}			
			}
		});
		
	}

	
	
	
	@Override
	public void onListDepartmentSelected(Long id) {
		IController ctrl = ControllerFactory.getIController();
		List<DtDepartment> department = ctrl.getListDepartment(true);
		
		//busco el departamento q selecciono.
		for (int i = 0; i < department.size(); i++) {
			if (department.get(i).getId() == id) {
				List<DtTouristicActivity> activities = department.get(i).getActivities();
				
				String[] activitiesName = activities.stream()
		                .map(DtTouristicActivity::getName)
		                .collect(Collectors.toList())
		                .toArray(new String[0]);
					
				listDepartmentActivities.setModel(new AbstractListModel() {
					String[] values = activitiesName;

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

	@Override
	public void onListDepartmentSelectedDt(DtDepartment department) {
		// TODO Auto-generated method stub
		
	}
}
