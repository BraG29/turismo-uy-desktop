package turismouydesktop.gui.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.ListUser;
import turismouydesktop.gui.panels.ListUserListener;
import turismouydesktop.gui.panels.ShowUserData;
import turismouydesktop.gui.panels.ShowUserDataListener;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtUser;

public class ConsultUser extends JFrame implements ShowUserDataListener, ListUserListener {

	private JPanel contentPane;
	private ShowUserData showUserData;
	private ListUser listUser;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultUser frame = new ConsultUser();
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
	public ConsultUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		showUserData = new ShowUserData();
		showUserData.setListener(this);
		showUserData.setBounds(276, 12, 572, 270);
		contentPane.add(showUserData);
		
		listUser = new ListUser();
		listUser.setBounds(0,  0, 264, 282);
		listUser.setListener(this);
		contentPane.add(listUser);

	}

	@Override
	public void onSelectActivity(Long id) {
		System.out.println("Actividad de id: " + id);
		
	}

	@Override
	public void onSelectDeparture(Long id) {
		System.out.println("Salida de id: " + id);
		
	}

	/**
	 * Señal recibida de ListUser
	 */
	@Override
	public void onSelectUser(Long id) {
		IController controller = ControllerFactory.getIController();
		DtUser userData = controller.getUserData(id);
		
		try {
			showUserData.loadData(userData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
