package turismouydesktop.gui.panels;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RegisterUser extends JFrame {

	private JPanel contentPane;
	private UserDataManagment userDataPanel;
	

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
		setBounds(100, 100, 452, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		userDataPanel = new UserDataManagment();
		userDataPanel.enableToEditForRegister();
		userDataPanel.setBounds(0, 45, 315, 270);
		
		contentPane.add(userDataPanel);
		
		
	}

}
