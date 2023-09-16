package turismouydesktop.gui.frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.panels.DepartmentDataManagment;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtDepartment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterDepartment extends JFrame {

	private JPanel contentPane;
	private DepartmentDataManagment departmentDataPanel;
	
	private List<DtDepartment> departments;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterDepartment frame = new RegisterDepartment();
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
	public RegisterDepartment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 311, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		departmentDataPanel = new DepartmentDataManagment();
		departmentDataPanel.setBounds(0, 0, 297, 168);
		contentPane.add(departmentDataPanel);
		
		IController controller = ControllerFactory.getIController();
		
		departments = controller.getListDepartment(false);
		
		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DtDepartment departmentData = departmentDataPanel.getData();
				
				try {
					checkForKey(departmentData);
					checkForNull(departmentData);
					
					controller.registerDepartment(departmentData);
					
				} catch (Exception exception) {
					PopUpWindow msgWindows = new PopUpWindow(
					"Error",
					exception.getMessage(),
					Color.RED);
			
					msgWindows.setVisible(true);
				}
			}
		});
		btnConfirm.setBounds(172, 180, 117, 25);
		contentPane.add(btnConfirm);
		
	}
	
	private void checkForNull(DtDepartment departmentData) {
		if(departmentData.getName().isBlank()) {
			throw new NullPointerException("El campo 'Nombre' no puede esta vacio");
		}
	}
	
	private void checkForKey(DtDepartment departmentData) {
		departments.forEach(departemnt -> {
			if(departemnt.getName().equalsIgnoreCase(departmentData.getName())) {
				throw new IllegalArgumentException("Ya existe un departamento con ese nombre");
			}
		});
	}

}
