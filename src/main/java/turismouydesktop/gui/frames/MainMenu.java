package turismouydesktop.gui.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JLocaleChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import turismouydesktop.gui.frames.ConsultUser;
import turismouydesktop.gui.frames.RegisterTouristicActivity;
import turismouydesktop.gui.frames.ConsultActivity;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setTitle("Turismo UY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegisterUser = new JButton("Registrar Usuario");
		btnRegisterUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUser registerUserGUI = new RegisterUser();
				registerUserGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnRegisterUser.setBounds(22, 32, 182, 25);
		contentPane.add(btnRegisterUser);
		
		JButton btnConsultUser = new JButton("Consultar Usuario");
		btnConsultUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultUser consultUserGUI = new ConsultUser();
				consultUserGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnConsultUser.setBounds(22, 69, 182, 25);
		contentPane.add(btnConsultUser);
		
		JButton btnUpdateUser = new JButton("Modificar Usuario");
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateUser updateUserGUI = new UpdateUser();
				updateUserGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnUpdateUser.setBounds(22, 106, 182, 25);
		contentPane.add(btnUpdateUser);
		
		JLabel lblNewLabel = new JLabel("Usuarios:");
		lblNewLabel.setBounds(12, 5, 70, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Paquetes Turisticos:");
		lblNewLabel_1.setBounds(12, 143, 172, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnRegisterTuristicBundle = new JButton("Crear Paquete");
		btnRegisterTuristicBundle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateBundle createBundleGUI = new CreateBundle();
				createBundleGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnRegisterTuristicBundle.setBounds(22, 170, 182, 25);
		contentPane.add(btnRegisterTuristicBundle);
		
		JButton btnAddActivityToBundle = new JButton("Agregar a Paquete ");
		btnAddActivityToBundle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddActivityToBundle addActivityTobundleGUI = new AddActivityToBundle();
				addActivityTobundleGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnAddActivityToBundle.setBounds(22, 207, 182, 25);
		contentPane.add(btnAddActivityToBundle);
		
		JButton btnConsultBundle = new JButton("Consultar Paquete");
		btnConsultBundle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultBundle consultBundleGUI = new ConsultBundle();
				consultBundleGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnConsultBundle.setBounds(22, 244, 182, 25);
		contentPane.add(btnConsultBundle);
		
		JLabel lblNewLabel_2 = new JLabel("Actividades Turisticas:");
		lblNewLabel_2.setBounds(222, 5, 182, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton btnRegisterTouristicActivity = new JButton("Alta Actividad ");
		btnRegisterTouristicActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterTouristicActivity registerActivityGUI = new RegisterTouristicActivity();
				registerActivityGUI.setVisible(true);
			}
		});
		
		btnRegisterTouristicActivity.setBounds(239, 32, 210, 25);
		contentPane.add(btnRegisterTouristicActivity);
		
		JButton btnConsultTouristicActivity = new JButton("Consulta Actividad");
		btnConsultTouristicActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultActivity consultActivityGUI = new ConsultActivity();
				consultActivityGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnConsultTouristicActivity.setBounds(239, 69, 210, 25);
		contentPane.add(btnConsultTouristicActivity);
		
		JButton btnCreateTouristicDeparture = new JButton("Alta Salida Turistica");
		btnCreateTouristicDeparture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDeparture createDepartureGUI = new CreateDeparture();
				createDepartureGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnCreateTouristicDeparture.setBounds(239, 170, 210, 25);
		contentPane.add(btnCreateTouristicDeparture);
		
		JButton btnConsultTouristicDeparture = new JButton("Consulta Salida Turistica");
		btnConsultTouristicDeparture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultDeparture consultDepartureGUI = new ConsultDeparture();
				consultDepartureGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnConsultTouristicDeparture.setBounds(239, 207, 210, 25);
		contentPane.add(btnConsultTouristicDeparture);
		
		JLabel lblNewLabel_3 = new JLabel("Salida Turistica:");
		lblNewLabel_3.setBounds(222, 140, 158, 20);
		contentPane.add(lblNewLabel_3);
		
		JButton btnRegisterTouristicDeparture = new JButton("Inscripcion a Salida ");
		btnRegisterTouristicDeparture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterInscription registerInscriptionGUI = new RegisterInscription();
				registerInscriptionGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnRegisterTouristicDeparture.setBounds(239, 244, 210, 25);
		contentPane.add(btnRegisterTouristicDeparture);
		
		JLabel lblNewLabel_4 = new JLabel("Departamentos:");
		lblNewLabel_4.setBounds(12, 281, 172, 15);
		contentPane.add(lblNewLabel_4);
		
		JButton btnRegisterDepartment = new JButton("Declarar independencia de nuevo departamento");
		btnRegisterDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterDepartment registerDepartmentGUI = new RegisterDepartment();
				registerDepartmentGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnRegisterDepartment.setBounds(22, 302, 427, 25);
		contentPane.add(btnRegisterDepartment);
	}
}
