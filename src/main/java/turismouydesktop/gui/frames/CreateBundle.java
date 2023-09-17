package turismouydesktop.gui.frames;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import turismouydesktop.gui.frames.PopUpWindow;
import turismouydesktop.gui.panels.InsertBundleData;
import uy.turismo.servidorcentral.logic.controller.ControllerFactory;
import uy.turismo.servidorcentral.logic.controller.IController;
import uy.turismo.servidorcentral.logic.datatypes.DtTouristicBundle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CreateBundle extends JFrame {

	private JPanel contentPane;
	private InsertBundleData insertBundleData;
	private PopUpWindow window;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateBundle frame = new CreateBundle();
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
	public CreateBundle() {
		
		IController controller = ControllerFactory.getIController();
		
		setTitle("Crear paquete turístico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		insertBundleData = new InsertBundleData();
		insertBundleData.setBounds(31, 12, 460, 367);
		contentPane.add(insertBundleData);

		
		JButton btnCreate = new JButton("Crear");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String name = insertBundleData.getName();
					String desc = insertBundleData.getDescription();
					Double disc = insertBundleData.getDiscount();
					Integer days = insertBundleData.getValidityBundle();
					LocalDate creationDate = insertBundleData.getCreationDate();
					
					DtTouristicBundle touristicbundle = new DtTouristicBundle(null, name, desc, days, disc , creationDate, null);	
					controller.registerTouristicBundle(touristicbundle);
					
				    window = new PopUpWindow("Éxito", "El Paquete fue dado de alta con éxito.", Color.GREEN);
					window.setVisible(true);
					
				}catch (Exception e1) {
					window = new PopUpWindow("Campo/s vacío/s", "Por favor rellene los campos vacíos", Color.RED);
					window.setVisible(true);
				}
					
			}
		});
		btnCreate.setBounds(292, 406, 117, 25);
		contentPane.add(btnCreate);
		
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(120, 406, 117, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnCancel);
	}
}
