package turismouydesktop.gui.frames;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import turismouydesktop.gui.panels.ListUser;
import turismouydesktop.gui.panels.ListUserListener;
import turismouydesktop.gui.panels.ShowActivityData;
import turismouydesktop.gui.panels.ShowDepartureData;
import turismouydesktop.gui.panels.UserDataManagment;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtProvider;
import uy.turismo.servidorcentral.logic.datatypes.DtTourist;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicDeparture;
import uy.turismo.servidorcentral.logic.datatypes.DtUser;

public class ConsultUser extends JFrame implements ListUserListener {

	private DtUser userData;
	
	private List<DtTouristicActivity> actvitiesData;
	private List<DtTouristicDeparture> departuresData;
	
	private JPanel contentPane;
	private UserDataManagment userDataPanel;
	private ShowDepartureData departureDataPanel;
	private ListUser listUser;
	
	//Lista de Actividades o Salidas
	private JLabel lblActivityOrDeparture;
	private JList listActivityOrDeparture;
	private JScrollPane scrollPaneList;
	
	private ShowActivityData activityVentana;


	/**
	 * Create the frame.
	 */
	public ConsultUser() {
		setTitle("Consulta Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(100, 100, 870, 322);
		setBounds(100, 100, 870, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		userDataPanel = new UserDataManagment();
		userDataPanel.setBounds(276, 12, 572, 270);
		contentPane.add(userDataPanel);
		
		listActivityOrDeparture = new JList<String>();
		
		scrollPaneList = new JScrollPane(listActivityOrDeparture);
		scrollPaneList.setBounds(320, 12, 240, 201);
		userDataPanel.add(scrollPaneList);
		JButton btnShowActivityOrDepartureData = new JButton("Mostrar Datos");
		btnShowActivityOrDepartureData.setBounds(366, 233, 143, 25);
		userDataPanel.add(btnShowActivityOrDepartureData);
		btnShowActivityOrDepartureData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Guardo el Nombre seleccionado
				String selectedName = (String) listActivityOrDeparture.getSelectedValue();
				//Checkeo que userData no sea null para evitar cualquier problema
				if(userData != null ) {
					//Checkeo si estoy tratando con Actividades o Salidas
					//Si el usuario es un Proveedor se que estoy tratando con actividades
					if(userData instanceof DtProvider) {
						//Checkeo por las dudas
						if(actvitiesData != null) {
							Long id = actvitiesData
									.stream()
									.filter(activity -> activity.getName().equalsIgnoreCase(selectedName))
									.findFirst()
									.get()
									.getId();
							initActivityData(id);
	
						}
					//Si el usuario sea un Turista se que estoy tratando con Salidas
					}else {
						//Checkeo por las dudas
						if(departuresData != null) {
							Long id = departuresData
									.stream()
									.filter(departure -> departure.getName().equalsIgnoreCase(selectedName))
									.findFirst()
									.get()
									.getId();
							initDepartureData(id);
						}
					}
				}
			}
		});
		btnShowActivityOrDepartureData.setEnabled(false);
		btnShowActivityOrDepartureData.setToolTipText("Elija un elemento de la lista");
		
		listActivityOrDeparture.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(listActivityOrDeparture.getSelectedValue() != null) {
					btnShowActivityOrDepartureData.setEnabled(true);
					btnShowActivityOrDepartureData.setToolTipText(null);
				}
			}
		});
		
		listUser = new ListUser();
		listUser.setBounds(0,  0, 264, 282);
		listUser.setListener(this);
		contentPane.add(listUser);
		
		lblActivityOrDeparture = new JLabel("");
		lblActivityOrDeparture.setBounds(594, 12, 240, 15);
		contentPane.add(lblActivityOrDeparture);
		

	}
	
	/**
	 * Señal recibida de ListUser
	 */
	@Override
	public void onSelectUser(Long id) {
		IController controller = ControllerFactory.getIController();
		userData = controller.getUserData(id);
		
		try {
			userDataPanel.loadData(userData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(userData instanceof DtProvider) {
			DtProvider providerData = (DtProvider) userData;
			
			this.lblActivityOrDeparture.setText("Actividades de " + userData.getName() + ":");
			
			this.actvitiesData = providerData.getTouristicActivities();
			
			//Carga de Lista:
			String[] activitiesName = this.actvitiesData.stream()
	                .map(DtTouristicActivity::getName)
	                .collect(Collectors.toList())
	                .toArray(new String[0]);
			
			loadList(activitiesName);
		}else if(userData instanceof DtTourist) {
			
			DtTourist touristData = (DtTourist) userData;			
	
			lblActivityOrDeparture.setText("Inscripciones de " + userData.getName() + ":");
			
			this.departuresData = touristData.getDepartures();
			
			String[] departuresName = departuresData.stream()
	                .map(DtTouristicDeparture::getName)
	                .collect(Collectors.toList())
	                .toArray(new String[0]);
			
			loadList(departuresName);
		}


		
	}
	
	private void loadList(String[] loadData) {
		listActivityOrDeparture.setModel(new AbstractListModel() {
			String[] values = loadData;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});	
	}
	
	private void initActivityData(Long id) {
		//TODO : Expandir ventana para mostrar el panel abajo
		//TODO : Buscar la Actividad en concreto e inicializar el panel de ShowActivityData
		IController controller = ControllerFactory.getIController();
		DtTouristicActivity DTA = controller.getTouristicActivityData(id);
		
		
		if(departureDataPanel != null) {
			departureDataPanel.setVisible(false);
		}
		
		if(activityVentana == null) {
			activityVentana = new ShowActivityData(DTA);
			activityVentana.setBounds(10, 294, 294, 179);
			
			if(departureDataPanel == null) {
				Rectangle contentDimensions = this.getBounds();
				//x,y,widht,height
				contentDimensions.height += 179;
				
				this.setBounds(contentDimensions);
			}
			
		}
		activityVentana.setValues(DTA);
		contentPane.add(activityVentana);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	private void initDepartureData(Long id) {
		IController controller = ControllerFactory.getIController();
		DtTouristicDeparture departureData = controller.getTouristicDepartureData(id);
		//10,294  294 X 179
		
		
		if(activityVentana != null) {
			activityVentana.setVisible(false);
		}
		
		
		if(departureDataPanel == null) {
			departureDataPanel = new ShowDepartureData();
			departureDataPanel.setBounds(10, 294, 294, 179);
			
			if(activityVentana == null) {
				Rectangle contentDimensions = this.getBounds();
				//x,y,widht,height
				contentDimensions.height += 179;
				this.setBounds(contentDimensions);
			}
		}
		departureDataPanel.loadData(departureData);
		contentPane.add(departureDataPanel);
		contentPane.revalidate();
		contentPane.repaint();
	}
}
