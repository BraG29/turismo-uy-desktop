package turismouydesktop.gui.panels;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.frames.PopUpWindow;
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUser frame = new RegisterUser();
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
	public RegisterUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		IController controller = ControllerFactory.getIController();
		
		users = controller.getListUser();
		
		userDataPanel = new UserDataManagment();
		userDataPanel.enableToEditForRegister();
		userDataPanel.setBounds(0, 45, 315, 270);
		
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
					
					controller.registerUser(userData);
				} catch (Exception exception) {	
						PopUpWindow msgWindows = new PopUpWindow(
						"Error",
						exception.getMessage(),
						Color.RED);
				
						msgWindows.setVisible(true);
				}
			}
		});
		btnConfirm.setBounds(195, 327, 117, 25);
		contentPane.add(btnConfirm);
	}
	
	private void checkForNull(DtUser userData) throws NullPointerException{
		if(userData.getNickname().isBlank()) {
			throw new NullPointerException("El campo 'Nickname' no puede ser vacio");
		}
		if(userData.getEmail().isBlank()) {
			throw new NullPointerException("El campo 'Email' no puede ser vacio");
		}
		if(userData == null) {
			throw new NullPointerException("Ha habido un error fatal");
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
