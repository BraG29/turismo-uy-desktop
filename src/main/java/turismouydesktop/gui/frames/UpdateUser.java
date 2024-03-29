package turismouydesktop.gui.frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.ListUser;
import turismouydesktop.gui.panels.ListUserListener;
import turismouydesktop.gui.panels.UserDataManagment;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtUser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateUser extends JFrame implements ListUserListener {

	DtUser userData;
	
	JButton btnConfirm; 
	
	private JPanel contentPane;
	private ListUser userListPanel;
	private UserDataManagment userDataPanel;

	/**
	 * Create the frame.
	 */
	public UpdateUser() {
		setTitle("Modificar usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 816, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		userListPanel = new ListUser();
		userListPanel.setListener(this);
		userListPanel.setBounds(0, 0, 264, 282);
		
		contentPane.add(userListPanel);
		
		userDataPanel = new UserDataManagment();
		userDataPanel.setBounds(276, 0, 528, 271);
		userDataPanel.enableToEditForUpdate();
		
		contentPane.add(userDataPanel);
		
		btnConfirm = new JButton("Confirmar Cambios");
		btnConfirm.setEnabled(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DtUser userData = userDataPanel.getModifiedData();
				
				if(userData != null) {
					IController controller = ControllerFactory.getIController();
					controller.updateUser(userData);
					PopUpWindow msgWindow = new PopUpWindow(
							"Exito", 
							"Se Actualizaron los Cambios", 
							Color.GREEN);
					msgWindow.setVisible(true);
				}else {
					PopUpWindow msgWindow = new PopUpWindow(
							"Error", 
							"No se ha modificado ningun campo", 
							Color.RED);
					msgWindow.setVisible(true);
				}
			}
		});
		btnConfirm.setBounds(422, 281, 175, 25);
		contentPane.add(btnConfirm);
	}

	@Override
	public void onSelectUser(Long id) {
		
		btnConfirm.setEnabled(true);
		
		IController controller = ControllerFactory.getIController();
		try {
			userData = controller.getUserData(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			userDataPanel.loadData(userData);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
