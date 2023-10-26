package turismouydesktop.gui.frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.ListDepartment;
import turismouydesktop.gui.panels.ListDepartmentListener;
import turismouydesktop.gui.panels.ListTourist;
import turismouydesktop.gui.panels.ListTouristListener;
import turismouydesktop.gui.panels.ShowDepartureData;
import turismouydesktop.gui.panels.UserDataManagment;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtInscription;
import uy.turismo.servidorcentral.logic.datatypes.DtTourist;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicDeparture;
import uy.turismo.servidorcentral.logic.datatypes.DtUser;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegisterInscription extends JFrame implements ListTouristListener, ListDepartmentListener {

	private JPanel contentPane;
	private JList listDepartures;
	private ListTourist listTouristsPane;
	private ListDepartment listDepartmentPane;
	
	private ShowDepartureData departureDataPane;
	private UserDataManagment userDataPanel;
	
	private String[] emptyArray = {"-"};
	
	private JComboBox comboBoxActivities;
	private JComboBox comboBoxDepartures;
	
	private List<DtTouristicActivity> activitiesData;
	private List<DtTouristicDeparture> departuresData;
	
	private DtTouristicDeparture singleDeparture;
	private DtUser singleUser;
	
	private JTextField textFieldMaxTourist;
	


	/**
	 * Create the frame.
	 */
	public RegisterInscription() {
		setTitle("Registrar Inscripcion a Salida");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 716, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		listDepartmentPane = new ListDepartment(208, 177);
		listDepartmentPane.setBounds(0, 23, 208, 177);
		listDepartmentPane.setListener(this);
		getContentPane().add(listDepartmentPane);
		
		comboBoxActivities = new JComboBox();
		comboBoxActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxActivities.getSelectedIndex() != 0) {
					String selectedActivity = (String) comboBoxActivities.getSelectedItem();
					
					Long activityId = activitiesData
							.stream()
							.filter(activity -> activity.getName().equals(selectedActivity))
							.findFirst()
							.get()
							.getId();
					
					IController controller = ControllerFactory.getIController();
					departuresData = controller.getListTouristicDeparture(activityId);
					
					if(!departuresData.isEmpty()) {
						String[] departureString = departuresData
								.stream()
								.map(DtTouristicDeparture::getName)
								.collect(Collectors.toList())
								.toArray(new String[0]);

						Integer totalLength = emptyArray.length + departureString.length;
						
						String[] concatenetedArray = new String[totalLength];
						System.arraycopy(emptyArray, 0, concatenetedArray, 0, emptyArray.length);
						System.arraycopy(departureString, 0, concatenetedArray, emptyArray.length, departureString.length);
						
						comboBoxDepartures.setModel(new DefaultComboBoxModel(concatenetedArray));
						comboBoxDepartures.setEnabled(true);
						
					}else {
						comboBoxDepartures.setModel(new DefaultComboBoxModel(emptyArray));
						comboBoxDepartures.setEnabled(false);
					}
					
				}
			}
		});
		comboBoxActivities.setModel(new DefaultComboBoxModel(emptyArray));
		comboBoxActivities.setEnabled(false);
		comboBoxActivities.setBounds(220, 54, 172, 24);
		contentPane.add(comboBoxActivities);
		
		JLabel lblSelectDepartment = new JLabel("Seleccione un Departamento");
		lblSelectDepartment.setBounds(0, 0, 214, 15);
		contentPane.add(lblSelectDepartment);
		
		JLabel lblSelectActivity = new JLabel("Seleccione una Atividad");
		lblSelectActivity.setBounds(226, 23, 172, 15);
		contentPane.add(lblSelectActivity);
		
		comboBoxDepartures = new JComboBox();
		comboBoxDepartures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxDepartures.getSelectedIndex() != 0) {
					String selectedDeparture = (String) comboBoxDepartures.getSelectedItem();
					
					Long departureId = departuresData
							.stream()
							.filter(departure -> departure.getName().equals(selectedDeparture))
							.findFirst()
							.get()
							.getId();
					
					IController controller = ControllerFactory.getIController();
					
					singleDeparture = controller.getTouristicDepartureData(departureId);
					
					departureDataPane.loadData(singleDeparture);
				}
			}
		});
		comboBoxDepartures.setModel(new DefaultComboBoxModel(emptyArray));
		comboBoxDepartures.setEnabled(false);
		comboBoxDepartures.setBounds(220, 141, 172, 24);
		contentPane.add(comboBoxDepartures);
		
		JLabel lblSeleccioneUnaSalida = new JLabel("Seleccione una Salida");
		lblSeleccioneUnaSalida.setBounds(226, 111, 166, 15);
		contentPane.add(lblSeleccioneUnaSalida);
		
		departureDataPane = new ShowDepartureData();
		departureDataPane.setBounds(416,23,287,185);
		contentPane.add(departureDataPane);
		
		listTouristsPane = new ListTourist();
		listTouristsPane.setListener(this);
		listTouristsPane.setBounds(0, 240, 259, 282);
		contentPane.add(listTouristsPane);
		
		userDataPanel = new UserDataManagment();
		userDataPanel.setBounds(271, 252, 304, 177);
		contentPane.add(userDataPanel);
		
		JLabel lblSelectTourist = new JLabel("Seleccione un Turista");
		lblSelectTourist.setBounds(0, 212, 160, 15);
		contentPane.add(lblSelectTourist);
		
		JLabel lblTouristData = new JLabel("Datos de Turista");
		lblTouristData.setBounds(271, 225, 121, 15);
		contentPane.add(lblTouristData);
		
		JLabel lblDepartureData = new JLabel("Datos de la Salida");
		lblDepartureData.setBounds(416, 0, 137, 15);
		contentPane.add(lblDepartureData);
		
		JLabel lblIngreseDatosDe = new JLabel("Ingrese Datos de Inscripción");
		lblIngreseDatosDe.setBounds(345, 441, 208, 15);
		contentPane.add(lblIngreseDatosDe);
		
		JDateChooser dateChooserInscriptionDate = new JDateChooser();
		dateChooserInscriptionDate.setBounds(345, 468, 113, 19);
		contentPane.add(dateChooserInscriptionDate);
		
		textFieldMaxTourist = new JTextField();
		textFieldMaxTourist.setBounds(384, 503, 45, 19);
		contentPane.add(textFieldMaxTourist);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(271, 468, 70, 15);
		contentPane.add(lblFecha);
		
		JLabel lblCantTuristas = new JLabel("Cant. Turistas:");
		lblCantTuristas.setBounds(268, 505, 113, 15);
		contentPane.add(lblCantTuristas);
		
		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				PopUpWindow msgWindow;
				
				try {
					if(singleDeparture == null) {
						throw new NullPointerException("Debe ingresar una Salida");
					}
					if(singleUser == null) {
						throw new NullPointerException("Debe ingresar un Turista");
						
					}
					if(dateChooserInscriptionDate.getDate() == null) {
						throw new IllegalArgumentException("Debe ingresar una Fecha");
						
					}
					
					Integer toursitAmount = Integer.parseInt(textFieldMaxTourist.getText());
					
					if(toursitAmount < 1 || toursitAmount > 99) {
						throw new IllegalArgumentException("Minimo un turista, maximo 99");
						
					}
					if(!checkForRepetedInscription()) {
						throw new IllegalArgumentException("Ese Turista ya esta inscripto Salida");
					}
					if(!checkForMaxTourist(toursitAmount)) {
						throw new IllegalArgumentException("Maximo de turistas alcanzado para esa Salida");
						
					}
					

					LocalDate inscriptionDate = dateChooserInscriptionDate.getDate()
							.toInstant()
							.atZone(ZoneId.systemDefault())
							.toLocalDate();
					
					
					DtInscription inscriptionData = new DtInscription(
							null,
							inscriptionDate,
							null,
							toursitAmount,
							(DtTourist) singleUser,
							singleDeparture
							);
					
					IController controller = ControllerFactory.getIController();
					controller.registerInscription(inscriptionData);

					msgWindow = new PopUpWindow(
							"Inscripcion Hecha", 
							"Se inscribio al turista " + singleUser.getNickname() 
							+ " a la salida " + singleDeparture.getName() + "<p>", 
							Color.GREEN);
					
					msgWindow.setVisible(true);
					
					
				} catch (Exception exception) {
					String message = exception.getMessage();
					
					if(exception instanceof NumberFormatException) {
						message = "Cantidad de Turistas invalido";
					}
					
					msgWindow = new PopUpWindow(
							"Error", 
							message, 
							Color.RED);
					
					msgWindow.setVisible(true);
				}
				
				
			}
		});
		btnConfirm.setBounds(577, 500, 117, 25);
		contentPane.add(btnConfirm);
	
	}

	@Override
	public void onListDepartmentSelected(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onListDepartmentSelectedDt(DtDepartment department) {
		
		activitiesData = department.getActivities();
		
		if(!activitiesData.isEmpty()) {
			String[] activitiesString = activitiesData
					.stream()
					.map(DtTouristicActivity::getName)
					.collect(Collectors.toList())
					.toArray(new String[0]);
			
			Integer totalLength = emptyArray.length + activitiesString.length;
			
			String[] concatenetedArray = new String[totalLength];
			System.arraycopy(emptyArray, 0, concatenetedArray, 0, emptyArray.length);
			System.arraycopy(activitiesString, 0, concatenetedArray, emptyArray.length, activitiesString.length);
			
			comboBoxActivities.setModel(new DefaultComboBoxModel(concatenetedArray));
			comboBoxActivities.setEnabled(true);
		}else {
			comboBoxActivities.setModel(new DefaultComboBoxModel(emptyArray));
			comboBoxActivities.setEnabled(false);
		}
		
		
	}

	@Override
	public void onListTouristSelected(Long id) {
		IController controller = ControllerFactory.getIController();
		
		try {
			singleUser = controller.getUserData(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			userDataPanel.loadData(singleUser);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public Boolean checkForRepetedInscription() {
		for(DtTourist tourist : singleDeparture.getTourists()) {
			if(tourist.getId() == singleUser.getId()){
				return false;
			}
		}
		
		return true;
	}
	
	public Boolean checkForMaxTourist(Integer touristsAmmount) {
		if(singleDeparture.getTourists().size() >= singleDeparture.getMaxTourist() ||
				touristsAmmount >= singleDeparture.getMaxTourist()) {
			return false;
		}
		
		return true;
	}
}
