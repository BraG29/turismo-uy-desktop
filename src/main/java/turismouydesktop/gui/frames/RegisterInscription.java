package turismouydesktop.gui.frames;

import java.awt.EventQueue;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.ListDepartment;
import turismouydesktop.gui.ListDepartmentListener;
import turismouydesktop.gui.panels.ListTourist;
import turismouydesktop.gui.panels.ListTouristListener;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicActivity;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;

public class RegisterInscription extends JFrame implements ListTouristListener, ListDepartmentListener {

	private JPanel contentPane;
	private ListTourist listTouristsPane;
	private ListDepartment listDepartmentPane;
	private JList listDepartures;
	
	JComboBox comboBoxActivities;
	JComboBox comboBoxDepartures;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterInscription frame = new RegisterInscription();
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
	public RegisterInscription() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		listDepartmentPane = new ListDepartment();
		listDepartmentPane.setBounds(0, 23, 208, 177);
		getContentPane().add(listDepartmentPane);
		
		comboBoxActivities = new JComboBox();
		comboBoxActivities.setModel(new DefaultComboBoxModel(new String[] {"-"}));
		comboBoxActivities.setEditable(false);
		comboBoxActivities.setBounds(220, 54, 172, 24);
		contentPane.add(comboBoxActivities);
		
		JLabel lblSelectDepartment = new JLabel("Seleccione un Departamento");
		lblSelectDepartment.setBounds(0, 0, 214, 15);
		contentPane.add(lblSelectDepartment);
		
		JLabel lblSelectActivity = new JLabel("Seleccione una Atividad");
		lblSelectActivity.setBounds(226, 23, 172, 15);
		contentPane.add(lblSelectActivity);
		
		comboBoxDepartures = new JComboBox();
		comboBoxDepartures.setModel(new DefaultComboBoxModel(new String[] {"-"}));
		comboBoxDepartures.setBounds(220, 141, 172, 24);
		contentPane.add(comboBoxDepartures);
		
		JLabel lblSeleccioneUnaSalida = new JLabel("Seleccione una Salida");
		lblSeleccioneUnaSalida.setBounds(226, 111, 166, 15);
		contentPane.add(lblSeleccioneUnaSalida);
		
		listTouristsPane = new ListTourist();
		
		
	}

	@Override
	public void onListDepartmentSelected(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onListDepartmentSelectedDt(DtDepartment department) {
		if(department.getActivities() != null) {
			String[] activitiesString = department
					.getActivities()
					.stream()
					.map(DtTouristicActivity::getName)
					.collect(Collectors.toList())
					.toArray(new String[0]);
			
			comboBoxActivities.setModel(new DefaultComboBoxModel(activitiesString));
			comboBoxActivities.setEnabled(true);
		}
		
		
	}

	@Override
	public void onListTouristSelected(Long id) {
		// TODO Auto-generated method stub
		
	}
}
