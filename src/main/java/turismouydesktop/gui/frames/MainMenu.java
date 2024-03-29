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
import javax.swing.SwingConstants;

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
		setBounds(100, 100, 492, 375);
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
		
		btnRegisterUser.setBounds(22, 32, 200, 25);
		contentPane.add(btnRegisterUser);
		
		JButton btnConsultUser = new JButton("Consultar Usuario");
		btnConsultUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultUser consultUserGUI = new ConsultUser();
				consultUserGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnConsultUser.setBounds(22, 69, 200, 25);
		contentPane.add(btnConsultUser);
		
		JButton btnUpdateUser = new JButton("Modificar Usuario");
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateUser updateUserGUI = new UpdateUser();
				updateUserGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnUpdateUser.setBounds(22, 106, 200, 25);
		contentPane.add(btnUpdateUser);
		
		JLabel lblNewLabel = new JLabel("Usuarios:");
		lblNewLabel.setBounds(22, 5, 70, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Paquetes Turisticos:");
		lblNewLabel_1.setBounds(22, 137, 172, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnRegisterTuristicBundle = new JButton("Crear Paquete");
		btnRegisterTuristicBundle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateBundle createBundleGUI = new CreateBundle();
				createBundleGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnRegisterTuristicBundle.setBounds(22, 160, 200, 25);
		contentPane.add(btnRegisterTuristicBundle);
		
		JButton btnAddActivityToBundle = new JButton("Agregar a Paquete ");
		btnAddActivityToBundle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddActivityToBundle addActivityTobundleGUI = new AddActivityToBundle();
				addActivityTobundleGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnAddActivityToBundle.setBounds(22, 197, 200, 25);
		contentPane.add(btnAddActivityToBundle);
		
		JButton btnConsultBundle = new JButton("Consultar Paquete");
		btnConsultBundle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultBundle consultBundleGUI = new ConsultBundle();
				consultBundleGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnConsultBundle.setBounds(22, 234, 200, 25);
		contentPane.add(btnConsultBundle);
		
		JLabel lblNewLabel_2 = new JLabel("Actividades/Salidas Turisticas:");
		lblNewLabel_2.setBounds(246, 5, 232, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton btnRegisterTouristicActivity = new JButton("Alta Actividad ");
		btnRegisterTouristicActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterTouristicActivity registerActivityGUI = new RegisterTouristicActivity();
				registerActivityGUI.setVisible(true);
			}
		});
		
		btnRegisterTouristicActivity.setBounds(246, 32, 232, 25);
		contentPane.add(btnRegisterTouristicActivity);
		
		JButton btnConsultTouristicActivity = new JButton("Consulta Actividad");
		btnConsultTouristicActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultActivity consultActivityGUI = new ConsultActivity();
				consultActivityGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnConsultTouristicActivity.setBounds(246, 69, 232, 25);
		contentPane.add(btnConsultTouristicActivity);
		
		JButton btnCreateTouristicDeparture = new JButton("Alta Salida Turistica");
		btnCreateTouristicDeparture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDeparture createDepartureGUI = new CreateDeparture();
				createDepartureGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnCreateTouristicDeparture.setBounds(246, 180, 232, 25);
		contentPane.add(btnCreateTouristicDeparture);
		
		JButton btnConsultTouristicDeparture = new JButton("Consulta Salida Turistica");
		btnConsultTouristicDeparture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultDeparture consultDepartureGUI = new ConsultDeparture();
				consultDepartureGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnConsultTouristicDeparture.setBounds(246, 217, 232, 25);
		contentPane.add(btnConsultTouristicDeparture);
		
		JButton btnRegisterTouristicDeparture = new JButton("Inscripcion a Salida ");
		btnRegisterTouristicDeparture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterInscription registerInscriptionGUI = new RegisterInscription();
				registerInscriptionGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnRegisterTouristicDeparture.setBounds(246, 254, 232, 25);
		contentPane.add(btnRegisterTouristicDeparture);
		
		JLabel lblNewLabel_4 = new JLabel("Departamentos:");
		lblNewLabel_4.setBounds(22, 268, 172, 15);
		contentPane.add(lblNewLabel_4);
		
		JButton btnRegisterDepartment = new JButton("Declarar independencia");
		btnRegisterDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterDepartment registerDepartmentGUI = new RegisterDepartment();
				registerDepartmentGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		
		btnRegisterDepartment.setBounds(22, 291, 200, 25);
		contentPane.add(btnRegisterDepartment);
		
		JButton btnCreateCategory = new JButton("Alta Categoria");
		btnCreateCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterCategory registerCategoriGUI = new RegisterCategory();
				registerCategoriGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		btnCreateCategory.setBounds(246, 143, 232, 25);
		contentPane.add(btnCreateCategory);
		
		JButton btnAcceptDeparture = new JButton("Aceptar/Rechazar Actividad");
		btnAcceptDeparture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcceptOrRejectActivities acceptOrRejectActivitiesGUI = new AcceptOrRejectActivities();
				acceptOrRejectActivitiesGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		btnAcceptDeparture.setBounds(246, 106, 232, 25);
		contentPane.add(btnAcceptDeparture);
		
		JButton btnMostViewed = new JButton("Actividad/Salida Visitada");
		btnMostViewed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MostVisitedActivitiesAndDepartures mostVisitedActivitiesAndDeparturesGUI = new MostVisitedActivitiesAndDepartures();
				mostVisitedActivitiesAndDeparturesGUI.setVisible(rootPaneCheckingEnabled);
			}
		});
		btnMostViewed.setBounds(246, 291, 232, 25);
		contentPane.add(btnMostViewed);
	}
}
