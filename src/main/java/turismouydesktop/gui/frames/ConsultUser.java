package turismouydesktop.gui.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.ShowUserData;
import turismouydesktop.gui.panels.ShowUserDataListener;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtUser;

public class ConsultUser extends JFrame implements ShowUserDataListener {

	private JPanel contentPane;
	private ShowUserData showUserData;


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
		setBounds(100, 100, 655, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		IController controller = ControllerFactory.getIController();
		
		showUserData = new ShowUserData();
		showUserData.setListener(this);
		showUserData.setBounds(12, 12, 621, 270);
		contentPane.add(showUserData);
		
		DtUser userData = controller.getUserData(11L);
		
		
		try {
			showUserData.loadData(userData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void onSelectActivity(Long id) {
		System.out.println("Actividad de id: " + id);
		
	}

	@Override
	public void onSelectDeparture(Long id) {
		System.out.println("Salida de id: " + id);
		
	}

}
