package turismouydesktop.gui.frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.UserDataManagment;
import turismouydesktop.gui.panels.UserDataManagment.ForWhat;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtUser;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class RegisterUser extends JFrame {

	private JPanel contentPane;
	private UserDataManagment userDataPanel;
	
	private List<DtUser> users;
	

	/**
	 * Create the frame.
	 */
	public RegisterUser() {
		setTitle("Registrar Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 545, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		userDataPanel = new UserDataManagment();
		userDataPanel.enableToEditForRegister();
		userDataPanel.setBounds(0, 45, 531, 300);
		
		contentPane.add(userDataPanel);
		
		JLabel lblUserType = new JLabel("Tipo de Usuario:");
		lblUserType.setBounds(12, 18, 116, 15);
		contentPane.add(lblUserType);
		
		JComboBox comboBoxUserType = new JComboBox();
		comboBoxUserType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxUserType.getSelectedIndex() == 1) {
					userDataPanel.setProviderDataVisibilityFalse();
					userDataPanel.setTouristDataVisibilityTrue();
					
				}else if(comboBoxUserType.getSelectedIndex() == 2) {
					userDataPanel.setTouristDataVisibilityFalse();
					userDataPanel.setProviderDataVisibilityTrue();
					
				}else {
					userDataPanel.setProviderDataVisibilityFalse();
					userDataPanel.setTouristDataVisibilityFalse();
				}
			}
		});
		comboBoxUserType.setModel(new DefaultComboBoxModel(new String[] {"-", "Turista", "Proveedor"}));
		comboBoxUserType.setBounds(136, 13, 128, 24);
		contentPane.add(comboBoxUserType);
		
		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshUsers();
				String message = "";
				String title = "";
				Color color = Color.BLACK;
				DtUser userData;
				try {
					if(comboBoxUserType.getSelectedIndex() == 1) {
						userData = userDataPanel.getTouristData(ForWhat.REGISTER);
						
					}else if(comboBoxUserType.getSelectedIndex() == 2) {
						userData = userDataPanel.getProviderData(ForWhat.REGISTER);
						
					}else {
						throw new IllegalArgumentException("Ingrese un tipo de Usuario");
					}
					
					checkForNull(userData);
					checkForUnique(userData);
					
					IController controller = ControllerFactory.getIController();
					
					controller.registerUser(userData);
					
					title = "Exito";
					message = "Se ha dado de alta al usuario: " + userData.getNickname() + " con exito.";
					color = Color.GREEN;
				} catch (Exception exception) {	
					title = "Error";
					message = exception.getMessage();
					color = Color.RED;
					
					System.err.println("Error en RegisterPanel: " + message);
				}

				PopUpWindow msgWindows = new PopUpWindow(
					title,
					message,
					color);
	
				msgWindows.setVisible(true);
			
			
			}
		});
		btnConfirm.setBounds(205, 357, 117, 25);
		contentPane.add(btnConfirm);
	}
	
	private void refreshUsers() {
		IController controller = ControllerFactory.getIController();
		try {
			users = controller.getListUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void checkForNull(DtUser userData) throws NullPointerException{
		if(userData.getNickname().isBlank()) {
			throw new NullPointerException("El campo 'Nickname' no puede ser vacio");
		}
		if(userData.getEmail().isBlank()) {
			throw new NullPointerException("El campo 'Email' no puede ser vacio");
		}
	}
	
	public void checkForUnique(DtUser userData) {
		users.forEach(user -> {
			if(user.getNickname().equals(userData.getNickname())) {
				throw new IllegalArgumentException("Ese Nickname ya existe. Pruebe otro");
				
			}
			if(user.getEmail().equals(userData.getEmail())) {
				throw new IllegalArgumentException("Ese Email ya esta registrado");
				
			}
		});
	}
}
